package net.giovannibotta.antlr.example.arith1;


import net.giovannibotta.antlr.example.arith1.visitor.ArithmeticExpressionEval1;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author giovanni
 * @since 4/15/14
 */
public class Eval1Test {
    @Test
    public void test() {
        ANTLRInputStream in = new ANTLRInputStream("12+20/2"); // no way to have parentheses in there!
        Expressions1Lexer lexer = new Expressions1Lexer(in);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        Expressions1Parser parser = new Expressions1Parser(tokenStream);
        ParserRuleContext tree = parser.start();

        ArithmeticExpressionEval1 visitor1 = new ArithmeticExpressionEval1();
        int result = visitor1.visit(tree);
        assertEquals(22, result);
    }
}
