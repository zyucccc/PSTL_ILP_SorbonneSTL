package com.paracamplus.pstl.interpreter.interfaces;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

public interface IInstance extends com.paracamplus.ilp4.interpreter.interfaces.IInstance{
	public Object arrayWrite(String fieldName, Object value)throws EvaluationException;
	public Object arrayRead(String fieldName)throws EvaluationException;

}
