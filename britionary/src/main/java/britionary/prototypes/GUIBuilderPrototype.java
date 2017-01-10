package britionary.prototypes;

import britionary.logic.Searcher;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 * Luokka tarjoaa metodeita käyttöliittymän rakentamiseen.
 */
public class GUIBuilderPrototype extends JFrame implements ActionListener {

    private JPanel search;
    private JPanel resultsPanel;
    private JTextPane resultsPane;
    private JScrollPane textScrollPane;
    private JButton britButton;
    private JButton allButton;
    private JTextField searchField;

    private GUIBuilderPrototype(String name) {
        super(name);
        setResizable(true);
    }

    /**
     * Metodi rakentaa käyttöliittymän luokan yksityisiä metodeita apuna
     * käyttäen.
     */
    public static void createAndShowGUI() {
        GUIBuilderPrototype frame = new GUIBuilderPrototype("Britionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addComponentsToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void addComponentsToPane(final Container pane) {
        search = new JPanel();
        search.setLayout(new GridLayout(1, 3));

        resultsPanel = new JPanel();
        resultsPanel.setLayout(new GridLayout(1, 1));

        searchField = new JTextField();

        britButton = new JButton(new AbstractAction("Brits") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = searchField.getText();
                Searcher searcher = Searcher.getInstance();
                resultsPane.setText(searcher.searchBrits(word));
            }
        });

        allButton = new JButton(new AbstractAction("All") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = searchField.getText();
                Searcher searcher = Searcher.getInstance();
                resultsPane.setText(searcher.searchAll(word));
            }
        });

        setPanelSize();
        search.add(searchField);
        search.add(britButton);
        search.add(allButton);

        setEditorPane();
        resultsPanel.add(resultsPane); //aseta tilalle scrollpane

        pane.add(search, BorderLayout.NORTH);
        //pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(resultsPanel, BorderLayout.CENTER);
    }

    private void setEditorPane() {
        resultsPane = new JTextPane();
        resultsPane.setEditable(false);
        setEditorScrollPane(resultsPane);
    }

    //TODO: korjaa näkyvyys
    private void setEditorScrollPane(JTextPane editorPane) {
        textScrollPane = new JScrollPane(editorPane);
        textScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //this.getContentPane().add(textScrollPane);
                /*

        textScrollPane.setPreferredSize(new Dimension(250, 145));
        textScrollPane.setMinimumSize(new Dimension(10, 10));
                */
    }

    private void setPanelSize() {
        Dimension buttonSize = britButton.getPreferredSize();
        search.setPreferredSize(new Dimension((int) (buttonSize.getWidth() * 8.0),
                (int) (buttonSize.getHeight() * 2.0)));
        resultsPanel.setPreferredSize(new Dimension((int) (buttonSize.getWidth() * 8.0),
                (int) (buttonSize.getHeight() * 20.0)));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String word = searchField.getText();
        Searcher searcher = Searcher.getInstance();
        resultsPane.setText(searcher.searchBrits(word));
    }

}
