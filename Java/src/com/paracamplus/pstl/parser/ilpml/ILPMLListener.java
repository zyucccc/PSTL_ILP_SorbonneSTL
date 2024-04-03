package com.paracamplus.pstl.parser.ilpml;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

import com.paracamplus.ilp4.interfaces.IASTmethodDefinition;
import com.paracamplus.pstl.interfaces.IASTfactory;
import com.paracamplus.pstl.interfaces.IASTincludeDefinition;
import com.paracamplus.ilp3.interfaces.IASTlambda;
import com.paracamplus.ilp3.interfaces.IASTnamedLambda;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.ilp2.interfaces.IASTdeclaration;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;

import antlr4.ILPMLgrammarPSTLListener;
import antlr4.ILPMLgrammarPSTLParser.GlobalDefContext;
import antlr4.ILPMLgrammarPSTLParser.IncludeDefContext;
import antlr4.ILPMLgrammarPSTLParser.IncludeDefinitionContext;

import static antlr4.ILPMLgrammarPSTLParser.*;

public class ILPMLListener implements ILPMLgrammarPSTLListener {
	
	protected IASTfactory factory;
	
	public ILPMLListener(IASTfactory factory) {
		super();
		this.factory = factory;		
	}

	
	/*
	 * Il faut se souvenir du nom de la classe en cours de définition pour
	 * créer correctement les sous-nœuds IASTmethodDefinition.
	 */
	protected String className = "<unknown>";

	
	// ILP1

	@Override 
	public void exitVariable(VariableContext ctx) { 
		ctx.node =  factory.newVariable(ctx.getText());
	}

	@Override 
	public void exitInvocation(
			InvocationContext ctx) { 
		ctx.node =  factory.newInvocation(
				ctx.fun.node, 
				toExpressions(ctx.args));
	}

	@Override 
	public void exitConstFloat(
			ConstFloatContext ctx) { 
		ctx.node = factory.newFloatConstant(ctx.floatConst.getText());
	}

	@Override 
	public void	exitConstInteger(
			ConstIntegerContext ctx) { 
		ctx.node = factory.newIntegerConstant(ctx.intConst.getText());
	}

	@Override 
	public void exitBinding(BindingContext ctx) { 
		ctx.node =  factory.newBlock(
				toBindings(ctx.vars, ctx.vals),
				ctx.body.node);
	}

	@Override 
	public void exitAlternative(
			AlternativeContext ctx) { 
		ctx.node = factory.newAlternative(
				ctx.condition.node, 
				ctx.consequence.node, 
				(ctx.alternant == null ? null : ctx.alternant.node));
	}

	@Override 
	public void exitSequence(
			SequenceContext ctx) {
		ctx.node =  factory.newSequence(toExpressions(ctx.exprs));
	}

	@Override 
	public void exitConstFalse(
			ConstFalseContext ctx) { 
		ctx.node = factory.newBooleanConstant("false");
	}

	@Override 
	public void exitUnary(UnaryContext ctx) { 
		ctx.node = factory.newUnaryOperation(
				factory.newOperator(ctx.op.getText()),
				ctx.arg.node);
	}

	@Override 
	public void exitConstTrue(
			ConstTrueContext ctx) {
		ctx.node = factory.newBooleanConstant("true");
	}

	@Override 
	public void exitConstString(
			ConstStringContext ctx) { 
		/*
		 * On enlève le " initial et final, et on interprète les codes
		 * d'échapement \n, \r, \t, \"
		 */
		String s = ctx.getText();
		StringBuilder buf = new StringBuilder();
		for (int i = 1; i < s.length() - 1; i++) {
			if (s.charAt(i) == '\\' && i < s.length() - 2) {
				switch (s.charAt(i+1)) {
				case 'n': buf.append('\n'); i++; break;
				case 'r': buf.append('\r'); i++; break;
				case 't': buf.append('\t'); i++; break;
				case '"': buf.append('\"'); i++; break;
				default: buf.append(s.charAt(i));
				}
			}
			else buf.append(s.charAt(i));
		}
		ctx.node = factory.newStringConstant(buf.toString());
	}

	@Override 
	public void exitBinary(BinaryContext ctx) { 
		ctx.node = factory.newBinaryOperation(
				factory.newOperator(ctx.op.getText()),
				ctx.arg1.node,
				ctx.arg2.node);				
	}

		
	
	/* Utilitaires de conversion ANTLR vers AST */
	
	protected IASTexpression[] toExpressions(
			List<ExprContext> ctxs) {
		if (ctxs == null) return new IASTexpression[0];
		IASTexpression[] r = new IASTexpression[ctxs.size()];
		int pos = 0;
		for (ExprContext e : ctxs) {
			r[pos++] = e.node;
		}
		return r;
	}
	
	protected IASTvariable[] toVariables(
			List<Token> vars, boolean addSelf) {
		if (vars == null) return new IASTvariable[0];
		IASTvariable[] r = new IASTvariable[vars.size() + (addSelf ? 1 : 0)];
		int pos = 0;
		if (addSelf) {
			// Les déclarations de méthodes ont une variable "self" implicite
			r[pos++] = factory.newVariable("self");
		}
		for (Token v : vars) {
			r[pos++] = factory.newVariable(v.getText());
		}
		return r;
	}

	protected String[] toStrings(List<Token> vars) {
		if (vars == null) return new String[0];
		String[] r = new String[vars.size()];
		int pos = 0;
		for (Token v : vars) {
			r[pos++] = v.getText();
		}
		return r;
	}

	protected IASTblock.IASTbinding[] toBindings(
			List<Token> vars, 
			List<ExprContext> exprs) {
		if (vars == null) return new IASTblock.IASTbinding[0];
		/* par construction, vars et ctxs ont la même taille */
		assert(vars.size() == exprs.size());
		IASTblock.IASTbinding[] r = new IASTblock.IASTbinding[exprs.size()];
		int pos = 0;
		for (Token v : vars) {
			r[pos] = factory.newBinding(
					factory.newVariable(v.getText()),
					exprs.get(pos).node
					);
			pos++;
		}
		return r;			
	}

	@Override	public void enterEveryRule(ParserRuleContext arg0) {}
	@Override	public void exitEveryRule(ParserRuleContext arg0) {}
	@Override	public void visitErrorNode(ErrorNode arg0) {}
	@Override	public void visitTerminal(TerminalNode arg0) {}
	@Override	public void enterConstInteger(ConstIntegerContext ctx) {}
	@Override	public void enterProg(ProgContext ctx) {}
	@Override	public void enterConstFloat(ConstFloatContext ctx) {}
	@Override	public void enterVariable(VariableContext ctx) {}
	@Override	public void enterBinary(BinaryContext ctx) {}
	@Override	public void enterAlternative(AlternativeContext ctx) {}	
	@Override	public void enterConstFalse(ConstFalseContext ctx) {}
	@Override	public void enterSequence(SequenceContext ctx) {}
	@Override	public void enterConstTrue(ConstTrueContext ctx) {}
	@Override	public void enterBinding(BindingContext ctx) {}
	@Override	public void enterConstString(ConstStringContext ctx) {}
	@Override	public void enterUnary(UnaryContext ctx) {}
	@Override	public void enterInvocation(InvocationContext ctx) {}


	// ILP2
	
	@Override
	public void exitGlobalFunDef(GlobalFunDefContext ctx) {
		ctx.node = factory.newFunctionDefinition(
				factory.newVariable(ctx.name.getText()),
				toVariables(ctx.vars, false), 
				ctx.body.node);
	}

	@Override
	public void exitVariableAssign(VariableAssignContext ctx) {
		ctx.node = factory.newAssignment(
				factory.newVariable(ctx.var.getText()),
				ctx.val.node);		
	}
   
	@Override
	public void exitLoop(LoopContext ctx) {
		ctx.node = factory.newLoop(ctx.condition.node, ctx.body.node);		
	}
	
	@Override public void enterGlobalFunDef(GlobalFunDefContext ctx) {}
	@Override public void enterVariableAssign(VariableAssignContext ctx) {}
	@Override public void enterLoop(LoopContext ctx) {}

	
	// ILP3


	@Override public void exitTry(TryContext ctx) {
		IASTlambda catcher = null;
		IASTexpression finallyer = null;
		if (ctx.catcher != null) {
			IASTvariable[] vars = { factory.newVariable(ctx.var.getText()) };
			catcher = factory.newLambda(vars, ctx.catcher.node);
		}
		if (ctx.finallyer != null) finallyer = ctx.finallyer.node;
		ctx.node = factory.newTry(ctx.body.node, catcher, finallyer);		
	}

	@Override
	public void exitLocalFunDef(LocalFunDefContext ctx) {
		ctx.node = factory.newNamedLambda(
				factory.newVariable(ctx.name.getText()),
				toVariables(ctx.vars, false), 
				ctx.body.node);
	}

	@Override
	public void exitCodefinitions(CodefinitionsContext ctx) {
		ctx.node = factory.newCodefinitions(
				toNamedLambdas(ctx.defs), 
				ctx.body.node);
	}

	@Override
	public void exitLambda(LambdaContext ctx) {
		ctx.node =	factory.newLambda(toVariables(ctx.vars, false), ctx.body.node);
	}

	protected IASTnamedLambda[] toNamedLambdas(
			List<LocalFunDefContext> ctxs) {
		if (ctxs == null) return new IASTnamedLambda[0];
		IASTnamedLambda[] r = new IASTnamedLambda[ctxs.size()];
		int pos = 0;
		for (LocalFunDefContext e : ctxs) {
			r[pos++] = e.node;
		}
		return r;		
	}
	
	@Override public void enterTry(TryContext ctx) {}
	@Override	public void enterLocalFunDef(LocalFunDefContext ctx) {}
	@Override	public void enterCodefinitions(CodefinitionsContext ctx) {}
	@Override	public void enterLambda(LambdaContext ctx) { }

	
	// ILP4
	//ajoute PSTL
//	@Override 
//	public void exitProg(ProgContext ctx) { 
//		// Sépare les définitions de fonctions et de classes
//		List<IASTfunctionDefinition> f = new ArrayList<>();
//		List<IASTclassDefinition> c = new ArrayList<>();
//	    List<IASTexpression> expressions = new ArrayList<>();
//		for (GlobalDefContext progelement : ctx.elements) {
//			if(progelement instanceof GlobalDefContext) {
////				if (progelement.def_prog != null) {  // Check if it's a GlobalDefContext
//				IASTdeclaration x = (IASTdeclaration) progelement.node;
//				if (x instanceof IASTfunctionDefinition) 
//					f.add((IASTfunctionDefinition)x);
//				else if (x instanceof IASTclassDefinition)
//					c.add((IASTclassDefinition)x);
//			}
////			else if (progelement instanceof ExprContext) {
//				else if (progelement.expr_prog != null) {  // Check if it's an ExprContext
//				IASTexpression exp = (IASTexpression) progelement.node;
//					expressions.add(exp);
//			}else {System.err.println("exitProg: unknown context");}
//			
//		}
//		
////		IASTexpression e = factory.newSequence(toExpressions(ctx.exprs));
//		IASTexpression e = factory.newSequence(expressions.toArray(new IASTexpression[0]));
//		ctx.node = factory.newProgram(
//				f.toArray(new IASTfunctionDefinition[0]),
//				c.toArray(new IASTclassDefinition[0]),
//				e);
//	}
	
	@Override 
	public void exitProg(ProgContext ctx) { 
		// Sépare les définitions de fonctions et de classes
		List<IASTfunctionDefinition> f = new ArrayList<>();
		List<IASTclassDefinition> c = new ArrayList<>();
		List<IASTincludeDefinition> includes = new ArrayList<>();
	    List<IASTexpression> expressions = new ArrayList<>();
		for (GlobalDefContext progelement : ctx.elements_globalDef) {
				IASTdeclaration x = (IASTdeclaration) progelement.node;
				if (x instanceof IASTfunctionDefinition) 
					f.add((IASTfunctionDefinition)x);
				else if (x instanceof IASTclassDefinition)
					c.add((IASTclassDefinition)x);
				else if (x instanceof IASTincludeDefinition)
					includes.add((IASTincludeDefinition)x);
		}
		for (ExprContext progelement : ctx.elements_expr) {
			IASTexpression exp = (IASTexpression) progelement.node;
			expressions.add(exp);
		}
		
//		IASTexpression e = factory.newSequence(toExpressions(ctx.exprs));
		IASTexpression e = factory.newSequence(expressions.toArray(new IASTexpression[0]));
		ctx.node = factory.newProgram(
				f.toArray(new IASTfunctionDefinition[0]),
				c.toArray(new IASTclassDefinition[0]),
				//change-
				e,
				includes.toArray(new IASTincludeDefinition[0]));
	}

//	@Override 
//	public void exitProg(ProgContext ctx) { 
//		// Sépare les définitions de fonctions et de classes
//		List<IASTfunctionDefinition> f = new ArrayList<>();
//		List<IASTclassDefinition> c = new ArrayList<>();
//		for (GlobalDefContext d : ctx.defs) {
//			IASTdeclaration x = d.node;
//			if (x instanceof IASTfunctionDefinition) 
//				f.add((IASTfunctionDefinition)x);
//			else if (x instanceof IASTclassDefinition)
//				c.add((IASTclassDefinition)x);
//		}
//		IASTexpression e = factory.newSequence(toExpressions(ctx.exprs));
//		ctx.node = factory.newProgram(
//				f.toArray(new IASTfunctionDefinition[0]),
//				c.toArray(new IASTclassDefinition[0]),
//				e);
//	}

	@Override
	public void exitSend(SendContext ctx) {
		ctx.node = factory.newSend(
				ctx.field.getText(), 
				ctx.obj.node, 
				toExpressions(ctx.args));
	}

	@Override
	public void exitGlobalFunctionDefinition(GlobalFunctionDefinitionContext ctx) {
		ctx.node = ctx.def.node;
	}

	@Override
	public void exitWriteField(WriteFieldContext ctx) {
		ctx.node = factory.newWriteField(
				ctx.field.getText(),  
				ctx.obj.node,  
				ctx.val.node);
	}

	@Override 
	public void enterClassDef(ClassDefContext ctx) { 
		// On se souvient du nom de la classe, pour exitMethodDef
		className = ctx.name.getText();
	}

	@Override
	public void exitClassDef(ClassDefContext ctx) {
		ctx.node = factory.newClassDefinition(
				ctx.name.getText(),
				(ctx.parent == null) ? "Object" : ctx.parent.getText(),
				toStrings(ctx.fields),
				toMethods(ctx.methods));
		className = "<unknown>";		
	}

	@Override
	public void exitMethodDef(MethodDefContext ctx) {
		ctx.node = factory.newMethodDefinition(
				factory.newVariable(ctx.name.getText()),
				toVariables(ctx.vars, true),
				ctx.body.node,
				ctx.name.getText(),
				className);
	}
	
	@Override
	public void exitReadField(ReadFieldContext ctx) {
		ctx.node = factory.newReadField(ctx.field.getText(), ctx.obj.node);		
	}

	@Override
	public void exitSelf(SelfContext ctx) {
		ctx.node = factory.newSelf();		
	}

	@Override
	public void exitSuper(SuperContext ctx) {
		ctx.node = factory.newSuper();		
	}

	@Override
	public void exitClassDefinition(ClassDefinitionContext ctx) {
		ctx.node = ctx.def.node;
	}

	@Override
	public void exitNew(NewContext ctx) {
		ctx.node = factory.newInstantiation(
				ctx.className.getText(),
				toExpressions(ctx.args));
	}

	protected IASTmethodDefinition[] toMethods(
			List<MethodDefContext> ctxs) {
		if (ctxs == null) return new IASTmethodDefinition[0];
		IASTmethodDefinition[] r = new IASTmethodDefinition[ctxs.size()];
		int pos = 0;
		for (MethodDefContext e : ctxs) {
			r[pos++] = e.node;
		}
		return r;		
	}

	@Override	public void enterSend(SendContext ctx) {}
	@Override	public void enterGlobalFunctionDefinition(GlobalFunctionDefinitionContext ctx) {}
	@Override	public void enterWriteField(WriteFieldContext ctx) {}
	@Override	public void enterMethodDef(MethodDefContext ctx) {}
	@Override	public void enterReadField(ReadFieldContext ctx) {}
	@Override	public void enterSelf(SelfContext ctx) {}
	@Override	public void enterSuper(SuperContext ctx) {}
	@Override	public void enterClassDefinition(ClassDefinitionContext ctx) {}
	@Override	public void enterNew(NewContext ctx) {}

	@Override
	public void enterIncludeDefinition(IncludeDefinitionContext ctx) {
		//laisse null
	}

	@Override
	public void exitIncludeDefinition(IncludeDefinitionContext ctx) {
		ctx.node = ctx.def.node;
	}

	@Override
	public void enterIncludeDef(IncludeDefContext ctx) {
		//laisse null
	}

	@Override
	public void exitIncludeDef(IncludeDefContext ctx) {
		String filepath = ctx.body.getText();
//		System.out.println("Test parser "+filepath);
	    //on supprime des guillemets" "dans text obtenut
		//car include work genre: include "path/array.ilpml"
	    filepath = filepath.substring(1, filepath.length() - 1);
//	    System.out.println("Test parser "+filepath);
	    
		ctx.node = factory.newIncludeDefinition(filepath);
	}

//	@Override
//	public void enterExprElement(ExprElementContext ctx) {}
//
//	@Override
//	public void exitExprElement(ExprElementContext ctx) {
//		ctx.node = ctx.expr_prog.node;
//	}
//
//	@Override
//	public void enterGlobalElement(GlobalElementContext ctx) {}
//
//	@Override
//	public void exitGlobalElement(GlobalElementContext ctx) {
//		ctx.node = ctx.def_prog.node;
//	}

	
	
}
