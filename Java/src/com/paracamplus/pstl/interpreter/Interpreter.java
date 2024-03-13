package com.paracamplus.pstl.interpreter;

import java.util.List;
import java.util.Vector;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.ilp4.interfaces.IASTmethodDefinition;
import com.paracamplus.ilp4.interfaces.IASTvisitor;
import com.paracamplus.ilp4.interpreter.ILPClass;
import com.paracamplus.ilp4.interpreter.interfaces.IClass;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;
import com.paracamplus.ilp4.interpreter.interfaces.IMethod;
import com.paracamplus.pstl.interfaces.IASTincludeDefinition;

public class Interpreter extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {
	public Interpreter(
			IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment,
			IClassEnvironment classEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment, classEnvironment);
	}

	public Object visit(IASTincludeDefinition iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
		System.out.println("Test Interpreter");
		String filepath = iast.getFilepath();
		System.out.println("Test path:"+filepath);
		// to do
		return "abc";
	}
	
	@Override
	 public IClass visit(IASTclassDefinition iast, ILexicalEnvironment lexenv) 
	            throws EvaluationException {
		System.out.println("Test Interpreter class");
	        List<IMethod> methods = new Vector<>();
	        for ( IASTmethodDefinition md : iast.getProperMethodDefinitions() ) {
	            IMethod m = visit(md, lexenv);
	            methods.add(m);
	        }
	        IClass clazz = new ILPClass(
	                getClassEnvironment(),
	                iast.getName(),
	                iast.getSuperClassName(),
	                iast.getProperFieldNames(),
	                methods.toArray(new IMethod[0]) );
	        return clazz;
	    }
    
    
}
