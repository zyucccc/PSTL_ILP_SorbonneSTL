package com.paracamplus.pstl.parser.ilpml;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//example code pour remplacer include par code reel
public class Preprocessor {
    
    public static String preprocessIncludes(String sourceCode) throws IOException {
        // A modifier,appel par interpreter
        Pattern pattern = Pattern.compile("include\\s+\"([^\"]*)\";");
        Matcher matcher = pattern.matcher(sourceCode);
        StringBuffer buffer = new StringBuffer();
        
        while (matcher.find()) {
            String includeFilePath = matcher.group(1);
            String fileContent = readFileContent(includeFilePath);
            // remplacer include par code reel
            matcher.appendReplacement(buffer, Matcher.quoteReplacement(fileContent));
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }
    
    private static String readFileContent(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
    }
}
