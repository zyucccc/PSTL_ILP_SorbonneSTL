package com.paracamplus.pstl.ast_java;

import com.paracamplus.ilp1.ast.ASTalternative;
import com.paracamplus.ilp1.ast.ASTbinaryOperation;
import com.paracamplus.ilp1.ast.ASTblock;
import com.paracamplus.ilp1.ast.ASTboolean;
import com.paracamplus.ilp1.ast.ASTfloat;
import com.paracamplus.ilp1.ast.ASTinteger;
import com.paracamplus.ilp1.ast.ASTinvocation;
import com.paracamplus.ilp1.ast.ASToperator;
import com.paracamplus.ilp1.ast.ASTsequence;
import com.paracamplus.ilp1.ast.ASTstring;
import com.paracamplus.ilp1.ast.ASTunaryOperation;
import com.paracamplus.ilp1.ast.ASTvariable;
import com.paracamplus.ilp1.interfaces.IASTalternative;
import com.paracamplus.ilp1.interfaces.IASTbinaryOperation;
import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTboolean;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTfloat;
import com.paracamplus.ilp1.interfaces.IASTinteger;
import com.paracamplus.ilp1.interfaces.IASTinvocation;
import com.paracamplus.ilp1.interfaces.IASToperator;
import com.paracamplus.ilp1.interfaces.IASTsequence;
import com.paracamplus.ilp1.interfaces.IASTstring;
import com.paracamplus.ilp1.interfaces.IASTunaryOperation;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interfaces.IASTblock.IASTbinding;
import com.paracamplus.ilp2.ast.ASTassignment;
import com.paracamplus.ilp2.ast.ASTfunctionDefinition;
import com.paracamplus.ilp2.ast.ASTloop;
import com.paracamplus.ilp2.interfaces.IASTassignment;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp2.interfaces.IASTloop;
import com.paracamplus.ilp3.ast.ASTcodefinitions;
import com.paracamplus.ilp3.ast.ASTlambda;
import com.paracamplus.ilp3.ast.ASTnamedLambda;
import com.paracamplus.ilp3.ast.ASTtry;
import com.paracamplus.ilp3.interfaces.IASTcodefinitions;
import com.paracamplus.ilp3.interfaces.IASTlambda;
import com.paracamplus.ilp3.interfaces.IASTnamedLambda;
import com.paracamplus.ilp3.interfaces.IASTtry;
import com.paracamplus.ilp4.ast.ASTclassDefinition;
import com.paracamplus.ilp4.ast.ASTfieldRead;
import com.paracamplus.ilp4.ast.ASTfieldWrite;
import com.paracamplus.ilp4.ast.ASTinstantiation;
import com.paracamplus.ilp4.ast.ASTmethodDefinition;
import com.paracamplus.pstl.ast_java.ASTprogram;
import com.paracamplus.ilp4.ast.ASTself;
import com.paracamplus.ilp4.ast.ASTsend;
import com.paracamplus.ilp4.ast.ASTsuper;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.ilp4.interfaces.IASTfieldRead;
import com.paracamplus.ilp4.interfaces.IASTfieldWrite;
import com.paracamplus.ilp4.interfaces.IASTinstantiation;
import com.paracamplus.ilp4.interfaces.IASTmethodDefinition;

import com.paracamplus.ilp4.interfaces.IASTself;
import com.paracamplus.ilp4.interfaces.IASTsend;
import com.paracamplus.ilp4.interfaces.IASTsuper;
import com.paracamplus.pstl.interfaces.IASTfactory;
import com.paracamplus.pstl.interfaces.IASTincludeDefinition;
import com.paracamplus.pstl.interfaces.IASTprogram;


public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory implements IASTfactory {

	@Override
	public IASTincludeDefinition newIncludeDefinition(String filepath) {
		return new ASTincludeDefinition(filepath);
	}
	
	@Override
	public IASTprogram newProgram(IASTfunctionDefinition[] functions,
                                  IASTclassDefinition[] clazzes, 
                                  IASTexpression expression,
                                  IASTincludeDefinition[] includes) {
        return new ASTprogram(functions, clazzes, expression,includes);
    }
	//ilp4
	 @Override
		public com.paracamplus.ilp4.interfaces.IASTprogram newProgram(IASTfunctionDefinition[] functions,
	                                  IASTclassDefinition[] clazzes, 
	                                  IASTexpression expression) {
	        return new com.paracamplus.ilp4.ast.ASTprogram(functions, clazzes, expression);
	    }
	    

	    
	    @Override
		public IASTclassDefinition newClassDefinition(
	            String className,
	            String superClassName, 
	            String[] fieldNames,
	            IASTmethodDefinition[] methodDefinitions) {
	        return new ASTclassDefinition(
	                className,
	                superClassName,
	                fieldNames,
	                methodDefinitions );
	    }

	    @Override
		public IASTmethodDefinition newMethodDefinition(
	            IASTvariable methodVariable,
	            IASTvariable[] variables, 
	            IASTexpression body,
	            String methodName,
	            String definingClassName ) {
	        return new ASTmethodDefinition(
	                methodVariable, variables, body, 
	                methodName, definingClassName);
	    }

	    @Override
		public IASTinstantiation newInstantiation(
	            String className,
	            IASTexpression[] arguments) {
	        return new ASTinstantiation(className, arguments);
	    }

	    @Override
		public IASTfieldRead newReadField(String fieldName, IASTexpression target) {
	        return new ASTfieldRead(fieldName, target);
	    }

	    @Override
		public IASTfieldWrite newWriteField(
	            String fieldName,
	            IASTexpression target, 
	            IASTexpression value) {
	        return new ASTfieldWrite(fieldName, target, value);
	    }

	    @Override
		public IASTsend newSend(
	            String message, 
	            IASTexpression receiver,
	            IASTexpression[] arguments) {
	        return new ASTsend(message, receiver, arguments);
	    }

	    @Override
		public IASTself newSelf() {
	        return new ASTself();
	    }

	    @Override
		public IASTsuper newSuper() {
	        return new ASTsuper();
	    }
	    //ilp3
	    @Override
		public com.paracamplus.ilp3.interfaces.IASTprogram newProgram(IASTfunctionDefinition[] functions, 
	                                  IASTexpression expression) {
	        return new com.paracamplus.ilp3.ast.ASTprogram(functions, expression);
	    }
	    
	    @Override
		public IASTtry newTry (IASTexpression body,
	                           IASTlambda catcher,
	                           IASTexpression finallyer ) {
	        return new ASTtry(body, catcher, finallyer);
	    }
	    
	    @Override
		public IASTlambda newLambda (IASTvariable[] variables,
	                                 IASTexpression body ) {
	        return new ASTlambda(variables, body);
	    }
	    
	    @Override
		public IASTnamedLambda newNamedLambda (
	            IASTvariable functionVariable,
	            IASTvariable[] variables,
	            IASTexpression body ) {
	        return new ASTnamedLambda(functionVariable, variables, body);
	    }
	    
	    @Override
		public IASTcodefinitions newCodefinitions (
	            IASTnamedLambda[] functions,
	            IASTexpression body ) {
	        return new ASTcodefinitions(functions, body);
	    }
	    //ilp2

	    @Override
		public IASTassignment newAssignment(IASTvariable variable,
	                                        IASTexpression value) {
	        return new ASTassignment(variable, value);
	    }


	    @Override
		public IASTloop newLoop(IASTexpression condition, IASTexpression body) {
	        return new ASTloop(condition, body);
	    }

	    @Override
		public IASTfunctionDefinition newFunctionDefinition(
	            IASTvariable functionVariable,
	            IASTvariable[] variables, 
	            IASTexpression body) {
	        return new ASTfunctionDefinition(functionVariable, variables, body);
	    }
//	    ilp1
	    @Override
		public com.paracamplus.ilp1.interfaces.IASTprogram newProgram(IASTexpression expression) {
	        return new com.paracamplus.ilp1.ast.ASTprogram(expression);
	    }
	    
	    @Override
		public IASToperator newOperator(String name) {
	        return new ASToperator(name);
	    }

	    @Override
		public IASTsequence newSequence(IASTexpression[] asts) {
	        return new ASTsequence(asts);
	    }

	    @Override
		public IASTalternative newAlternative(IASTexpression condition,
	                                          IASTexpression consequence, 
	                                          IASTexpression alternant) {
	        return new ASTalternative(condition, consequence, alternant);
	    }

	    @Override
		public IASTvariable newVariable(String name) {
	        return new ASTvariable(name);
	    }


	    @Override
		public IASTunaryOperation newUnaryOperation(IASToperator operator,
	                                                IASTexpression operand) {
	        return new ASTunaryOperation(operator, operand);
	    }

	    @Override
		public IASTbinaryOperation newBinaryOperation(IASToperator operator,
	            IASTexpression leftOperand, IASTexpression rightOperand) {
	        return new ASTbinaryOperation(operator, leftOperand, rightOperand);
	    }

	    @Override
		public IASTinteger newIntegerConstant(String value) {
	        return new ASTinteger(value); 
	    }

	    @Override
		public IASTfloat newFloatConstant(String value) {
	        return new ASTfloat(value);
	    }

	    @Override
		public IASTstring newStringConstant(String value) {
	        return new ASTstring(value);
	    }

	    @Override
		public IASTboolean newBooleanConstant(String value) {
	        return new ASTboolean(value);
	    }


	    @Override
		public IASTblock newBlock(IASTbinding[] binding,
	                              IASTexpression body) {
	        return new ASTblock(binding, body);
	    }
	    @Override
		public IASTbinding newBinding(IASTvariable variable, IASTexpression initialisation) {
	        return new ASTblock.ASTbinding(variable, initialisation);
	    }
	    
	    @Override
		public IASTinvocation newInvocation(IASTexpression function,
	            IASTexpression[] arguments) {
	    	return new ASTinvocation(function, arguments);
	    }
	

	

}
