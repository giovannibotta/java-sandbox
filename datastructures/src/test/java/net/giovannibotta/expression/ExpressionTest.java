package net.giovannibotta.expression;

import org.junit.Test;

import static net.giovannibotta.expression.Expression.*;

/**
 * @author giovanni
 * @since 4/11/14
 */
public class ExpressionTest {
    @Test
    public void test() {
        Expression exp = not(
                or(
                        and(eq("x", 3), neq("y", 9)),
                        or(and(eq("z", 2), eq("x", 10)),
                                predicate("f", "w"))
                )
        );

        System.out.println(exp);
    }
}
