
package webparser.common;

import java.util.List;

public interface Observable {
    
    	public void registerObserver(Observer o);
	
	public void removeObserver(Observer o);
        
        public void notifyObservers(List link);
}
