grammar SUMgrammar;

prog returns [sum.interfaces.iast.IASTprogram node]
	: (stmts+=stmt ';'?) * EOF
	;
	
stmt returns [sum.interfaces.iast.IASTstatement node]
	: expr #Expression
	| 'let' var=IDENT '='? val=expr? #Binding
	| 'print' val=expr #Print
	| 'scan' var=IDENT #Scan
	| 'if' cond=expr 'then' '{' cons=stmt '}' 'else' '{' alt=stmt '}' #Alternative
	;
	
expr returns [sum.interfaces.iast.IASTexpression node]
	: intConst=INT # ConstInteger
	| stringConst=STRING # ConstString
	| ident=IDENT #Ident
	| arg1=expr op=('*' | '/' | '+') arg2=expr #BinOp
	| arg1=expr op=('<' | '=' | '>') arg2=expr #RelationBinOp
	| arg1=expr op=('AND' | 'OR') arg2=expr #LogicBinOp
	| 'NOT' arg=expr #LogicUnOp;
	
	
	
INT : [0-9]+;
IDENT : [a-zA-Z_] [a-zA-Z0-9_]*;
STRING : '"' (ESC | ~["\\])*  '"';
ESC : '\\' [\\nrt"];
LINE_COMMENT : '//' (~[\r\n])* -> skip;
COMMENT : '/*' ('*' ~[/] | ~[*])* '*/' -> skip;

SPACE : [ \t\r\n]+ -> skip;