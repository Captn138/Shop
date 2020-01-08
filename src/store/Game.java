package store;

public class Game extends Product{
    private String category;
    private String genre;
    private Platform platform;
    private int year;
    
    public String category(){return category;}

    public String genre(){return genre;}

    public Platform platform(){return platform;}

    public int year(){return year;}

    public Game(String name, String id, double price, int stock, String img, String genre, Platform platform, int year){
        super(name, id, price, stock, img);
        category = "Game";
        this.genre = genre;
        this.platform = platform;
        this.year = year;
    }
}