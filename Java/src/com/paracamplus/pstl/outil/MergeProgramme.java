package com.paracamplus.pstl.outil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.paracamplus.pstl.interfaces.IASTprogram;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.pstl.ast_java.ASTfactory;
import com.paracamplus.pstl.interfaces.IASTfactory;

public class MergeProgramme {
	public IASTprogram mergePrograms(ArrayList<IASTprogram> programs) {
	    IASTfactory factory = new ASTfactory();
	   IASTprogram mergedProgram = factory.newProgram(new IASTfunctionDefinition[0], new IASTclassDefinition[0], factory.newSequence(new IASTexpression[0]),null);

	   List<IASTexpression> allExpressions = new ArrayList<>();
	    for (IASTprogram program : programs) {
	        // merge classes
	        for (IASTclassDefinition cd : program.getClassDefinitions()) {
	            mergedProgram.addClassDefinition(cd);
	        }

	        // merge functions
	        for (IASTfunctionDefinition fd : program.getFunctionDefinitions()) {
	            mergedProgram.addFunctionDefinition(fd);
	        }

	        // to do : merge variables globals?
	      

	        // to do : merge exprs?
	        allExpressions.add(program.getBody());
	    }


        IASTexpression mergedExpression = factory.newSequence(allExpressions.toArray(new IASTexpression[0]));
        mergedProgram.updateExpression(mergedExpression);

	    return mergedProgram;
	}

}
