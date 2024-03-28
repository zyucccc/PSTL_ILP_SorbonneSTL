package com.paracamplus.pstl.ast_java.ilp1;

import com.paracamplus.pstl.interfaces.ilp1.IASTinteger;

public class ASTinteger extends com.paracamplus.ilp1.ast.ASTinteger implements IASTinteger {
    public ASTinteger(String description) {
        super(description);
    }

    @Override
    public <Result, Anomaly extends Throwable> Result accept(com.paracamplus.pstl.interfaces.IASTvisitor_Convert<Result, Anomaly> visitor) throws Anomaly {
        return ((com.paracamplus.pstl.interfaces.IASTvisitor_Convert<Result, Anomaly>) visitor).visit(this);
    }
}
