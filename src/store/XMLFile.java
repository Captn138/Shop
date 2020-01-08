package store;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

import org.w3c.dom.*;
import java.io.IOException;
import java.io.File;
import java.util.LinkedList;

/** XMLDemo classe qui demontre comment lire et ecrire un fichier XML
 * La classe lit un XML qui contient plusieurs elements <pre>{@code<client>}</pre> (mais n'en ecrit qu'un seul):
 *  <pre>
 * {@code
 *   <?xml version="1.0" encoding="UTF-8" standalone="no"?>
 *      <clients>
 *		   <client>
 *		      <UUID>4f392743-c9ba-4230-9b93-a1c79c0a13c4</UUID>
 *			  <firstName>Brad</firstName>
 *			  <lastName>Pitt</lastName>
 *			  <address>150 Broadway St., New York</address>
 *			</client>
 *		</clients>
 * }
 * </pre>
 *	@author Felicia Ionascu
*/		
public class XMLFile {
	private TransformerFactory transformerFactory;
	private Transformer transformer;
	private DocumentBuilderFactory documentFactory;
	private DocumentBuilder documentBuilder;
	private String filename;
	private Element root;

	public XMLFile(String filename){
		this.filename = filename;
		try {
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
			documentFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentFactory.newDocumentBuilder();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
	}

	/**
	 * La methode qui transforme le document en memoire en fichier XML sur le disque
	 * dur
	 * 
	 * @param document le document à transformer
	 * @param filePath le chemin (répértoire et nom) du fichier XML à créer
	 */
	public void createXMLFile(Document document) {
		try {
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(filename));

			// transform the DOM Object to an XML File
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(domSource, streamResult);

		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
		System.out.println("Done creating XML File");
	}

	/**
	 * La methode qui crée le document en memoire
	 * 
	 * @return le document créé
	 */
	public Document createXMLDocument() {
		return documentBuilder.newDocument();
	}

	/**
	 * La methode qui lit un fichier XML et le transforme en liste de noeuds en
	 * mémoire
	 * 
	 * @param filePath le chemin (répértoire et nom) du fichier XML à lire
	 * @return la liste des noeuds lus
	 */
	public NodeList parseXMLFile() {
		NodeList elementNodes = null;
		try {
			Document document = documentBuilder.parse(new File(filename));
			root = document.getDocumentElement();

			elementNodes = root.getChildNodes();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return elementNodes;
	}

	/*
	  public void readXML(){ NodeList nodes = this.parseXMLFile(XML_INPUT_FILE); if
	  (nodes == null) return;
	  
	  for (int i = 0; i<nodes.getLength(); i++) { if (nodes.item(i).getNodeType()
	  == Node.ELEMENT_NODE) { Element currentElement = (Element) nodes.item(i); if
	  (currentElement.getNodeName().equals("client")) { try { String firstName =
	  currentElement.getElementsByTagName("firstName").item(0).getTextContent();
	  String lastName =
	  currentElement.getElementsByTagName("lastName").item(0).getTextContent();
	  String address =
	  currentElement.getElementsByTagName("address").item(0).getTextContent();
	  String uuid = null; UUID uniqueID = null; try { uuid =
	  currentElement.getElementsByTagName("UUID").item(0).getTextContent(); } catch
	  (Exception ex) { System.out.println("Empty UUID, will create a new one"); }
	  if ((uuid == null) || (uuid.isEmpty())) uniqueID = UUID.randomUUID(); else
	  uniqueID = UUID.fromString(uuid); //verify that I read everything correctly:
	  System.out.println(firstName + " " + lastName + " " + address + " " +
	  uniqueID.toString()); } catch (Exception ex) {
	  System.out.println("Something is wrong with the XML client element"); } } } }
	  }
	 */

public void updatexml(NodeList nodes){
	for (int i = 0; i < nodes.getLength(); i++) {
		if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
			root.appendChild(nodes.item(i));
		}
	}
}

	/**
	 * Methode pour démontrer l'écriture d'un fichier XML avec un seul élément
	 */
	public void writeXML(String elementName, LinkedList<String> childs) {
		Document document = this.createXMLDocument();
		if (document == null)
			return;

		root = document.createElement("root");
		document.appendChild(root);

		Element newelement = document.createElement(elementName);
		for (int i = 0; i < childs.size(); i++) {
			String[] string = childs.get(i).split("#");
			Element element = document.createElement(string[0]);
			element.appendChild(document.createTextNode(string[1]));
			newelement.appendChild(element);
		}
		root.appendChild(newelement);

		this.createXMLFile(document);
	}

}