package ui.windows;

import model.Note;
import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectedNoteWindow extends Window {
    private Note note;
    private JLabel title;
    private JLabel message;

    private String button1 = "menu";

    public SelectedNoteWindow(ThoughtDumpGUI gui, Note note) {
        super(gui);
        this.note = note;
        setLayout(new GridLayout(3, 1));

        placeTitle();
        placeMessage();
        placeButtons();
    }

    private void placeTitle() {
        super.placeText(title, note.getNoteTitle(), bigSize);
    }

    private void placeMessage() {
        super.placeText(message, note.getMessage(), medSize);
    }

    private void placeButtons() {
        JButton b1 = new JButton(button1);
        JPanel buttonRow = formatButtonRow(b1);
        this.add(buttonRow);
        ActionListener commonActionListener = createCommonActionListener();
        b1.addActionListener(commonActionListener);
    }

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
