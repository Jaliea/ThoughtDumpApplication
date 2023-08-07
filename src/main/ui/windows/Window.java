package ui.windows;

import ui.ThoughtDumpGUI;

import javax.swing.*;
import java.awt.*;

public abstract class Window extends JPanel {

    private final ThoughtDumpGUI gui;

    private String font = "Dialog";
    protected int bigSize = 20;
    protected int medSize = 14;

    protected int buttonSpacing = 10;

    public Window(ThoughtDumpGUI gui) {
        this.gui = gui;
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    // EFFECTS: assigns text to given JLABEL with given text size
    public void placeText(JLabel something, String text, int size) {
        something = new JLabel("<html><div style='text-align: center;'>" + text + "</div></html>", JLabel.CENTER);
        something.setFont(new Font(font, Font.PLAIN, size));
        this.add(something);
    }

    public void placeItalics(JLabel something, String text, int size) {
        something = new JLabel("<html><div style='text-align: center;'>" + text + "</div></html>", JLabel.CENTER);
        something.setFont(new Font(font, Font.ITALIC, size));
        this.add(something);
    }

    //EFFECTS: returns the ThoughtDumpGUI for this tab
    public ThoughtDumpGUI getGUI() {
        return gui;
    }

}
