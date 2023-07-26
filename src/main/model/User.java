package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class User implements Writable {
    private String name;
    private List<Folder> myFolders;

    public User(String name) {
        this.name = name;
        myFolders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getNumFolders() {
        return myFolders.size();
    }

    public List<Folder> getFolders() {
        return myFolders;
    }

    // REQUIRES: non-empty folderList
    // MODIFIES: this
    // EFFECTS: adds given list of folders to user folders
    //          if folder is already in list, then the folder is updates
    //          (folder is updated and put back in the same index)
    //          else, the folder is added to the end
    public void addFolders(List<Folder> folderList) {
        for (Folder singleFolder : folderList) {
            if (myFolders.contains(singleFolder)) {
                int index = myFolders.indexOf(singleFolder);
                myFolders.set(index, singleFolder);
            } else {
                addFolder(singleFolder);
            }
        }
    }

    // MODIFIES : this
    // EFFECTS : adds a single folder to the user's list of folders
    public void addFolder(Folder folder) {
        myFolders.add(folder);
    }

    // EFFECTS : returns a list of the user's folder names
    public List<String> folderNames() {
        List<String> nameList = new ArrayList<>();
        for (Folder singleFolder : myFolders) {
            nameList.add(singleFolder.getFolderTitle());
        }
        return nameList;
    }

    // EFFECTS : finds the folder with the given name
    public Folder findFolder(String folderName) {
        List<String> nameList = folderNames();
        int index = nameList.indexOf(folderName);
        return myFolders.get(index);
        // TODO : EXCEPTION WHERE THE FOLDER NAME ISNT FOUND
    }

    // MODIFIES : this
    // EFFECTS : adds a note to the given folder of the user
    public void addNote(Note note, String folderName) {
        Folder folder = findFolder(folderName);
        folder.addNote(note);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("folders", foldersToJson());
        return json;
    }

    private JSONArray foldersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Folder folder : myFolders) {
            jsonArray.put(folder.toJson());
        }
        return jsonArray;
    }
}
