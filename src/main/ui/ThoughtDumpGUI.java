package ui;

import model.Folder;
import model.Note;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.windows.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ThoughtDumpGUI extends JFrame {
    private static final String JSON_USER1_STORAGE = "./data/user1.json";
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

//    private CardLayout windows = new CardLayout();
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
        removeCurrentWindow();
        currentWindow = new MenuWindow(this);
        placeNewWindow();
    }

    // EFFECTS: loads create new note window
    public void loadCreateNoteWindow() {
        removeCurrentWindow();
        note = new Note();
        note.select();
        currentWindow = new CreateNewNoteWindow(this);
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
    }

    public void loadSelectedFolderWindow(Folder folder) {
        removeCurrentWindow();
        currentWindow = new ViewSelectedFolderWindow(this, folder);
        placeNewWindow();
    }

    public void loadSavedMenuWindow() {
        note.unselect();
        removeCurrentWindow();
        currentWindow = new SavedMenuWindow(this);
        placeNewWindow();
    }

    // EFFECTS: loads quit window asking user if they would like to save
    public void loadQuitWindow() {

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
