package com.paracamplus.ilp4.ilp4tme10.compiler.normalizer;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.ilp4tme10.ast.ASTdefined;
import com.paracamplus.ilp4.ilp4tme10.ast.ASTexists;


public class NormalizationFactory
	extends com.paracamplus.ilp4.compiler.normalizer.NormalizationFactory
	implements INormalizationFactory {

	 @Override
	public IASTexpression newExists(IASTvariable variable) {
		return new ASTexists(variable);
	}

	@Override
	public IASTexpression newDefined(IASTvariable variable) {
		return new ASTdefined(variable);
	}


}
