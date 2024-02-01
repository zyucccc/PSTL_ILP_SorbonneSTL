package com.paracamplus.ilp4.ilp4tme8.compiler.interfaces;

import com.paracamplus.ilp4.ilp4tme8.interfaces.IASThasProperty;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTreadProperty;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTwriteProperty;

public interface IASTCvisitor<Result, Data, Anomaly extends Throwable> 
	extends com.paracamplus.ilp4.compiler.interfaces.IASTCvisitor<Result, Data, Anomaly> {


	Result visit(IASThasProperty iast, Data data) throws Anomaly;
	Result visit(IASTreadProperty iast, Data data) throws Anomaly;
	Result visit(IASTwriteProperty iast, Data data) throws Anomaly;
}
