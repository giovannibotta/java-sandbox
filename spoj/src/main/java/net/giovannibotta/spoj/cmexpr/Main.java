package net.giovannibotta.spoj.cmexpr;

/**
 * CMEXPR
 *
 * @author giovanni
 * @since 4/11/14
 */
public class Main {

//    static ExpNode parse(String s){
//        ExpNode node = null;
//        for(int i=0;i<s.length();i++){
//            char c = s.charAt(i);
//            if(isToken(c)){
//                if(isOperator(c)){
//                    ExpNode o = new ExpNode(node,operator(c),null);
//                }else{
//                    if(node==null) node = new ExpNode()
//                    else node.r
//                }
//            }
//        }
//    }
    private static boolean isOperator(char c) {
        return c=='+'||c=='-'||c=='*'||c=='/';
    }
    private static Operator operator(char c){
        if(c=='+')return Operator.plus;
        if(c=='-')return Operator.minus;
        if(c=='*')return Operator.times;
        if(c=='/')return Operator.div;
        throw new IllegalArgumentException("Trying to parse character "+c+" as operator");
    }
    private static boolean isToken(char c) {
        return c!='('&&c!=')'&c!=' ';
    }
    static interface Exp{}
    static class Var implements Exp{
        final char v;
        Var(char v) {
            this.v = v;
        }
    }
    static class ExpNode implements Exp{
        final Operator op;
        Exp l, r;
        ExpNode(Exp l, Operator op, Exp r) {
            this.op = op;
            this.l = l;
            this.r = r;
        }
    }
    static enum Operator {
        plus(0), minus(0), times(1), div(1);
        private final int prec;
        Operator(int prec) {
            this.prec = prec;
        }
        int precedence() {
            return prec;
        }
    }
}
