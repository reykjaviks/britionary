package britionary.logic;

/**
 * Luokka tarjoaa metodin käyttäjän syöttämien hakusanojen muuntamiseen.
 */
public class Converter {
    
    /**
     * Metodi muuntaa hakusanan siihen muotoon, että sillä voidaan hakea tietoa
     * Oxford Dictionary:n ohjelmointirajapinnasta.
     * 
     * @param   word    Käyttäjän antama hakusana
     * @return          Siistitty hakusana
     */
    public String convert(String word) {

        StringBuilder str = new StringBuilder(word.toLowerCase());

        // Korvaa alaviivat
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '_') {
                str.setCharAt(i, ' ');
            }
        }
        // Poistaa numerot
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                str.deleteCharAt(i);
                i--;
            }
        }
        // Poistaa alkuvälilyönnin
        if (Character.isSpaceChar(str.charAt(0))) {
            str.deleteCharAt(0);
        }
        // Poistaa loppuvälilyönnin
        if (Character.isSpaceChar(str.charAt(str.length() - 1))) {
            str.deleteCharAt(str.length() - 1);
        }
        
        return str.toString();

    }

}
