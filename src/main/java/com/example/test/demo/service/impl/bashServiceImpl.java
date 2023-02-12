package com.example.test.demo.service.impl;

import com.example.test.demo.utils.SSHTool;
import com.example.test.demo.utils.fileReadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.binary.XSSFBHyperlinksTable;
import org.springframework.stereotype.Service;
import sun.security.jgss.GSSHeader;

import java.io.IOException;
import java.nio.ReadOnlyBufferException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class bashServiceImpl {

    //文件

    public static void main(String[] args) throws IOException, InterruptedException {
        //引入sshtools
        SSHTool sshTool = new SSHTool("192.168.50.106", "root", "root", 22, StandardCharsets.UTF_8);
        //文件位置,只能放files文件夹里
        String path = "mysqld";

        log.debug("已连接");
        List<String> lines = fileReadUtil.fileToList(path);
        int a=1;
        //遍历执行脚本
        for (String line : lines) {
            System.out.println(a+++":   "+line);
            //等待3秒
            if ("sleep".equals(line)){
                Thread.sleep(3000);
                continue;
            }
            sshTool.exec(line);
        }
        System.out.println("已完成");
    }

}

