package com.paracamplus.pstl.interfaces;

import com.paracamplus.pstl.interfaces.ilp1.IASTalternative;

public interface IASTvisitor_Convert<Result, Anomaly extends Throwable> {
    Result visit(IASTalternative iast) throws Anomaly;

}