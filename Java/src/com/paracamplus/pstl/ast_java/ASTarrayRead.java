package com.paracamplus.pstl.ast_java;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.pstl.interfaces.IASTvisitor;
import com.paracamplus.pstl.interfaces.IASTarrayRead;

public class ASTarrayRead extends ASTexpression
implements IASTarrayRead {

    public ASTarrayRead (IASTexpression target, IASTexpression index) {
        this.target = target;
    	this.index = index;
    }
    private final IASTexpression index;
    private final IASTexpression target;
    
    public IASTexpression getTarget() {
        return target;
    }

    public IASTexpression getIndex() {
        return index;
    }

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