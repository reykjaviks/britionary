package piu.dictionary;

public class Object {
    private String id;
    private String language;
    private String region;
    
    Object(String id, String language, String region) {
        setId(id);
        setLang(language);
        setReg(region);
    }
    private void setId(String id){
        this.id = id;
    }
    private void setLang(String language) {
        this.language = language;
    }
    private void setReg(String region) {
        this.region = region;
    }
}
