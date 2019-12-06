package store;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WriteXMLFile {

    Document doc;
    Document parser;
    Transformer transformer;
    DOMSource source;
    StreamResult sortie;
    Element root;
    Element parsedroot;

    /*Element dvd = doc.createElement("DVD");
    root.appendChild(dvd);
    dvd.setAttribute("id", "0001");
    Element genre = doc.createElement("genre");
    genre.appendChild(doc.createTextNode("Action"));
    dvd.appendChild(genre);*/

    public Node getnode(Product product){
        NodeList list = parsedroot.getElementsByTagName(product.category());
        for(int i=0; i<list.getLength(); i++){
            NamedNodeMap attrlist = list.item(i).getAttributes();
            if(attrlist.getNamedItem("id").toString().contains(product.id())){
                return list.item(i);
            }
        }
        return null;
    }

    public void add(Product child, Product parent){
        NodeList list = doc.getElementsByTagName(parent.category());
        for(int i=0; i<list.getLength(); i++){
            NamedNodeMap attrlist = list.item(i).getAttributes();
            if(attrlist.getNamedItem("id").toString().contains(parent.id())){
                Element childnode = doc.createElement(parent.category());
                list.item(i).appendChild(childnode);
            }
        }
    }

    public WriteXMLFile(String filename, String rootname){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File(filename);
            if(!file.exists()){
                file.createNewFile();
            }
            doc = builder.newDocument();
            root = doc.createElement(rootname);
            doc.appendChild(root);
            TransformerFactory transformerfactory = TransformerFactory.newInstance();
            transformer = transformerfactory.newTransformer();
            source = new DOMSource(doc);
            sortie = new StreamResult(file);
            transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source, sortie);
            parser = builder.parse(filename);
            parsedroot = parser.getDocumentElement();
        }catch(final ParserConfigurationException e){
            e.printStackTrace();
        }catch (final IOException e) {
            e.printStackTrace();
        }catch(final TransformerConfigurationException e){
            e.printStackTrace();
        }catch(final TransformerException e){
            e.printStackTrace();
        }catch(SAXException e){
            e.printStackTrace();
        }
    }
}