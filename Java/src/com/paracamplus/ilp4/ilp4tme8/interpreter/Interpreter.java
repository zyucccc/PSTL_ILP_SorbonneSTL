package com.paracamplus.ilp4.ilp4tme8.interpreter;

import java.util.List;
import java.util.Vector;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASThasProperty;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTreadProperty;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTvisitor;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTwriteProperty;
import com.paracamplus.ilp4.interfaces.IASTinstantiation;
import com.paracamplus.ilp4.interpreter.interfaces.IClass;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;

public class Interpreter extends com.paracamplus.ilp4.interpreter.Interpreter 
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {
	
	public Interpreter(
			IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment,
			IClassEnvironment classEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment, classEnvironment);
	}
	
	// 访问 hasProperty 节点的方法
    @Override
    public Object visit(IASThasProperty iast, ILexicalEnvironment lexenv)
            throws EvaluationException {
        // 评估目标对象和属性表达式
        Object target = iast.getTarget().accept(this, lexenv);
        Object property = iast.getProperty().accept(this, lexenv);
        
        // 检查目标是否是实例并且属性是否是字符串
        if (!(target instanceof ILPInstance))
            throw new EvaluationException("目标不是实例 " + target);
        if (!(property instanceof String))
            throw new EvaluationException("属性不是字符串 " + property);
        ILPInstance itarget = (ILPInstance)target;
        String sproperty = (String)property;
        
        // 检查实例是否有该属性
        return new Boolean(itarget.hasProperty(sproperty));
    }
    // 访问 readProperty 节点的方法
    @Override
    public Object visit(IASTreadProperty iast, ILexicalEnvironment lexenv)
            throws EvaluationException {
        // 评估目标对象和属性表达式
        Object target = iast.getTarget().accept(this, lexenv);
        Object property = iast.getProperty().accept(this, lexenv);
        
        // 检查目标和属性的类型
        if (!(target instanceof ILPInstance))
            throw new EvaluationException("目标不是实例 " + target);
        if (!(property instanceof String))
            throw new EvaluationException("属性不是字符串 " + property);
        ILPInstance itarget = (ILPInstance)target;
        String sproperty = (String)property;

        // 读取实例的属性值
        return itarget.readProperty(sproperty);
    }

    // 访问 writeProperty 节点的方法
    @Override
    public Object visit(IASTwriteProperty iast, ILexicalEnvironment lexenv)
            throws EvaluationException {
        // 评估目标对象、属性和值表达式
        Object target = iast.getTarget().accept(this, lexenv);
        Object property = iast.getProperty().accept(this, lexenv);
        Object value = iast.getValue().accept(this, lexenv);
        
        // 检查目标和属性的类型
        if (!(target instanceof ILPInstance))
            throw new EvaluationException("目标不是实例 " + target);
        if (!(property instanceof String))
            throw new EvaluationException("属性不是字符串 " + property);
        ILPInstance itarget = (ILPInstance)target;
        String sproperty = (String)property;

        // 写入实例的属性值
        return itarget.writeProperty(sproperty, value);
    }

    // 重写实例化节点的访问方法，以创建扩展的实例
    @Override
    public Object visit(IASTinstantiation iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        // 获取类信息并评估构造函数参数
        IClass clazz = getClassEnvironment().getILPClass(iast.getClassName());
        List<Object> args = new Vector<Object>();
        for (IASTexpression arg : iast.getArguments()) {
            Object value = arg.accept(this, lexenv);
            args.add(value);
        }
        // 创建新的扩展实例
        return new ILPInstance(clazz, args.toArray());
    }   
	
	
	

}
