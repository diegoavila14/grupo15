package UseCase;

public class Connection {
	
	String type; // basic=0 extend=1 include=2 isa=3
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
