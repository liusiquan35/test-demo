package com.example.test.demo.utils;

public class vaUtil {

    //1.判断大小写
    public static boolean isabc(String str){
        //判断ascii码
        for (int i = 0; i < str.length(); i++) {
            if (str.indexOf(i)>97 || str.indexOf(i)<122){
                return false;
            }
        }
        return true;
    }

    //身份证验证
    public static boolean yzIdentity(String str){
        //是否满18个
        if (str.length()==18){
            //判断是否可以叠加
            StringBuilder stringB = new StringBuilder(str);

        }

        return false;

    }
}
