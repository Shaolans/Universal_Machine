#include <stdlib.h>
#include <stdio.h>
#include "ast.h"

Expr* newASTNum(int v) {
	Expr* r = malloc(sizeof(*r));
	r->tag = ASTNum;
	r->content.num = v;
	return r;
}

Expr* newASTId(char* v) {
	Expr* r = malloc(sizeof(*r));
	r->tag = ASTId;
	r->content.id = v;
	return r;
}

Expr* newASTPrim(Oprim op, Expr* e1, Expr* e2) {

	Expr* r = malloc(sizeof(*r));
	r->tag = ASTPrim;
	r->content.binOp.op = op;
	r->content.binOp.opand1 = e1;
	r->content.binOp.opand2 = e2;
	return r;
}

Expr* newASTPrimUn(Oprim op, Expr* e){
	Expr * r =  malloc(sizeof(*r));
	r->tag = ASTPrimUn;
	r->content.unOp.op = op;
	r->content.unOp.opand = e;
	return r;

}


Expr* newASTIf(Expr* cond, Stat* then, Stat *els){
	Expr *r = malloc(sizeof(*r));
	r->tag =ASTIf;
	r->content.ifthenelse.cond = cond;
	r->content.ifthenelse.then = then;
	r->content.ifthenelse.els = els;
	return r;

}


Stat* newASTPrint(Expr* e){
	Stat* r = malloc(sizeof(*r));
	r->tag = ASTPrint;
	r->e = e;
	return r;
}



Cmds* newASTStatCmds(Stat* s, Cmds* c){
	Cmds* r = malloc(sizeof(*r));
	r->tag = ASTStatCmds;
	r->content.s = s;
	r->cmds = c;
	return r;
}



