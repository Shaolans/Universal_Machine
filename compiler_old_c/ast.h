typedef struct _expr Expr;
typedef struct _exprs Exprs;
typedef struct _stat Stat;
typedef struct _cmds Cmds;
typedef enum _tag Tag;
typedef enum _oprim Oprim;

enum _tag {
	ASTNum, ASTId, ASTPrim, ASTPrimUn, ASTIf,
	ASTPrint, ASTStatCmds
};


enum _oprim { AST_ADD, AST_SUB, AST_DIV, AST_MUL, AST_NOT, AST_OR, AST_AND, AST_EQ, AST_LT, AST_GT};

struct _expr {
	Tag tag;
	union {
		int num;
		char* id;
		struct {
			Oprim op;
			Expr *opand1;
			Expr *opand2;
		} binOp;
		struct {
			Oprim op;
			Expr *opand;
		} unOp;
		
		struct {
			Expr *cond;
			Stat *then;
			Stat *els;
		}ifthenelse;
		
		
	} content;
};

struct _exprs {

	Expr * e;
	Exprs * exprs;
	
};


struct _stat{
	Tag tag;
	Expr * e;
};


struct _cmds{
	Tag tag;
	Cmds* cmds;
	union{
		Stat* s;
	}content;
};


Expr* newASTNum(int n);
Expr* newASTId(char* x);
Expr* newASTPrim(Oprim op, Expr* e1, Expr* e2);
Expr* newASTPrimUn(Oprim op, Expr* e);
Expr* newASTIf(Expr* e1, Stat *e2, Stat *e3);

Stat* newASTPrint(Expr* e);


Cmds* newASTStatCmds(Stat* s, Cmds* c);

#define tagOf(r) r->tag
#define getNum(r) r->content.num
#define getId(r) r->content.id
#define getOp(r) r->content.binOp.op==0? r->content.unOp.op : r->content.binOp.op
#define getOpand1(r) r->content.binOp.opand1
#define getOpand2(r) r->content.binOp.opand2
#define getOpand(r)	r->content.unOp.opand
#define getCond(r)	r->content.ifthenelse.cond
#define getThen(r)	r->content.ifthenelse.then
#define getElse(r)	r->content.ifthenelse.els
#define getExpr(r)	r->content.app.e==0?r->content.app.e : r->content.abs.e
#define getExprs(r)	r->content.app.exprs
#define getType(r)	r->t
#define getTypes(r)	r->types
#define getArgs(r)	r->content.abs.args
