/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webparser.common;

import java.util.ArrayList;

/**
 *
 * @author ilia
 */
public interface Observable {
    
    	public void registerObserver(ParserObserver o);
	
	public void removeObserver(ParserObserver o);

}
