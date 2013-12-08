package webparser.views.builder;

import webparser.common.Model;
import webparser.common.View;
import webparser.common.Сontroller;

public class ViewDirector {

    public View createMainView(
            ParserViewBuilder vb,
            Сontroller c,
            Model m) throws NoSuchFieldException {

        if (null == vb) {
            vb = new ParserViewBuilder();
        }
        vb.buildView();
        vb.buldController(c);
        vb.buldModel(m);

        return vb.getView();
    }
}
