package model;

import java.util.ArrayList;

// Represents a folder with a title and full of notes
public class Folder {

    private String title;
    private ArrayList<Note> notes;

    // EFFECTS : creates a new selected empty folder with the given name
    public Folder() {
        title = "";
        notes = new ArrayList<>();
    }

    // EFFECTS : returns the title of the folder
    public String getFolderTitle() {
        return title;
    }

    // MODIFIES : this
    // EFFECTS : if selected, renames the folder
    public void name(String newName) {
        this.title = newName;
    }

    // EFFECTS : if selected, lists all notes in the folder
    public ArrayList<Note> viewNotes() {
        return notes;
    }

    // MODIFIES : this
    // EFFECTS : adds the given note to the list of notes in folder
    public void addNote(Note note) {
        if (note.isSelected()) {
            notes.add(note);
            note.unselect();
        }
    }


}
