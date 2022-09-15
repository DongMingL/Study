package com.ldm.recursion;

/**
 * @author 梁东明
 * 2022/8/25
 * 86139
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class RecursionTest {
    public static void main(String[] args) {
        int res  = factorial(4);
        System.out.println("res = " + res);

    }
    //阶乘
    public static int factorial(int n){
        if (n == 1){
            return 1;
        }else {
            return factorial(n-1)*n;
        }
    }
}
