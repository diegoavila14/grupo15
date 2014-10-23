package UseCase;

public class UserCase {
	
	String id;
	String name;
	
	public UserCase(String Id, String Name)
	{
		this.id = Id;
		this.name = Name;
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
