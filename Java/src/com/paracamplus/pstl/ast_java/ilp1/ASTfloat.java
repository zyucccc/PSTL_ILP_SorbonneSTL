package com.paracamplus.pstl.ast_java.ilp1;

import com.paracamplus.pstl.interfaces.IASTvisitor_Convert;
import com.paracamplus.pstl.interfaces.ilp1.IASTfloat;

public class ASTfloat extends com.paracamplus.ilp1.ast.ASTfloat implements IASTfloat {
    public ASTfloat(String description) {
        super(description);
    }

    @Override
    public <Result, Anomaly extends Throwable> Result accept(IASTvisitor_Convert<Result, Anomaly> visitor) throws Anomaly {
        return ((IASTvisitor_Convert<Result, Anomaly>) visitor).visit(this);
    }
}
