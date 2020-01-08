package store;

public class DVD extends Product{
    private String category;
    private String cast;
    private String genre;
    private int duration;

    public String category(){return category;}

    public String cast(){return cast;}

    public String genre(){return genre;}

    public int duration(){return duration;}

    public DVD(String name, String id, double price, int stock, String img, String cast, String genre, int duration){
        super(name, id, price, stock, img);
        category = "DVD";
        this.cast = cast;
        this.genre = genre;
        this.duration = duration;
    }
}