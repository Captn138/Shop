package store;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String clientid;
    private String productid;
    private int nbproduct;
    private String date;
    private LocalDateTime ldt;
    private DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public String clientid(){return clientid;}

    public String productid(){return productid;}

    public int nbproduct(){return nbproduct;}

    public String date(){return date;}

    public Transaction(String clientid, String productid, int nbproduct){
        this.clientid = clientid;
        this.productid = productid;
        this.nbproduct = nbproduct;
        ldt = LocalDateTime.now();
        this.date = dft.format(ldt);
    }
}