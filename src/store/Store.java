package store;

import java.util.LinkedList;
import java.util.UUID;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Store {
    private String name;
    private String logo;
    private NodeList productList;
    private NodeList clientList;
    private NodeList transactionList;
    private XMLFile xmlTransaction;
    private XMLFile xmlClient;
    private XMLFile xmlProduct;

    public String name() {return name;}

    public String logo() {return logo;}

    public NodeList clientlist() {return clientList;}

    public NodeList productlist() {return productList;}

    public NodeList transactionslist() {return transactionList;}

    public void newTransaction(UUID clientid, UUID productid, int nbproduct){
        //ajoute la transaction dans le fichier xml transactions, puis change le stock du produit et update le fichier xml produits
        Transaction transaction = new Transaction(clientid.toString(), productid.toString(), nbproduct);
        LinkedList<String> childs = new LinkedList<String>();
        childs.add("clientid#" + clientid.toString());
        childs.add("productid#" + productid.toString());
        childs.add("nbproduct#" + nbproduct);
        childs.add("date#" + transaction.date());
        xmlTransaction.writeXML("transaction", childs);
        for (int i = 0; i < productList.getLength(); i++) {
            if (productList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element currentelement = (Element) productList.item(i);
                if(currentelement.getElementsByTagName("id").item(0).getTextContent() == productid.toString()){
                    int stock = Integer.parseInt(currentelement.getElementsByTagName("stock").item(0).getTextContent());
                    currentelement.getElementsByTagName("stock").item(0).setTextContent(Integer.toString(stock-1));
                    xmlProduct.updatexml(productList);
                    return;
                }
            }
        }
    }

    public void addNewCustomer(Customer customer){
        //ajoute un nouveau client au fichier xml clients
        LinkedList<String> childs = new LinkedList<String>();
        childs.add("id#" + customer.id());
        childs.add("fname#" + customer.fname());
        childs.add("lname#" + customer.lname());
        childs.add("address#" + customer.address());
        xmlClient.writeXML("client", childs);
    }

    public Store(String name, String logo, String clientsfile, String productsfile, String transactionsfile) throws InterruptedException {
        this.name = name;
        this.logo = logo;
        
        xmlProduct = new XMLFile(productsfile);
        xmlClient = new XMLFile(clientsfile);
        xmlTransaction = new XMLFile(transactionsfile);

        productList = xmlProduct.parseXMLFile();
        clientList = xmlClient.parseXMLFile();
        transactionList = xmlTransaction.parseXMLFile();
        
        System.out.println("\nSystem ready!");
    }

    public static void main(String[] args) {
        try {
            new Store("Watto's Shop", "./files/logo.png", "./files/clients.xml", "./files/products.xml", "./files/transactions.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}