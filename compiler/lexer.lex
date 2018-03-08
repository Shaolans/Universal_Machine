%{
	#include <stdlib.h>
	#include "ast.h"
	#include "y.tab.h"
%}

nls "\n"|"\r"|"\r\n"
nums "-"?[0-9]+
idents [a-zA-Z][a-zA-Z0-9]*

%%

[ \t] { /* On ignore */ }
"+" return(PLUS);
"-" return(MINUS);
"*" return(TIMES);

"/" return(DIV);
"(" return(LPAR);
")" return(RPAR);

"=" return (EQUALS);
"<" return (LESSTHAN);
">" return (GREATERTHAN);

"NOT" return(NOT);
"AND" return(AND);
"OR"	return(OR);



"if"	return(IF);
"then"	return(THEN);
"else"	return(ELSE);

"{"	return(LACC);
"}"	return(RACC);


";"	return(PVIRGULE);

"PRINT"	return(PRINT);

{nls} { return(0); }
{nums} {
	yylval.num=atoi(yytext);
	return(NUM);
}

{idents} {
	yylval.str=strdup(yytext);
	return(IDENT);
}
