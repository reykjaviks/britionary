package britionary.logic;

/**
 * Luokka tarjoaa metodeita käyttäjän syöttämien hakusanojen muuntamiseen.
 */
public class Converter {

    /**
     * Metodi muuntaa käyttäjän syöttämää sanaa niin, että sillä voidaan hakea
     * tietoa Oxford Dictionary:n ohjelmointirajapinnasta.
     * 
     * @param   word    käyttäjän syöttämä sana
     * @return          hakusana
     */
    public static String convert(String word) {
        return word.toLowerCase().trim().replace('_', ' ');
    }

}