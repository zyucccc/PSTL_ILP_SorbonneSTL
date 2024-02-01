package com.paracamplus.ilp4.ilp4tme8.compiler.normalizer;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface INormalizationFactory
	extends com.paracamplus.ilp4.compiler.normalizer.INormalizationFactory {

    IASTexpression newExistsProperty(
            IASTexpression target,
            IASTexpression property);

    IASTexpression newReadProperty(
            IASTexpression target,
            IASTexpression property);

    IASTexpression newWriteProperty(
            IASTexpression target,
            IASTexpression property,
            IASTexpression value);

}
