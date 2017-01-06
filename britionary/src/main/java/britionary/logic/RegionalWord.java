package britionary.logic;

public class RegionalWord {
    
    private final String region;
    private final String word;
    
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
