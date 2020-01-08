package store;

public enum Language{
    EN("English"), FR("French"), GE("German"), SP("Spain"), CH("Chinese");

    private String languagename;

    public String toString(){return languagename;}

    Language(String languagename){
        this.languagename = languagename;
    }
}