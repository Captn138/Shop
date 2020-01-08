package store;

public class Customer{
    private String id;
    private String fname;
    private String lname;
    private String address;

    public String id(){return id;}

    public String fname(){return fname;}

    public String lname(){return lname;}

    public String address(){return address;}

    public Customer(String id, String fname, String lname, String address){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
    }
}