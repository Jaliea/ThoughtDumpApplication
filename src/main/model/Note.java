package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a note with a title and message, and can be either selected or unselected to move into a folder
public class Note implements Writable {

    private boolean selected;
    private String title;
    private String message;
    private Folder folder;

    // EFFECTS : constructs a selected empty note not assigned to a folder
    public Note() {
        selected = true;
        title = "";
        message = "";
        folder = null;
    }

    // SETTERS
    public void select() {
        selected = true;
    }

    public void unselect() {
        selected = false;
    }

    // GETTERS
    public boolean isSelected() {
        return selected;
    }

    public String getMessage() {
        return message;
    }

    public String getNoteTitle() {
        return title;
    }

    public Folder getFolder() {
        return folder;
    }

    // REQUIRES : selected note
    // MODIFIES : this
    // EFFECTS : writes a message on note
    public void write(String message) {
        this.message = message;
    }

    // REQUIRES : selected note
    // MODIFIES : this
    // EFFECTS : adds the note to the selected folder
    public void addToFolder(Folder folder) {
        this.folder = folder;
        folder.addNote(this);
    }

    // REQUIRES : selected note
    // MODIFIES : this
    // EFFECTS : puts the selected note into the trash
    public Object trash() {
        return null;
    }

    // REQUIRES : selected note
    // EFFECTS : returns the message of the selected note
    public String view() {
        return message;
    }

    // REQUIRES : selected note
    // MODIFIES : this
    // EFFECTS : edits the message of the note to the new message
    public void edit(String newMessage) {
        this.message = newMessage;
    }

    // REQUIRES : selected note
    // MODIFIES : this
    // EFFECTS : renames current title to new title
    public void renameTitle(String newTitle) {
        this.title = newTitle;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("message", message);
        return json;
    }
}
