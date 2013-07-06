package webparser.common;

import java.util.ArrayList;
import java.util.List;

public interface Model {

    public void setURL(String url);

    public void setLinks(String page, List links);

    public void updateLinks(String page, List links);

    public void deleteLinks(String url);

    public void loadLinks(String url);

    public List getLinks();
}
