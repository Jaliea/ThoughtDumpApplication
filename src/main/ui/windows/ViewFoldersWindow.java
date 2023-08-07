package ui.windows;

import model.Folder;
import model.Note;
import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewFoldersWindow extends Window {
    private JLabel title;
    private List<Folder> folders;
    private Note note;
    private int buttonSpacing = 10;

    public ViewFoldersWindow(ThoughtDumpGUI gui, List<Folder> folders, Note note) {
        super(gui);
        this.folders = folders;
        this.note = note;

        placeTitle();
        placeButtons();
        this.setAlignmentX(CENTER_ALIGNMENT);

        setLayout(new GridLayout(3, 1));
    }

    private void placeTitle() {
        super.placeText(title, "select the folder you want to open", bigSize);
    }

    private void placeButtons() {
        if (folders.isEmpty()) {
            getGUI().loadCreateNewFolderWindow();
        } else {
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

            buttons.setAlignmentX(CENTER_ALIGNMENT);
            buttons.setAlignmentY(CENTER_ALIGNMENT);
            this.add(buttons, CENTER_ALIGNMENT);
        }
    }

    private ActionListener createCommonActionListener(Folder folder) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(folder.getFolderTitle())) {
                    if (note.isSelected()) {
                        getGUI().loadSavedMenuWindow();
                    } else {
                        getGUI().loadSelectedFolderWindow(folder);
                    }
                }
            }
        };
    }


}
