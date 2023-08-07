package ui.windows;

import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends Window {

    private JLabel title;
    private String titleText = "select from:";

    private String button1 = "create a note";
    private String button2 = "view your folders";
    private String button3 = "quit";

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
        JButton b1 = new JButton(button1);
        JButton b2 = new JButton(button2);
        JButton b3 = new JButton(button3);

        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.add(b2);
        buttonRow.add(b3);
        this.add(buttonRow);

        ActionListener commonActionListener = createCommonActionListener();
        b1.addActionListener(commonActionListener);
        b2.addActionListener(commonActionListener);
        b3.addActionListener(commonActionListener);
    }

    private ActionListener createCommonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(button1)) {
                    getGUI().loadCreateNoteWindow();
                } else if (buttonPressed.equals(button2)) {
                    getGUI().loadViewFoldersWindow();
                } else if (buttonPressed.equals(button3)) {
                    getGUI().loadQuitWindow();
                }
            }
        };
    }
}
