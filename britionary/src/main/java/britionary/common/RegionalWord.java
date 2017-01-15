package britionary.common;

/**
 * Luokka tarjoaa metodeita maantieteellisten sanojen tallentamiseen.
 */
public class RegionalWord {
    
    private final String region;
    private final String word;

    /**
     * Konstruktori luo uuden alueellisen sanan.
     *
     * @param   region  maantieteellinen alue
     * @param   word    sana
     */
    public RegionalWord(String region, String word) {
        this.region = region;
        this.word = word;
    }
    
    public String getRegion() {
        return this.region;
    }
    
    public String getWord() {
        return this.word;
    }

    @Override
    public int hashCode() {
        return 17 + this.word.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof RegionalWord) {
            if (((RegionalWord) object).getWord().equals(this.word)) {
                return true;
            }
        }
        return false;
    }

}
