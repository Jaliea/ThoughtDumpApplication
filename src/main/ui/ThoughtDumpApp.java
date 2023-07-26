package ui;

import model.Folder;
import model.Note;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// creates a new Thought Dump application window
public class ThoughtDumpApp {
    private static final String JSON_USER1_STORAGE = "./data/user1.json";

    private Note note;
    private ArrayList<Folder> folders;
    private Scanner input;
    boolean keepGoing;
    private User user;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS : runs the ThoughtDump application
    public ThoughtDumpApp() {
        user = new User("current user");
        jsonWriter = new JsonWriter(JSON_USER1_STORAGE);
        jsonReader = new JsonReader(JSON_USER1_STORAGE);
        runThoughtDump();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // the following code is taken from the TellerApp class in the TellerApp project:
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/main/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java
    private void runThoughtDump() {
        keepGoing = true;
        String command;

        init();
        welcome();
        processLoadCommand(input.next());

        while (keepGoing) {
            mainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("4")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nbye bye :<");
        System.out.println("see u next time");
    }

    // MODIFIES: this
    // EFFECTS: initializes user
    // the following code is taken from the TellerApp class in the TellerApp project:
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/main/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java
    private void init() {
        folders = new ArrayList<>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        note = new Note();
        note.unselect();
    }

    // EFFECTS: displays welcome message when application is first launched
    private void welcome() {
        System.out.println("\nhihi :]");
        System.out.println("welcome to ThoughtDump !");
        System.out.println("a cozy and safe space where you can dump all your thoughts");
        System.out.println("\ndo you want to load a previous save file?");
        System.out.println("\t1 -> yes");
        System.out.println("\t2 -> no");
    }

    // MODIFIES: this
    // EFFECTS: if user input is 1, then load a previous save file
    //          else nothing, skip entire process and continue with application
    private void processLoadCommand(String command) {
        if (command.equals("1")) {
            loadSavedDumps();
        } else if (!command.equals("2")) {
            System.out.println("selection not valid... try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads notes and folders from file
    // the following code is taken and modified from the WorkRoomApp class in the JsonSerializationDemo project:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java
    private void loadSavedDumps() {
        try {
            user = jsonReader.read();
            System.out.println(user.getName() + " : loaded from " + JSON_USER1_STORAGE);
            folders = (ArrayList<Folder>) user.getFolders();
        } catch (IOException e) {
            System.out.println("unable to read from file " + JSON_USER1_STORAGE);
        }
    }

    // EFFECTS: displays menu of options to user
    // the following code is taken from the TellerApp class in the TellerApp project:
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/main/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java
    private void mainMenu() {
        System.out.println("\nmenu");
        System.out.println("\nselect from:");
        System.out.println("\t1 -> create new note");
        System.out.println("\t2 -> view folders");
        System.out.println("\t3 -> save");
        System.out.println("\t4 -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    // the following code is taken and modified from the TellerApp class in the TellerApp project:
    // https://github.students.cs.ubc.ca/CPSC210/TellerApp/blob/main/src/main/ca/ubc/cpsc210/bank/ui/TellerApp.java
    private void processCommand(String command) {
        switch (command) {
            case "1":
                createNote();
                break;
            case "2":
                viewFolders();
                break;
            case "3":
                saveSavedDumps();
                break;
            default:
                System.out.println("selection not valid... try again");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new note with user's thoughts and given title
    private void createNote() {
        note = new Note();
        note.select();
        System.out.println("dump your thoughts");
        note.write(input.next());
        System.out.println("name your thoughts");
        note.renameTitle(input.next());
        displayNoteMenu();
        selectNoteMenu();
    }

    // EFFECTS : displays menu for user to either save or dump thought
    private void displayNoteMenu() {
        System.out.println("\nselect from:");
        System.out.println("\t1 -> save your thoughts");
        System.out.println("\t2 -> dump your thoughts");
    }

    // MODIFIES: this
    // EFFECTS: goes to the correct processes to either save or dump the thought
    //         based on the user input, if the user input is invalid, it loops
    private void selectNoteMenu() {
        String command = input.next();
        if (command.equals("1")) {
            saveNoteMenu();
        } else if (command.equals("2")) {
            note.trash();
            note.unselect();
        } else {
            System.out.println("selection not valid... try again");
            selectNoteMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS : displays menu for user to save thought
    private void saveNoteMenu() {
        viewFolders();
        note.unselect();
        System.out.println("note saved !");
    }

    // MODIFIES: this
    // EFFECTS : view the user's folders
    private void viewFolders() {
        System.out.println("\nselect the folder you want to open:");
        int number = 1;
        if (folders.isEmpty()) {
            System.out.println("you have no folders yet !");
            createFolder();
        } else {
            for (Folder folders : folders) {
                System.out.println("\t" + number + "-> " + folders.getFolderTitle());
                number++;
            }
            System.out.println("\t" + number + " -> create a new folder");
            selectFolderMenu(number);
        }
    }

    // MODIFIES: this
    // EFFECTS: if the user doesn't have a folder, it goes to the create a folder menu
    //          else, if there is a note selected, it is saved to the folder that the user chooses
    //          else, it opens the chosen folder
    private void selectFolderMenu(int number) {
        String command = input.next();
        int numberCommand = Integer.parseInt(command) - 1;
        if (numberCommand + 1 == number) {
            createFolder();
        } else {
            Folder selectedFolder = folders.get(numberCommand);
            if (note.isSelected()) {
                selectedFolder.addNote(note);
            } else {
                openFolder(selectedFolder);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: create a new folder based on user input
    private void createFolder() {
        System.out.println("create a new folder !");
        System.out.println("\n give your new folder a name");
        Folder folder = new Folder(input.next());
        folders.add(folder);
        folder.addNote(note);
    }

    // MODIFIES: this
    // EFFECTS: prints a list of notes in the given folder, giving the user the option to
    //          open any of them
    private void openFolder(Folder folder) {
        System.out.println("\nfolder opened : " + folder.getFolderTitle());
        ArrayList<Note> listOfNotes = folder.viewNotes();
        int number = 1;
        if (listOfNotes.isEmpty()) {
            System.out.println("you have no notes yet !");
            createNote();
        } else if (!note.isSelected()) {
            System.out.println("\nselect the note you want to open:");
            for (Note note : listOfNotes) {
                System.out.println("\t" + number + " -> " + note.getNoteTitle());
                number++;
            }
            openNote(folder);
        }
    }

    // MODIFIES: this
    // EFFECTS : prints the note title and message
    private void openNote(Folder folder) {
        String command = input.next();
        int numberCommand = Integer.parseInt(command) - 1;
        ArrayList<Note> listOfNotes = folder.viewNotes();
        note = listOfNotes.get(numberCommand);
        System.out.println("\nnote title : " + note.getNoteTitle());
        System.out.println("note message : " + note.getMessage());
    }

    // MODIFIES: this + json
    // EFFECTS : saves the recent notes and folders to file
    // the following code is taken and modified from the WorkRoomApp class in the JsonSerializationDemo project:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java
    private void saveSavedDumps() {
        try {
            user.addFolders(folders);
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            System.out.println("\nrecent changes saved to " + JSON_USER1_STORAGE);
        } catch (FileNotFoundException e) {
            System.out.println("unable to save to file " + JSON_USER1_STORAGE);
        }

    }
}

