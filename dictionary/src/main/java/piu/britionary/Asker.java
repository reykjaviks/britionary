package piu.britionary;
import java.io.InputStream;
import java.util.Scanner;

public class Asker { //Singleton?
    
    private Scanner scanner;
    private InputStream systemIn; //Used for testing
    
    public Asker() {
        this.systemIn = System.in;
    }
    public Asker(InputStream in) {
        this.systemIn = in;
    }
    
    private String scan() {
       
        try {
            scanner = new Scanner(systemIn);
            return scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return "";
    }
    public String getWord() {
        return scan();
    }
}
