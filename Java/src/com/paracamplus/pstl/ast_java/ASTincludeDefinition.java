package com.paracamplus.pstl.ast_java;


import com.paracamplus.pstl.interfaces.IASTincludeDefinition;

public class ASTincludeDefinition implements IASTincludeDefinition {
 private String filepath; 
 
 public ASTincludeDefinition(String filepath) {
     this.filepath = filepath;
 }
 
 public String getFilename() {
     return filepath;
 }
 
}