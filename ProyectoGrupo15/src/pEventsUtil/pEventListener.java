package pEventsUtil;
import java.util.EventObject;


public interface pEventListener {
	
        public void handleEvent(EventObject e, Object...params);
	
}
