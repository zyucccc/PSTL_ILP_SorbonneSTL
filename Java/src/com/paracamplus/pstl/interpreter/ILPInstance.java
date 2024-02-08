package com.paracamplus.pstl.interpreter;

import java.util.HashMap;
import java.util.Map;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;

import com.paracamplus.ilp4.interpreter.interfaces.IClass;
import com.paracamplus.pstl.interpreter.interfaces.IInstance;

public class ILPInstance extends com.paracamplus.ilp4.interpreter.ILPInstance implements IInstance{
	
	// 用于存储动态属性的映射
		protected Map<String,Object> properties;	
		
	    // 构造函数
		public ILPInstance(IClass clazz, Object[] fields)
				throws EvaluationException {
			super(clazz, fields);
			// properties 字段的延迟分配
		}

		// 判断对象是否具有指定的属性（字段）
		public boolean hasProperty(String fieldName)
	        throws EvaluationException {
			try {
				classOf().getOffset(fieldName);
				// 如果没有抛出异常，说明字段是在类中声明的
				return true;
			}
			catch (EvaluationException e) {
				// 如果字段未在类中声明，则检查是否为动态字段
				return (properties != null) && properties.containsKey(fieldName);
			}
		}
		
	    // 读取属性值
		public Object arrayRead(String fieldName) 
			throws EvaluationException {
			try {
				return read(fieldName);
			}
			catch (EvaluationException e) {
				// 如果字段未在类中声明，则尝试读取动态字段
				if ((properties == null || !properties.containsKey(fieldName)))
					throw new EvaluationException(
							"Object has no property " + fieldName);
				return properties.get(fieldName);
			}
		}
		
	    // 写入属性值
		public Object arrayWrite(String fieldName, Object value)
			throws EvaluationException {
			try {
				return write(fieldName, value);
			}
			catch (EvaluationException e) {
				// 如果字段未在类中声明
				// 则创建或更新动态字段
				if (properties==null) {
					// 动态字段表的延迟创建
					properties = new HashMap<>();
				}
				Object r = properties.put(fieldName, value);
				if (r==null) {
					// 如果属性之前不存在，则返回 false
					return new Boolean(false);
				}
				return r;
			}
		}
}