package webparser.common;

import webparser.entity.Page;

/**
 * This interface must implement a class that is going to become an observer
 */
public interface Observer {

    public void createView();

    public void update(Page p);

    public void update(boolean finish);
}