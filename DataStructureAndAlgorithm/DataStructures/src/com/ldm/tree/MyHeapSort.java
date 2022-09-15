package com.ldm.tree;

import java.util.Arrays;

/**
 * @author 梁东明
 * 2022/9/3
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class MyHeapSort {
    public static void main(String[] args) {
        int[] arr = {3,9,12,6,2,4,15};
        heapSort(arr);
    }

    /**
     * 堆排序
     *
     * @param arr 待排序的数组
     */
    public static void heapSort(int[] arr){
        System.out.println("这就是堆排序~");
        int temp = 0;
        for (int k = (arr.length-1)/2;k>=0;k--){
            adjustSort(arr,k, arr.length);
        }
        for (int i = arr.length - 1; i > 0 ; i--) {
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustSort(arr,0,i);
        }

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 调整堆
     *
     * @param arr    传入的数组
     * @param i      开始调整堆的索引
     * @param length 被调整的堆的长度
     */
    public static void adjustSort(int[]arr,int i, int length){
        int temp = arr[i];
        for (int k = i*2 +1; k < length  ; k = k*2 +1) {
            if (k+1 < length && arr[k] < arr[k+1]){
                k++;
            }
            if (arr[k] > arr[i]){
                arr[i] = arr[k];
                i =  k ;
            }else {
                break;
            }
        }
        arr[i] = temp;
    }
}
