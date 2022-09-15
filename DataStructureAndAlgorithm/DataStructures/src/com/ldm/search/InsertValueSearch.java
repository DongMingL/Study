package com.ldm.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 梁东明
 * 2022/8/30
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 * 插值查找
 * 插值查找与二分查找的区别：查找次数比二分查找少；查找的mid值不同
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        //先创建一个容量为100，1为第一个值的递增数组
        int arr[] = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i+1;
        }
        int i = insertValueSearch(arr, 0, arr.length - 1, 64);
        System.out.println("64的索引是 = " + i);



    }

    /**
     * 编写插值查找算法，要求：数组是有序的
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 查找的值
     * @return  返回值,找到直接返回，没找到就返回-1
     */
    public static int insertValueSearch(int[] arr,int left,int right, int findVal){
        System.out.println("查找次数");
        //在数组中没找到该值，返回-1 比二分查找多了一个条件：判断mid是否越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]){
            return -1;
        }
        //求出mid
        int mid = left + (right - left)*(findVal -arr[left])/(arr[right] -arr[left]);
        int midVal = arr[mid];
        if ( findVal > midVal ){
            //开始向右递归
            return insertValueSearch(arr, mid+1,right,findVal);
        }else if (findVal < midVal){
            //开始向左递归
            return insertValueSearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }

}
