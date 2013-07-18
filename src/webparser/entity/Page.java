
package webparser.entity;

/**
 *
 * Entity Page
 */
public class Page {

    private String url;
    private int level;
    private int links;

    public Page(String url) {
        this.url = url;
    }

    public boolean equals(Page o) {
        return getUrl().equals(o.getUrl());
    }

    public String getUrl() {
        return url;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public int getLinks() {
        return links;
    }

    public void setLinks(int count) {
        links = count;
    }

    @Override
    public String toString() {
        return "Page " + getUrl() + " level " + getLevel();
    }
}
