package com.paracamplus.pstl.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTarrayRead extends IASTarray{
	public IASTexpression getTarget();
	public IASTexpression getIndex();

}
