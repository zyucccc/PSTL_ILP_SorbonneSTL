package com.paracamplus.pstl.test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import com.paracamplus.ilp4.compiler.test.CompilerRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.GlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.OperatorEnvironment;
import com.paracamplus.ilp1.compiler.OperatorStuff;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.compiler.optimizer.IdentityOptimizer;
import com.paracamplus.ilp1.parser.ParseException;
import com.paracamplus.ilp1.parser.xml.IXMLParser;
import com.paracamplus.ilp3.compiler.GlobalVariableStuff;
import com.paracamplus.ilp4.ast.ASTfactory;
import com.paracamplus.ilp4.compiler.Compiler;
import com.paracamplus.ilp4.interfaces.IASTfactory;
import com.paracamplus.ilp4.parser.ilpml.ILPMLParser;
import com.paracamplus.ilp4.parser.xml.XMLParser;

@RunWith(Parameterized.class)
public class CompilerTest extends com.paracamplus.ilp3.compiler.test.CompilerTest {

//    protected static String[] samplesDirName = { "SamplesPSTL_compiler" };
    protected static String[] samplesDirName = { "SamplesILP1" };
    protected static String pattern = ".*\\.ilpml";
    protected static String scriptCommand = "C/compileThenRun.sh +gc";
    protected static String XMLgrammarFile = "XMLGrammars/grammar4.rng";

    public CompilerTest(final File file) {
        super(file);
    }

    public void configureRunner(CompilerRunner run) throws CompilationException {
        // configuration du parseur
        IASTfactory factory = new ASTfactory();
        IXMLParser xmlparser = new XMLParser(factory);
        xmlparser.setGrammar(new File(XMLgrammarFile));
        run.setXMLParser(xmlparser);
        run.setILPMLParser(new ILPMLParser(factory));

        // configuration du compilateur
        IOperatorEnvironment ioe = new OperatorEnvironment();
        OperatorStuff.fillUnaryOperators(ioe);
        OperatorStuff.fillBinaryOperators(ioe);
        IGlobalVariableEnvironment gve = new GlobalVariableEnvironment();
        GlobalVariableStuff.fillGlobalVariables(gve);
        Compiler compiler = new Compiler(ioe, gve);
        compiler.setOptimizer(new IdentityOptimizer());
        run.setCompiler(compiler);

        // configuration du script de compilation et exécution
        run.setRuntimeScript(scriptCommand);
    }

    @Parameters(name = "{0}")
    public static Collection<File[]> data() throws Exception {
        return CompilerRunner.getFileList(samplesDirName, pattern);
    }

    @Test
    public void processFile() throws CompilationException, ParseException, IOException {
        CompilerRunner run = new CompilerRunner();
        configureRunner(run);
        run.checkPrintingAndResult(file, run.compileAndRun(file));
    }
}
