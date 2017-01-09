package britionary.prototypes;

import britionary.logic.Searcher;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GUIBuilderPrototype extends JFrame implements ActionListener {

    private JTextField sField;
    private JTextField pField;
    private JButton button;
    private Searcher searcher = Searcher.getInstance();

    public void buildGUI() {

        this.setLayout(new FlowLayout());
        sField = new JTextField("", 20);
        pField = new JTextField("", 20);
        button = new JButton("Search");
        this.add(sField);
        this.add(pField);
        this.add(button);
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String word = sField.getText();
        pField.setText(searcher.searchBrits(word));
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() { //Miksei tässä ole sulkua?

            public void run() {
                GUIBuilderPrototype window = new GUIBuilderPrototype();
                window.buildGUI();
                window.setTitle("Britionary");
                window.pack();
                window.setVisible(true);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

    }

}
