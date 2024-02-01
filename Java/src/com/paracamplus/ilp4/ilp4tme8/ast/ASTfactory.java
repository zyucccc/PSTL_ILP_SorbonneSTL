package com.paracamplus.ilp4.ilp4tme8.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTfactory;

public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory  implements IASTfactory {
	
	
	@Override
	public IASTexpression newExistsProperty(IASTexpression target,
			IASTexpression property) {
		return new ASThasProperty(target, property);
	}

	@Override
	public IASTexpression newReadProperty(IASTexpression target,
			IASTexpression property) {
		return new ASTreadProperty(target, property);
	}

	@Override
	public IASTexpression newWriteProperty(IASTexpression target,
			IASTexpression property, IASTexpression value) {
		return new ASTwriteProperty(target, property, value);
	}

}
