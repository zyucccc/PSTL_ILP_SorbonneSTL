package com.paracamplus.pstl.ast_java;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.pstl.interfaces.IASTarrayWrite;
import com.paracamplus.pstl.interfaces.IASTvisitor;

public class ASTarrayWrite extends ASTexpression
implements IASTarrayWrite {

    public ASTarrayWrite (
                          IASTexpression target,
                          IASTexpression index,
                          IASTexpression value) {
        this.index = index;
        this.target = target;
        this.value = value;
    }
    private final IASTexpression index;
    private final IASTexpression target;
    private final IASTexpression value;
    
    public IASTexpression getTarget() {
        return target;
    }

    public IASTexpression getIndex() {
        return index;
    }
    
    public IASTexpression getValue() {
        return value;
    }

    // Le visiteur doit en réalité être un IASTvisitorTME8 ou un IASTCvisitorTME8
    public <Result, Data, Anomaly extends Throwable> Result 
        accept(com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor, Data data)
                throws Anomaly {
    	if (visitor instanceof IASTvisitor) {
    		return ((IASTvisitor<Result,Data,Anomaly>)visitor).visit(this,data);
    	}
//    	if (visitor instanceof IASTCvisitor) {
//    		return ((IASTCvisitor<Result,Data,Anomaly>)visitor).visit(this,data);
//    	}
    	throw new IllegalArgumentException(
    			"visitor argument must implement IASTvisitorTME8 ou IASTCvisitorTME8");
    }
}