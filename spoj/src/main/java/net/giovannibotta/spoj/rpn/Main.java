package net.giovannibotta.spoj.rpn;

/**
 * @author giovanni
 * @since 4/17/14
 */
public class Main {
    public static void main(String[] args) throws Exception {
        final char[] stack = new char[7];
        final char[] buffer = new char[100];
        int stackPos = -1;
        int bufferSize = 0;

        final java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        int n = Integer.valueOf(r.readLine());
        for (int j = 0; j < n; j++) {
            String s = r.readLine();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c >= 'a' && c <= 'z') buffer[bufferSize++] = c;
                else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                    char onStack = 0;
                    if (stackPos >= 0)
                        onStack = stack[stackPos];
                    while (stackPos >= 0
                            && (onStack == '+' || onStack == '-' || onStack == '*' || onStack == '/' || onStack == '^')
                            && ((onStack == '+' || onStack == '-') ? 0 : (onStack == '*' || onStack == '/') ? 1 : 2)
                            >= ((c == '+' || c == '-') ? 0 : (c == '*' || c == '/') ? 1 : 2)) {
                        buffer[bufferSize++] = stack[stackPos--];
                        if (stackPos >= 0) onStack = stack[stackPos];
                    }
                    stack[++stackPos] = c;
                } else if (c == '(') {
                    stack[++stackPos] = c;
                } else if (c == ')') {
                    char t;
                    while ((t = stack[stackPos--]) != '(') {
                        buffer[bufferSize++] = t;
                    }
                }
            }
            while (stackPos >= 0) {
                buffer[bufferSize++] = stack[stackPos--];
            }
            buffer[bufferSize++] = '\n';
        }
        System.out.print(new String(buffer, 0, bufferSize));
    }
}
