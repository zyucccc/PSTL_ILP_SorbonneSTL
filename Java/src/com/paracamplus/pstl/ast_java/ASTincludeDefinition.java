package com.paracamplus.pstl.ast_java;


import com.paracamplus.ilp1.interfaces.IASTvisitable;
import com.paracamplus.pstl.interfaces.IASTvisitor;
import com.paracamplus.pstl.interfaces.IASTincludeDefinition;

public class ASTincludeDefinition implements IASTincludeDefinition, IASTvisitable {
 private String filepath; 
 
 public ASTincludeDefinition(String filepath) {
     this.filepath = filepath;
//     System.out.println("AST creer avec "+filepath);
 }
 
 @Override
 public String getFilepath() {
//	 System.out.println("AST getFilepath "+filepath);
     return filepath;
 }


    @Override
    public <Result, Data, Anomaly extends Throwable>
    Result accept(com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor, Data data)
            throws Anomaly {
        return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
    }
}