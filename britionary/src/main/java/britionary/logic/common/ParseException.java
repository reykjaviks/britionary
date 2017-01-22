package britionary.logic.common;

/**
 * Luokka tarjoaa metodeita parsimispoikkeusten luomiseen.
*/
public class ParseException extends Exception {

    /**
     * Konstruktori luo uuden parsimispoikkeuksen ja asettaa viestin.
     *
     * @param   message virhetapahtumaa kuvaava viesti
     */
    public ParseException(String message) {
        super(message);
    }
    
}