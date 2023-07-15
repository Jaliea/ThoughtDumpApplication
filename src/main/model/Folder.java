package model;

import java.util.HashSet;

public class Folder {

    private boolean selected;
    private String title;
    private HashSet<Note> notes;

    // EFFECTS : creates a new selected empty folder with the given name
    public Folder(String name) {
        selected = true;
        this.title = name;
        notes = new HashSet<>();
    }

    // EFFECTS : returns true if the folder is selected
    public boolean isSelected() {
        return selected;
    }

    // EFFECTS : returns the title of the folder
    public String getFolderTitle() {
        return title;
    }

    // MODIFIES : this
    // EFFECTS : if selected, renames the folder
    public void rename(String newName) {
        this.title = newName;
    }

    // EFFECTS : if selected, lists all notes in the folder
    public HashSet<String> viewNotes() {
        HashSet<String> list = new HashSet<>();
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
