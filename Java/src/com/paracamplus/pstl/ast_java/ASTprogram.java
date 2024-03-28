package com.paracamplus.pstl.ast_java;

import java.util.Arrays;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.pstl.interfaces.IASTincludeDefinition;
import com.paracamplus.pstl.interfaces.IASTprogram;
//import com.paracamplus.pstl.interfaces.ilp1.IASTexpression;

public class ASTprogram extends com.paracamplus.ilp4.ast.ASTprogram
implements IASTprogram {
	//change-
    public ASTprogram(IASTfunctionDefinition[] functions,
                      IASTclassDefinition[] clazzes,
//					  com.paracamplus.pstl.interfaces.ilp1.
							  IASTexpression expression,
                      IASTincludeDefinition[] includes) {
    	super(functions,clazzes,expression);
    	this.includes = includes;
		this.expression = expression;
    }
    
    protected IASTincludeDefinition[] includes;

	//change-
	protected
//	com.paracamplus.pstl.interfaces.ilp1.
			IASTexpression expression;
    
	@Override
	public IASTincludeDefinition[] getIncludes() {
		return includes;
	}
	@Override
	public void addClassDefinition(IASTclassDefinition new_class) {
	    IASTclassDefinition[] newClasses = new IASTclassDefinition[clazzes.length + 1];
	    System.arraycopy(clazzes, 0, newClasses, 0, clazzes.length);
	    newClasses[clazzes.length] = new_class;
	    this.clazzes = newClasses;
	}

	@Override
	public void addFunctionDefinition(IASTfunctionDefinition newFunction) {
	    IASTfunctionDefinition[] newFunctions = new IASTfunctionDefinition[functions.size() + 1]; 
	    for (int i = 0; i < functions.size(); i++) {
	        newFunctions[i] = functions.get(i);
	    }  
	    newFunctions[functions.size()] = newFunction;
	    this.functions = Arrays.asList(newFunctions);
	}
	//change-
	@Override
	public void updateExpression(
//			com.paracamplus.pstl.interfaces.ilp1.
					IASTexpression new_expr) {
		this.expression = new_expr;
	}

	//change-
	public
//	com.paracamplus.pstl.interfaces.ilp1.
			IASTexpression getBody() {
		return this.expression;
	}
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Classes:\n");
        for (IASTclassDefinition clazz : clazzes) {
            sb.append(" - ").append(clazz.getName()).append("\n");
        }
        
        sb.append("Functions:\n");
        for (IASTfunctionDefinition function : functions) {
            sb.append(" - ").append(function.getName()).append("\n");
        }
        
         //to_string exprs?
        sb.append("Expression: ").append(expression).append("\n");
        
        return sb.toString();
    }

//	@Override
//	public <Result, Anomaly extends Throwable> Result accept(com.paracamplus.pstl.interfaces.IASTvisitor_Convert<Result, Anomaly> visitor) throws Anomaly {
//		return ((com.paracamplus.pstl.interfaces.IASTvisitor_Convert<Result, Anomaly>) visitor).visit(this);
//	}
//
//	@Override
//	public <Result, Data, Anomaly extends Throwable> Result accept(IASTvisitor<Result, Data, Anomaly> visitor, Data data) throws Anomaly {
//		return com.paracamplus.pstl.interfaces.IASTvisitor.visit(this, data);
//	}
}
