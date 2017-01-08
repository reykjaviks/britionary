package britionary.prototypes;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Luokka tarjoaa metodeita käyttäjän syötteiden lukemiseen. Tämä luokka on 
 * poistettu luokkakaaviosta, koska sen tarpeellisuudesta ei ole vielä varmuutta.
*/
public class AskerPrototype {

    private String word;
    private Scanner scanner;
    private final InputStream systemIn; // Käytetään testaukseen

    public AskerPrototype() {
        this.systemIn = System.in;
    }

    public AskerPrototype(InputStream in) {
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
