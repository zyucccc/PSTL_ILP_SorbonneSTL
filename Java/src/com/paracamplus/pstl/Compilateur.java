package com.paracamplus.pstl;

import java.io.*;
import java.util.Collection;
import java.util.Vector;

import antlr4.ILPMLgrammarPSTLLexer;
import antlr4.ILPMLgrammarPSTLParser;
import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.interpreter.EmptyLexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.parser.ParseException;
import com.paracamplus.ilp1.parser.Parser;
import com.paracamplus.ilp1.tools.FileTool;
import com.paracamplus.ilp1.tools.ProgramCaller;
import com.paracamplus.pstl.interfaces.IASTprogram;
import com.paracamplus.pstl.parser.ilpml.ILPMLListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.paracamplus.ilp1.interpreter.GlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.OperatorEnvironment;
import com.paracamplus.ilp1.interpreter.OperatorStuff;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.interpreter.GlobalVariableStuff;

import com.paracamplus.pstl.interfaces.IASTfactory;

import com.paracamplus.pstl.interpreter.Interpreter;
import com.paracamplus.pstl.parser.ilpml.ILPMLParser;
import com.paracamplus.pstl.ast_java.ASTfactory;

import com.paracamplus.ilp4.interpreter.ClassEnvironment;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;

import static org.junit.Assert.assertEquals;

// gestion exception
// definir exception throws exception
// rendre l'exception plus lisible(numero de ligne)
//numero de ligne stocke dans ANTLR

// /"

// etindre les erreurs

//rapport :
//schema compilation
//future de rapport
//montrer quelques exemples de COde C
// partie de test : nb,2 parties: 1 partie pour au fur au mesure, 2 partie pour les tests existants
// explication pour 2ieme partie , au but ,ca marche pas,et apres on corrige.....

//les choses interessantes

//la notion bootstrap

@RunWith(Parameterized.class)
public class Compilateur {

//    protected static String[] samplesDirName = { "SamplesPSTL_Compilateur" };
    protected static String[] samplesDirName = { "SamplesILP1" };
    protected static String pattern = ".*\\.ilpml";
    protected static String scriptCommand = "C/compileThenRun.sh +gc";

    protected File file;
    protected Parser parser;
    protected Interpreter interpreter;
    protected ConvertisseurAST convertisseur;
    protected IASTfactory factory;
    protected StringWriter stdout;

    public Compilateur(final File file) {
        this.file = file;
        this.parser = new Parser();

        //compilateur
        this.factory = new ASTfactory();
        parser.setILPMLParser(new ILPMLParser(factory));

        this.stdout = new StringWriter();
        //convertisseur
        this.convertisseur = new ConvertisseurAST();
    }


    @Test
    public void processFile() throws EvaluationException, ParseException, IOException, CompilationException {

        com.paracamplus.ilp1.interfaces.IASTprogram program = parser.parse(file);

        //interpreteur

        IGlobalVariableEnvironment gve = new GlobalVariableEnvironment();
        GlobalVariableStuff.fillGlobalVariables(gve, stdout);
        IOperatorEnvironment oe = new OperatorEnvironment();
        OperatorStuff.fillUnaryOperators(oe);
        OperatorStuff.fillBinaryOperators(oe);
        IClassEnvironment ice = new ClassEnvironment(stdout);
        this.interpreter = new Interpreter(gve, oe, ice);


        //fusionner les includes
        IASTprogram merged_prgram = (IASTprogram) interpreter.visitIncludeProgram((IASTprogram) program, null);

        //convertir AST java À AST ilp
        convertisseur.visit(merged_prgram);
        //
        StringBuilder sb = new StringBuilder();
        //sb -> AST ilp
        StringBuilder sb_ast = convertisseur.getSb();
        //bibliotheque
        StringBuilder sb_biblio = new StringBuilder();
        sb_biblio.append("include \"./Java/src/com/paracamplus/pstl/bibliotheque_outil.ilpml\";\n");
        sb_biblio.append("include \"./Java/src/com/paracamplus/pstl/bibliotheque_AST.ilpml\";\n");
        //emettre Code C
        StringBuilder sb_eval = new StringBuilder();
        sb_eval.append("context = new Context(noDestination);\n" +
                   "program.eval(context);\n");
        //melanger bibliotheque et Program AST ilp
        sb.append(sb_biblio);
        sb.append("program =");
        sb.append(sb_ast);
        sb.append(sb_eval);

//        System.out.println("Test: ");
//        System.out.println(sb.toString());

        //2ieme traitement
        process_prog_ilp(sb);

    }

    public void process_prog_ilp(StringBuilder sb) throws ParseException, EvaluationException, IOException, CompilationException {
        IASTprogram program = (IASTprogram) this.getProgram(sb);
        ILexicalEnvironment lexenv = new EmptyLexicalEnvironment();
        interpreter.visit(program, lexenv);
        String codeC = stdout.toString();
//        System.out.println("Value: " + result);
        //Code C
//        System.out.println("Test code: >>>>>>>>>>>>");
//        System.out.println("Printing: " + printing);
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
        //rediriger vers fichier.c
        File cFile = FileTool.changeSuffix(file, "c");
        FileTool.stuffFile(cFile, codeC);

        // detection de Windows
        boolean isWindows = System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0;

        String cPath =  cFile.getAbsolutePath();
        if (isWindows) {
            // sous Windows, adaptation du chemin du fichier
            cPath = "\"/mnt/" + cPath.substring(0,1).toLowerCase() + cPath.replace('\\', '/').substring(2) + "\"";
        }

        // pretty-print du C généré et affichage
//        try {
//            String indentProgram = "indent " + cPath;
//            ProgramCaller pcindent = new ProgramCaller(indentProgram);
//            pcindent.run();
//        } catch (Exception exc) {
//        }
//        System.out.println(FileTool.slurpFile(cFile));

        // lancement du script de compilation et d'exécution
        if (scriptCommand == null) {
            throw new CompilationException("runtime script not set");
        }
        String compileProgram = "bash " + scriptCommand + " " + cPath;
        ProgramCaller pc = new ProgramCaller(compileProgram);
        pc.setVerbose();
        pc.run();
        assertEquals("Comparing return code", 0, pc.getExitValue());
    }


    @Parameters(name = "{0}")
    public static Collection<File[]> data() throws Exception {
        Collection<File[]> result = new Vector<>();
        File[] testFiles = FileTool.getFileList(samplesDirName, pattern);
        for ( final File f : testFiles ) {
            result.add(new File[]{ f });
        }
        return result;
    }

  //Parse
public com.paracamplus.ilp4.interfaces.IASTprogram getProgram(StringBuilder sb) throws ParseException {
    try {
        ANTLRInputStream in = new ANTLRInputStream(sb.toString());
        // flux de caractères -> analyseur lexical
        ILPMLgrammarPSTLLexer lexer = new ILPMLgrammarPSTLLexer(in);
        // analyseur lexical -> flux de tokens
        CommonTokenStream tokens =	new CommonTokenStream(lexer);
        // flux tokens -> analyseur syntaxique
        ILPMLgrammarPSTLParser parser = new ILPMLgrammarPSTLParser(tokens);
        // démarage de l'analyse syntaxique
        ILPMLgrammarPSTLParser.ProgContext tree = parser.prog();
        // parcours de l'arbre syntaxique et appels du Listener
        ParseTreeWalker walker = new ParseTreeWalker();
        com.paracamplus.pstl.parser.ilpml.ILPMLListener extractor = new ILPMLListener((IASTfactory)factory);
        walker.walk(extractor, tree);
        return tree.node;
    } catch (Exception e) {
        throw new ParseException(e);
    }
}

}
