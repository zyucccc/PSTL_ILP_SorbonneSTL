package com.paracamplus.pstl.interfaces.ilp1;

import com.paracamplus.ilp1.annotation.OrNull;
import com.paracamplus.pstl.interfaces.IASTvisitable;

public interface IASTalternative extends com.paracamplus.ilp1.interfaces.IASTalternative, IASTvisitable,IASTexpression{

    IASTexpression getCondition();
    IASTexpression getConsequence();
    @OrNull
    IASTexpression getAlternant();

}