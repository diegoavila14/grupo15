package UseCase;

public class Connection {
	
	String type;
	String idFrom;
	String idTo;
	
	public Connection(String Type, String IdFrom, String IdTo)
	{
		this.type = Type;
		this.idFrom = IdFrom;
		this.idTo = IdTo;
		
	}
	
	public String getType(){
		
		return type;
	}
	public String getidFrom(){
		
		return idFrom;
	}
	public String getidTo(){
		
		return idTo;
	}
}
