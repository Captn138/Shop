package store;

public class Game extends Product{
    private String category;
    private String genre;
    private Platform platform;
    private int year;
    
    public String category(){
        return category;
    }

    public String genre(){
        return genre;
    }

    public Platform platform(){
        return platform;
    }

    public int year(){
        return year;
    }

    public Game(){
        category = "Game";
    }
}