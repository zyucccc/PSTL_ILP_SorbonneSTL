package com.paracamplus.pstl.interfaces;


import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.pstl.interfaces.ilp1.IASTalternative;

public interface IASTvisitor<Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp4.interfaces.IASTvisitor<Result, Data, Anomaly>{
    Result visit(IASTincludeDefinition iast, Data data) throws Anomaly;

}
