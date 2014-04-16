package net.giovannibotta.antlr.example.bool2;

import com.google.common.collect.ImmutableMap;
import net.giovannibotta.antlr.example.bool2.visitor.BooleanExpressionEval2;
import net.giovannibotta.antlr.example.bool2.visitor.BooleanExpressionParenthesize2;
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
    private ParserRuleContext parse(String exp){
        ANTLRInputStream in = new ANTLRInputStream(exp);
        BooleanExpressions2Lexer lexer = new BooleanExpressions2Lexer(in);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        BooleanExpressions2Parser parser = new BooleanExpressions2Parser(tokenStream);
        return parser.start();
    }

    @Test
    public void test() {
        ParserRuleContext tree = parse("x|y");

        BooleanExpressionParenthesize2 parenthesize = new BooleanExpressionParenthesize2();
        System.out.println(parenthesize.visit(tree));

        tree = parse("~ ( a/2 + 20) <= (3+b)*2 ");
        System.out.println(parenthesize.visit(tree));


        BooleanExpressionEval2 visitor = new BooleanExpressionEval2(
                ImmutableMap.of("x", Boolean.FALSE, "y", Boolean.TRUE), ImmutableMap.of("a", 21, "b", 1)
        );
        assertTrue(visitor.visit(tree));

        visitor = new BooleanExpressionEval2(
                ImmutableMap.of("x", Boolean.TRUE, "y", Boolean.TRUE), ImmutableMap.of("a", -50, "b", 1)
        );
        assertTrue(visitor.visit(tree));
    }
}
