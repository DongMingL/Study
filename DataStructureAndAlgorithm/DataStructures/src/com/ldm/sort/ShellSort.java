package com.ldm.sort;

import java.util.Arrays;

/**
 * @author 梁东明
 * 2022/8/27
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     *自己写的代码
     * 交换式
     * @param arr
     */
    public static void shellSort(int[] arr){
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap ; i < arr.length; i++) {
                for (int j = i -gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            System.out.println("第"+(++count)+"轮:\n"+Arrays.toString(arr));
        }
    }

    /**
     * 移位式
     * @param arr
     */
    public static void shellSort2(int[] arr){
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {//缩小增量
            for (int i = gap; i <arr.length ; i++) { //插入排序
              int j = i;  //保存待插入节点的索引
              int temp = arr[j];  //保存待插入节点:来自无序表,用来和有序表的节点比较,找到合适位置再插入
               if (arr[j] < arr[j - gap]){//小于同组的前一个节点,需要遍历前一个节点为尾巴的有序表
                   //j - gap >= 0防止数组越界,,开始遍历
                   while(j - gap >= 0 && temp < arr[j -gap]){
                       arr[j] = arr[j - gap];
                       j -= gap;
                   }
                   arr[j] = temp;
               }

            }
        }
    }
    /**
     * 移位式
     *自己写的第二遍,增强记忆
     *
     */
    public static void shellSort3(int[] arr){
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap;  i < arr.length ; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]){
                    while(j - gap >= 0 && temp < arr[j - gap]){
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }
    /**
     * 老师的希尔排序分析
     * @param arr
     */
    public static void shellSort1(int[] arr){
        int temp = 0;
        //逐步推导,比较好理解
        //第一轮把10个数据分为5组
        for (int i = 5; i <arr.length ; i++) {
            //遍历各组中所有的元素(共5组,每组两个数据),步长5
            for (int j = i - 5; j >= 0 ; j -= 5) {
                if (arr[j] > arr[j + 5]){ //谁节点大就交换节点,没啥好解释的.
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("第一轮希尔排序后:\n"+ Arrays.toString(arr));

        //第二轮把10个数据分为5/2 = 2组
        for (int i = 2; i <arr.length ; i++) {
            //遍历各组中所有的元素(共5组,每组两个数据),步长2
            for (int j = i - 2; j >= 0 ; j -= 2) {
                if (arr[j] > arr[j + 2]){ //谁节点大就交换节点,没啥好解释的.
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("第二轮希尔排序后:\n"+ Arrays.toString(arr));
        //第三轮把10个数据分为 2/2 = 1 组
        for (int i = 1; i <arr.length ; i++) {
            //遍历各组中所有的元素(共5组,每组两个数据),步长1
            for (int j = i - 1; j >= 0 ; j -= 1) {
                if (arr[j] > arr[j + 1]){ //谁节点大就交换节点,没啥好解释的.
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("第三轮希尔排序后:\n"+ Arrays.toString(arr));
    }


}
