package ui;

import model.Folder;
import model.Note;
import model.User;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.windows.MenuWindow;
import ui.windows.WelcomeWindow;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ThoughtDumpGUI extends JFrame {
    private static final String JSON_USER1_STORAGE = "./data/user1.json";
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    private JPanel window;

    private Note note;
    private ArrayList<Folder> folders;
//    private Scanner input;
//    boolean keepGoing;
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

    // EFFECTS: loads welcome window
    public void loadWelcomeWindow() {
        window = new WelcomeWindow(this);
        add(window);
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
    public void loadMenuWindow() {
        remove(window);
        window = new MenuWindow(this);
        add(window);
    }

}
