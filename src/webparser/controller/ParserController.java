package webparser.controller;

import webparser.common.Сontroller;
import java.util.List;
import webparser.common.Model;
import webparser.common.View;
import webparser.validator.Validator;
import webparser.views.builder.ViewDirector;

public class ParserController implements Сontroller {

    Model model;
    View view;

    public ParserController(Model model) {
        this.model = model;
    }

    public ParserController(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void run() {
        if (null == view) {
            view = createViewInstance();
        }
        view.createView();
        view.createList(model.getUrls());
        view.disableStopButton();
        view.disableSaveButton();
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
        if (new ControllerValidator().checkUrl(url)) {
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
    public void save(String page, List links) {
        view.disableSaveButton();
        List pages = model.getUrls();
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
        model.getLinks(url);
    }

    private class ControllerValidator {

        public boolean checkUrl(String url) {
            if (url.equals("")) {
                view.confirmMessage("Error", "Enter site url!");
                return false;
            } else if (!Validator.checkUrl(url)) {
                view.confirmMessage("Error", "<html>Enter valid site url!<br>(http://www.site.com)");
                return false;
            } else {
                model.setURL(url);
            }
            return true;
        }
    }

    protected View createViewInstance() {
        try {
            view = new ViewDirector().createMainView(null, this, model);
        } catch (NoSuchFieldException ex) {
            ex.printStackTrace();
        }
        return view;
    }
}
