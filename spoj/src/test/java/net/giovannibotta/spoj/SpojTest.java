package net.giovannibotta.spoj;

import java.io.*;

/**
 * @author giovanni
 * @since 4/17/14
 */
public class SpojTest {
    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private static InputStream originalInput = System.in;
    private static PrintStream originalOutput = System.out;

    public void resetInputOutput() {
        resetInput();
        resetOutput();
    }

    public void resetOutput(){
        System.setOut(originalOutput);
    }

    public void resetInput(){
        System.setIn(originalInput);
    }

    public void setInput(String str) {
        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
        System.setIn(bais);
        System.setOut(new PrintStream(baos));
    }

    public String getOutput() {
        ByteArrayOutputStream oldBaos = baos;
        baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        try {
            oldBaos.flush();
            oldBaos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return oldBaos.toString();
    }
}
