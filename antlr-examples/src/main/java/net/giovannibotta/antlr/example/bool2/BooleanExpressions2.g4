grammar BooleanExpressions2;

@parser::header {
import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
}

@parser::members {
private final HashSet<String> integerVariables = new HashSet<String>();
private final HashSet<String> booleanVariables = new HashSet<String>();

private void defineBooleanVariable(String var){
    if(integerVariables.contains(var)) throw new IllegalArgumentException("Variable " + var + " already declared as an integer variable");
    booleanVariables.add(var);
}

private void defineIntegerVariable(String var){
    if(booleanVariables.contains(var)) throw new IllegalArgumentException("Variable " + var + " already declared as a boolean variable");
    integerVariables.add(var);
}

public Set<String> getIntegerVariables(){
    return ImmutableSet.copyOf(integerVariables);
}

public Set<String> getBooleanVariables(){
    return ImmutableSet.copyOf(booleanVariables);
}
}

start                : booleanExpression;

// variables/constants
IntegerConstant      : ('0'..'9')+ ;
BooleanConstant      : 'true' | 'false' ;
Variable             : ('a'..'z')+ ;

// arithmetic expressions
arithmeticExpression : '(' child=arithmeticExpression ')'                                          #arithParenExp
                     | left=arithmeticExpression op=('*'|'/') right=arithmeticExpression           #arithOpExpr
                     | left=arithmeticExpression op=('+'|'-') right=arithmeticExpression           #arithOpExpr
                     | constant=IntegerConstant                                                    #arithAtomExpr
                     | variable=Variable {defineIntegerVariable($variable.text);}                  #arithAtomExpr
                     ;

arithmeticRelation   : left=arithmeticExpression op=('==' | '!=' | '>' | '<' | '>=' | '<=') right=arithmeticExpression ;

// boolean expressions
booleanExpression    : '(' child=booleanExpression ')'                                             #boolParenExp
                     | '~' child=booleanExpression                                                 #boolNotExp
                     | left=booleanExpression op='&' right=booleanExpression                       #boolOpExpr
                     | left=booleanExpression op='|' right=booleanExpression                       #boolOpExpr
                     | constant=BooleanConstant                                                    #boolAtomExpr
                     | variable=Variable {defineBooleanVariable($variable.text);}                  #boolAtomExpr
                     | arithmeticRelation                                                          #arithRel
                     ;

WS                   : (' ' | '\t' | '\r'| '\n') -> skip ;

