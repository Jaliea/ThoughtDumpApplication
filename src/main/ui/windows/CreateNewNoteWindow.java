package ui.windows;

import model.Folder;
import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CreateNewNoteWindow extends Window {

    private JLabel title;
    private JLabel message;
    private JTextField userTitle;
    private JTextArea userDump;
    private GridBagConstraints grid = new GridBagConstraints();
    private List<Folder> folders;

    private String button1 = "save";
    private String button2 = "dump";

    public CreateNewNoteWindow(ThoughtDumpGUI gui, List<Folder> folders) {
        super(gui);
        this.folders = folders;

        setLayout(new GridBagLayout());

        initializeGrid();
        initializeButtons();

        placeUserInput();
        placeButtons();
    }

    public CreateNewNoteWindow(ThoughtDumpGUI gui, List<Folder> folders, Folder folder) {
        super(gui);
        this.folders = folders;

        setLayout(new GridBagLayout());

        initializeGrid();
        initializeButtons();

        placeUserInput();
        placeButtons();
        placeItalics();
    }

    private void placeItalics() {
        JLabel italics = new JLabel("<html><div style='text-align: center;'>" + "this folder is empty !"
                + "</div></html>", JLabel.CENTER);
        italics.setFont(new Font("Dialog", Font.ITALIC, 10));
        grid.gridx = 1;
        grid.gridy = 3;
        this.add(italics, grid);
    }

    // EFFECTS: places save and dump buttons
    private void placeButtons() {
        JButton b1 = new JButton(button1);
        JButton b2 = new JButton(button2);
        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.add(b2);

        grid.gridx = 0;
        grid.gridy = 2;

        add(buttonRow, grid);

        ActionListener commonActionListener = createCommonActionListener();

        b1.addActionListener(commonActionListener);
        b2.addActionListener(commonActionListener);
    }

    private ActionListener createCommonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(button1)) {
                    getGUI().saveNote(userTitle.getText(), userDump.getText());
                    if (folders.isEmpty()) {
                        getGUI().loadCreateNewFolderWindow();
                    } else {
                        getGUI().loadViewFoldersWindow();
                    }
                } else if (buttonPressed.equals(button2)) {
                    getGUI().loadMenuWindow();
                }

            }
        };
    }

    // MODIFIES: this
    // EFFECTS: places buttons on the grid layout of window
    private void placeUserInput() {
        add(title, grid);

        grid.weightx = 0.5;
        grid.gridx = 1;
        grid.gridy = 0;

        add(userTitle, grid);

        grid.ipady = 40;
        grid.weightx = 0.0;
        grid.gridwidth = 1;
        grid.gridx = 0;
        grid.gridy = 1;
        add(message, grid);

        grid.gridwidth = 2;
        grid.gridx = 1;
        add(userDump, grid);
    }

    // MODIFIES: this
    // EFFECTS: creates a grid on the window to place components
    private void initializeGrid() {
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.gridx = 0;
        grid.gridy = 0;
        grid.insets = new Insets(10,20,10,20);
    }

    // MODIFIES: this
    // EFFECTS: creates buttons
    private void initializeButtons() {
        title = new JLabel("name your dump       ");
        message = new JLabel("dump your thoughts      ");


        userTitle = new JTextField(50);
        userDump = new JTextArea(20, 50);
        userDump.setLineWrap(true);
        userDump.setWrapStyleWord(true);
    }

}
