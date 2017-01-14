package britionary.logic;

/**
 * Luokka tarjoaa metodeita käyttäjän syöttämien hakusanojen siistimiseen.
 */
public class Converter {

    /**
     * Metodi siistii hakusanaa niin, että sillä voidaan hakea tietoa
     * Oxford Dictionary:n ohjelmointirajapinnasta.
     * 
     * @param   word    käyttäjän antama hakusana
     * @return          siistitty hakusana
     */
    public static String convert(String word) {
        return word.toLowerCase().trim().replace('_', ' ');
    }

}