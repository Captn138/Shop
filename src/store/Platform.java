package store;

public enum Platform{
    N("Nintendo"), PC("PC"), PS("PlayStation"), X("XBOX");
    
    private String consolename;

    public String toString(){
        return consolename;
    }

    private Platform(String consolename) {
        this.consolename = consolename;
    }
}