package com.paracamplus.pstl.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CompileAndRunCCode {

    public static void main(String[] args) {

        String sourceFilePath = "SamplesPSTL_compiler";
        String scriptPath = "C/compileThenRun.sh";

        ProcessBuilder processBuilder = new ProcessBuilder(scriptPath,"+gc", sourceFilePath);

        try {
            // 启动进程
            Process process = processBuilder.start();
            // 读取进程的标准输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            // 等待进程结束
            int exitCode = process.waitFor();
            System.out.println("Exited with code : " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
