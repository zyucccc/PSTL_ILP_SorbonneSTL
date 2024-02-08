package com.paracamplus.pstl.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory {
	IASTexpression newArrayInitiation(
			IASTexpression target,
			IASTexpression index);
	
	IASTexpression newArrayRead(
			IASTexpression target,
			IASTexpression index);
	
	IASTexpression newArrayWrite(
			IASTexpression target,
			IASTexpression index,
			IASTexpression value);
	

}
