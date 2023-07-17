package model;

import java.util.ArrayList;

public class Folder {

    private boolean selected;
    private String title;
    private ArrayList<Note> notes;

    // EFFECTS : creates a new selected empty folder with the given name
    public Folder() {
        selected = true;
        title = "";
        notes = new ArrayList<>();
    }

    // EFFECTS : returns true if the folder is selected
    public boolean isSelected() {
        return selected;
    }

    public void select() {
        selected = true;
    }

    public void unselect() {
        selected = false;
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
    public ArrayList<String> viewNotes() {
        ArrayList<String> list = new ArrayList<>();
        for (Note note: notes) {
            list.add(note.getNoteTitle());
        }
        return list;
    }

    // MODIFIES : this
    // EFFECTS : adds the given note to the list of notes in folder
    public void addNote(Note note) {
        notes.add(note);
    }


}
