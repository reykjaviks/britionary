package britionary.logic;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Luokkaa käytetään käyttäjän syötteiden lukemiseen.
 * Tämä luokka on poistettu luokkakaaviosta, koska
 * sen tarpeellisuudesta ei ole vielä varmuutta.
*/
public class Asker {

    private String word;
    private Scanner scanner;
    private final InputStream systemIn; // Käytetään testaukseen

    public Asker() {
        this.systemIn = System.in;
    }

    public Asker(InputStream in) {
        this.systemIn = in;
    }

    public String getWord() {
        return this.word;
    }

    public void scan() {
        try {
            scanner = new Scanner(systemIn);
            this.word = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

}
