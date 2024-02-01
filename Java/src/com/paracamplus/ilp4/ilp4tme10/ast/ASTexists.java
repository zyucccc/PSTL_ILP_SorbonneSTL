package com.paracamplus.ilp4.ilp4tme10.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.ilp4tme10.compiler.interfaces.IASTCvisitor;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTexists;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTvisitor;

public class ASTexists extends ASTexpression implements IASTexists {

	public ASTexists(IASTvariable variable) {
		this.variable = variable;
	}
	
	protected IASTvariable variable;
	
	public IASTvariable getVariable() {
		return variable;
	}
	
	@Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor, Data data)
			throws Anomaly {
		if (visitor instanceof IASTCvisitor) {
			return ((IASTCvisitor<Result,Data,Anomaly>)visitor).visit(this,data);
		}
		else if (visitor instanceof IASTvisitor) {
			return ((IASTvisitor<Result,Data,Anomaly>)visitor).visit(this,data);
		}
		else {
		   	throw new IllegalArgumentException(
	    			"illegal visitor");
		}
	}
}
