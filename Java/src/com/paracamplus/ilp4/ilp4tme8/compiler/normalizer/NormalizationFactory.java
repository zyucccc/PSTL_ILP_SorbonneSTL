package com.paracamplus.ilp4.ilp4tme8.compiler.normalizer;

import com.paracamplus.ilp4.ilp4tme8.ast.ASThasProperty;
import com.paracamplus.ilp4.ilp4tme8.ast.ASTreadProperty;
import com.paracamplus.ilp4.ilp4tme8.ast.ASTwriteProperty;
import com.paracamplus.ilp1.interfaces.IASTexpression;


public class NormalizationFactory 
extends com.paracamplus.ilp4.compiler.normalizer.NormalizationFactory 
implements INormalizationFactory {

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
