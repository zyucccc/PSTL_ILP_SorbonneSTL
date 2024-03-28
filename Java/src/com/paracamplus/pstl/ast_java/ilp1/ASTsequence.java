package com.paracamplus.pstl.ast_java.ilp1;

import com.paracamplus.pstl.interfaces.IASTvisitor_Convert;
import com.paracamplus.pstl.interfaces.ilp1.IASTexpression;
import com.paracamplus.pstl.interfaces.ilp1.IASTsequence;

public class ASTsequence extends com.paracamplus.ilp1.ast.ASTsequence implements IASTsequence {
    public ASTsequence(IASTexpression[] expressions) {
        super(expressions);
        this.expressions = expressions;
    }
    protected IASTexpression[] expressions;

    @Override
    public com.paracamplus.ilp1.interfaces.IASTexpression[] getExpressions() {
        return this.expressions;
    }

    @Override
    public <Result, Anomaly extends Throwable> Result accept(IASTvisitor_Convert<Result, Anomaly> visitor) throws Anomaly {
        return ((IASTvisitor_Convert<Result, Anomaly>) visitor).visit(this);
    }
}
