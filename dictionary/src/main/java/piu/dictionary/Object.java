package piu.dictionary;

public class Object {
    private String id;
    private String language;
    private String region;
    
    Object(String id, String language, String region) {
        this.id = id;
        this.language = language;
        this.region = region;
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
