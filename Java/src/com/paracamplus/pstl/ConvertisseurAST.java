//package com.paracamplus.pstl;
//
//import com.paracamplus.ilp1.ast.ASTnamed;
//import com.paracamplus.ilp1.interfaces.IASTalternative;
//import com.paracamplus.ilp1.interfaces.IASTboolean;
//import com.paracamplus.ilp1.interfaces.IASTfloat;
//import com.paracamplus.ilp1.interfaces.IASTinteger;
//import com.paracamplus.ilp1.interfaces.IASTstring;
//import com.paracamplus.ilp1.interfaces.Inamed;
//import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
//import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
//import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
//import com.paracamplus.pstl.interfaces.IASTprogram;
//
//public class ConvertisseurAST {
//
//	//constructeur
//	public ConvertisseurAST(IASTprogram program) {
//         visit(program);
//    }
//
//	//ast expression
//	//definir des methodes pour collecter var globals
//	//blabla
//
//	public String visit(IASTprogram iast) {
//
//	// traiter tous les includes  en java
//	String bibliotheque = "";//tous les copie de texte de bibliotheque: ASTs,liste chainee,NULL
//
//	String program = "";
//	String classes = "";
//	String functions = "";
//	String exprs = "";
//
//	//classes:
//	for ( IASTclassDefinition cd : iast.getClassDefinitions() ) {
//           classes += this.visit(cd);
//     }
//	//functions:
//	for ( IASTfunctionDefinition fd : iast.getFunctionDefinitions() ) {
//		functions += this.visit(fd);
//        String v = fd.getName();
//        //mecanisme global variable collector?
//
//    }
//
//	//collecter les var global
//	// function global stocker une liste des var globals
//
//	//expressions:
//	       exprs += this.visit(iast.getBody());
//
//	       program = String.format ("new ASTprogram(%s,%s,%s)",classes,functions,exprs);
//	       return bibliotheque += program;
//	}
//	//plus efficace
//	//String builder
//
//	//exemple Alternative
//	public String visit(IASTalternative iast) {
//
//	    String conditionCode = visit(iast.getCondition());
//	    String consequenceCode = visit(iast.getConsequence());
//	    String alternantCode = "";
//
//	    if (iast.isTernary()) {
//	        alternantCode = visit(iast.getAlternant());
//	        return String.format("new ASTalternative(%s,%s,%s)", conditionCode, consequenceCode, alternantCode);
//	    } else {
//	        return String.format("new ASTalternative(%s,%s,new NULL())", conditionCode, consequenceCode);
//	    }
//	}
//	public String visit(IASTboolean iast) {
//        return String.valueOf(iast.getValue());
//    }
//
//    public String visit(IASTinteger iast) {
//
//        return String.valueOf(iast.getValue());
//    }
//
//
//    public String visit(IASTfloat iast) {
//        return String.valueOf(iast.getValue());
//    }
//
//
//    public String visit(IASTstring iast) {
//        return iast.getValue();
//	}
//
//	public String visit() {
//
//	}
//
//	//inutile ici?
////    public String visit(Inamed javaNode) {
////        String name = javaNode.getName();
////
////        String ilpCode = "class ASTnamed extends AST {\n" +
////                         "    var name = \"" + name + "\";\n\n" +
////                         "    method getName () \n" +
////                         "      self.name;\n" +
////                         "}\n";
////
////        return ilpCode;
////    }
//
//}
