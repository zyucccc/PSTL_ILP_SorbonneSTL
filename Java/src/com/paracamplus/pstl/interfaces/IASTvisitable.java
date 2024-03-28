package com.paracamplus.pstl.interfaces;

import com.paracamplus.pstl.interfaces.IASTvisitor_Convert;

public interface IASTvisitable extends com.paracamplus.ilp1.interfaces.IASTvisitable {
    <Result,Anomaly extends Throwable>
    Result accept(IASTvisitor_Convert<Result, Anomaly> visitor) throws Anomaly;
}
