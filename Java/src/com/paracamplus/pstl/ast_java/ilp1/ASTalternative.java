package com.paracamplus.pstl.ast_java.ilp1;

import com.paracamplus.ilp1.annotation.OrNull;
import com.paracamplus.pstl.interfaces.ilp1.IASTexpression;
import com.paracamplus.pstl.interfaces.IASTvisitor_Convert;
import com.paracamplus.pstl.interfaces.ilp1.IASTalternative;

public class ASTalternative extends com.paracamplus.ilp1.ast.ASTalternative implements IASTalternative {
    public ASTalternative(IASTexpression condition, IASTexpression consequence, IASTexpression alternant) {
        super(condition, consequence, alternant);
        this.condition = condition;
        this.consequence = consequence;
        this.alternant = alternant;
    }

    private final IASTexpression condition;
    private final IASTexpression consequence;
    private @OrNull
    final IASTexpression alternant;

    @Override
    public IASTexpression getCondition() {
        return condition;
    }

    @Override
    public IASTexpression getConsequence() {
        return consequence;
    }

    @Override @OrNull
    public IASTexpression getAlternant() {
        return alternant;
    }


    @Override
    public <Result, Anomaly extends Throwable> Result accept(IASTvisitor_Convert<Result, Anomaly> visitor) throws Anomaly {
        return ((IASTvisitor_Convert<Result, Anomaly>) visitor).visit(this);
    }
}
