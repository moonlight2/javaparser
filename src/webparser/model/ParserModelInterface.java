/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webparser.model;

import java.util.ArrayList;
import webparser.common.ParserObserver;
import webparser.views.ParserView;

/**
 *
 * @author ilia
 */
public interface ParserModelInterface {

	public void start();
	
	public void stop();
	
	public void setURL(String url);

	public void setLinks(String page, ArrayList links);
	
	public void updateLinks(String page, ArrayList links);
	
	public void deleteLinks(String url);

	public void loadLinks(String url);

	public ArrayList getLinks();

    public void registerObserver(ParserObserver parserObserver);



}
