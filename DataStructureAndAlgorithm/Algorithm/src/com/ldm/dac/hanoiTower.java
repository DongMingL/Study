package com.ldm.dac;

/**
 * @author 梁东明
 * 2022/9/11
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class hanoiTower {
    public static void main(String[] args) {
        HanoiTower(3,'A','B','C');
    }

    //汉诺塔移动方案
    public static void HanoiTower(int num, char a, char b, char c){

        //如果只有一个盘，直接移动
        if (num == 1){
            System.out.println("第1个盘从 " + a +"->" + c);
        }else { //如果不止一个盘，我们总是可以看作是两个盘，
            //最上面的盘是 num - 1
            //最下面的盘是 num
            //先把最上面的盘从A移动到B。移动过程使用到C，递归
            HanoiTower(num - 1,a ,c,b);
            //把最下面的盘从A移动到C 。
            System.out.println("第" +num+"个盘从 " + a +"->"+c);
            //再把B上的盘移动到C盘，移动过程使用到A，递归
            HanoiTower(num -1,b , a, c);

        }
    }
}
