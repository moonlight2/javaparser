package webparser.common;

import webparser.entity.Page;

public interface Observable {

    public abstract void registerObserver(Observer o);

    public abstract void removeObserver(Observer o);

    public abstract void notifyObservers(Page p);
    
    public abstract void notifyObservers(boolean finish);
}
