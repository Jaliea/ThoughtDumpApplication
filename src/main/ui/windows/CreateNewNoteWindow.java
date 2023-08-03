package ui.windows;

import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;

public class CreateNewNoteWindow extends Window {

    private JLabel title;
    private JLabel message;
    private JTextField userTitle;
    private JTextArea userDump;

    public CreateNewNoteWindow(ThoughtDumpGUI gui) {
        super(gui);


        title = new JLabel("name your dump       ");
        message = new JLabel("dump your thoughts");


        userTitle = new JTextField(50);
        userDump = new JTextArea(20, 50);

        title.setLabelFor(userTitle);

        add(title, BorderLayout.NORTH);
        add(userTitle, RIGHT_ALIGNMENT);
        add(message,LEFT_ALIGNMENT);
        add(userDump, BorderLayout.SOUTH);
    }

    private void placeButtons() {

    }
}
