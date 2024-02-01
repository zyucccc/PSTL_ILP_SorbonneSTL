package com.paracamplus.ilp4.ilp4tme8.interpreter.interfaces;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

public interface IInstance extends com.paracamplus.ilp4.interpreter.interfaces.IInstance {
	
	public boolean hasProperty(String fieldName)
	        throws EvaluationException;
		
		public Object readProperty(String fieldName) 
			throws EvaluationException;
		
		public Object writeProperty(String fieldName, Object value)
			throws EvaluationException;

}
