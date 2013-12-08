package webparser.views.builder;

import webparser.common.Model;
import webparser.common.View;
import webparser.common.Сontroller;
import webparser.views.ParserView;

public class ParserViewBuilder extends ViewBuilder {

    public static final int MODEL_FIELD = 1;
    public static final int CONTROLLER_FIELD = 2;


    @Override
    public void buildView() {
        view = new ParserView();
    }

    public void buldModel(Model m) {
        if (null != m) {
            view.setModel(m);
            view.attachToModel();
        }
    }

    public void buldController(Сontroller c) {
        if (null != c) {
            view.setController(c);
        }
    }

    @Override
    public View getView() throws NoSuchFieldException {

        int requiredElements = 0;

        if (null == view.getController()) {
            requiredElements += CONTROLLER_FIELD;
        }
        if (null == view.getModel()) {
            requiredElements += MODEL_FIELD;
        }

        if (requiredElements > 0) {
            throw new NoSuchFieldException("Please, fill all required fields");
        }

        return view;
    }
}
