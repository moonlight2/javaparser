package webparser;

import webparser.common.Model;
import webparser.common.Сontroller;
import webparser.controller.ParserController;
import webparser.model.ParserModel;

/**
 * The program is for parsing web pages. Determines the number of pages on the
 * site, the number of external links of each page and the number of inbound
 * links to each page.
 *
 * @author Osipov Ilia
 * @email iosipov83@gmail.com
 * @version 1.0
 */
public class WebParser {

    public static void main(String[] args) {
        Model model = new ParserModel();
        Сontroller controller = new ParserController(model);
        controller.run();
    }
}