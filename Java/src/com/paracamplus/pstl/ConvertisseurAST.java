package com.paracamplus.pstl;

import com.paracamplus.ilp1.ast.ASTnamed;
import com.paracamplus.ilp1.interfaces.IASTalternative;
import com.paracamplus.ilp1.interfaces.Inamed;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.pstl.interfaces.IASTprogram;

public class ConvertisseurAST {
	
	//constructeur
	public ConvertisseurAST(IASTprogram program) {
         visit(program);
    }
	
	public String visit(IASTprogram iast) {
	String ilpCode = "";
		
	//classes:
	for ( IASTclassDefinition cd : iast.getClassDefinitions() ) {
           ilpCode += this.visit(cd);
     }
	//functions:
	for ( IASTfunctionDefinition fd : iast.getFunctionDefinitions() ) {
		ilpCode += this.visit(fd);
        String v = fd.getName();
        //mecanisme global variable collector?
        
    }
	//expressions:
	       ilpCode += this.visit(iast.getBody());
	       
	       return ilpCode;
	}
	
	public String visit() {
		
	}
	
	
	
	public String visit(IASTalternative iast) {

	    String conditionCode = visit(iast.getCondition());
	    String consequenceCode = visit(iast.getConsequence());
	    String alternantCode = "";

	    if (iast.isTernary()) {
	        alternantCode = visit(iast.getAlternant());
	        return String.format("new ASTalternative", conditionCode, consequenceCode, alternantCode);
	    } else {
	        return String.format("if (%s) then (%s)", conditionCode, consequenceCode);
	    }
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
