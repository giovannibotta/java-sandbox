package net.giovannibotta.antlr.example.bool2;

import com.google.common.collect.ImmutableMap;
import net.giovannibotta.antlr.example.bool2.visitor.BooleanExpressionEval2;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author giovanni
 * @since 4/15/14
 */
public class BoolEval2Test {
    @Test
    public void test() {
        ANTLRInputStream in = new ANTLRInputStream("~ (a+20/2) <= (3+b)*2 | x"); // no way to have parentheses in there!
        BooleanExpressions2Lexer lexer = new BooleanExpressions2Lexer(in);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        BooleanExpressions2Parser parser = new BooleanExpressions2Parser(tokenStream);
        ParserRuleContext tree = parser.start();

        BooleanExpressionEval2 visitor = new BooleanExpressionEval2(
                ImmutableMap.of("x", false), ImmutableMap.of("a", 11, "b", 1)
        );
        boolean eval = visitor.visit(tree);
        assertTrue(eval);
    }
}
