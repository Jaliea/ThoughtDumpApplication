package ui.windows;

import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitWindow extends Window {

    private String button1 = "yes";
    private String button2 = "no";

    private String sep = System.getProperty("file.separator");
    private ImageIcon goodbyeImage = new ImageIcon(System.getProperty("user.dir") + sep
            + "images" + sep + "goodbye.png");

    public QuitWindow(ThoughtDumpGUI gui) {
        super(gui);
        setLayout(new GridLayout(3, 1));
        placeImage();
        placeTitle();
        placeButtons();
    }

    private void placeImage() {
        JLabel image = new JLabel(goodbyeImage);
        this.add(image);
    }

    private void placeTitle() {
        super.placeText(new JLabel("title"), "thanks for stopping by !<br>do you want to save before you go?",
                bigSize);
    }

    private void placeButtons() {
        JButton b1 = new JButton(button1);
        JButton b2 = new JButton(button2);

        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.add(b2);

        ActionListener commonActionListener = createCommonActionListener();
        b1.addActionListener(commonActionListener);
        b2.addActionListener(commonActionListener);

        this.add(buttonRow);
    }

    private ActionListener createCommonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(button1)) {
                    getGUI().saveDumps();
                }
                getGUI().quitApp();
            }
        };
    }
}
