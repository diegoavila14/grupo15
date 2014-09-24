
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
			}
=======
		
>>>>>>> parent of bccf055... EditorV1.1
		
		
<<<<<<< HEAD
		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(archivo);
			
			System.out.println(doc.getDocumentElement().getNodeName());
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						DiagClase window = new DiagClase();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			
						
			
		} 
		
		catch (IOException | ParserConfigurationException | SAXException e) {

			e.printStackTrace();
		}
		
		teclado.close();
=======
>>>>>>> parent of bccf055... EditorV1.1
	}

}
