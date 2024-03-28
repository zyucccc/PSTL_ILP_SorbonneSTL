package com.paracamplus.pstl.interfaces;

import com.paracamplus.ilp1.interfaces.*;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;


public interface IASTvisitor_Convert<Result, Anomaly extends Throwable> {
    //ilp1
    Result visit(IASTalternative iast) throws Anomaly;
    Result visit(IASTboolean iast) throws Anomaly;
    Result visit(IASTfloat iast) throws Anomaly;
    Result visit(IASTinteger iast) throws Anomaly;
    Result visit(IASTstring iast) throws Anomaly;
    Result visit(IASTsequence iast) throws Anomaly;
    //ilp4
    Result visit(IASTclassDefinition iast) throws Anomaly;


}