package com.ldm.sort;

/**
 * @author 梁东明
 * 2022/8/26
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class BubbleSort {
    public static void main(String[] args) {
        //int[] arr= {3, 8, 1, 17, 9, 13};
        
        //给有100个乱序数据的数组排序
        int[] randomArray = new int[100];
        //插入数据当然要遍历啦！！！
        for (int i = 0; i < randomArray.length; i++) {
            //如果不会使用Math接口的方法，不用担心
            //我会在文章的尾部提供JDK8相关的官方接口文档，直接搜索查看就行啦！！！
            randomArray[i] = (int)(Math.random()*100);  //随机生成0-100的随机数
        }
        
        BubbleSortMethod(randomArray);

    }
    public static void BubbleSortMethod(int[] arr){
        System.out.println("排序之前");
        //遍历输出数组
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
        int temp = 0;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1 -i; j++) {
                if (arr[j] > arr[j+1]){
                    temp  = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        System.out.println("\n"+"排序之后");
        //遍历输出数组
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }

    }
}
