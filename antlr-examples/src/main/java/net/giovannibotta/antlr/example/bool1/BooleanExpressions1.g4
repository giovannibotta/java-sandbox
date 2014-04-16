grammar BooleanExpressions1;

start                : booleanExpression;

// variables/constants
Integer              : ('0'..'9')+ ;
BooleanConstant      : 'true' | 'false' ;

// arithmetic expressions
arithmeticExpression : '(' child=arithmeticExpression ')'                                #arithParenExp
                     | left=arithmeticExpression op=('*'|'/') right=arithmeticExpression #arithOpExpr
                     | left=arithmeticExpression op=('+'|'-') right=arithmeticExpression #arithOpExpr
                     | arithmeticAtom=Integer                                            #arithAtomExpr
                     ;

arithmeticRelation   : left=arithmeticExpression op=('==' | '!=' | '>' | '<' | '>=' | '<=') right=arithmeticExpression ;

// boolean expressions
booleanExpression    : '(' child=booleanExpression ')'                                   #boolParenExp
                     | '~' child=booleanExpression                                       #boolNotExp
                     | left=booleanExpression op='&' right=booleanExpression             #boolOpExpr
                     | left=booleanExpression op='|' right=booleanExpression             #boolOpExpr
                     | arithmeticRelation                                                #arithRel
                     | booleanAtom=BooleanConstant                                       #boolAtomExpr
                     ;

WS                   : (' ' | '\t' | '\r'| '\n') -> skip ;

