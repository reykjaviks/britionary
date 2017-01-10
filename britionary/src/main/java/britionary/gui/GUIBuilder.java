package britionary.gui;

import britionary.logic.Searcher;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class GUIBuilder extends JFrame {

    private JPanel searchPanel;
    private JPanel resultsPanel;
    private JTextField searchField;
    private JButton britsButton;
    private JButton allButton;
    private JTextPane resultsPane;
    private JScrollPane scrollPane;
    private GridLayout searchLayout;
    private GridLayout resultsLayout;

    private GUIBuilder(String name) {
        super(name);
        setResizable(true);
    }

    public static void createAndShowGUI() {
        GUIBuilder frame = new GUIBuilder("Britionary");
        frame.createComponents();
        frame.setComponents();
        frame.addComponents();
        frame.getRootPane().setDefaultButton(frame.britsButton); // Mieti myöhemmin
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents() {
        searchPanel = new JPanel();
        resultsPanel = new JPanel();
        searchField = new JTextField();
        createButtons();
        resultsPane = new JTextPane();
        scrollPane = new JScrollPane(resultsPane);
        searchLayout = new GridLayout(1, 3);
        resultsLayout = new GridLayout(1, 1);
    }

    private void setComponents() {
        searchPanel.setLayout(searchLayout);
        resultsPanel.setLayout(resultsLayout);
        setPanelSize();
        resultsPane.setEditable(false);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setLayout(new BorderLayout());
    }

    private void addComponents() {
        searchPanel.add(searchField);
        searchPanel.add(britsButton);
        searchPanel.add(allButton);
        resultsPanel.add(scrollPane);
        this.add(searchPanel, BorderLayout.NORTH);
        this.add(resultsPanel, BorderLayout.CENTER);
    }

    private void createButtons() {
        britsButton = new JButton(new AbstractAction("Brits") {
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
    }

    private void setPanelSize() {
        Dimension buttonDimensions = britsButton.getPreferredSize();
        Dimension searchPanelDimensions = new Dimension((int) (buttonDimensions.getWidth() * 8.0),
                (int) (buttonDimensions.getHeight() * 2.0));
        Dimension resultsPanelDimensions = new Dimension((int) (buttonDimensions.getWidth() * 8.0),
                (int) (buttonDimensions.getHeight() * 20.0));

        searchPanel.setPreferredSize(searchPanelDimensions);
        resultsPanel.setPreferredSize(resultsPanelDimensions);
    }

}
