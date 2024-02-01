// 导入所需的类和接口
package com.paracamplus.ilp4.ilp4tme10.interpreter;

import java.util.Set;
import com.paracamplus.ilp4.interfaces.IASTprogram;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTdefined;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTexists;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTvisitor;

// 继承自ILP4的Interpreter类，并实现IASTvisitor接口
public class Interpreter extends com.paracamplus.ilp4.interpreter.Interpreter
	implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {

	// 保存收集到的全局变量的集合
	protected Set<String> globals;
	
	// 构造函数
	public Interpreter(
			IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment,
			IClassEnvironment classEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment, classEnvironment);
	}
	
	// 重写visit方法以处理IASTprogram
	@Override
	public Object visit(IASTprogram iast, ILexicalEnvironment lexenv)
			throws EvaluationException {
		// 收集全局变量
		globals = (new GlobalVariableCollectorInterpreter()).visit(iast).getGlobals();
		// 执行解释
		return super.visit(iast, lexenv);
	}
	
	// 实现对IASTexists的访问
	@Override
	public Object visit(IASTexists iast, ILexicalEnvironment env)
			throws EvaluationException {
		IASTvariable var = iast.getVariable();
		return new Boolean(
				// 检查全局变量集合中是否包含该变量
				globals.contains(var.getName()) ||
				// 检查全局变量环境中是否有该变量（用于常量和原语，它们不在globals中）
				getGlobalVariableEnvironment().getGlobalVariableValue(var.getName()) != null ||
				// 检查局部变量是否存在
				env.isPresent(iast.getVariable()));
	}

	// 实现对IASTdefined的访问
	@Override
	public Object visit(IASTdefined iast, ILexicalEnvironment env)
			throws EvaluationException {
		IASTvariable var = iast.getVariable();
		return new Boolean (
				// 检查全局变量环境中是否已经遇到过该变量（或原语）
				getGlobalVariableEnvironment().getGlobalVariableValue(var.getName()) != null ||
				// 检查局部变量是否存在
				env.isPresent(var));
	}

}
