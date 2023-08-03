package ui.windows;

import ui.ButtonNames;
import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeWindow extends Window {

    private JLabel title;
    private String titleText = "hihi<br>welcome to ThoughtDump"
            + "<br>a cozy and safe space where you can dump all your thoughts";

    private JLabel question;
    private String questionText = "do you want to load a previous save file?";


    public WelcomeWindow(ThoughtDumpGUI gui) {
        super(gui);

        setLayout(new GridLayout(3, 1));

        placeTitle();
        placeButtons();
    }

    private void placeTitle() {
//        title = new JLabel("<html><div style='text-align: center;'>" + titleText + "</div></html>", JLabel.CENTER);
//        title.setFont(new Font(font, Font.PLAIN, 20));
//        this.add(title);
        super.placeText(title, titleText, bigSize);
    }

    private void placeButtons() {
        super.placeText(question, questionText, medSize);
        JButton b1 = new JButton(ButtonNames.YES.getValue());
        JButton b2 = new JButton((ButtonNames.NO.getValue()));

        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.add(b2);

        this.add(buttonRow);

        ActionListener commonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.YES.getValue())) {
                    getGUI().loadSavedDumps();
                }
                getGUI().loadMenuWindow();
            }
        };

        b1.addActionListener(commonActionListener);
        b2.addActionListener(commonActionListener);
    }
}
