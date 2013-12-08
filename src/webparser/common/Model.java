package webparser.common;

import java.util.List;

public abstract class Model implements Movable {

    public abstract void setURL(String url);

    public abstract List getUrls();

    public abstract void setLinks(String page, List<List> links);

    public abstract void updateLinks(String page, List<List> links);

    public abstract void deleteLinks(String url);

    public abstract void getLinks(String url);
    
    public abstract Observable getManager();
}
