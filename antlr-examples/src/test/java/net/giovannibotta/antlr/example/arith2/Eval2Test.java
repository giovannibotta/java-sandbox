package net.giovannibotta.antlr.example.arith2;

import net.giovannibotta.antlr.example.arith2.visitor.ArithmeticExpressionEval2;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author giovanni
 * @since 4/15/14
 */
public class Eval2Test {
    @Test
    public void test() {
        ANTLRInputStream in = new ANTLRInputStream("(12+20)/2");
        Expressions2Lexer lexer = new Expressions2Lexer(in);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        Expressions2Parser parser = new Expressions2Parser(tokenStream);
        ParserRuleContext tree = parser.start();

        ArithmeticExpressionEval2 visitor = new ArithmeticExpressionEval2();
        int result = visitor.visit(tree);
        assertEquals(16, result);
    }
}
