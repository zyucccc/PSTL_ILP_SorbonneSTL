package com.paracamplus.pstl.interfaces;


import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
//import com.paracamplus.pstl.interfaces.ilp1.IASTexpression;

public interface IASTprogram extends com.paracamplus.ilp4.interfaces.IASTprogram {
	public IASTincludeDefinition[] getIncludes();
	public void addClassDefinition(IASTclassDefinition new_class);
	public void addFunctionDefinition(IASTfunctionDefinition new_func);
	//change-
	public
//	com.paracamplus.pstl.interfaces.ilp1.
			IASTexpression getBody();
	//change-
	public void updateExpression(
//			com.paracamplus.pstl.interfaces.ilp1.
					IASTexpression new_expr);
//	IASTelement[] getElements();
}
