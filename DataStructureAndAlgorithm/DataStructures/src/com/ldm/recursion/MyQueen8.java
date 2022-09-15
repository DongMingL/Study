package com.ldm.recursion;

/**
 * @author 梁东明
 * 2022/8/26
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class MyQueen8 {
    int max1 = 8;
    int[] array = new int[max1];
    static int count = 0;
    static int backCount;

    public static void main(String[] args) {
        //测试我自己写的代码是否正确
        MyQueen8 myQueen8 = new MyQueen8();
        myQueen8.check1(0);
        System.out.println("解法 = " + count);
        System.out.println("回溯次数 = " + backCount);
    }
    /**
     * 插入n皇后
     */
    void check1(int n){
        if (n == max1){
            print();
            return;
        }
        for (int i = 0; i < max1; i++) {
            array[n] = i;
            if (judge1(n)){
                check1(n+1);
            }

        }
    }
    /**
     * 判断插入的第n个皇后是否冲突
     */
    private boolean judge1(int n){
        backCount++;
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] -array[i])){
                return false;
            }
        }
        return true;
    }
    /**
     * 打印一维数组
     */
    private void print(){
        count++;
        for (int i : array) {
            System.out.print(i+" ");
        }
        System.out.println();

    }
}
