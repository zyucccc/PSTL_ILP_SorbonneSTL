package com.paracamplus.pstl.ast_java;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.pstl.interfaces.IASTarrayInitiation;
import com.paracamplus.pstl.interfaces.IASTvisitor;

public class ASTarrayInitiation extends ASTexpression
implements IASTarrayInitiation {

    public ASTarrayInitiation (IASTexpression type, IASTexpression size) {
        this.type = type;
    	this.size = size;
    }
    private final IASTexpression size;
    private final IASTexpression type;
    
    public IASTexpression getType() {
        return type;
    }

    public IASTexpression getSize() {
        return size;
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