package com.ldm.sort;

import java.util.Arrays;

/**
 * @author 梁东明
 * 2022/8/29
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        int[] temp = new int[arr.length];  //归并排序需要额外的空间
        //本人非常建议你在此处下一个断点,跟着debug一步一步查看代码的执行过程
        //这样有利于你理解代码,否则单单看注解很难看懂.
        mergerSort(arr,0, arr.length-1, temp);
        System.out.println("归并排序后~\n"+ Arrays.toString(arr));
    }
    //下面两个方法充分利用 分-治 的思想
    /**
     * 分解的方法
     * @param arr  排序的数组
     * @param left  左边有序列表的初始索引
     * @param right  右边的索引
     * @param temp  做中转的临时数组
     */
    public static void mergerSort(int[] arr, int left, int right, int[] temp){
        if (left < right){
            int mid = (left+right)/2; //中间索引
            //向左递归进行分解
            mergerSort(arr, left, mid, temp);
            //向右递归进行分解
            mergerSort(arr,mid+1,right,temp);

            //合并
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     * //合并方法
     * @param arr  排序的数组
     * @param left  左边有序列表的初始索引
     * @param mid    中间索引
     * @param right  右边的索引
     * @param temp  做中转的临时数组
     */
    public static void merge(int[] arr,int left, int mid,int right,int[] temp){
        int i = left;   //初始化i,左边有序序列的初始索引
        int j = mid +1 ;   //初始化j,右边有序序列的初始索引
        int t = 0;       //指向temp数组的当前索引

        //第一步:
        //先把左右两边有序的数据按照规则填充到temp数组
        //直到左右两边的有序序列,有一边完全处理完毕
        while (i <=mid && j<=right){ //开始遍历左右两边
            if(arr[i] < arr[j]){ //谁小谁就进入temp数组,记得后移,方便后续遍历
                temp[t] = arr[i];
                t++;
                i++;
            }else{
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        //第二步:
        //把有数据剩余的一边也插入temp数组中
        while (i <= mid){  //可能是左边有序序列有剩余
            temp[t] = arr[i];
            i++;
            t++;
        }
        while (j <= right){  //也有可能是右边有序序列有剩余
            temp[t] = arr[j];
            j++;
            t++;
        }
        //第三步:.
        //把temp数组的元素重新插入到arr中
        //注意,并不是每次拷贝所有数据
        t = 0;
        int tempLeft = left;
        System.out.println("tempLeft=\t"+tempLeft +"\tright=\t"+ right);
        while(tempLeft <= right ){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
