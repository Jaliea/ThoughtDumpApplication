package ui;

import model.EventLog;
import model.Folder;
import model.Note;
import model.User;
import model.Event;
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


    // MODIFIES: this
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

    // MODIFIES: this
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
            folders = (ArrayList<Folder>) user.getFolders();
            EventLog.getInstance().logEvent(new Event((user.getName() + " : loaded from " + JSON_USER1_STORAGE)));
        } catch (IOException e) {
            System.out.println("unable to read from file " + JSON_USER1_STORAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads welcome window
    public void loadWelcomeWindow() {
        currentWindow = new WelcomeWindow(this);
        this.add(currentWindow);
    }

    // MODIFIES: this
    // EFFECTS: loads main menu window
    public void loadMenuWindow() {
        if (note != null && note.isSelected()) {
            note.unselect();
        }
        removeCurrentWindow();
        currentWindow = new MenuWindow(this);
        placeNewWindow();
    }

    // MODIFIES: this
    // EFFECTS: loads window to create a new note
    public void loadCreateNoteWindow() {
        removeCurrentWindow();
        note = new Note();
        note.select();
        currentWindow = new CreateNewNoteWindow(this, folders);
        placeNewWindow();
    }

    // MODIFIES: this
    // EFFECTS: loads window to create a new note from an empty folder
    public void loadCreateNoteWindow(Folder folder) {
        removeCurrentWindow();
        note = new Note();
        note.select();
        currentWindow = new CreateNewNoteWindow(this, folders, folder);
        placeNewWindow();
    }

    // MODIFIES: Note note
    // EFFECTS: saves user's input into a note
    public void saveNote(String userTitle, String userMessage) {
        note.renameTitle(userTitle);
        note.write(userMessage);
        EventLog.getInstance().logEvent(new Event("created new note: " + note.getNoteTitle()));
    }

    // MODIFIES: this
    // EFFECTS: loads window to view all folders
    public void loadViewFoldersWindow() {
        removeCurrentWindow();
        currentWindow = new ViewFoldersWindow(this, folders, note);
        placeNewWindow();
    }

    // MODIFIES: this
    // EFFECTS: loads window to create a new folder
    public void loadCreateNewFolderWindow() {
        removeCurrentWindow();
        currentWindow = new CreateNewFolderWindow(this, note);
        placeNewWindow();
    }

    // MODIFIES: user
    // EFFECTS: adds folder to user's list of folders
    public void createFolder(Folder folder) {
        folders.add(folder);
    }

    // MODIFIES: this
    // EFFECTS: loads window to select a folder to view or save a note in
    public void loadSelectedFolderWindow(Folder selectedFolder) {
        removeCurrentWindow();
        currentWindow = new ViewSelectedFolderWindow(this, selectedFolder);
        placeNewWindow();
    }

    // MODIFIES: this
    // EFFECTS: loads window to view the selected note
    public void loadSelectedNoteWindow(Note selectedNote) {
        removeCurrentWindow();
        currentWindow = new SelectedNoteWindow(this, selectedNote);
        placeNewWindow();
    }

    // MODIFIES: Folder selectedFolder
    // EFFECTS: saves the current note to the selected folder
    public void saveNoteToFolder(Folder selectedFolder) {
        int folderIndex = folders.indexOf(selectedFolder);
        Folder folder = folders.get(folderIndex);
        folder.addNote(note);
        EventLog.getInstance().logEvent(new Event("added "
                + note.getNoteTitle() + " note to " + selectedFolder.getFolderTitle() + " folder"));
    }

    // MODIFIES: this
    // EFFECTS: loads modified main menu window
    public void loadSavedMenuWindow() {
        if (note != null && note.isSelected()) {
            assert note != null;
            note.unselect();
        }
        removeCurrentWindow();
        currentWindow = new SavedMenuWindow(this);
        placeNewWindow();
    }

    // MODIFIES: this
    // EFFECTS: loads quit window asking user if they would like to save
    public void loadQuitWindow() {
        removeCurrentWindow();
        currentWindow = new QuitWindow(this);
        placeNewWindow();
    }

    // MODIFIES: user and json
    // EFFECTS: saves all recent data to the user1
    public void saveDumps() {
        try {
            user.addFolders(folders);
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            EventLog.getInstance().logEvent(new Event("saved recent changed to " + JSON_USER1_STORAGE));
        } catch (FileNotFoundException e) {
            System.out.println("unable to save to file " + JSON_USER1_STORAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: quits application
    public void quitApp() {

        EventPrinter printer = new EventPrinter();
        printer.printLog(EventLog.getInstance());
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    // MODIFIES: this
    // EFFECTS: refreshes current window to display a new window
    private void placeNewWindow() {
        this.add(currentWindow);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: removes the JPanel currently displayed
    private void removeCurrentWindow() {
        if (currentWindow != null) {
            remove(currentWindow);
        }
    }
}
