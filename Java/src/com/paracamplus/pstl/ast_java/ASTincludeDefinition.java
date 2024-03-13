package com.paracamplus.pstl.ast_java;


import com.paracamplus.pstl.interfaces.IASTincludeDefinition;

public class ASTincludeDefinition implements IASTincludeDefinition {
 private String filepath; 
 
 public ASTincludeDefinition(String filepath) {
     this.filepath = filepath;
     System.out.println("AST creer avec "+filepath);
 }
 
 @Override
 public String getFilepath() {
	 System.out.println("AST getFilepath "+filepath);
     return filepath;
 }
 
}