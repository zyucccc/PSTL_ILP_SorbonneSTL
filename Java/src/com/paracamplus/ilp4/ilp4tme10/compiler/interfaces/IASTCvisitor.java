package com.paracamplus.ilp4.ilp4tme10.compiler.interfaces;

import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTdefined;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTexists;

public interface IASTCvisitor<Result, Data, Anomaly extends Throwable> 
extends com.paracamplus.ilp4.compiler.interfaces.IASTCvisitor<Result, Data, Anomaly>,
com.paracamplus.ilp4.ilp4tme10.interfaces.IASTvisitor<Result, Data, Anomaly> {


Result visit(IASTexists iast, Data data) throws Anomaly;
Result visit(IASTdefined iast, Data data) throws Anomaly;

}
