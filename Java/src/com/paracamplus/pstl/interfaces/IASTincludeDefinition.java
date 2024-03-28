package com.paracamplus.pstl.interfaces;

import com.paracamplus.ilp1.interfaces.IASTvisitable;
import com.paracamplus.ilp2.interfaces.IASTdeclaration;

public interface IASTincludeDefinition extends IASTdeclaration,IASTvisitable {
	public String getFilepath();

}
