package com.ldm.kmp;

/**
 * @author 梁东明
 * 2022/9/12
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 *
 * 字符串匹配
 */
public class ViolenceMath {
    public static void main(String[] args) {

        String str1 = " ldm mdl nomLn dnh";
        String str2 = "nh";
        int i = violenceMath(str1, str2);
        if ( i == -1){
            System.out.println("str1中没有str2");
        }else {
            System.out.println("str2在str1出现的索引位置在：" + i);
        }


    }
    //暴力匹配算法实现
    public static int violenceMath(String str1, String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len  = s2.length;

        int i = 0; //字符串s1的初始索引
        int j = 0; //字符串s2的初始索引

        //
        while ( i < s1Len && j < s2Len){
          if ( s1[i] == s2[j]){//当出现第一个字符相等，继续比较
              i++;
              j++;
          }else {
              //不相等的话，i 后退 j-1 步
              //j 回到 第一步  即置为0
              i = i - (j-1);
              j = 0;
          }
        }

        //当退出循环，表示已经把两组字符串遍历完成了
        //接下就要看一看是否出现字符匹配的现象了
        if ( j == s2Len){  //如果str1中有str2的话，那么str2 应该遍历到尾部
            return i - j;  //那么就把str2在str1出现的索引返回
        }else {
            return -1;
        }

    }

}
