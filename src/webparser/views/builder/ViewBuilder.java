
package webparser.views.builder;

import webparser.common.View;


public abstract class ViewBuilder {
    
    protected View view;
    
    public abstract void buildView();
    
    public abstract View getView() throws NoSuchFieldException;
    
}
