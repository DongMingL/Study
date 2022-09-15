package com.ldm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁东明
 * 2022/8/30
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 * 二分查找（折半）查找（适用于有序序列）
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1,3,3,3,5,7,9,13,34,45,78};
        /*//切记arr是有序数组！！！！二分查找只适合有序数组
        //我一开始跟着老师代码完全一样就是报 栈溢出 的错误
        //通过debug才发现我的arr一开始是无序的。又一次加深印象了.原来二分查找只适合有序序列。
        int binarySearch = binarySearch(arr, 0, arr.length - 1, 79);
        System.out.println("binarySearch = " + binarySearch);*/

        List<Integer> integers = binarySearch2(arr, 0, arr.length - 1, 3);
        System.out.println("值为3的下标分别为 = " + integers);
    }


    /**
     * //二分查找算法
     * @param arr 被查找的数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal  要查找的值
     * @return   返回要查找的值或者-1
     */
    public static int binarySearch(int[] arr, int left, int right,int findVal){
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (left > right){
            return -1;
        }

        if ( findVal > midVal ){
            //开始向右递归
            return binarySearch(arr, mid+1,right,findVal);
        }else if (findVal < midVal){
            //开始向左递归
            return binarySearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }
    /**
     * //对二分查找算法优化
     * 即查找的值有多个相同的值，将所有相同的值查找出来
     *
     * 思路分析：
     * 1、找到mid值时不用立即返回。
     * 2、向mid索引值的左边扫描将满足的的相同值的下标保存在集合ArrayList
     * 3、向mid索引值的右边扫描将满足的的相同值的下标保存在集合ArrayList
     * 4、返回ArrayList
     * @param arr 被查找的数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal  要查找的值
     * @return   返回要查找的值或者-1
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        System.out.println("查找次数");
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        //这时候不能返回-1啦，要返回一个空的Integer集合
        if (left > right){
            return new ArrayList<Integer>();
        }

        if ( findVal > midVal ){
            //开始向右递归
            return binarySearch2(arr, mid+1,right,findVal);
        }else if (findVal < midVal){
            //开始向左递归
            return binarySearch2(arr,left,mid-1,findVal);
        }else {
            List<Integer> resIndexList = new ArrayList<>();
           // 2、向mid索引值的左边扫描将满足的的相同值的下标保存在集合ArrayList
            int temp = mid -1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal){
                    break;
                }
                //将相同的值保存在集合中
                resIndexList.add(temp);
                temp --;  //temp左移
            }
            resIndexList.add(mid);
            // 3、向mid索引值的右边扫描将满足的的相同值的下标保存在集合ArrayList
            temp = mid +1;
            while (true) {
                if (temp > arr.length -1 || arr[temp] != findVal){
                    break;
                }
                //将相同的值保存在集合中
                resIndexList.add(temp);
                temp ++;  //temp右移
            }
            return resIndexList;
        }
    }
}
