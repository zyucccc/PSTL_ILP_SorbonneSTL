package com.paracamplus.pstl.ast_java.ilp1;

import com.paracamplus.pstl.interfaces.ilp1.IASTstring;

public class ASTstring extends com.paracamplus.ilp1.ast.ASTstring implements IASTstring {
    public ASTstring(String value) {
        super(value);
    }

    @Override
    public <Result, Anomaly extends Throwable> Result accept(com.paracamplus.pstl.interfaces.IASTvisitor_Convert<Result, Anomaly> visitor) throws Anomaly {
        return ((com.paracamplus.pstl.interfaces.IASTvisitor_Convert<Result, Anomaly>) visitor).visit(this);
    }
}
