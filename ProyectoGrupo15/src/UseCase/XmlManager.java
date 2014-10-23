package UseCase;
import java.io.*;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XmlManager 
{
	File file;
	public Diagram diagram;
	
	public XmlManager(File f)
	{
		this.file = f;		
		readFile();
	}
	
	public void readFile()
	{
		
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
			Node root = doc.getElementsByTagName("UseCaseDiagram").item(0);
			String nameD = root.getAttributes().getNamedItem("name").getNodeValue();
			System.out.println("Diagrama: "+nameD);
			diagram = new Diagram(nameD);
			
			Node actors = doc.getElementsByTagName("actors").item(0);
			NodeList actorList = actors.getChildNodes();
		
			for (int i = 0; i < getListLength(actorList); i++)
			{
				Node currentItem = getChildIndex(actorList,i);
			    String type = currentItem.getAttributes().getNamedItem("type").getNodeValue();
			    String id = currentItem.getAttributes().getNamedItem("id").getNodeValue();
			    String name = currentItem.getAttributes().getNamedItem("name").getNodeValue();
			    UseCase.Actor a = new UseCase.Actor(type,id,name);
			    diagram.addActor(a);
			    System.out.println(type + " "+ id + " " + name );
			}
			System.out.println("Finish actors");
			
			//Para obtener User Cases
			Node ucases = doc.getElementsByTagName("usecases").item(0);
			NodeList ucList = ucases.getChildNodes();
			
			for (int i = 0; i < getListLength(ucList); i++)
			{
				Node currentItem = getChildIndex(ucList,i);
			    String id = currentItem.getAttributes().getNamedItem("id").getNodeValue();
			    String name = currentItem.getAttributes().getNamedItem("name").getNodeValue();
			    UseCase.UserCase uc = new UseCase.UserCase(id, name);
			    diagram.addUserCase(uc);
			    System.out.println(id + " " + name );
			}
			System.out.println("Finish use cases");
			
			//Para agarrar conecctions
			Node connections = doc.getElementsByTagName("connections").item(0);
			NodeList cList = connections.getChildNodes();
			
			for (int i = 0; i < getListLength(cList); i++)
			{
				Node currentItem = getChildIndex(cList,i);
			    String type = currentItem.getAttributes().getNamedItem("type").getNodeValue();
			    String idFrom = currentItem.getAttributes().getNamedItem("from").getNodeValue();
			    String idTo = currentItem.getAttributes().getNamedItem("to").getNodeValue();
			    UseCase.Connection c = new UseCase.Connection(type,idFrom,idTo);
			    diagram.addConnection(c);
			    System.out.println(type + " "+ idFrom + " " + idTo );
			}
			System.out.println("Finish connections");
			System.out.println("Finish all");
			
			
		} 
		
		catch (IOException | ParserConfigurationException | SAXException e) 
		{
			String mensajeError = "Error en el formato del archivo XML" + "\nInténtelo denuevo con otro archivo";
			JOptionPane.showMessageDialog(null,mensajeError,"Parsing Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public static int getListLength(NodeList nl)
	{
		int r = nl.getLength();
		return (r-1)/2;
	}
	
	public static Node getChildIndex(NodeList nl,int index)
	{
		return nl.item((index*2)+1);
	}
	
	public Diagram getDiagram()
	{
		return diagram;
	}

}
