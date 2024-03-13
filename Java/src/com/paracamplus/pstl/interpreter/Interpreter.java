package com.paracamplus.pstl.interpreter;

import java.util.List;
import java.util.Vector;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp3.interpreter.primitive.Throw.ThrownException;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.ilp4.interfaces.IASTmethodDefinition;
import com.paracamplus.pstl.interfaces.IASTprogram;
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
	
	 @Override 
	    public Object visit(com.paracamplus.ilp1.interfaces.IASTprogram iast, ILexicalEnvironment lexenv) throws EvaluationException {
	    	return visit((IASTprogram)iast, lexenv);
	    }

	  
	    public Object visit(IASTprogram iast, ILexicalEnvironment lexenv) 
	            throws EvaluationException {
	    	for ( IASTincludeDefinition include : iast.getIncludes() ) {
	            this.visit(include, lexenv);
	        }
	        for ( IASTclassDefinition cd : iast.getClassDefinitions() ) {
	            this.visit(cd, lexenv);
	        }
	        for ( IASTfunctionDefinition fd : iast.getFunctionDefinitions() ) {
	            Object f = this.visit(fd, lexenv);
	            String v = fd.getName();
	            getGlobalVariableEnvironment().addGlobalVariableValue(v, f);
	        }
	        try {
	            return iast.getBody().accept(this, lexenv);
	        } catch (ThrownException exc) {
	            return exc.getThrownValue();
	        } catch (Exception exc) {
	            return exc;
	        }
	    }

	public Object visit(IASTincludeDefinition iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
		System.out.println("Test Interpreter");
		String filepath = iast.getFilepath();
		System.out.println("Test path:"+filepath);
		// to do
		return null;
	}
	
	@Override
	 public IClass visit(IASTclassDefinition iast, ILexicalEnvironment lexenv) 
	            throws EvaluationException {
//		System.out.println("Test Interpreter class");
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
