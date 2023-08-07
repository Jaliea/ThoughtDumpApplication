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
        savedMsg = new JLabel("<html><div style='text-align: center;'>" + "saved :]" + "</div></html>",
                JLabel.CENTER);
        savedMsg.setFont(new Font("Dialog", Font.ITALIC, 10));
        this.add(savedMsg);
    }
}
