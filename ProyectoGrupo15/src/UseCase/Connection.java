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

}
