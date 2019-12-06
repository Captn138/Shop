package store;

import java.util.Date;

public class Transaction {
    private String clientid;
    private String productid;
    private int nbproduct;
    private Date date;

    public String clientid(){
        return clientid;
    }

    public String productid(){
        return productid;
    }

    public int nbproduct(){
        return nbproduct;
    }

    public Date date(){
        return date;
    }

    public Transaction(){

    }
}