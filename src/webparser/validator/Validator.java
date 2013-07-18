
package webparser.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validator {

    public static boolean checkUrl(String url) {
        Pattern p = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        Matcher m = p.matcher(url);
        boolean b = m.matches();
        return b;
    }
}