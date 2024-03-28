package com.paracamplus.pstl.ast_java.ilp1;

import com.paracamplus.pstl.interfaces.IASTvisitor_Convert;
import com.paracamplus.pstl.interfaces.ilp1.IASTboolean;

public class ASTboolean extends com.paracamplus.ilp1.ast.ASTboolean implements IASTboolean {
    public ASTboolean(String description) {
        super(description);
    }

    @Override
    public <Result, Anomaly extends Throwable> Result accept(IASTvisitor_Convert<Result, Anomaly> visitor) throws Anomaly {
        return ((IASTvisitor_Convert<Result, Anomaly>) visitor).visit(this);
    }
}
