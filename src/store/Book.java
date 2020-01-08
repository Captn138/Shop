package store;

public class Book extends Product{
    private String category;
    private String author;
    private Language lang;
    private int pages;

    public String category(){return category;}

    public String author(){return author;}

    public Language lang(){return lang;}

    public int pages(){return pages;}

    public Book(String name, String id, double price, int stock, String img, String author, Language lang, int pages){
        super(name, id, price, stock, img);
        category = "Book";
        this.author = author;
        this.lang = lang;
        this.pages = pages;
    }
}