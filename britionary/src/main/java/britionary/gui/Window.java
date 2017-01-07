package britionary.gui;

import britionary.logic.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener {

    private JPanel searchPanel;
    private JTextField searchField;
    private JButton searchButton;
    
    private JPanel resultPanel;
    private JTextField resultField;
    
    private GroupLayout layout;

    private Searcher searcher = Searcher.getInstance();

    public void buildGUI() {

        // this.setLayout(new FlowLayout());
        searchPanel = new JPanel();
        searchField = new JTextField("", 20);
        searchButton = new JButton("Search");

        resultPanel = new JPanel();
        resultField = new JTextField("", 20);

        layout = new GroupLayout(this);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addComponent(searchField)
                .addComponent(searchButton)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        //.addGroup()
                        .addComponent(resultField))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(searchField)
                        .addComponent(searchButton))
                .addComponent(resultField)
        );

        searchField.addActionListener(this);
        searchButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resultField.setText(searcher.search(searchField.getText()));
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() { //Miksei tässä ole sulkua?

            public void run() {
                Window window = new Window();
                window.buildGUI();
                window.setTitle("Britionary");
                window.pack();
                window.setVisible(true);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

    }

}
