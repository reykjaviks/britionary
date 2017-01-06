package britionary.logic;

public class WordResult {
    
    private final String region;
    private final String word;
    
    public WordResult(String region, String word) {
        this.region = region;
        this.word = word;
    }
    
    public String getRegion() {
        return this.region;
    }
    
    public String getWord() {
        return this.word;
    }
}
