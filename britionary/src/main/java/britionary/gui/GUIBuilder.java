package britionary.gui;

import britionary.logic.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class GUIBuilder extends JFrame implements ActionListener {

    private JPanel search;
    private JPanel results;
    private JTextPane textPane;
    private JScrollPane textScrollPane;
    private JButton searchButton;
    private JTextField searchField;

    public GUIBuilder(String name) {
        super(name);
        setResizable(false);
    }

    public void setEditorPane() {
        textPane = new JTextPane();
        textPane.setEditable(false);
        setEditorScrollPane(textPane);
    }

    //TODO: set visible(?)
    private void setEditorScrollPane(JTextPane editorPane) {
        textScrollPane = new JScrollPane(editorPane);
        textScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textScrollPane.setPreferredSize(new Dimension(250, 145));
        textScrollPane.setMinimumSize(new Dimension(10, 10));
    }

    public void setButtonSize() {
        Dimension buttonSize = searchButton.getPreferredSize();
        search.setPreferredSize(new Dimension((int) (buttonSize.getWidth() * 5.0),
                (int) (buttonSize.getHeight() * 2.0)));
        results.setPreferredSize(new Dimension((int) (buttonSize.getWidth() * 2.5),
                (int) (buttonSize.getHeight() * 10.0)));
    }

    public void addComponentsToPane(final Container pane) {

        search = new JPanel();
        search.setLayout(new GridLayout(1, 2));

        results = new JPanel();
        results.setLayout(new GridLayout(1, 1));

        searchField = new JTextField();
        searchField.addActionListener(this);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);

        setButtonSize();
        search.add(searchField);
        search.add(searchButton);
        

        setEditorPane();
        results.add(textPane);

        pane.add(search, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(results, BorderLayout.SOUTH);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String word = searchField.getText();
        Searcher searcher = Searcher.getInstance();
        textPane.setText(searcher.search(word));
    }

    public static void createAndShowGUI() {
        GUIBuilder frame = new GUIBuilder("Britionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentsToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

}
