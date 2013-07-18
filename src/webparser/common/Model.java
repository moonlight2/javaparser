package webparser.common;

import java.util.List;

public interface Model {

    public void setURL(String url);
    
    public List getUrls();

    public void setLinks(String page, List links);

    public void updateLinks(String page, List links);

    public void deleteLinks(String url);

    public void getLinks(String url);
}
