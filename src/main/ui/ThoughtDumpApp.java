package ui;

import model.Folder;
import model.Note;

import java.util.ArrayList;
import java.util.Scanner;

public class ThoughtDumpApp {
    private Note note;
    private ArrayList<Folder> folders;
    private Scanner input;
    boolean keepGoing;

    // EFFECTS : runs the ThoughtDump application
    public ThoughtDumpApp() {
        runThoughtDump();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runThoughtDump() {
        keepGoing = true;
        String command;

        init();
        welcome();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("3")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nbye bye :<");
        System.out.println("see u next time");
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        folders = new ArrayList<>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        note = new Note();
        note.unselect();
    }

    private void welcome() {
        System.out.println("\nhihi :]");
        System.out.println("welcome to ThoughtDump !");
        System.out.println("a cozy and safe space where you can dump all your thoughts");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nmenu");
        System.out.println("\nselect from:");
        System.out.println("\t1 -> create new note");
        System.out.println("\t2 -> view folders");
        System.out.println("\t3 -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            createNote();
        } else if (command.equals("2")) {
            viewFolders();
        } else {
            System.out.println("selection not valid... try again");
        }
    }

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

    // EFFECTS : displays menu for user to save thought
    private void saveNoteMenu() {
        viewFolders();
        note.unselect();
        System.out.println("note saved !");
    }

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

    // EFFECTS: create a new folder based on user input
    private void createFolder() {
        System.out.println("create a new folder !");
        Folder folder = new Folder();
        System.out.println("\n give your new folder a name");
        folder.name(input.next());
        folders.add(folder);
        if (note.isSelected()) {
            folder.addNote(note);
            note.unselect();
        }
    }

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



    private void openNote(Folder folder) {
        String command = input.next();
        int numberCommand = Integer.parseInt(command) - 1;
        ArrayList<Note> listOfNotes = folder.viewNotes();
        note = listOfNotes.get(numberCommand);
        System.out.println("\nnote title : " + note.getNoteTitle());
        System.out.println("note message : " + note.getMessage());
    }
}

