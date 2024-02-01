package com.paracamplus.ilp4.ilp4tme8.interfaces;
import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory {
	IASTexpression newExistsProperty(
			IASTexpression target,
			IASTexpression property);
	
	IASTexpression newReadProperty(
			IASTexpression target,
			IASTexpression property);
	
	IASTexpression newWriteProperty(
			IASTexpression target,
			IASTexpression property,
			IASTexpression value);
	

}
