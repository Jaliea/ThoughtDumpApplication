package ui;

import model.Folder;
import model.Note;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.windows.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ThoughtDumpGUI extends JFrame {
    private static final String JSON_USER1_STORAGE = "./data/user1.json";
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    private JPanel currentWindow;
    private Color background = new Color(195, 166, 222);

    private Note note;
    private ArrayList<Folder> folders;
    private User user;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private WelcomeWindow welcome;
    private MenuWindow menu;
    private CreateNewNoteWindow newNote;
    private ViewFoldersWindow viewFolders;

    // EFFECTS: sets up window that ThoughtDump will be displayed
    public ThoughtDumpGUI() {
        super("ThoughtDump");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        user = new User("current user");
        init();

        loadWelcomeWindow();

        setVisible(true);
    }

    // EFFECTS: initializes user
    private void init() {
        jsonWriter = new JsonWriter(JSON_USER1_STORAGE);
        jsonReader = new JsonReader(JSON_USER1_STORAGE);
        folders = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: loads notes and folders from file
    // the following code is taken and modified from the WorkRoomApp class in the JsonSerializationDemo project:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/ui/WorkRoomApp.java
    public void loadSavedDumps() {
        try {
            user = jsonReader.read();
            System.out.println(user.getName() + " : loaded from " + JSON_USER1_STORAGE);
            folders = (ArrayList<Folder>) user.getFolders();
        } catch (IOException e) {
            System.out.println("unable to read from file " + JSON_USER1_STORAGE);
        }
    }


    // EFFECTS: loads main menu
    public void loadWelcomeWindow() {
        currentWindow = new WelcomeWindow(this);
        this.add(currentWindow);
    }

    // EFFECTS: loads main menu
    public void loadMenuWindow() {
        if (note != null && note.isSelected()) {
            note.unselect();
        }
        removeCurrentWindow();
        currentWindow = new MenuWindow(this);
        placeNewWindow();
    }

    // EFFECTS: loads create new note window
    public void loadCreateNoteWindow() {
        removeCurrentWindow();
        note = new Note();
        note.select();
        currentWindow = new CreateNewNoteWindow(this, folders);
        placeNewWindow();
    }

    public void loadCreateNoteWindow(Folder folder) {
        removeCurrentWindow();
        note = new Note();
        note.select();
        currentWindow = new CreateNewNoteWindow(this, folders, folder);
        placeNewWindow();
    }

    // EFFECTS: saves user's input into a note
    public void saveNote(String userTitle, String userMessage) {
        note.renameTitle(userTitle);
        note.write(userMessage);
    }

    // EFFECTS: loads view folders window
    public void loadViewFoldersWindow() {
        removeCurrentWindow();
        currentWindow = new ViewFoldersWindow(this, folders, note);
        placeNewWindow();
    }

    public void loadCreateNewFolderWindow() {
        removeCurrentWindow();
        currentWindow = new CreateNewFolderWindow(this, note);
        placeNewWindow();
    }

    public void createFolder(Folder folder) {
        folders.add(folder);
    }

    public void loadSelectedFolderWindow(Folder selectedFolder) {
        removeCurrentWindow();
        currentWindow = new ViewSelectedFolderWindow(this, selectedFolder);
        placeNewWindow();
    }

    public void loadSelectedNoteWindow(Note selectedNote) {
        removeCurrentWindow();
        currentWindow = new SelectedNoteWindow(this, selectedNote);
        placeNewWindow();
    }

    public void saveNoteToFolder(Folder selectedFolder) {
        int folderIndex = folders.indexOf(selectedFolder);
        Folder folder = folders.get(folderIndex);
        folder.addNote(note);
    }

    public void loadSavedMenuWindow() {
        if (note != null && note.isSelected()) {
            assert note != null;
            note.unselect();
        }
        removeCurrentWindow();
        currentWindow = new SavedMenuWindow(this);
        placeNewWindow();
    }

    // EFFECTS: loads quit window asking user if they would like to save
    public void loadQuitWindow() {
        removeCurrentWindow();
        currentWindow = new QuitWindow(this);
        placeNewWindow();
    }

    public void saveDumps() {
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

    public void quitApp() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    private void placeNewWindow() {
        this.add(currentWindow);
        revalidate();
        repaint();
    }

    private void removeCurrentWindow() {
        if (currentWindow != null) {
            remove(currentWindow);
        }
    }
}
