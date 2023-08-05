package ui;

import model.Folder;
import model.Note;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.windows.CreateNewNoteWindow;
import ui.windows.MenuWindow;
import ui.windows.ViewFoldersWindow;
import ui.windows.WelcomeWindow;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ThoughtDumpGUI extends JFrame {
    private static final String JSON_USER1_STORAGE = "./data/user1.json";
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    private CardLayout windows = new CardLayout();
    private JPanel currentWindow = new JPanel(windows);
    private Color background = new Color(195, 166, 222);

    private Note note;
    private ArrayList<Folder> folders;
    private User user;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: sets up window that ThoughtDump will be displayed
    public ThoughtDumpGUI() {
        super("ThoughtDump");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        user = new User("current user");
        init();

        loadWindows();
        loadWelcomeWindow();

        setVisible(true);
    }

    // EFFECTS: initializes user
    private void init() {
        jsonWriter = new JsonWriter(JSON_USER1_STORAGE);
        jsonReader = new JsonReader(JSON_USER1_STORAGE);
        folders = new ArrayList<>();
        note = new Note();
        note.unselect();
    }

    public void loadWindows() {
        WelcomeWindow welcome = new WelcomeWindow(this);
        MenuWindow menu = new MenuWindow(this);
        CreateNewNoteWindow newNote = new CreateNewNoteWindow(this);
        ViewFoldersWindow viewFolders = new ViewFoldersWindow(this);

        currentWindow.add(welcome, "welcome");
        currentWindow.add(menu, "menu");
        currentWindow.add(newNote, "new note");
        currentWindow.add(viewFolders, "view folders");
        this.add(currentWindow);
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
        windows.show(currentWindow, "welcome");
    }

    // EFFECTS: loads main menu
    public void loadMenuWindow() {
        windows.show(currentWindow, "menu");
    }

    // EFFECTS: loads create new note window
    public void loadCreateNoteWindow() {
        windows.show(currentWindow, "new note");
    }

    // EFFECTS: saves user's input into a note
    public void saveNote(String userTitle, String userMessage) {
        note.renameTitle(userTitle);
        note.write(userMessage);
    }

    // EFFECTS: loads view folders window
    public void loadViewFoldersWindow() {
        windows.show(currentWindow, "view folders");
    }

    // EFFECTS: loads quit window asking user if they would like to save
    public void loadQuitWindow() {

    }

}
