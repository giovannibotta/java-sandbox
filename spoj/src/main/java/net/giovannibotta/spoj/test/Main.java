package net.giovannibotta.spoj.test;

/**
 * @author giovanni
 * @since 4/11/14
 */
public class Main {
    public static void main(String[] args) throws java.lang.Exception {
        java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        String s;
        while (!(s = r.readLine()).startsWith("42")) System.out.println(s);
    }
}
