package com.paracamplus.ilp4.ilp4tme8.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTwriteProperty extends IASTproperty {
	 IASTexpression getTarget();
	 IASTexpression getProperty();
	 IASTexpression getValue();
	 
//	 但这里的“get”并不是指获取对象的现有属性值，而是指获取表达式中指定的新值。
//	 在类似于 obj["property"] = value; 的表达式中，value 是我们想要设置给 obj 对象的 property 属性的新值。
//	 getValue() 方法就是用来获取这个 value 表达式的。
}
