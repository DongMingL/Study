package com.ldm.recursion;

/**
 * @author 梁东明
 * 2022/8/25
 * 86139
 * 8皇后递归
 * 人生小建议，看不懂的方法或者类记得CTRL + 点击 看看注解
 */
public class Queue8 {
    //定义一个max表示有多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置位置的结果，比如 arr = { 0 , 4, 7, 5, 2, 6, 1, 3}
    int[] arr = new int[max];
    static int count = 0;//这个变量是统计有多少种解法
    static int judgeCount; //这个变量是统计回溯了多少次的
    public static void main(String[] args) {
        //测试代码
        Queue8 queue8 = new Queue8();
        //这里的0，是从第一个皇后放起，不影响你有多少个皇后，因为一开始就定义了max = 8；
        queue8.check(0);
        System.out.println("一共有 = " + count+"解法");
        System.out.println("一共有判断冲突的 = " + judgeCount+" 次回溯次数");
    }
    /**
     * 编写一个方法，放置第n个皇后
     * 特别注意：check 是每一次递归时，就会进入check中都有 for（int i = 0; i < max ;i++),因此会有回溯
     * 回溯就是把上一层没走完的for循环走下去，不是把本次的for循环走完，很重要的知识点！！！！，家人们一定要记住
     * @param n 表示第几个皇后
     */
    void check(int n){
        if (n == max){  //如果n是第八个皇后，说明已经放完全部的皇后
            print();
            return;
        }
        /*
        //依次放入皇后，并判断是否冲突
        //for循环的快捷键就是fori，
        //可以直接写max.fori就可以迅速打印for循环啦！
        //无聊的小知识又增加了
        */
        for (int i = 0; i < max; i++) {
            //先把当前的皇后n，放到该行的第一列
            arr[n] = i;

            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)){ //不冲突
                //接着放置n+1个皇后，即开始递归 很重要！！！！！！一定要看懂这行代码
                check(n+1);
            }
            //如果冲突，就继续执行arr[n] = i;但是i在循环中，下一次循环i=i+1;即 将第n个皇后，放置在本行的后移一个位置
        }
    }
    /**
     *  查看当我们放置第n个皇后,就去检测该皇后是否和前面已经摆放的皇后冲突
     * @param n 表示第n个皇后
     * @return true
     */
    private boolean judge(int n){
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if (arr[i] ==arr[n] || Math.abs(n-i) == Math.abs(arr[n]-arr[i])){
                /*
                //如果两个皇后在同一列会相互攻击，同一斜线也会相互攻击，同一行也会相互攻击，所以要排除
                ///Math.abs ctrl+点击进去看源码可以知道这是用来求绝对值的
                //更详细的说法是： 行数的差值不能等于列数的差值，相等就说明在同一斜线上
                //数学学过吧？ 把y=x在象限画出来就可以很清楚理解了 斜率为1，在同一条斜线上
                //你可能会说，还有一个是否在同一行没有判断，如果皇后同一行也会相互攻击
                //但是你有没有想过：n是递增的，它们是不可能在同一行的 ，所以不需要判断在同一行
                 */
                return false;
            }
        }
        return true;
    }
    /**
     * 写一个方法，可以将皇后摆放的位置打印出来
     * 就是一个增强for循环对一维数组遍历
     */
    private void print(){
        count++;
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }
}
