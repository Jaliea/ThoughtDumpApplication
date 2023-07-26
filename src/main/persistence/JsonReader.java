package persistence;

import model.User;
import model.Folder;
import model.Note;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// reads user notes and folders from JSON stored data in file
// the following code is taken and modified from the JsonReader class in the JsonSerializationDemo project:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/JsonReader.java
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads user info from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseUser(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses user from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        User user = new User(name);
        parseFolders(user, jsonObject);
        return user;
    }

    // MODIFIES: user
    // EFFECTS: parses folders from JSON object and adds them to the user
    private void parseFolders(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("folders");
        for (Object json : jsonArray) {
            JSONObject nextFolder = (JSONObject) json;
            addFolder(user, nextFolder);
        }
    }

    // MODIFIES: user
    // EFFECTS: parses a single folder from JSON object and adds to user
    private void addFolder(User user, JSONObject jsonObject) {
        String name = jsonObject.getString("folder");
        Folder folder = new Folder(name);
        user.addFolder(folder);
        addNotes(user, jsonObject, name);
    }

    // MODIFIES: user
    // EFFECTS: parses all notes from a folder from JSON object and adds to user
    private void addNotes(User user, JSONObject jsonObject, String folderName) {
        JSONArray jsonArray = jsonObject.getJSONArray("notes");
        for (Object json : jsonArray) {
            JSONObject nextNote = (JSONObject) json;
            addNote(user, nextNote, folderName);
        }
    }

    // MODIFIES: user
    // EFFECTS: parses a single note from JSON object and adds to user in the correct folder
    private void addNote(User user, JSONObject jsonObject, String folderName) {
        String title = jsonObject.getString("title");
        String message = jsonObject.getString("message");
        Note note = new Note();
        note.write(message);
        note.renameTitle(title);
        user.addNote(note, folderName);
    }
}
