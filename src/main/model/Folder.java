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

    // GETTERS
    public String getFolderTitle() {
        return title;
    }

    // MODIFIES : this
    // EFFECTS : renames the folder
    public void name(String newName) {
        this.title = newName;
    }

    // EFFECTS : lists all notes in the folder
    public ArrayList<Note> viewNotes() {
        return notes;
    }

    // REQUIRES : note is selected
    // MODIFIES : this
    // EFFECTS : adds the given note to the list of notes in folder
    public void addNote(Note note) {
        if (note.isSelected()) {
            notes.add(note);
            note.unselect();
        }
    }


}
