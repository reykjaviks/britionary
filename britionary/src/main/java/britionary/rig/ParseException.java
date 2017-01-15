package britionary.rig;

/**
 * Luokka tarjoaa metodeita erillisten parsimispoikkeusten heittämiseen.
*/
public class ParseException extends Exception {

    /**
     * Konstruktori luo uuden parsimispoikkeuksen ja asettaa sille viestin.
     *
     * @param   message virhetapahtumaa kuvaava viesti
     */
    public ParseException(String message) {
        super(message);
    }
    
}