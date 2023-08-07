package ui.windows;

import model.Folder;
import model.Note;
import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNewFolderWindow extends Window {
    private JLabel title;
    private Note note;
    private JTextField userTitle;

    private String button1 = "save";

    public CreateNewFolderWindow(ThoughtDumpGUI gui, Note note) {
        super(gui);
        this.note = note;

        setLayout(new GridLayout(4, 1));

        placeTitle();
        placeUserInput();
        placeButtons();
    }

    private void placeTitle() {
        super.placeText(title, "name your new folder", bigSize);
    }

    private void placeUserInput() {
        userTitle = new JTextField(50);
        this.add(userTitle);
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
                    Folder folder = new Folder(userTitle.getText());
                    getGUI().createFolder(folder);
                    if (note != null && note.isSelected()) {
                        getGUI().saveNoteToFolder(folder);
                    }
                    getGUI().loadSavedMenuWindow();
                }
            }
        };
    }
}
