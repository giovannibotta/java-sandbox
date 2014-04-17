package net.giovannibotta.spoj.rpn;

import net.giovannibotta.spoj.SpojTest;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author giovanni
 * @since 4/17/14
 */
public class RPNTest extends SpojTest {
    @Test
    public void testBasic() throws Exception {
        setInput(in);

        Main.main(null);

        String o = getOutput();
        assertEquals(out, o);
        resetInputOutput();
        System.out.println(o);
    }

//    @Ignore
    @Test
    public void perfTest() throws Exception {
        int repeats = 1000000;
        long elapsed = 0;
        for (int i = 0; i < repeats; i++) {
            setInput(in);
            long start = System.nanoTime();
            Main.main(null);
            elapsed += (System.nanoTime() - start);
            getOutput(); // clear the baos
        }
        double ms = ((double) elapsed) / 1000000.0; // convert to ms
        resetInputOutput();
        System.out.format("Elapsed time: %.3f ms%n", ms);
    }

    private final String in = "4\n" +
            "(a+(b*c))\n" +
            "((a+b)*(z+x))\n" +
            "((a+t)*((b+(a+c))^(c+d)))\n" +
            "((a-g)^l/c^h*(l^f-g^y)^i^j)\n";
    private final String out = "abc*+\n" +
            "ab+zx+*\n" +
            "at+bac++cd+^*\n" +
            "ag-l^ch^/lf^gy^-i^j^*\n";
}
