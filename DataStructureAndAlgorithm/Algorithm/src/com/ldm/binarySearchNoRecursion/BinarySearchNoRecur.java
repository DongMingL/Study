package com.ldm.binarySearchNoRecursion;

/**
 * @author 梁东明
 * 2022/9/10
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 * 二分查找非递归算法
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = { 1,3,5,7,9,12,15,18,21,24,27};

        int i = binarySearch(arr, 24);
        if ( i == -1){
            System.out.println("数组中没有该元素");
        }else {
            System.out.println("1在数组中的索引是 = " + i);
        }
    }

    /**
     * 二分查找非递归
     *
     * @param arr    传进来的有序数组
     * @param target 目标元素
     * @return int   返回该目标元素的下标，没有就返回-1
     */
    public static int binarySearch(int[] arr, int target){

        int left = 0;
        int right = arr.length - 1;
        while (left <= right){  //开始查找
            int mid = (left + right) / 2;
            if (arr[mid] == target){//找到就直接返回下标
                return mid;
            }else if (arr[mid] > target){//说明目标元素在数组的左边，在左边继续查找
                right = mid - 1 ;
            }
            else if (arr[mid] < target){//说明目标元素在数组的右边，在右边继续查找
                left = mid + 1;
            }
        }
        //说明数组中没有该目标，直接返回-1
        return -1;
    }
}
