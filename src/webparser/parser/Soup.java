
package webparser.parser;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * The class is engaged in parsing web pages
 */
public class Soup {

    /**
     * @param url - url page on which to find the link.
     * @return linksArray - an array of references received from the page.
     * @throws IOException - exception is thrown if the page was not found.
     */
    public ArrayList<String> getLinks(String url) throws IOException {
        ArrayList<String> linksArray = new ArrayList<String>();
        Document doc = Jsoup.connect(url).get();
        Elements links = (Elements) doc.select("a[href]");
        for (org.jsoup.nodes.Element link : links) {
            linksArray.add(link.attr("abs:href"));
        }
        return linksArray;
    }
}