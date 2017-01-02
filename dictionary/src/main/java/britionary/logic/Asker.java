package britionary.logic;
import java.io.InputStream;
import java.util.Scanner;

public class Asker {

    private String word;
    private Scanner scanner;
    private final InputStream systemIn; //Used for testing

    public Asker() {
        this.systemIn = System.in;
    }

    public Asker(InputStream in) {
        this.systemIn = in;
    }

    public void scan() {
        try {
            scanner = new Scanner(systemIn);
            this.word = scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public String getWord() {
        return this.word;
    }
}
