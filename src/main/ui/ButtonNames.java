package ui;

public enum ButtonNames {
    YES("yes"),
    NO("no"),
    CREATE_NOTE("create a new note"),
    VIEW_FOLDERS("view your folders"),
    SAVE("save"),
    QUIT("quit");

    private final String name;

    ButtonNames(String name) {
        this.name = name;
    }

    //EFFECTS: returns name value of this button
    public String getValue() {
        return name;
    }
}
