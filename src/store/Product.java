package store;

public class Product{
    private String category;
    private String name;
    private String id;
    private double price;
    private int stock;
    private String img;

    public String category(){return category;}

    public String name(){return name;}

    public String id(){return id;}

    public double price(){return price;}

    public int stock(){return stock;}

    public String img(){return img;}

    public Product(String name, String id, double price, int stock, String img){
        this.name = name;
        this.id = id;
        this.price = price;
        this.stock = stock;
        this.img = img;
    }
}