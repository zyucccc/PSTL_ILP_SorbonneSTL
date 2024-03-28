package com.paracamplus.pstl.interfaces;




public interface IASTvisitor<Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp4.interfaces.IASTvisitor<Result, Data, Anomaly>{
    Result visit(IASTincludeDefinition iast, Data data) throws Anomaly;

}
