package com.paracamplus.pstl;

import com.paracamplus.ilp1.ast.ASTnamed;
import com.paracamplus.ilp1.interfaces.*;
import com.paracamplus.pstl.interfaces.ilp1.IASTalternative;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;

//pstl import

import com.paracamplus.pstl.interfaces.IASTprogram;
import com.paracamplus.pstl.interfaces.IASTvisitor_Convert;
import com.paracamplus.pstl.interfaces.ilp1.IASTexpression;

public class ConvertisseurAST implements IASTvisitor_Convert<Object, EvaluationException> {

	//constructeur
//	public ConvertisseurAST(IASTprogram program) {
//		this.visit(program);
//    }

	//ast expression
	//definir des methodes pour collecter var globals
	//blabla

	public Object visit(IASTprogram iast) throws EvaluationException {
		StringBuilder sb = new StringBuilder();

		String program = "";
//		String classes = "";
//		String functions = "";
		String expr = "";

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

	//expressions:
		   //cast
	       expr = ((IASTexpression)iast.getBody()).accept(this).toString();

	       sb.append("new ASTprogram(");
		   sb.append("expr");
		   sb.append(")");
	       System.out.println(program);
	       return  program;
	}
	public Object visit(IASTclassDefinition iast) {
		//cast
		iast = (IASTclassDefinition) iast;

	// A completer
		return null;
	}
	@Override
	public Object visit(com.paracamplus.ilp1.interfaces.IASTalternative iast_old) throws EvaluationException {
		//cast
		IASTalternative iast = (IASTalternative) iast_old;

		StringBuilder sb = new StringBuilder();

	    String conditionCode = iast.getCondition().accept(this).toString();
	    String consequenceCode = iast.getConsequence().accept(this).toString();

		sb.append("new ASTalternative(");
		sb.append(conditionCode);
		sb.append(",");
		sb.append(consequenceCode);

	    if (iast.isTernary()) {
	        String alternantCode = iast.getAlternant().accept(this).toString();
			sb.append(",");
			sb.append(alternantCode);
	    } else {
			sb.append(",new NULL()");
	    }
		sb.append(")");
		return sb.toString();
	}
	@Override
	public Object visit(com.paracamplus.ilp1.interfaces.IASTboolean iast) {
		return String.valueOf(iast.getValue());
    }
    @Override
    public Object visit(com.paracamplus.ilp1.interfaces.IASTinteger iast) {
        return String.valueOf(iast.getValue());
    }
    @Override
    public Object visit(com.paracamplus.ilp1.interfaces.IASTfloat iast) {
		return String.valueOf(iast.getValue());
    }

	@Override
    public Object visit(com.paracamplus.ilp1.interfaces.IASTstring iast) {
		return iast.getValue();
	}

	@Override
	public Object visit(IASTsequence iast) throws EvaluationException {
		//a completer
		return null;
	}


	//inutile ici?
//    public String visit(Inamed javaNode) {
//        String name = javaNode.getName();
//
//        String ilpCode = "class ASTnamed extends AST {\n" +
//                         "    var name = \"" + name + "\";\n\n" +
//                         "    method getName () \n" +
//                         "      self.name;\n" +
//                         "}\n";
//
//        return ilpCode;
//    }

}
