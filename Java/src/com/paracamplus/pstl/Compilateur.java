package com.paracamplus.pstl;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Vector;

import antlr4.ILPMLgrammar1Lexer;
import antlr4.ILPMLgrammar1Parser;
import antlr4.ILPMLgrammarPSTLLexer;
import antlr4.ILPMLgrammarPSTLParser;
import com.paracamplus.ilp1.parser.ParseException;
import com.paracamplus.ilp1.parser.Parser;
import com.paracamplus.ilp1.tools.FileTool;
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


@RunWith(Parameterized.class)
public class Compilateur {

    protected static String[] samplesDirName = { "SamplesPSTL_Compilateur" };
    protected static String pattern = ".*\\.ilpml";

    protected File file;
    protected Parser parser;
    protected Interpreter interpreter;
    protected ConvertisseurAST convertisseur;
    protected IASTfactory factory;

    public Compilateur(final File file) {
        this.file = file;
        this.parser = new Parser();

        //compilateur
        this.factory = new ASTfactory();
        parser.setILPMLParser(new ILPMLParser(factory));

        //convertisseur
        this.convertisseur = new ConvertisseurAST();
    }


    @Test
    public void processFile() throws EvaluationException, ParseException, IOException {

        com.paracamplus.ilp1.interfaces.IASTprogram program = parser.parse(file);

        //interpreteur
        StringWriter stdout = new StringWriter();
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
        //melanger bibliotheque et Program AST ilp
        sb.append(sb_biblio);
        sb.append(sb_ast);

        System.out.println("Test: ");
        System.out.println(sb.toString());

        //2ieme traitement
        process_prog_ilp(sb);

        System.out.println("Test: ");
        System.out.println(sb.toString());
    }

    public void process_prog_ilp(StringBuilder sb) throws ParseException {
        IASTprogram program = (IASTprogram) this.getProgram(sb);
        
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

//    public com.paracamplus.ilp1.interfaces.IASTprogram getProgram(StringBuilder sb) throws ParseException {
//        try {
//            ANTLRInputStream in = new ANTLRInputStream(sb.toString());
//            // flux de caractères -> analyseur lexical
//            ILPMLgrammar1Lexer lexer = new ILPMLgrammar1Lexer(in);
//            // analyseur lexical -> flux de tokens
//            CommonTokenStream tokens =	new CommonTokenStream(lexer);
//            // flux tokens -> analyseur syntaxique
//            ILPMLgrammar1Parser parser =	new ILPMLgrammar1Parser(tokens);
//            // démarage de l'analyse syntaxique
//            ILPMLgrammar1Parser.ProgContext tree = parser.prog();
//            // parcours de l'arbre syntaxique et appels du Listener
//            ParseTreeWalker walker = new ParseTreeWalker();
//            ILPMLListener extractor = new ILPMLListener(this.factory);
//            walker.walk(extractor, tree);
//            return tree.node;
//        } catch (Exception e) {
//            throw new ParseException(e);
//        }
//    }
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
