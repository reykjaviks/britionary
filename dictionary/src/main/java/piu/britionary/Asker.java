package piu.britionary;

import java.util.Scanner;

public class Asker { //Singleton?
    
    private Scanner reader;
    private String word;
    
    public void scan() {
        reader = new Scanner(System.in);
        
        try {
            word = reader.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    public String getWord() {
        return word;
    }
}
