package com.paracamplus.pstl.interfaces;


public interface IASTvisitor<Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp4.interfaces.IASTvisitor<Result, Data, Anomaly>{

    Result visit(IASTarrayRead iast, Data data) throws Anomaly;
    Result visit(IASTarrayWrite iast, Data data) throws Anomaly;
    Result visit(IASTarrayInitiation iast, Data data) throws Anomaly;

}
