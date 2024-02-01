package com.paracamplus.ilp4.ilp4tme8.compiler;

import java.util.Set;

import com.paracamplus.ilp4.ilp4tme8.compiler.interfaces.IASTCvisitor;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASThasProperty;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTreadProperty;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTwriteProperty;
import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalVariable;

public class GlobalVariableCollector extends com.paracamplus.ilp4.compiler.GlobalVariableCollector 
implements IASTCvisitor<
Set<IASTCglobalVariable>, 
Set<IASTCglobalVariable>, 
CompilationException> {
	
	@Override
	public Set<IASTCglobalVariable> visit(IASThasProperty iast,
			Set<IASTCglobalVariable> data) throws CompilationException {
		data = iast.getTarget().accept(this,data);
		data = iast.getProperty().accept(this,data);
		return data;
	}

	@Override
	public Set<IASTCglobalVariable> visit(IASTreadProperty iast,
			Set<IASTCglobalVariable> data) throws CompilationException {
		data = iast.getTarget().accept(this,data);
		data = iast.getProperty().accept(this,data);
		return data;
	}

	@Override
	public Set<IASTCglobalVariable> visit(IASTwriteProperty iast,
			Set<IASTCglobalVariable> data) throws CompilationException {
		data = iast.getTarget().accept(this,data);
		data = iast.getProperty().accept(this,data);
		data = iast.getValue().accept(this,data);
		return data;
	}
	
	

}
