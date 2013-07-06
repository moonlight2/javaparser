
package webparser.model;

import webparser.common.Model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import webparser.common.Observable;
import webparser.db.DBHelper;
import webparser.parser.Soup;
import webparser.parser.Transform;
import webparser.common.Observer;


public class ParserModel implements Model, Observable {

    private String url;
    private Thread myThread;
    private ArrayList observers;
    private ArrayList complete;
    private List links;
    private DBHelper db;

    public ParserModel() {
        observers = new ArrayList();
        complete = new ArrayList();
        try {
            db = new DBHelper();
        } catch (Exception e) {
            e.printStackTrace();
        }
        links = db.getPages();
    }

    public void start() {
        myThread = new Thread(new RunParser());
        myThread.start();
    }

    public void stop() {
        myThread.stop();
    }

    @Override
    public void setURL(String url) {
        this.url = url;
    }

    @Override
    public void setLinks(String page, List links) {
        db.insertLinks(page, links);
    }

    @Override
    public void updateLinks(String page, List links) {
        db.updateLinks(page, links);
    }

    @Override
    public void deleteLinks(String url) {
        db.deleteLinks(url);
    }

    /**
     * Returns a list of internal links that are in the database.
     *
     * @param url - url page to which links will be loaded.
     */
    @Override
    public void loadLinks(String url) {
        List links = db.getLinksByUrl(url);
        for (int x = 0; x < links.size(); x++) {
            notifyObservers((ArrayList) links.get(x));
        }
    }

    /**
     * Returns a list of all domains that are stored in the database.
     */
    @Override
    public List getLinks() {
        links = db.getPages();
        return links;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    /**
     * Notifies observers when we receive full information about about one of
     * the pages.
     *
     * @param link - An array that contains the name,
     * @param link quantity of inbound links and the level of nesting
     */
    @Override
    public void notifyObservers(List link) {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(link);
        }
    }

    /**
     * Notifies observers when parsing site completed.
     *
     * @param finish - Returns true at the close.
     */
    public void notifyObservers(boolean finish) {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update((boolean) finish);
        }
    }

    /**
     * Method sends a request to the url and takes all the links that are on it.
     * Links are sorted into two arrays: with all the links and all completed
     * links. Method sorts external and internal links on every page of the
     * completed and notifies observers on the page. Method is executed as long
     * as the number of completed pages will not match the number of
     *
     * @param url - url page to which links will be loaded.
     */
    public void go(String url) {
        Transform transform = new Transform();
        List<ArrayList> all = new ArrayList<ArrayList>();
        List<ArrayList> complete = new ArrayList<ArrayList>();
        List<String> allStrings = new ArrayList<String>();
        List<String> links = new ArrayList<String>();
        List<String> insideLinks = new ArrayList<String>();
        List<String> outLinks = new ArrayList<String>();
        ArrayList endLinksArray = new ArrayList();

        ArrayList first = new ArrayList();
        first.add(url);
        first.add(1);
        all.add(first); // first, add the title page in the list of pages.
        allStrings.add(url);

        int count = 0;
        while (all.size() != complete.size()) {
            ArrayList currentLinkArray = all.get(count);
            String currentLink = (String) currentLinkArray.get(0);

            links = getLinks(currentLink);
            insideLinks = transform.sortLinks(links, url);
            outLinks = transform.sortOutsideLinks(links, url);

            int numberOfOutsideLinks = outLinks.size();

            for (String str : insideLinks) {
                if (allStrings.contains(str) == false) {
                    allStrings.add(str);

                    /**
                     * Array, which will contain all information about the
                     * current page
                     */
                    ArrayList current = new ArrayList();
                    current.add(str);

                    /**
                     * Nesting level will be one greater than the level of
                     * nesting of the page from which we get the current
                     */
                    int level = (int) currentLinkArray.get(1) + 1;
                    current.add(level);
                    all.add(current);
                }
            }
            count++;
            currentLinkArray.add(numberOfOutsideLinks);
            complete.add(currentLinkArray);
            notifyObservers(currentLinkArray);
        }
        notifyObservers(true);
    }

    private class RunParser implements Runnable {

        @Override
        public void run() {
            try {
                go(url);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Get all links from url
     *
     * @param page - url page to which links will be loaded.
     * @return - array with all the links to this page.
     */
    private List<String> getLinks(String page) {
        try {
            Soup soup = new Soup();
            return soup.getLinks(page);
        } catch (IOException e) {
            System.out.println("Page not found");
        }
        return null;
    }
}