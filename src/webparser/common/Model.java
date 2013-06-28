package webparser.common;

import java.util.ArrayList;

public interface Model {

    public void setURL(String url);

    public void setLinks(String page, ArrayList links);

    public void updateLinks(String page, ArrayList links);

    public void deleteLinks(String url);

    public void loadLinks(String url);

    public ArrayList getLinks();
}
