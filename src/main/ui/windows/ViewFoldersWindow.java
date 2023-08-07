package ui.windows;

import model.Folder;
import model.Note;
import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// represents the window where the user views all their saved folders
public class ViewFoldersWindow extends Window {
    private JLabel title;
    private List<Folder> folders;
    private Note note;

    private String cnfButton = "create a new folder";

    // EFFECTS: creates a window with a title and buttons that are all in the center of the screen
    public ViewFoldersWindow(ThoughtDumpGUI gui, List<Folder> folders, Note note) {
        super(gui);
        this.folders = folders;
        this.note = note;

        placeTitle();
        placeButtons();
        this.setAlignmentX(CENTER_ALIGNMENT);

        setLayout(new GridLayout(BoxLayout.X_AXIS, 1));
    }

    // MODIFIES: this
    // EFFECTS: places the title of the window at the top of the screen
    private void placeTitle() {
        super.placeText(title, "select the folder you want to open", bigSize);
    }

    // MODIFIES: this
    // EFFECTS: places buttons for all the user's folders and a button to create a new folder
    private void placeButtons() {
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

        for (Folder folder : folders) {
            JButton button = new JButton(folder.getFolderTitle());
            button.setAlignmentX(CENTER_ALIGNMENT);
            buttons.add(button);
            ActionListener commonActionListener = createCommonActionListener(folder);
            button.addActionListener(commonActionListener);
            buttons.add(Box.createRigidArea(new Dimension(0, buttonSpacing)));
        }

        JButton newFolder = new JButton(cnfButton);
        newFolder.setAlignmentX(CENTER_ALIGNMENT);
        ActionListener commonActionListener = createCommonActionListener();
        newFolder.addActionListener(commonActionListener);
        buttons.add(newFolder);

        buttons.setAlignmentX(CENTER_ALIGNMENT);
        buttons.setAlignmentY(CENTER_ALIGNMENT);
        this.add(buttons, CENTER_ALIGNMENT);
    }

    // MODIFIES: buttons of folders
    // EFFECTS: makes buttons clickable and actionable to navigate to the selected folder window
    private ActionListener createCommonActionListener(Folder folder) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(folder.getFolderTitle())) {
                    if (note != null && note.isSelected()) {
                        getGUI().saveNoteToFolder(folder);
                        getGUI().loadSavedMenuWindow();
                    } else {
                        if (folder.viewNotes().isEmpty()) {
                            getGUI().loadCreateNoteWindow(folder);
                        } else {
                            getGUI().loadSelectedFolderWindow(folder);
                        }
                    }
                }
            }
        };
    }

    // MODIFIES: create a new folder button
    // EFFECTS: makes button clickable and navigates to the create a new folder window
    private ActionListener createCommonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(cnfButton)) {
                    getGUI().loadCreateNewFolderWindow();
                }
            }
        };
    }


}
