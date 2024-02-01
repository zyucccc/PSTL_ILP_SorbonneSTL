package com.paracamplus.ilp4.ilp4tme8.compiler.normalizer;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.compiler.interfaces.IASTCclassDefinition;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASThasProperty;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTreadProperty;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTvisitor;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTwriteProperty;

public class Normalizer extends com.paracamplus.ilp4.compiler.normalizer.Normalizer 
implements IASTvisitor<IASTexpression, INormalizationEnvironment, CompilationException> {
	
		protected INormalizationFactory factoryTME8;
		
		public Normalizer(INormalizationFactory factory,
				IASTCclassDefinition objectClass) {
			super(factory, objectClass);
			this.factoryTME8 = factory;
		}

		@Override
		public IASTexpression visit(IASThasProperty iast,
				INormalizationEnvironment env) throws CompilationException {
	        IASTexpression target = iast.getTarget().accept(this, env);
	        IASTexpression property = iast.getProperty().accept(this, env);	
	        return factoryTME8.newExistsProperty(target, property);		
		}

		@Override
		public IASTexpression visit(IASTreadProperty iast,
				INormalizationEnvironment env) throws CompilationException {
	        IASTexpression target = iast.getTarget().accept(this, env);
	        IASTexpression property = iast.getProperty().accept(this, env);	
	        return factoryTME8.newReadProperty(target, property);		
		}

		@Override
		public IASTexpression visit(IASTwriteProperty iast,
				INormalizationEnvironment env) throws CompilationException {
	        IASTexpression target = iast.getTarget().accept(this, env);
	        IASTexpression property = iast.getProperty().accept(this, env);	
	        IASTexpression value = iast.getValue().accept(this, env);	
	        return factoryTME8.newWriteProperty(target, property, value);		
		}
	
}
