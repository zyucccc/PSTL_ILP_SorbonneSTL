package com.paracamplus.pstl.ast_java;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.pstl.interfaces.IASTelement;
import com.paracamplus.pstl.interfaces.IASTprogram;

public class ASTprogram extends com.paracamplus.ilp4.ast.ASTprogram
implements IASTprogram {
    public ASTprogram(IASTfunctionDefinition[] functions,
                      IASTclassDefinition[] clazzes,
                      IASTexpression expression) {
    	super(functions,clazzes,expression);
    }
    
    protected IASTelement[] elements;
    
	@Override
	public IASTelement[] getElements() {
		return elements;
	}
  
}
