
package webparser.common;

import java.util.List;


public abstract class View {

    protected Model model;
    protected Сontroller controller;

    public void setController(Сontroller c) {
        this.controller = c;
    }

    public void setModel(Model m) {
        this.model = m;
    }

    public Model getModel() {
        return model;
    }

    public Сontroller getController() {
        return controller;
    }

    public void createView() {
    }

    public void createList(List arrayList) {
    }

    public void disableSaveButton() {
    }

    public void disableStopButton() {
    }

    public void enableStartButton() {
    }

    public void enableSaveButton() {
    }

    public void enableLoadButton() {
    }

    public void enableDeleteButton() {
    }

    public void confirmMessage(String finish, String parsing_complete) {
    }

    public void clearLinksArray() {
    }

    public void clearTable() {
    }

    public void disableStartButton() {
    }

    public void disableLoadButton() {
    }

    public void disableDeleteButton() {
    }

    public void enableStopButton() {
    }

    public void addToList(String page) {
    }

    public void removeFromList(String url) {
    }
    
    public void attachToModel(){}
}
