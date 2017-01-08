package britionary.logic;

public class ParseException extends Exception {

    /**
     * Metodi mahdollistaa erillistern parsimispoikkeusten heittämisen.
     *
     * @param   message Virhetapahtumaa kuvaava viesti
     */
    public ParseException(String message) {
        super(message);
    }
    
}
