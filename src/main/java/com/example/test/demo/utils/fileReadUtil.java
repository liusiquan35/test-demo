package com.example.test.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class fileReadUtil {
    public static List<String> fileToList(String path) throws IOException {

        path = "./src/main/java/com/example/test/demo/files/"+path;
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        /*
        BufferedReader提供方法:
        String readLine()
        该方法可以就读取一行字符串
        如果流读取到了末尾,则返回值为null
         */

        List<String> lines = new ArrayList<>();
        String line=null;//  br.readLine();  一行一行地读取
        while ((line=br.readLine()) != null) {
            StringBuilder str = new StringBuilder();

            if (line.trim().charAt(0)=='#'){
                line=null;
                continue;
                }

            lines.add(line);
            }

        br.close();
        log.info(String.valueOf(lines));
    return lines;
    }
}