package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

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

    public int getNumNotes() {
        return notes.size();
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

    // EFFECTS : returns a list of the user's note titles
    public List<String> noteNames() {
        List<String> nameList = new ArrayList<>();
        for (Note singleNote : notes) {
            nameList.add(singleNote.getNoteTitle());
        }
        return nameList;
    }

    // EFFECTS : finds the note with the given title
    public Note findNote(String noteTitle) {
        List<String> nameList = noteNames();
        int index = nameList.indexOf(noteTitle);
        return notes.get(index);
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

    // MODIFIES: json file
    // EFFECTS: returns user's notes in folder as a JSON array
    private JSONArray notesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Note note : notes) {
            jsonArray.put(note.toJson());
        }
        return jsonArray;
    }
}

