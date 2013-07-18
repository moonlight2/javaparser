package webparser.model;

import webparser.entity.PageCollection;
import webparser.entity.Page;
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
    private List observers;
    private List links;
    private DBHelper db;

    public ParserModel() {
        observers = new ArrayList();
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

    /**
     * Returns a list of all domains that are stored in the database.
     */
    @Override
    public List getUrls() {
        links = db.getPages();
        return links;
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
     * Load links from the database
     * @param url - url page to which links will be loaded.
     */
    @Override
    public void getLinks(String url) {
        PageCollection links = db.getLinksByUrl(url);
        while (links.getSize() > 0) {
            Page p = links.removeFirst();
            notifyObservers(p);
        }
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

    public void notifyObservers(Page link) {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(link);
        }
    }

    public void notifyObservers(boolean finish) {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update((boolean) finish);
        }
    }

    public void go(String url) {

        PageCollection all = new PageCollection();
        PageCollection complete = new PageCollection();
        Transform transform = new Transform();

        Page p = new Page(url); // first page
        p.setLevel(1);
        all.add(p); // add the first page to array of pages

        while (all.getSize() > 0) {

            Page curPage = all.removeFirst();
            List links = getLinksFromPage(curPage.getUrl());

            if (null != links) {

                List<String> insLinks = transform.sortLinks(links, url);
                List<String> outLinks = transform.sortOutsideLinks(links, url);

                int OutLinksCount = outLinks.size(); // get the number of external links

                for (String str : insLinks) {
                    Page page = new Page(str);
                    int level = (int) curPage.getLevel() + 1;
                    page.setLevel(level);
                    all.add(page);
                }
                curPage.setLinks(OutLinksCount);
                complete.add(curPage);
                notifyObservers(curPage);
            }
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

    private List<String> getLinksFromPage(String page) {
        try {
            Soup soup = new Soup();
            return soup.getLinks(page);
        } catch (IOException e) {
            System.out.println("Page " + page + " not found");
        }
        return null;
    }
}