package ui.windows;

import model.Folder;
import model.Note;
import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewSelectedFolderWindow extends Window {
    private JLabel title;
    private Folder folder;
    private List<Note> notes;

    public ViewSelectedFolderWindow(ThoughtDumpGUI gui, Folder folder) {
        super(gui);
        this.folder = folder;
        notes = folder.viewNotes();
        placeTitle();
        placeButtons();
        this.setAlignmentX(CENTER_ALIGNMENT);

        setLayout(new GridLayout(3, 1));

    }

    private void placeTitle() {
        super.placeText(title, "select the note you want to view", bigSize);
    }

    private void placeButtons() {
        if (notes.isEmpty()) {
            getGUI().loadCreateNewFolderWindow();
        } else {
            JPanel buttons = new JPanel();
            buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

            for (Note note : notes) {
                JButton button = new JButton(note.getNoteTitle());
                button.setAlignmentX(CENTER_ALIGNMENT);
                buttons.add(button);
                ActionListener commonActionListener = createCommonActionListener(note);
                button.addActionListener(commonActionListener);
                buttons.add(Box.createRigidArea(new Dimension(0, buttonSpacing)));
            }

            buttons.setAlignmentX(CENTER_ALIGNMENT);
            buttons.setAlignmentY(CENTER_ALIGNMENT);
            this.add(buttons, CENTER_ALIGNMENT);
        }
    }

    private ActionListener createCommonActionListener(Note note) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(note.getNoteTitle())) {
                    getGUI().loadSelectedNoteWindow(note);
                }
            }
        };
    }

    private void placeItalics() {
        super.placeItalics(new JLabel(), "this folder has no notes yet !", 10);
    }
}
