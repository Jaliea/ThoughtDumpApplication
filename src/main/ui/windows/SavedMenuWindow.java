package ui.windows;

import ui.ThoughtDumpGUI;

import javax.swing.*;

// represents a modified main menu window that is navigated to after the user saves something
public class SavedMenuWindow extends MenuWindow {
    private JLabel savedMsg;

    // EFFECTS: creates a modified main menu window with an additional message at the bottom
    public SavedMenuWindow(ThoughtDumpGUI gui) {
        super(gui);
        placeSavedMessage();
    }

    // MODIFIES: this
    // EFFECTS: adds a small message in italics
    private void placeSavedMessage() {
        super.placeItalics(savedMsg, "saved :]", 10);
    }
}
