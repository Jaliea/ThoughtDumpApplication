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
    }

    // EFFECTS: adds a folder to list of folders for account
    private void addFolder() {
        folders.add(new Folder());
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nhihi :]");
        System.out.println("welcome to ThoughtDump !");
        System.out.println("a cozy and safe space where you can dump all your thoughts");
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
        } else {
            System.out.println("selection not valid... try again");
            selectNoteMenu();
        }
    }

    // EFFECTS : displays menu for user to save thought
    private void saveNoteMenu() {
        viewFolders();
        System.out.println("note saved !");
    }

    // EFFECTS : view the user's folders
    private void viewFolders() {
        System.out.println("\nselect the folder you want to open:");
        int number = 1;
        if (folders.isEmpty()) {
            System.out.println("you have no folders yet !");
            createFolder();
        }
        for (Folder folders: folders) {
            System.out.println("\t" + number + "-> " + folders.getFolderTitle());
            number++;
        }
        System.out.println("\t" + number + " -> create a new folder");
        selectFolderMenu(number);
    }

    private void selectFolderMenu(int number) {
        String command = input.next();
        int numberCommand = Integer.parseInt(command) - 1;
        if (numberCommand + 1 == number) {
            createFolder();
        } else {
            Folder selectedFolder = folders.get(numberCommand);
            openFolder(selectedFolder);
        }
    }

    // EFFECTS: create a new folder based on user input
    private void createFolder() {
        System.out.println("create a new folder !");
        Folder folder = new Folder();
        System.out.println("\n give your new folder a name");
        folder.rename(input.next());
        folders.add(folder);
        viewFolders();
    }

    private void openFolder(Folder folder) {
        System.out.println("\n" + folder.getFolderTitle());
        System.out.println(folder.viewNotes());
    }
}

