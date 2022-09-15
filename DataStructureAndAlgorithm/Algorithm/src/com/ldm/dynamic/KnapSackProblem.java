package com.ldm.dynamic;

import java.util.Arrays;

/**
 * @author 梁东明
 * 2022/9/11
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 *
 * 0-1背包问题
 *
 */
public class KnapSackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3}; //物品的重量
        int[] val = {1500,3000,2000};//物品的价值

        int m = 4; //背包的容量
        int n = val.length; //物品的个数

        //创建二维数组表示 前i个商品能够装入容量为j的背包的最大价值
        int[][] v = new int[n+1][m+1];
        //为了记录物品的放置情况，再定义一个二维数组
        int[][] path = new int[n+1][m+1];




        //规定不能装任何东西的时候，或者背包的容量为0的时候，最大价值就是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;  //第一列设置为0（背包的容量为0的时候）
        }
        /*
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0; //第一行设置为0（背包不装任何东西的时候）
        }
        */
        //上面三行代码可以用这一行代码代替（CTRL + 点击 看看源码，这样你就懂了）
        //很多方法，都不需要我们重复造轮子，JDK自己就已经封装好了，我们直接调用就行了
        Arrays.fill(v[0], 0);//第一行设置为0（背包不装任何东西的时候）


        //动态规划
        for (int i = 1; i < v.length; i++) { //不处理第一行，因为上两步已经把它们置为0了
            for (int j = 1; j < v[i].length; j++) { //不出来第一列，

                //当准备加入新增的物品的容量大于当前背包的容量时，
                //此时背包的最大价值就是加入该物品之前，其他已经放入到背包中的最大价值
                 if ( w[i-1] > j){ //本来应该是w[i] > j 的，但是我们的程序是从1开始的，所以要减1
                     v[i][j] = v[i-1][j];
                 }
                //3.如果准备加入的物品的重量比背包包容量小的话，
                // 有两种选择 1、把当前物品放入背包  2、不把当前物品放入背包，
                // 这两种选择取决于 放入当前物品后是否会导致背包容量溢出，
                // 如果溢出的话，就比较当前背包的价值与溢出的物品的价值大小
                // 如果溢出前物品的价值大，就不放入当前物品。
                // 如果溢出前的物品价值小，就放入当前物品，把之前放入的物品取出。
                // 如果不溢出的话，把当前物品也加入到背包中，之前的物品也不需要取出。
                 else if (j >= w[i-1]){//本来应该是j >= w[i] 的，但是我们的程序是从1开始的，所以要减1

                     // v[i][j] = Math.max(v[i-1][j],val[i-1] + v[i-1][j-w[i-1]]);
                     if (v[i-1][j] > val[i-1] + v[i-1][j-w[i-1]] ){
                         v[i][j] = v[i-1][j];

                     }else {
                         v[i][j] =  val[i-1] + v[i-1][j-w[i-1]];
                         path[i][j] = 1;
                     }


                 }
            }
        }


        //遍历二维数组，输出元素
        System.out.println("背包中物品的价值变化");
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] +"\t");
            }
            System.out.println();
        }

        System.out.println("================");
        int i = path.length - 1; //行的最大下标
        int j = path[0].length - 1;  //列的最大下标
        //从path的最后面开始找，看看到底背包中最后放置了哪几个物品
        while ( i > 0 && j > 0){
            if (path[i][j] == 1){
                System.out.println("第 " + i +" 个物品放入到背包中");
                j -= w[i-1];
            }
            i--;
        }
        System.out.println("背包中物品的最大价值是：" +v[n][m]);
    }
}
