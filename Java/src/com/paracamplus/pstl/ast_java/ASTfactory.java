package com.paracamplus.pstl.ast_java;

import com.paracamplus.pstl.interfaces.IASTfactory;
import com.paracamplus.pstl.interfaces.IASTincludeDefinition;


public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory  implements IASTfactory {

	@Override
	public IASTincludeDefinition newIncludeDefinition(String filepath) {
		return new ASTincludeDefinition(filepath);
	}
	

	

}
