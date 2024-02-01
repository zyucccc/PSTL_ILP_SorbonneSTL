package com.paracamplus.ilp4.ilp4tme10.compiler.normalizer;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp4.compiler.interfaces.IASTCclassDefinition;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTdefined;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTexists;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTvisitor;


public class Normalizer 
    extends com.paracamplus.ilp4.compiler.normalizer.Normalizer
	implements IASTvisitor<IASTexpression, INormalizationEnvironment, CompilationException> {

		INormalizationFactory factoryTME10;
	
	   public Normalizer (INormalizationFactory factory,
               IASTCclassDefinition objectClass ) {
		   super(factory,objectClass);
		   factoryTME10 = factory;
	   }
	   
	@Override
	public IASTexpression visit(IASTexists iast, INormalizationEnvironment data)
			throws CompilationException {
		return factoryTME10.newExists(visit(iast.getVariable(), data));
	}

	@Override
	public IASTexpression visit(IASTdefined iast, INormalizationEnvironment data)
			throws CompilationException {
		return factoryTME10.newDefined(visit(iast.getVariable(), data));
	}
	   
}
