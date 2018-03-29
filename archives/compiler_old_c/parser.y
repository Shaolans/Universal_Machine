%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "ast.h"
#include "toUM.h"

int yylex (void);
int yyerror (char *);
Expr* theExpr;
Cmds* theCmds;

%}

%token<num> NUM
%token<str> IDENT
%token PLUS MINUS TIMES DIV NOT AND OR EQUALS LESSTHAN
%token PRINT
%token IF THEN ELSE
%token LACC RACC
%token LPAR RPAR
%token PVIRGULE
%token NL
%left PLUS MINUS
%left TIMES DIV
%left NOT OR AND
%left EQUALS LESSTHAN GREATERTHAN
%union {
	int num;
	char* str;
	Expr* expr;
	Stat* stat;
	Cmds* cmds;
}
%type<expr> exp
%type<stat> stat
%type<cmds> cmds

%start prog

%%

prog : cmds { theCmds= $1; } 

;

exp:
NUM { $$ = newASTNum($1); }
| IDENT { $$ = newASTId($1); }
| exp PLUS exp { $$ = newASTPrim(AST_ADD,$1,$3); }
| exp MINUS exp { $$ = newASTPrim(AST_SUB,$1,$3); }
| exp TIMES exp { $$ = newASTPrim(AST_MUL,$1,$3); }
| exp DIV exp { $$ = newASTPrim(AST_DIV,$1,$3); }
| NOT exp { $$ = newASTPrimUn(AST_NOT, $2); }
| exp OR exp { $$ = newASTPrim(AST_OR, $1, $3) ; }
| exp AND exp { $$ = newASTPrim(AST_AND, $1, $3) ; }
| exp EQUALS exp { $$ = newASTPrim(AST_EQ, $1, $3);}
| exp LESSTHAN exp { $$ = newASTPrim(AST_LT, $1, $3);}
| exp GREATERTHAN exp { $$ = newASTPrim(AST_GT, $1, $3);}
| IF exp THEN LACC stat RACC ELSE LACC stat RACC { $$ = newASTIf($2, $5, $9);}

;



stat : 
	PRINT exp { $$ = newASTPrint($2);}
;


cmds :
	stat { $$ = newASTStatCmds($1, NULL);}
	| stat PVIRGULE cmds { $$ = newASTStatCmds($1, $3); }
;

%%

int yyerror(char *s) {
	printf("error: %s\n",s);
	return 1;
}


int main(int argc, char **argv) {
	yyparse();
	compileProg(theCmds);
	printf("\n");
	return 0;
}
