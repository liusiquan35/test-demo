package com.example.test.demo.utils;

import java.nio.charset.Charset;



import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 步周:
 * 1.获取链接
 * 2.返回结果
 * 3.解析结果并输出到控制台
 */
public class SSHTool {

        private Connection conn;
        private String ipAddr;
        private Charset charset = StandardCharsets.UTF_8;
        private String userName;
        private String password;
        private int port;

        public SSHTool(String ipAddr, String userName, String password, int port, Charset charset) {
            this.ipAddr = ipAddr;
            this.userName = userName;
            this.password = password;
            this.port=port;
            if (charset != null) {
                this.charset = charset;
            }
        }

        /**
         * 登录远程Linux主机
         *
         * @return 是否登录成功
         */
        private boolean login() {
            conn = new Connection(ipAddr,port);
            try {
                // 连接
                conn.connect();
                // 认证
                return conn.authenticateWithPassword(userName, password);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }


        /**
         * 执行Shell脚本或命令
         *
         * @param cmds 命令行序列
         * @return 脚本输出结果
         */
        public StringBuilder exec(String cmds) throws IOException {
            InputStream in = null;
            StringBuilder result = new StringBuilder();
            try {
                if (this.login()) {
                    // 打开一个会话
                    Session session = conn.openSession();
                    session.execCommand(cmds);
                    in = session.getStdout();
                    result = this.processStdout(in, this.charset);
                    //获取出错的结果
                    if ("".equals(result.toString().trim())){
                        result = this.processStdout(session.getStderr(),this.charset);
                    }

                    conn.close();
                    System.err.println(result);
                }
            } finally {
                if (null != in) {
                    in.close();
                }
            }
            return result;
        }

        /**
         * 解析流获取字符串信息
         *
         * @param in      输入流对象
         * @param charset 字符集
         * @return 脚本输出结果
         */
        public StringBuilder processStdout(InputStream in, Charset charset) throws FileNotFoundException {
            byte[] buf = new byte[1024];
            StringBuilder sb = new StringBuilder();
            try {
                // 此方法是休息10秒后最后一次性输出2行数据
                int length;
                while ((length = in.read(buf)) != -1) {
                    sb.append(new String(buf, 0, length));
                }

                // 这个会按照脚本一步一步执行，中途有休息10秒。
                BufferedReader reader = null;
                String result = null;
                reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                while ((result = reader.readLine()) != null) {//如果读到的不为null
                    System.out.println(result);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb;
        }
    }



