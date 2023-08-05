package ui.windows;

import ui.ButtonNames;
import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateNewNoteWindow extends Window {

    private JLabel title;
    private JLabel message;
    private JTextField userTitle;
    private JTextArea userDump;
    private GridBagConstraints grid = new GridBagConstraints();

    public CreateNewNoteWindow(ThoughtDumpGUI gui) {
        super(gui);

        setLayout(new GridBagLayout());

        initializeGrid();
        initializeButtons();

        placeUserInput();
        placeButtons();
    }

    // EFFECTS: places save and dump buttons
    private void placeButtons() {
        JButton b1 = new JButton("save");
        JButton b2 = new JButton("dump");

        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.add(b2);

        grid.gridx = 0;
        grid.gridy = 2;

        add(buttonRow, grid);

        ActionListener commonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals("save")) {
                    getGUI().saveNote(userTitle.getText(), userDump.getText());
                    getGUI().loadViewFoldersWindow();
                } else if (buttonPressed.equals("dump")) {
                    getGUI().loadMenuWindow();
                }

            }
        };

        b1.addActionListener(commonActionListener);
        b2.addActionListener(commonActionListener);
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
