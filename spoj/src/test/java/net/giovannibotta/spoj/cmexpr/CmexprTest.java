package net.giovannibotta.spoj.cmexpr;

import net.giovannibotta.spoj.SpojTest;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author giovanni
 * @since 4/21/14
 */
public class CmexprTest extends SpojTest {

    @Test
    public void test() throws IOException {
        setInput(IN);
        Main.main(null);
        String o = getOutput();
        System.out.println(o);
        assertEquals(OUT, o);
    }

    private final static String IN = "8\n" +
            "(a+(b*c))\n" +
            "((a+b)*c)\n" +
            "(a*(b*c))\n" +
            "(a*(b/c)*d)\n" +
            "((a/(b/c))/d)\n" +
            "((x))\n" +
            "(a+b)-(c-d)-(e/f)\n" +
            "(a+b)+(c-d)-(e+f)\n";

    private final static String OUT = "a+b*c\n" +
            "(a+b)*c\n" +
            "a*b*c\n" +
            "a*b/c*d\n" +
            "a/(b/c)/d\n" +
            "x\n" +
            "a+b-(c-d)-e/f\n" +
            "a+b+c-d-(e+f)\n";
}
