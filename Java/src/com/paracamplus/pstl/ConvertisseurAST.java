package com.paracamplus.pstl;

import com.paracamplus.ilp1.ast.ASTnamed;
import com.paracamplus.pstl.interfaces.ilp1.IASTalternative;
import com.paracamplus.ilp1.interfaces.IASTboolean;
import com.paracamplus.ilp1.interfaces.IASTfloat;
import com.paracamplus.ilp1.interfaces.IASTinteger;
import com.paracamplus.ilp1.interfaces.IASTstring;
import com.paracamplus.ilp1.interfaces.Inamed;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;

//pstl import

import com.paracamplus.pstl.interfaces.IASTprogram;
import com.paracamplus.pstl.interfaces.IASTvisitor_Convert;

public class ConvertisseurAST implements IASTvisitor_Convert<Object, EvaluationException> {

	//constructeur
	public ConvertisseurAST(IASTprogram program) {
         visit(program);
    }

	//ast expression
	//definir des methodes pour collecter var globals
	//blabla

	public String visit(IASTprogram iast) {

	String program = "";
	String classes = "";
	String functions = "";
	String exprs = "";

	//classes:
	for ( IASTclassDefinition cd : iast.getClassDefinitions() ) {
           classes += this.visit(cd);
     }
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
//	       exprs += this.visit(iast.getBody());

	       program = String.format ("new ASTprogram(%s,%s,%s)",classes,functions,exprs);
	       return  program;
	}
	//plus efficace
	//String builder
	@Override
	public Object visit(IASTalternative iast) throws EvaluationException {
		StringBuilder sb = new StringBuilder();
//        String test = iast.accept(this).toString();
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
	public String visit(IASTboolean iast) {
		return String.valueOf(iast.getValue());
    }

    public String visit(IASTinteger iast) {
        return String.valueOf(iast.getValue());
    }

    public String visit(IASTfloat iast) {
		return String.valueOf(iast.getValue());
    }


    public String visit(IASTstring iast) {

		return iast.getValue();
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
