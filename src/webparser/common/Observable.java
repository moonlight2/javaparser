package webparser.common;

import webparser.entity.Page;

public interface Observable {

    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyObservers(Page link);
}
