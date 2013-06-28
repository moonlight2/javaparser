
package webparser.common;

import java.util.ArrayList;

/**
 * This interface must implement a class 
 * that is going to become an observer
 */
public interface Observer {

	public void createView();
	
	public void update(ArrayList link);
	
	public void update(boolean finish);
}