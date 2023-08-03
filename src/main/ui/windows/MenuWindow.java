package ui.windows;

import ui.ButtonNames;
import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends Window {

    private JLabel title;
    private String titleText = "select from:";

    public MenuWindow(ThoughtDumpGUI gui) {
        super(gui);

        setLayout(new GridLayout(3, 1));

        placeTitle();
        placeButtons();
    }

    private void placeTitle() {
        super.placeText(title, titleText, bigSize);
    }

    private void placeButtons() {
        JButton b1 = new JButton(ButtonNames.CREATE_NOTE.getValue());
        JButton b2 = new JButton(ButtonNames.VIEW_FOLDERS.getValue());
//        JButton b3 = new JButton(ButtonNames.SAVE.getValue());
        JButton b4 = new JButton(ButtonNames.QUIT.getValue());

        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.add(b2);
//        buttonRow.add(b3);
        buttonRow.add(b4);
        this.add(buttonRow);

        ActionListener commonActionListener = createCommonActionListener();
        b1.addActionListener(commonActionListener);
        b2.addActionListener(commonActionListener);
//        b3.addActionListener(commonActionListener);
        b4.addActionListener(commonActionListener);
    }

    private ActionListener createCommonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.CREATE_NOTE.getValue())) {
                    getGUI().loadCreateNoteWindow();
                } else if (buttonPressed.equals(ButtonNames.VIEW_FOLDERS.getValue())) {
                    getGUI().loadViewFoldersWindow();
                } else if (buttonPressed.equals(ButtonNames.QUIT.getValue())) {
                    getGUI().loadQuitWindow();
                }
            }

            ;
        };
    }
}
