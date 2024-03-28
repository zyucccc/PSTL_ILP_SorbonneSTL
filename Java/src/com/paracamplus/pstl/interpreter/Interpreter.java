package com.paracamplus.pstl.interpreter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp3.interpreter.primitive.Throw.ThrownException;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.ilp4.interfaces.IASTmethodDefinition;
import com.paracamplus.pstl.ConvertisseurAST;
import com.paracamplus.pstl.interfaces.IASTprogram;
import com.paracamplus.pstl.outil.IncludeHandler;
import com.paracamplus.pstl.outil.readFichier;
import com.paracamplus.pstl.outil.MergeProgramme;
import com.paracamplus.pstl.interfaces.IASTvisitor;
import com.paracamplus.ilp4.interpreter.ILPClass;
import com.paracamplus.ilp4.interpreter.interfaces.IClass;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;
import com.paracamplus.ilp4.interpreter.interfaces.IMethod;
import com.paracamplus.pstl.ast_java.ASTfactory;
import com.paracamplus.pstl.interfaces.IASTfactory;
import com.paracamplus.pstl.interfaces.IASTincludeDefinition;

public class Interpreter extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {
	public Interpreter(
			IGlobalVariableEnvironment globalVariableEnvironment,
			IOperatorEnvironment operatorEnvironment,
			IClassEnvironment classEnvironment) {
		super(globalVariableEnvironment, operatorEnvironment, classEnvironment);
	}
	
	 @Override 
	    public Object visit(com.paracamplus.ilp1.interfaces.IASTprogram iast, ILexicalEnvironment lexenv) throws EvaluationException {
	    	return visit((IASTprogram)iast, lexenv);
	    }

	  
	    public Object visit(IASTprogram iast, ILexicalEnvironment lexenv) 
	            throws EvaluationException {

            iast = (IASTprogram)this.visitIncludeProgram(iast, lexenv);

//			System.out.println("Test Convertisseur:>>>>>>>>");
//			ConvertisseurAST convertisseur = new ConvertisseurAST();
//			Object result = convertisseur.visit(iast);


//	    	ArrayList<IASTprogram> list_program = new ArrayList<IASTprogram>();
//			//traiter tous les includes,ajoute les programmes de "Include" dans la liste
//	    	for ( IASTincludeDefinition include : iast.getIncludes() ) {
//	           IASTprogram program = (IASTprogram) this.visit(include, lexenv);
//               this.visitIncludeProgram(program, lexenv);
//	            if(program != null) {
//	            	list_program.add(program);
//	            }
//	        }
//			//ajoute le programme courant dans la liste
//			list_program.add(iast);
//			//merge les programmes
//	    	MergeProgramme mergeProgramme = new MergeProgramme();
//	    	IASTprogram mergedPrograme = mergeProgramme.mergePrograms(list_program);
//	    	//mise a jour le programme
//	    	iast = mergedPrograme ;



	        for ( IASTclassDefinition cd : iast.getClassDefinitions() ) {
	            this.visit(cd, lexenv);
	        }
	        for ( IASTfunctionDefinition fd : iast.getFunctionDefinitions() ) {
	            Object f = this.visit(fd, lexenv);
	            String v = fd.getName();
	            getGlobalVariableEnvironment().addGlobalVariableValue(v, f);
	        }
	        try {
	            return iast.getBody().accept(this, lexenv);
	        } catch (ThrownException exc) {
	            return exc.getThrownValue();
	        } catch (Exception exc) {
	            return exc;
	        }
	        
	    }

	public Object visitIncludeProgram(IASTprogram iast, ILexicalEnvironment lexenv)
			throws EvaluationException {
		ArrayList<IASTprogram> list_program = new ArrayList<IASTprogram>();
		//traiter tous les include recursivement
		for (IASTincludeDefinition include : iast.getIncludes()) {
			IASTprogram includedProgram = (IASTprogram) this.visit(include, lexenv);
			includedProgram = (IASTprogram)this.visitIncludeProgram(includedProgram, lexenv);
			if (includedProgram != null) {
				list_program.add(includedProgram);
			}
		}
		list_program.add(iast);
		//merge programs
		MergeProgramme mergeProgramme = new MergeProgramme();
		IASTprogram mergedPrograme = mergeProgramme.mergePrograms(list_program);
		//mise à jour
		return mergedPrograme;
	}
    @Override
	public Object visit(IASTincludeDefinition iast, ILexicalEnvironment lexenv)
            throws EvaluationException {
		String filepath = iast.getFilepath();
		System.out.println("Test path:"+filepath);
		//current working path
//		  String currentDir = System.getProperty("user.dir");
//        System.out.println("current path：" + currentDir);
		
		IASTfactory factory = new ASTfactory();
		IncludeHandler handler = new IncludeHandler(factory);
        IASTprogram includeProgram = null;
		try {
			String content = readFichier.readIncludeFileContent(filepath);
			System.out.println(content);
			//ANTLR
			includeProgram = (IASTprogram) handler.parseIncludeContent(content);

			//this.visit(includeProgram, lexenv);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return includeProgram;
	}
	
	@Override
	 public IClass visit(IASTclassDefinition iast, ILexicalEnvironment lexenv) 
	            throws EvaluationException {
//		System.out.println("Test Interpreter class");
	        List<IMethod> methods = new Vector<>();
	        for ( IASTmethodDefinition md : iast.getProperMethodDefinitions() ) {
	            IMethod m = visit(md, lexenv);
	            methods.add(m);
	        }
	        IClass clazz = new ILPClass(
	                getClassEnvironment(),
	                iast.getName(),
	                iast.getSuperClassName(),
	                iast.getProperFieldNames(),
	                methods.toArray(new IMethod[0]) );
	        return clazz;
	    }
    
    
}
