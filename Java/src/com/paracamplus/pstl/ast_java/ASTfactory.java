package com.paracamplus.pstl.ast_java;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
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
import com.paracamplus.ilp4.ast.ASTprogram;
import com.paracamplus.ilp4.ast.ASTself;
import com.paracamplus.ilp4.ast.ASTsend;
import com.paracamplus.ilp4.ast.ASTsuper;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.ilp4.interfaces.IASTfieldRead;
import com.paracamplus.ilp4.interfaces.IASTfieldWrite;
import com.paracamplus.ilp4.interfaces.IASTinstantiation;
import com.paracamplus.ilp4.interfaces.IASTmethodDefinition;
import com.paracamplus.ilp4.interfaces.IASTprogram;
import com.paracamplus.ilp4.interfaces.IASTself;
import com.paracamplus.ilp4.interfaces.IASTsend;
import com.paracamplus.ilp4.interfaces.IASTsuper;
import com.paracamplus.pstl.interfaces.IASTfactory;
import com.paracamplus.pstl.interfaces.IASTincludeDefinition;


public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory  implements IASTfactory {

	@Override
	public IASTincludeDefinition newIncludeDefinition(String filepath) {
		return new ASTincludeDefinition(filepath);
	}
	
////ilp4
//
//    @Override
//	public IASTprogram newProgram(IASTfunctionDefinition[] functions,
//                                  IASTclassDefinition[] clazzes, 
//                                  IASTexpression expression) {
//        return new ASTprogram(functions, clazzes, expression);
//    }
//    
//
//    
//    @Override
//	public IASTclassDefinition newClassDefinition(
//            String className,
//            String superClassName, 
//            String[] fieldNames,
//            IASTmethodDefinition[] methodDefinitions) {
//        return new ASTclassDefinition(
//                className,
//                superClassName,
//                fieldNames,
//                methodDefinitions );
//    }
//
//    @Override
//	public IASTmethodDefinition newMethodDefinition(
//            IASTvariable methodVariable,
//            IASTvariable[] variables, 
//            IASTexpression body,
//            String methodName,
//            String definingClassName ) {
//        return new ASTmethodDefinition(
//                methodVariable, variables, body, 
//                methodName, definingClassName);
//    }
//
//    @Override
//	public IASTinstantiation newInstantiation(
//            String className,
//            IASTexpression[] arguments) {
//        return new ASTinstantiation(className, arguments);
//    }
//
//    @Override
//	public IASTfieldRead newReadField(String fieldName, IASTexpression target) {
//        return new ASTfieldRead(fieldName, target);
//    }
//
//    @Override
//	public IASTfieldWrite newWriteField(
//            String fieldName,
//            IASTexpression target, 
//            IASTexpression value) {
//        return new ASTfieldWrite(fieldName, target, value);
//    }
//
//    @Override
//	public IASTsend newSend(
//            String message, 
//            IASTexpression receiver,
//            IASTexpression[] arguments) {
//        return new ASTsend(message, receiver, arguments);
//    }
//
//    @Override
//	public IASTself newSelf() {
//        return new ASTself();
//    }
//
//    @Override
//	public IASTsuper newSuper() {
//        return new ASTsuper();
//    }
//    //ilp3
//    @Override
//   	public IASTprogram newProgram(IASTfunctionDefinition[] functions, 
//                                     IASTexpression expression) {
//           return new ASTprogram(functions, expression);
//       }
//       
//       @Override
//   	public IASTtry newTry (IASTexpression body,
//                              IASTlambda catcher,
//                              IASTexpression finallyer ) {
//           return new ASTtry(body, catcher, finallyer);
//       }
//       
//       @Override
//   	public IASTlambda newLambda (IASTvariable[] variables,
//                                    IASTexpression body ) {
//           return new ASTlambda(variables, body);
//       }
//       
//       @Override
//   	public IASTnamedLambda newNamedLambda (
//               IASTvariable functionVariable,
//               IASTvariable[] variables,
//               IASTexpression body ) {
//           return new ASTnamedLambda(functionVariable, variables, body);
//       }
//       
//       @Override
//   	public IASTcodefinitions newCodefinitions (
//               IASTnamedLambda[] functions,
//               IASTexpression body ) {
//           return new ASTcodefinitions(functions, body);
//       }
	

}
