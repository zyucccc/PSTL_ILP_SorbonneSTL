// 导入所需的类和接口
package com.paracamplus.ilp4.ilp4tme10.compiler;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTClocalVariable;
import com.paracamplus.ilp4.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp4.ilp4tme10.compiler.interfaces.IASTCvisitor;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTdefined;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTexists;

import java.util.Set;

// 继承自ILP4的FreeVariableCollector类，并实现IASTCvisitor接口
public class FreeVariableCollector extends com.paracamplus.ilp4.compiler.FreeVariableCollector 
	implements IASTCvisitor<Void, Set<IASTClocalVariable>, CompilationException> {

	// 构造函数
	public FreeVariableCollector(IASTCprogram program) {
		super(program);
	}
	
	// 'exists' 关键字的访问：忽略其参数
	@Override
	public Void visit(IASTexists iast, Set<IASTClocalVariable> data)
			throws CompilationException {
		return null;
	}

	// 'defined' 关键字的访问：像读取变量一样处理
	@Override
	public Void visit(IASTdefined iast, Set<IASTClocalVariable> data)
			throws CompilationException {
		// 调用变量的 accept 方法，传递 data
		return iast.getVariable().accept(this,  data);
	}
//	exists 只是检查变量的存在性，不涉及变量的使用或赋值，
//	因此它的参数被忽略。而 defined 涉及到变量的使用状态，因此需要检查其参数是否是自由变量。

}
