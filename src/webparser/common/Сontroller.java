package webparser.common;

import java.util.List;

public interface Сontroller {

    void run();
    
    void start(String url);

    void stop();

    void finish();

    void save(String url, List links);

    void delete(String url);

    void load(String url);
}
