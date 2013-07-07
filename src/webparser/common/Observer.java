
package webparser.common;

import java.util.List;
import webparser.model.Page;

/**
 * This interface must implement a class 
 * that is going to become an observer
 */
public interface Observer {

	public void createView();
        
	public void update(Page link);
        
	public void update(List link);
	
	public void update(boolean finish);
}