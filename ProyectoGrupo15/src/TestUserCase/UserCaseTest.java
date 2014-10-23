package TestUserCase;

import static org.junit.Assert.*;

import org.junit.Test;

import UseCase.UserCase;

public class UserCaseTest {

	@Test
	public void test() 
	{
		UserCase uc = new UserCase("id","name");
		boolean b1 = uc.getID().equals("id");
		boolean b2 = uc.getName().equals("name");
		assertTrue("Test de creación de User Case", b1 && b2);
	}

}
