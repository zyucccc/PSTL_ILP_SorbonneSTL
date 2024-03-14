package com.paracamplus.pstl.outil;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import com.paracamplus.ilp4.interfaces.IASTprogram;
import com.paracamplus.pstl.interfaces.IASTfactory;
import com.paracamplus.pstl.parser.ilpml.ILPMLListener;

import antlr4.ILPMLgrammarPSTLLexer;
import antlr4.ILPMLgrammarPSTLParser;
import java.io.IOException;
import java.io.StringReader;

public class IncludeHandler {

    private IASTfactory factory;

    public IncludeHandler(IASTfactory factory) {
        this.factory = factory;
    }

    public IASTprogram parseIncludeContent(String includeContent) throws IOException {
    	ANTLRInputStream in = new ANTLRInputStream(new StringReader(includeContent));
        ILPMLgrammarPSTLLexer lexer = new ILPMLgrammarPSTLLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ILPMLgrammarPSTLParser parser = new ILPMLgrammarPSTLParser(tokens);

        ILPMLgrammarPSTLParser.ProgContext tree = parser.prog();
        ParseTreeWalker walker = new ParseTreeWalker();
        ILPMLListener extractor = new ILPMLListener(factory);
        walker.walk(extractor, tree);

        return tree.node;
    }
}
