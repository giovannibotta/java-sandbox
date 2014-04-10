package giovannibotta.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author giovanni
 * @since 4/10/14
 */
public class RegexExamples {
    @Test
    public void test1() {
        String s = "1:[1,3,2],2:[1],3:[4,3],4:[4,3], 5 : [123, 53, 1231], 123 : [ 54 , 98 , 434 ]";
        Pattern p = Pattern.compile("[\\d]*\\s*:\\s*\\[((\\d*)(\\s*|\\s*,\\s*))*\\]");
        Matcher matcher = p.matcher(s);

        while (matcher.find())
            System.out.println(matcher.group());
    }
}
