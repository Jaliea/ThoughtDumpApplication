package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a folder with a title and full of notes
public class Folder implements Writable {

    private String title;
    private ArrayList<Note> notes;

    // EFFECTS : creates a new selected empty folder with the given name
    public Folder(String title) {
        this.title = title;
        notes = new ArrayList<>();
    }

    // GETTERS
    public String getFolderTitle() {
        return title;
    }

    // MODIFIES : this
    // EFFECTS : renames the folder
    public void rename(String newName) {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("folder", title);
        json.put("notes", notesToJson());
        return json;
    }

    private JSONArray notesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Note note : notes) {
            jsonArray.put(note.toJson());
        }
        return jsonArray;
    }
}

