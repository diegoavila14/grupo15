package pEventsUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class pEvent {
	 private List<pEventListener> listeners = new ArrayList<pEventListener>();
	  public synchronized void addEventListener(pEventListener listener)  {
	    listeners.add(listener);
	  }
	  public synchronized void removeEventListener(pEventListener listener)   {
	    listeners.remove(listener);
	  }
	 
	  // call this method whenever you want to notify
	  //the event listeners of the particular event
	  public synchronized void fireEvent(Object...params) {
		pEventObject event = new pEventObject(this);
	    Iterator<pEventListener> i = listeners.iterator();
	    while(i.hasNext())  {
	      i.next().handleEvent(event,params);
	    }
	  }

}
