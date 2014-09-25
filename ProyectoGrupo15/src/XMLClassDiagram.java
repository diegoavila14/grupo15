import java.io.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;




public class XMLClassDiagram {

	
	File file;
	ClassDiagram cd;

	public XMLClassDiagram(File f)
	{
		this.file = f;
		cd = new ClassDiagram();
		Leer();
	}
	
	
	public void Leer(){
		
	
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			
			Element rootElement = doc.getDocumentElement();	
			
			NodeList classes = rootElement.getElementsByTagName("class");
			
			
						
			for (int i = 0; i < classes.getLength(); i++) // las clases
			{
				Node clase = classes.item(i);
				String id = clase.getAttributes().getNamedItem("id").getNodeValue();
				String nombre = clase.getAttributes().getNamedItem("name").getNodeValue();
				Clase c = new Clase(id,nombre);
				
			
				NodeList att = clase.getChildNodes();
				
				Node atri = getChildIndex(att,0);
				Node meth = getChildIndex(att,1);
						
				NodeList listatt = atri.getChildNodes();
				NodeList listmeth = meth.getChildNodes();
				
				for (int j = 0; j < getListLength(listatt); j++) // los atributos
				{
					Node currentItem = getChildIndex(listatt,j);
				    String type = currentItem.getAttributes().getNamedItem("type").getNodeValue();
				    String visibility = currentItem.getAttributes().getNamedItem("visibility").getNodeValue();
				    String name = currentItem.getAttributes().getNamedItem("name").getNodeValue();
				    Atributos a = new Atributos(visibility,type,name);
				    c.addAtt(a);
				    
				}
				

				for (int j = 0; j < getListLength(listmeth); j++) // los metodos
				{
					Node currentItem = getChildIndex(listmeth,j);
				    String type = currentItem.getAttributes().getNamedItem("type").getNodeValue();
				    String visibility = currentItem.getAttributes().getNamedItem("visibility").getNodeValue();
				    String name = currentItem.getAttributes().getNamedItem("name").getNodeValue();
				    Metodos m = new Metodos(name,type,visibility);
				    
				    NodeList pa = currentItem.getChildNodes();
				    
				    for (int k = 0; k < getListLength(pa); k++) // parametros 
					{
				    	Node currentItem2 = getChildIndex(pa,k);
				        String type2 = currentItem2.getAttributes().getNamedItem("type").getNodeValue();
						String name2 = currentItem2.getAttributes().getNamedItem("name").getNodeValue();
						Parametros p = new Parametros(name2,type2);
						m.addParam(p);
						
					
					}
				    
				    
				    c.addMethod(m);
	
				}
				
				cd.addClass(c);
				
			}

			
			
			
			
			// Conexiones, son identicas a las de los usercase
			Node connections = doc.getElementsByTagName("connections").item(0);
			NodeList cList = connections.getChildNodes();
			
			for (int i = 0; i < getListLength(cList); i++)
			{
				Node currentItem = getChildIndex(cList,i);
			    String type = currentItem.getAttributes().getNamedItem("type").getNodeValue();
			    String idFrom = currentItem.getAttributes().getNamedItem("from").getNodeValue();
			    String idTo = currentItem.getAttributes().getNamedItem("to").getNodeValue();
			    UseCase.Connection c = new UseCase.Connection(type,idFrom,idTo);
			    cd.addConnections(c);
			    System.out.println(type + " "+ idFrom + " " + idTo );
			}
			
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
		
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
	
	
	public ClassDiagram getDiagram()
	{
		return cd;
	}

}
