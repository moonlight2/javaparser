/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webparser.controller;

import java.util.ArrayList;

/**
 *
 * @author ilia
 */
public interface ControllerInterface {
    
    	void start(String url);
	
	void stop();
	
	void finish();
	
	void save(String url, ArrayList linksSize);
	
	void delete(String url);
	
	void load(String url);

	void errorMessage();
    
}
