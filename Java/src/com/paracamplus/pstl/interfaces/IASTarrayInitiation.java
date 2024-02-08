package com.paracamplus.pstl.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTarrayInitiation extends IASTarray {
	
	public IASTexpression getType();
	public IASTexpression getSize();

}
