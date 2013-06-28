package webparser.common;

import java.util.ArrayList;

public interface Сontroller {

    void start(String url);

    void stop();

    void finish();

    void save(String url, ArrayList linksSize);

    void delete(String url);

    void load(String url);
}
