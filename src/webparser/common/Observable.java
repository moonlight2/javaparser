
package webparser.common;

import java.util.ArrayList;

public interface Observable {
    
    	public void registerObserver(Observer o);
	
	public void removeObserver(Observer o);
        
        public void notifyObservers(ArrayList link);
}
