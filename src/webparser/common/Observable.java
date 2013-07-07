
package webparser.common;

import java.util.List;
import webparser.model.Page;

public interface Observable {
    
    	public void registerObserver(Observer o);
	
	public void removeObserver(Observer o);
        
        public void notifyObservers(List link);
        
        public void notifyObservers(Page link);
}
