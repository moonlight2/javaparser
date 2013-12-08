/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webparser.parser;

import java.util.LinkedList;
import java.util.List;

/**
 * Class for sorting and changing links.
 */
public class Transform {

    public List sortLinks(List<String> arrayOfLinks, String url) {
        
        List<String> newArray = new LinkedList<String>();

        for (String link : arrayOfLinks) {
            if ((link.indexOf(url) == 0)) {
                if (newArray.contains(link) == false) {
                    newArray.add(link);
                }
            }
        }
        return newArray;
    }

    public List sortOutsideLinks(List<String> arrayOfLinks, String url) {
        
        List<String> newArray = new LinkedList<String>();

        for (String link : arrayOfLinks) {
            if ((link.indexOf(url) == -1)) {

                if (newArray.contains(link) == false) {
                    newArray.add(link);
                }
            }
        }
        return newArray;
    }

    /**
     * Change basic url from somesite.com to http://www.somesite.com
     *
     * @param page - url page to be changed.
     */
    public String changeURL(String page) {
        char[] ch = page.toCharArray();
        char lastOne = ch[ch.length - 1];
        String path[] = page.split("//");
        String st = Character.toString(lastOne);

        if (st.equals("/") == true) {
            page = page.substring(0, (ch.length - 1));
        }
        if ((page.indexOf("www") == 0) == true) {
            page = "http://" + page;
        }
        return page;
    }
}