package com.lyp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunPythonWav {

    public static void getPython(String[] pyPath) throws IOException {

        Process process;
        process = Runtime.getRuntime().exec(pyPath);
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        in.close();
    }

}
