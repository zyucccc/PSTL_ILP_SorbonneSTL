package com.paracamplus.pstl.interfaces;

import com.paracamplus.ilp1.interfaces.IASToperator;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp3.interfaces.IASTlambda;
import com.paracamplus.ilp3.interfaces.IASTnamedLambda;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.ilp4.interfaces.IASTmethodDefinition;
import com.paracamplus.ilp1.interfaces.IASTblock.IASTbinding;
import com.paracamplus.ilp1.interfaces.IASTexpression;


public interface IASTfactory  extends com.paracamplus.ilp4.interfaces.IASTfactory {
   IASTincludeDefinition newIncludeDefinition(String filepath);

   //change-
   IASTprogram newProgram(IASTfunctionDefinition[] functions,
	   		IASTclassDefinition[] clazzes,
//                          com.paracamplus.pstl.interfaces.ilp1.
                                  IASTexpression expression,
	           IASTincludeDefinition[] includes);
   
   //ilp4
   com.paracamplus.ilp4.interfaces.IASTprogram newProgram(
   		IASTfunctionDefinition[] functions,
   		IASTclassDefinition[] clazzes,
           IASTexpression expression);
   

   IASTclassDefinition newClassDefinition(
           String className,
           String superClassName,
           String[] fieldNames,
           IASTmethodDefinition[] methodDefinitions );
   
   IASTmethodDefinition newMethodDefinition(
           IASTvariable methodVariable,
           IASTvariable[] variables,
           IASTexpression body, 
           String methodName,
           String definingClassName  );
   
   IASTexpression newInstantiation(
           String className,
           IASTexpression[] arguments );
   
   IASTexpression newReadField(
           String fieldName,
           IASTexpression object );
   
   IASTexpression newWriteField(
           String fieldName,
           IASTexpression object,
           IASTexpression value );
   
   IASTvariable newSelf();
   
   IASTexpression newSend(
           String message,
           IASTexpression receiver,
           IASTexpression[] arguments );
   
   IASTexpression newSuper();
   
//   ilp3
   
  	com.paracamplus.ilp3.interfaces.IASTprogram newProgram(
      		IASTfunctionDefinition[] functions,
              IASTexpression expression);
      
      IASTexpression newTry (IASTexpression body,
                             IASTlambda catcher,
                             IASTexpression finallyer );

      IASTlambda newLambda (IASTvariable[] variables,
                                IASTexpression body );

      IASTnamedLambda newNamedLambda(
              IASTvariable functionVariable,
              IASTvariable[] variables,
              IASTexpression body );
      
      IASTexpression newCodefinitions(IASTnamedLambda[] functions,
                                      IASTexpression body);
   //ILP2
      
      IASTexpression newLoop(IASTexpression condition,
                             IASTexpression body);

      IASTfunctionDefinition newFunctionDefinition(
              IASTvariable functionVariable,
              IASTvariable[] variables,
              IASTexpression body);
      
      IASTexpression newAssignment(IASTvariable variable,
              IASTexpression value);
      //ilp1
      com.paracamplus.ilp1.interfaces.IASTprogram newProgram(
              IASTexpression expression);


      //change-
//      com.paracamplus.pstl.interfaces.ilp1.
              IASTexpression newSequence(
//                      com.paracamplus.pstl.interfaces.ilp1.
                              IASTexpression[] asts);

      IASTexpression newAlternative(
              IASTexpression condition,
              IASTexpression consequence,
              IASTexpression alternant);

      IASToperator newOperator(String name);
      
      IASTvariable newVariable(String name);
      

      IASTexpression newUnaryOperation(
              IASToperator operator,
              IASTexpression operand);

      IASTexpression newBinaryOperation(
              IASToperator operator,
              IASTexpression leftOperand,
              IASTexpression rightOperand);

      IASTexpression newIntegerConstant(String value);

      IASTexpression newFloatConstant(String value);

      IASTexpression newStringConstant(String value);

      IASTexpression newBooleanConstant(String value);


      IASTexpression newBlock(IASTbinding[] binding,
                              IASTexpression body);

      IASTbinding newBinding(IASTvariable v, IASTexpression exp);
      
      IASTexpression newInvocation(
              IASTexpression function,
              IASTexpression[] arguments);
	
}
