package com.paracamplus.pstl.outil;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class readFichier {
	
	public static String readIncludeFileContent(String filePath) throws IOException {
	    //read content d'un fichier
	    byte[] encoded = Files.readAllBytes(Paths.get(filePath));
	    return new String(encoded, StandardCharsets.UTF_8);
	}

}
