/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webparser.model;

import java.util.ArrayList;
import webparser.views.ParserObserver;

/**
 *
 * @author ilia
 */
public interface ParserModelInterface {

	public void start();
	
	public void stop();
	
	public void setURL(String url);
	
	public void registerObserver(ParserObserver o);
	
	public void removeObserver(ParserObserver o);

	public void setLinks(String page, ArrayList links);
	
	public void updateLinks(String page, ArrayList links);
	
	public void deleteLinks(String url);

	public void loadLinks(String url);

	public ArrayList getLinks();

}
