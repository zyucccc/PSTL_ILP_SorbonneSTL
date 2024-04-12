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
	private StringBuilder sb = new StringBuilder();
	private int compteur_list = 0;

	public StringBuilder getSb() {
		return this.sb;
	}


	public Object visit(IASTprogram iast) throws EvaluationException {
		   sb.append("new ASTprogram(");

		   iast.getBody().accept(this,null);
		   sb.append(")");
		   sb.append(";");

		   System.out.println(sb.toString());

	       return sb;
	}

	//ILP1
	@Override
	public Object visit(IASTalternative iast,ILexicalEnvironment lexenv) throws EvaluationException {
		sb.append("new ASTalternative(");
		iast.getCondition().accept(this,null);
		sb.append(",");
		iast.getConsequence().accept(this,null);
		sb.append(",");
	    if (iast.isTernary()) {
			iast.getAlternant().accept(this,null);
	    } else {
			sb.append("new NULL()");
	    }
		sb.append(")");
		return null;
	}

	@Override
	public Object visit(IASTbinaryOperation iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		sb.append("new ASTbinaryOperation(");
		sb.append("\"");
		sb.append(iast.getOperator().getName());
		sb.append("\"");
		sb.append(",");
		iast.getLeftOperand().accept(this,null);
		sb.append(",");
		iast.getRightOperand().accept(this,null);
		sb.append(")");
		return null;
	}

	@Override
	public Object visit(IASTblock iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		//compteur_list -> pour generer different index pour chaque appel de visit block
		compteur_list ++;

		sb.append("new ASTblock(");

		String list_str = "list"+compteur_list;
		String binding_str = "";

		IASTblock.IASTbinding[] bindings = iast.getBindings();
		sb.append("(");
		sb.append(list_str).append("=").append("new List(new NULL());");

		for ( IASTblock.IASTbinding binding : bindings ) {
				sb.append(list_str).append(".add(").append("new ASTbinding(");
				binding.getVariable().accept(this, null);
				sb.append(",");
				binding.getInitialisation().accept(this, null);
				sb.append("));");
		}

		sb.append(list_str);
        sb.append(")");
		sb.append(",");
		iast.getBody().accept(this,null);
		sb.append(")");
		return null;
	}

	@Override
	public Object visit(IASTboolean iast,ILexicalEnvironment lexenv) {
		sb.append("new ASTboolean(");
		sb.append(iast.getValue());
		sb.append(")");
		return null;
    }
    @Override
    public Object visit(IASTinteger iast,ILexicalEnvironment lexenv) {
		sb.append("new ASTinteger(");
		sb.append(iast.getValue());
		sb.append(")");
		return null;
    }

	@Override
	public Object visit(IASTinvocation iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		sb.append("new ASTinvocation(");
		compteur_list ++;
		String list_str = "list"+compteur_list;
		iast.getFunction().accept(this,null);
		sb.append(",");
		IASTexpression[] expressions = iast.getArguments();
		sb.append("(");
		sb.append(list_str).append("=").append("new List(new NULL());");
		for ( IASTexpression e : expressions ) {
			sb.append(list_str).append(".add(");
			e.accept(this, null);
			sb.append(");");
		}
		sb.append(list_str);
		sb.append(")");
		sb.append(")");
		return null;
	}

	@Override
    public Object visit(IASTfloat iast,ILexicalEnvironment lexenv) {
		sb.append("new ASTfloat(");
		sb.append(iast.getValue());
		sb.append(")");
		return null;
    }

	@Override
    public Object visit(IASTstring iast,ILexicalEnvironment lexenv) {
		sb.append("new ASTstring(");
		sb.append(iast.getValue());
		sb.append(")");
		return null;
	}

	@Override
	public Object visit(IASTsequence iast,ILexicalEnvironment lexenv) throws EvaluationException {
		compteur_list++;
		sb.append("new ASTsequence(");
		sb.append("(");
		String list_str = "list"+compteur_list;
		sb.append(list_str).append("=").append("new List(new NULL());");
		IASTexpression[] expressions = iast.getExpressions();
		for ( IASTexpression e : expressions ) {
			sb.append(list_str).append(".add(");
			e.accept(this, null);
			sb.append(");");
		}
		sb.append(list_str);
		sb.append(")");
		sb.append(")");
		return null;
	}
	@Override
	public Object visit(IASTunaryOperation iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		sb.append("new ASTunaryOperation(");
		sb.append("\"");
		sb.append(iast.getOperator().getName());
		sb.append("\"");
		sb.append(",");
		iast.getOperand().accept(this,null);
		sb.append(")");
		return null;
	}

	@Override
	public Object visit(IASTvariable iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
		sb.append("new ASTvariable(");
		sb.append("\"");
		sb.append(iast.getName());
		sb.append("\"");
		sb.append(")");
		return null;
	}

	//ILP2
	@Override
	public Object visit(IASTassignment iast, ILexicalEnvironment iLexicalEnvironment) throws EvaluationException {
        sb.append("new ASTassignment(");
		iast.getVariable().accept(this,null);
		sb.append(",");
		iast.getExpression().accept(this,null);
		sb.append(")");
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
