package net.giovannibotta.antlr.example.bool1;

import net.giovannibotta.antlr.example.bool1.visitor.BooleanExpressionEval1;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author giovanni
 * @since 4/15/14
 */
public class BoolEval1Test {
    @Test
    public void test() {
        ANTLRInputStream in = new ANTLRInputStream("~ (12+20/2) <= (3+4)*2"); // no way to have parentheses in there!
        BooleanExpressions1Lexer lexer = new BooleanExpressions1Lexer(in);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        BooleanExpressions1Parser parser = new BooleanExpressions1Parser(tokenStream);
        ParserRuleContext tree = parser.start();

        BooleanExpressionEval1 visitor = new BooleanExpressionEval1();
        boolean eval = visitor.visit(tree);
        assertTrue(eval);
    }
}
