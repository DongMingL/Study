package com.ldm.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁东明
 * 2022/8/24
 * 86139
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class TestDemo {

    public static void main(String[] args) {

    }
    public static List<String> Test(String s){
        //定义一个List,存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0; //这时是一个指针，用于遍历 中缀表达式字符串
        String str; // 对多位数的拼接
        char c; // 每遍历到一个字符，就放入到c
        do {
            //如果c是一个非数字，我需要加入到ls
            if((c=s.charAt(i)) < 48 ||  (c=s.charAt(i)) > 57) {
                ls.add("" + c);
                i++; //i需要后移
            } else { //如果是一个数，需要考虑多位数
                str = ""; //先将str 置成"" '0'[48]->'9'[57]
                while(i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        }while(i < s.length());
        return ls;//返回
    }
}
