package ui.windows;

import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;

public class SavedMenuWindow extends MenuWindow {
    private JLabel savedMsg;

    public SavedMenuWindow(ThoughtDumpGUI gui) {
        super(gui);
        placeSavedMessage();
    }

    private void placeSavedMessage() {
        super.placeItalics(savedMsg, "saved :]", 10);
    }
}
