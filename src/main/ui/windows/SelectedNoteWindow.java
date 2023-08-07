package ui.windows;

import model.Note;
import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// represents the window where the user can read a previously saved message
public class SelectedNoteWindow extends Window {
    private Note note;
    private JLabel title;
    private JLabel message;

    private String button1 = "menu";

    // EFFECTS: creates a window with the selected note title, message, and buttons
    public SelectedNoteWindow(ThoughtDumpGUI gui, Note note) {
        super(gui);
        this.note = note;
        setLayout(new GridLayout(3, 1));

        placeTitle();
        placeMessage();
        placeButtons();
    }

    // MODIFIES: this
    // EFFECTS: places the note title at the top of the screen
    private void placeTitle() {
        super.placeText(title, note.getNoteTitle(), bigSize);
    }

    // MODIFIES: this
    // EFFECTS: places the note message below the title
    private void placeMessage() {
        super.placeText(message, note.getMessage(), medSize);
    }

    // MODIFIES: this
    // EFFECTS: places a button for the user to navigate back to the main menu
    private void placeButtons() {
        JButton b1 = new JButton(button1);
        JPanel buttonRow = formatButtonRow(b1);
        this.add(buttonRow);
        ActionListener commonActionListener = createCommonActionListener();
        b1.addActionListener(commonActionListener);
    }

    // MODIFIES: button
    // EFFECTS: makes the button clickable and actionable where it changes the window
    private ActionListener createCommonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(button1)) {
                    getGUI().loadMenuWindow();
                }
            }
        };
    }
}
