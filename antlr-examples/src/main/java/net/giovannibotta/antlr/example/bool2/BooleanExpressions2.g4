grammar BooleanExpressions2;

start                : booleanExpression;

// variables/constants
Integer              : ('0'..'9')+ ;
Variable             : ('a'..'z')+ ;
BooleanConstant      : 'true' | 'false' ;

// arithmetic expressions
arithmeticExpression : left=arithmeticExpression op=('*'|'/') right=arithmeticExpression #arithOpExpr
                     | left=arithmeticExpression op=('+'|'-') right=arithmeticExpression #arithOpExpr
                     | '(' child=arithmeticExpression ')'                                #arithParenExp
                     | constant=Integer                                                  #arithAtomExpr
                     | variable=Variable                                                 #arithAtomExpr
                     ;
arithmeticRelation   : left=arithmeticExpression op=('==' | '!=' | '>' | '<' | '>=' | '<=') right=arithmeticExpression ;

// boolean expressions
booleanExpression    : left=booleanExpression op='&' right=booleanExpression             #boolOpExpr
                     | left=booleanExpression op='|' right=booleanExpression             #boolOpExpr
                     | arithmeticRelation                                                #arithRel
                     | '(' child=booleanExpression ')'                                   #boolParenExp
                     | '~' child=booleanExpression                                       #boolNotExp
                     | constant=BooleanConstant                                          #boolAtomExpr
                     | variable=Variable                                                 #boolAtomExpr
                     ;

WS                   : (' ' | '\t' | '\r'| '\n') -> skip ;

