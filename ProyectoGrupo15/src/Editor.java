
public class Editor {

	public static void main(String[] args) {
		
<<<<<<< HEAD
		boolean correcto = false;
		File archivo = null;
		Scanner teclado = null;
		while(correcto == false){ // Para checkear que sea un xml
		
		teclado = new Scanner(System.in);
		JFileChooser fc = new JFileChooser();
		int respuesta = fc.showOpenDialog(null);
		archivo = fc.getSelectedFile();
		if(getExtension(archivo).equals("xml")){
			correcto = true;
		}
<<<<<<< HEAD
<<<<<<< HEAD
			}
=======
=======
		
		
		}
>>>>>>> parent of 175c994... EditorV1.2
=======
		
		
		}
>>>>>>> parent of 175c994... EditorV1.2
		
>>>>>>> parent of bccf055... EditorV1.1
		
		
<<<<<<< HEAD
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			
			Document doc = dBuilder.parse(archivo);
			
			System.out.println(doc.getDocumentElement().getNodeName());
			
			DiagramaDeClase dc = new DiagramaDeClase();
<<<<<<< HEAD
=======
			
>>>>>>> parent of 175c994... EditorV1.2
			
			dc.FrameDiagClase = new JFrame("Diagrama de Clase");
		/*	dc.FrameDiagClase.addWindowListener(new WindowAdapter() {
				 public void windowClosing(WindowEvent e)
				 {System.exit(0);} });  */   // es para que termine el programa cuando se cierre, puede servir mas adelante
			dc.FrameDiagClase.getContentPane().add(dc.myPanel);
			dc.FrameDiagClase.pack(); 
			dc.FrameDiagClase.setVisible(true);
			
<<<<<<< HEAD
			dc.FrameDiagClase = new JFrame("Diagrama de Clase");
		/*	dc.FrameDiagClase.addWindowListener(new WindowAdapter() {
				 public void windowClosing(WindowEvent e)
				 {System.exit(0);} });  */   // es para que termine el programa cuando se cierre, puede servir mas adelante
			dc.FrameDiagClase.getContentPane().add(dc.myPanel);
			dc.FrameDiagClase.pack(); 
			dc.FrameDiagClase.setVisible(true);
			
=======
>>>>>>> parent of 175c994... EditorV1.2
			
		} 
		
		catch (IOException | ParserConfigurationException | SAXException e) {

			e.printStackTrace();
		}
		
		teclado.close();
=======
>>>>>>> parent of bccf055... EditorV1.1
	}

}
