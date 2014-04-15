grammar BooleanExpressions1;

start                : booleanExpression;

// variables/constants
Integer              : ('0'..'9')+ ;
BooleanConstant      : 'true' | 'false' ;

// arithmetic expressions
arithmeticExpression : left=arithmeticExpression op=('*'|'/') right=arithmeticExpression #arithOpExpr
                     | left=arithmeticExpression op=('+'|'-') right=arithmeticExpression #arithOpExpr
                     | '(' child=arithmeticExpression ')'                                #arithParenExp
                     | arithmeticAtom=Integer                                            #arithAtomExpr
                     ;
arithmeticRelation   : left=arithmeticExpression op=('==' | '!=' | '>' | '<' | '>=' | '<=') right=arithmeticExpression ;

// boolean expressions
booleanExpression    : left=booleanExpression op='&' right=booleanExpression             #boolOpExpr
                     | left=booleanExpression op='|' right=booleanExpression             #boolOpExpr
                     | arithmeticRelation                                                #arithRel
                     | '(' child=booleanExpression ')'                                   #boolParenExp
                     | '~' child=booleanExpression                                       #boolNotExp
                     | booleanAtom=BooleanConstant                                       #boolAtomExpr
                     ;

WS                   : (' ' | '\t' | '\r'| '\n') -> skip ;

