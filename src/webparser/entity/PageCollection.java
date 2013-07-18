
package webparser.entity;

import webparser.entity.Page;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 *
 * @author ilia
 */
public class PageCollection {

    private Deque pages;
    private HashSet urls;
    String s;

    public PageCollection() {
        urls = new LinkedHashSet();
        pages = new ArrayDeque();
    }

    public void add(Page o) {
        if (urls.add(o.getUrl())) {
            pages.add(o);
        }
    }

    public Page getFirst() {
        return (Page) pages.getFirst();
    }

    public Page removeFirst() {
        return (Page) pages.removeFirst();
    }

    public int getSize() {
        return pages.size();
    }

    public boolean contains(Page o) {
        return pages.contains(o);
    }

    @Override
    public String toString() {
        return pages.toString();
    }
}
