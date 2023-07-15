package model;

public class Note {

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

    // EFFECTS : returns true if the note is selected
    public boolean isSelected() {
        return selected;
    }

    // EFFECTS : returns the selected note message
    public String getMessage() {
        return message;
    }

    public String getNoteTitle() {
        return title;
    }

    // EFFECTS : returns the folder that the selected note is assigned to
    public Folder getFolder() {
        return folder;
    }


    // MODIFIES : this
    // EFFECTS : writes a message on note
    public void write(String message) {
        this.message = message;
    }

    // MODIFIES : this
    // EFFECTS : adds the note to the selected folder
    public void addToFolder(Folder folder) {
        this.folder = folder;
        folder.addNote(this);
    }

    // MODIFIES : this
    // EFFECTS : puts the selected note into the trash
    public Object trash() {
        return null;
    }

    // EFFECTS : returns the message of the selected note
    public String view() {
        return message;
    }

    // MODIFIES : this
    // EFFECTS : edits the message of the note to the new message
    public void edit(String newMessage) {
        this.message = newMessage;
    }

    // MODIFIES : this
    // EFFECTS : renames current title to new title
    public void renameTitle(String newTitle) {
        this.title = newTitle;
    }
}
