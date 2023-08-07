package ui.windows;

import model.Folder;
import model.Note;
import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// represents the window where all the notes of the selected folder are displayed as clickable buttons to view the note
public class ViewSelectedFolderWindow extends Window {
    private JLabel title;
    private Folder folder;
    private List<Note> notes;

    // EFFECTS: creates a window with a title a buttons in the center
    public ViewSelectedFolderWindow(ThoughtDumpGUI gui, Folder folder) {
        super(gui);
        this.folder = folder;
        notes = folder.viewNotes();
        placeTitle();
        placeButtons();
        this.setAlignmentX(CENTER_ALIGNMENT);

        setLayout(new GridLayout(3, 1));
    }

    // MODIFIES: this
    // EFFECTS: places the title at the top of the screen
    private void placeTitle() {
        super.placeText(title, "select the note you want to view", bigSize);
    }

    // MODIFIES: this
    // EFFECTS: places buttons for all the notes in the folder under the title
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

    // MODIFIES: buttons
    // EFFECTS: makes buttons clickable and actionable so that the window changes to the clicked note
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
}
