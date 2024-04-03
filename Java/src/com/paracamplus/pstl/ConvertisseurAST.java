package com.paracamplus.pstl;

import com.paracamplus.ilp1.interfaces.*;
import com.paracamplus.ilp2.interfaces.IASTassignment;
import com.paracamplus.ilp2.interfaces.IASTloop;
import com.paracamplus.ilp3.interfaces.IASTcodefinitions;
import com.paracamplus.ilp3.interfaces.IASTlambda;
import com.paracamplus.ilp3.interfaces.IASTtry;
import com.paracamplus.ilp4.interfaces.*;
import com.paracamplus.pstl.interfaces.IASTincludeDefinition;
import com.paracamplus.pstl.interfaces.IASTvisitor;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;

//pstl import

import com.paracamplus.pstl.interfaces.IASTprogram;

public class ConvertisseurAST implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {
	StringBuilder sb = new StringBuilder();
	int compteur_list = 0;

	//ast expression
	//definir des methodes pour collecter var globals
	//blabla

	public Object visit(IASTprogram iast) throws EvaluationException {
		sb.append("new ASTprogram(");
	//classes:
//	for ( IASTclassDefinition cd : iast.getClassDefinitions() ) {
//           classes += this.visit(cd);
//     }
	//functions:
//	for ( IASTfunctionDefinition fd : iast.getFunctionDefinitions() ) {
//		functions += this.visit(fd);
//        String v = fd.getName();
//        //mecanisme global variable collector?
//
//    }

	//collecter les var global
	// function global stocker une liste des var globals


		//print in ilpml: mecanisme de interpreterTest,(rediriger Sortie Standard vers une chaine de caractere)
		   //expressions:
//		   expr = iast.getBody().toString();
//		   System.out.println(expr);
		   String expr = ((IASTexpression)iast.getBody()).accept(this,null).toString();
           sb.append(expr);
		   sb.append(")");
		   //Test
		   System.out.println(sb.toString());

	       return sb;
	}
	public Object visit(IASTclassDefinition iast) {
		//cast
		iast = (IASTclassDefinition) iast;

	// A completer
		return null;
	}

	//ILP1
	@Override
	public Object visit(IASTalternative iast,ILexicalEnvironment lexenv) throws EvaluationException {
	    String conditionCode = iast.getCondition().accept(this,null).toString();
	    String consequenceCode = iast.getConsequence().accept(this,null).toString();

		sb.append("new ASTalternative(");
		sb.append(conditionCode);
		sb.append(",");
		sb.append(consequenceCode);

	    if (iast.isTernary()) {
	        String alternantCode = iast.getAlternant().accept(this,null).toString();
			sb.append(",");
			sb.append(alternantCode);
	    } else {
			sb.append(",new NULL()");
	    }
		sb.append(")");
		return null;
	}

	@Override
	public Object visit(IASTbinaryOperation iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		return null;
	}

	@Override
	public Object visit(IASTblock iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		//compteur_list -> pour generer different index pour chaque appel de visit block
		compteur_list ++;

		sb.append("new ASTblock(");

		String list_str = "list"+compteur_list;
		String binding_str = "";
		String bindings_str = "";
		IASTblock.IASTbinding[] bindings = iast.getBindings();
		bindings_str = list_str + "=" + "new List(new NULL());";
		for ( IASTblock.IASTbinding binding : bindings ) {
			binding_str = "new ASTbinding(" + binding.getVariable().accept(this,null) + "," + binding.getInitialisation().accept(this,null) + ")";
			bindings_str = bindings_str + list_str + ".add(" + binding_str + ");";
		}

		sb.append(bindings_str);
		sb.append(",");
		sb.append(iast.getBody().accept(this,null));
		return null;
	}

	@Override
	public Object visit(IASTboolean iast,ILexicalEnvironment lexenv) {
//		sb.append("new ASTboolean(");
//		sb.append(iast.getValue());
//		sb.append(")");
		String str = "";
		str = "new ASTboolean(" + iast.getValue() + ")";
		return str;
    }
    @Override
    public Object visit(IASTinteger iast,ILexicalEnvironment lexenv) {
//		sb.append("new ASTinteger(");
//		sb.append(iast.getValue());
//		sb.append(")");
		String str = "";
		str = "new ASTinteger(" + iast.getValue() + ")";
		return str;
    }

	@Override
	public Object visit(IASTinvocation iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		return null;
	}

	@Override
    public Object visit(IASTfloat iast,ILexicalEnvironment lexenv) {
//		sb.append("new ASTfloat(");
//		sb.append(iast.getValue());
//		sb.append(")");
		String str = "";
		str = "new ASTfloat(" + iast.getValue() + ")";
		return str;
    }

	@Override
    public Object visit(IASTstring iast,ILexicalEnvironment lexenv) {
//		sb.append("new ASTstring(");
//		sb.append(iast.getValue());
//		sb.append(")");
		String str = "";
		str = "new ASTstring(" + iast.getValue() + ")";
		return str;
	}

	@Override
	public Object visit(IASTsequence iast,ILexicalEnvironment lexenv) throws EvaluationException {
		IASTexpression[] expressions = iast.getExpressions();
		Object lastValue = null;
		for ( IASTexpression e : expressions ) {
			lastValue = e.accept(this, null);
		}
		return lastValue;
	}
	@Override
	public Object visit(IASTunaryOperation iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		return null;
	}

	@Override
	public Object visit(IASTvariable iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
//		sb.append("new ASTvariable(");
//		sb.append(iast.getName());
//		sb.append(")");
		String str = "";
		str = "new ASTvariable(" + iast.getName() + ")";
		return str;
	}

	//ILP2
	@Override
	public Object visit(IASTassignment iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
        sb.append("new ASTassignment(");
		sb.append(iast.getVariable().accept(this,null));
		sb.append(",");
		sb.append(iast.getExpression().accept(this,null));
		sb.append(")");
		sb.append(";");
		return null;
	}

	@Override
	public Object visit(IASTloop iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		return null;
	}

	@Override
	public Object visit(IASTcodefinitions iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		return null;
	}

	@Override
	public Object visit(IASTlambda iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		return null;
	}

	@Override
	public Object visit(IASTtry iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		return null;
	}

	@Override
	public Object visit(IASTinstantiation iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		return null;
	}

	@Override
	public Object visit(IASTfieldRead iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		return null;
	}

	@Override
	public Object visit(IASTself iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		return null;
	}

	@Override
	public Object visit(IASTsend iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		return null;
	}

	@Override
	public Object visit(IASTsuper iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		return null;
	}

	@Override
	public Object visit(IASTfieldWrite iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		return null;
	}

	@Override
	public Object visit(IASTincludeDefinition iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		return null;
	}
}
