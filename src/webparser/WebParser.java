
package webparser;


import webparser.controller.ControllerInterface;
import webparser.controller.ParserController;
import webparser.model.ParserModel;
import webparser.model.ParserModelInterface;

/**
 * The program for parsing web pages. 
 * Determines the number of pages on the site, 
 * the number of external links of each page 
 * and the number of inbound links to each page.
 * @author Osipov Ilia
 * @email iosipov83@gmail.com
 * @version 1.0
 */
public class WebParser {

	public static void main(String[] args){
		ParserModelInterface model     = new ParserModel();
		ControllerInterface controller = new ParserController(model);
	}
}