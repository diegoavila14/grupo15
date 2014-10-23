package UseCase;

public class Actor {
	
	String type;
	String id;
	String name;
	
	public Actor(String Type, String Id, String Name)
	{
		this.type =	Type;
		this.id = Id;
		this.name = Name;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public String getID()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}

}
