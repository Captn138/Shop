package store;

public class Book extends Product{
    private String category;
    private String author;
    private Language lang;
    private int pages;

    public String category(){
        return category;
    }

    public String author(){
        return author;
    }

    public Language lang(){
        return lang;
    }

    public int pages(){
        return pages;
    }

    public Book(){
        category = "Book";
    }
}