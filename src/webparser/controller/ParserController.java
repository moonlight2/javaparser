/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webparser.controller;



import java.util.ArrayList;
import webparser.classes.CheckData;
import webparser.model.ParserModelInterface;
import webparser.views.ParserView;

public class ParserController implements ControllerInterface {

    ParserModelInterface model;
    ParserView view;
    CheckData check;

    public ParserController(ParserModelInterface model) {
        check = new CheckData();
        this.model = model;
        view = new ParserView(this, model);
        view.createView();
        view.createList(model.getLinks());
        view.disableStopButton();
        view.disableSaveButton();
        view.enableStartButton();
    }

    @Override
    public void stop() {
        model.stop();
        view.disableStopButton();
        view.enableStartButton();
        view.enableSaveButton();
        view.enableLoadButton();
        view.enableDeleteButton();
    }

    @Override
    public void finish() {
        view.confirmMessage("Finish", "Parsing complete!");
        view.enableStartButton();
        view.disableStopButton();
        view.enableSaveButton();
        view.enableLoadButton();
        view.enableDeleteButton();
        model.stop();
    }

    @Override
    public void start(String url) {
        if (checkUrl(url)) {
            view.clearLinksArray();
            view.clearTable();
            view.disableSaveButton();
            view.disableStartButton();
            view.disableLoadButton();
            view.disableDeleteButton();
            view.enableStopButton();
            model.start();
        }
    }

    @Override
    public void save(String page, ArrayList links) {
        view.disableSaveButton();
        ArrayList pages = model.getLinks();
        for (int x = 0; x < pages.size(); x++) {
            System.out.println(pages.get(x));
        }
        if (pages.contains(page) == true) {
            model.updateLinks(page, links);
            view.confirmMessage("Save", page + " has been update!");
        } else {
            model.setLinks(page, links);
            view.confirmMessage("Save", page + " has been saved!");
            view.addToList(page);
        }
    }

    @Override
    public void delete(String url) {
        model.deleteLinks(url);
        view.confirmMessage("Delete", "<html>" + url + "<br>has been removed from the list!");
        view.removeFromList(url);
    }

    @Override
    public void load(String url) {
        view.disableSaveButton();
        view.clearLinksArray();
        view.clearTable();
        model.loadLinks(url);
    }

    public void errorMessage() {
        view.confirmMessage("Error", "Select an url from the list!");
    }

    private boolean checkUrl(String url) {
        if (url.equals("")) {
            view.confirmMessage("Error", "Enter site url!");
            return false;
        } else if (!check.checkUrl(url)) {
            view.confirmMessage("Error", "<html>Enter valid site url!<br>(http://www.site.com)");
            return false;
        } else {
            model.setURL(url);
        }
        return true;
    }

}
