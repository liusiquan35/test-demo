package com.example.test.demo.service;

/**
 * 用于验证前端的数据是否准确
 */
public class VerifyUtils {


    //验证身份证
        public static boolean yzIdentity(String identity) {
            int nums=0;

            //是否18位
            if (isNumeric(identity) && identity.length() == 18) {
                //验证相加
                for (int i = 0; i < 18; i++) {
                    if (i==17){


                    }else{
                        nums += identity.indexOf(i) - 48;
                    }
                }
            }
            //排除其他所有不适条件
            return false;
        }


    //验证手机号







    //是否为数字
    public static boolean isNumeric(String str){
        for(int i=str.length();--i>=0;){
            int chr=str.charAt(i);
             if(chr<48 || chr>57){
                return false;
            }

        }
        return true;
    }

}
