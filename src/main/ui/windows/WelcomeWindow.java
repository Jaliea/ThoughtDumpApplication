package ui.windows;

import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeWindow extends Window {

    private JLabel title;
    private String titleText = "hihi<br>welcome to ThoughtDump :]"
            + "<br>a cozy and safe space where you can dump all your thoughts";

    private JLabel question;
    private String questionText = "do you want to load a previous save file?";

    private String button1 = "yes";
    private String button2 = "no";


    public WelcomeWindow(ThoughtDumpGUI gui) {
        super(gui);

        setLayout(new GridLayout(3, 1));

        placeTitle();
        placeButtons();
    }

    private void placeTitle() {
        super.placeText(title, titleText, bigSize);
    }

    private void placeButtons() {
        super.placeText(question, questionText, medSize);
        JButton b1 = new JButton(button1);
        JButton b2 = new JButton(button2);

        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.add(b2);

        this.add(buttonRow);

        ActionListener commonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(button1)) {
                    getGUI().loadSavedDumps();
                }
                getGUI().loadMenuWindow();
            }
        };

        b1.addActionListener(commonActionListener);
        b2.addActionListener(commonActionListener);
    }
}
