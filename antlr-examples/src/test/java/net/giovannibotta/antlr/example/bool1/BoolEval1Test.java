package net.giovannibotta.antlr.example.bool1;

import net.giovannibotta.antlr.example.bool1.visitor.BooleanExpressionEval1;
import net.giovannibotta.antlr.example.bool1.visitor.BooleanExpressionParenthesize1;
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
    private ParserRuleContext parse(String exp){
        ANTLRInputStream in = new ANTLRInputStream(exp);
        BooleanExpressions1Lexer lexer = new BooleanExpressions1Lexer(in);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        BooleanExpressions1Parser parser = new BooleanExpressions1Parser(tokenStream);
        return parser.start();
    }
    @Test
    public void test() {
        ParserRuleContext tree = parse("true | false & 2>1");
        BooleanExpressionParenthesize1 parenthesize = new BooleanExpressionParenthesize1();
        System.out.println(parenthesize.visit(tree));

        tree = parse("~ 12+20/2 <= (3+4)*2 | 2<1 & 3+2==5");

        BooleanExpressionEval1 visitor = new BooleanExpressionEval1();
        boolean eval = visitor.visit(tree);
        assertTrue(eval);
        System.out.println(parenthesize.visit(tree));
    }
}
