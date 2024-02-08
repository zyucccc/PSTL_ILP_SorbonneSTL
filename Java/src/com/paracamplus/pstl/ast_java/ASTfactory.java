package com.paracamplus.pstl.ast_java;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.pstl.interfaces.IASTfactory;


public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory  implements IASTfactory {
	
	
	@Override
	public IASTexpression newArrayInitiation(IASTexpression target, IASTexpression index) {
		return null;
	}

	@Override
	public IASTexpression newArrayRead(IASTexpression target, IASTexpression index) {
		return new ASTarrayRead(target, index);
	}

	@Override
	public IASTexpression newArrayWrite(IASTexpression target, IASTexpression index, IASTexpression value) {
		return new ASTarrayWrite(target, index, value);
	}

}
