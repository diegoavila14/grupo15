package TestUserCase;

import static org.junit.Assert.*;

import java.awt.Point;

import UseCaseEditor.ConnectLine;

import org.junit.Test;

public class ConnectLineTest {

	@Test
	public void test() 
	{
		Point p1 = new Point(1,2);
		Point p2 = new Point(3,-4);
		ConnectLine cl = new ConnectLine(p1,p2,ConnectLine.LINE_TYPE_RECT_2BREAK, 
					ConnectLine.LINE_START_HORIZONTAL, ConnectLine.LINE_ARROW_EXTEND);
		boolean b1 = cl.getP1().equals(p1);
		boolean b2 = cl.getP2().equals(p2);
		boolean b3 = cl.getLineStart() == 0;
		boolean b4 = cl.getLineType() == 2;
		boolean b5 = cl.getLineArrow() == 1;
		
		assertTrue(b1 & b2 & b3 & b4 & b5);
	}

}
