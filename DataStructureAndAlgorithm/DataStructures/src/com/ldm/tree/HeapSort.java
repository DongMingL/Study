package com.ldm.tree;

import java.util.Arrays;

/**
 * @author 梁东明
 * 2022/9/2
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9,-2,99};
        heapSort(arr);
    }
    public static void heapSort(int[] arr){
        System.out.println("堆排序");

        int temp =0;   //临时变量
        //1、将无序数组构建出一个堆，根据升降排序的需求选择大顶堆或者小顶堆
        //这样做的目的是将数组最大或最小的值升到堆顶
        //(arr.length-1)/2  从最后一个非叶子节点开始调整堆
        //至于(arr.length-1)/2怎么得来的，我再提一下：
        //最后一个非叶子节点==最后一个叶子节点的父节点
        //最后一个叶子节点的索引就是arr.length
        for (int i = (arr.length-1)/2; i >= 0;i--){
            adjustHeap(arr,i, arr.length);
        }
        //2、上一步已经把数组最大值升到堆顶了
        //所以我们需要将堆顶元素和末尾元素交换，将最大（小）值放到数组末尾
        //重新调整堆，使arr.length-1的元素继续排成大（小）堆顶，最大值不参与下一次的堆所以要arr.length-1
        //然后循环1、2步的操作，直到完全排序好
        //这个for循环是倒序循环的，看清楚了，每次取出的j就是数组末尾的索引
        for (int j = arr.length - 1; j > 0; j--){
            //交换节点，如果这个还不会，赶紧去学冒泡排序
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //开始调整堆,记住啦j是递减的，所以需要调整的堆会越来越小
            //直到数组被排序完毕
            adjustHeap(arr,0, j);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 调整堆
     * 功能：完成将以i对应的非叶子节点的树调整成大顶堆
     *（小提示：要学会这个排序，最好先学会完全二叉树，巧了，我主页刚好有教程，
     * 翻一翻，动个小手去学吧！否则代码你只会有用而看不懂。）
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组的索引
     * @param length 表示对多少个元素继续调整，length是在逐渐的减少
     */
    public static void adjustHeap(int[] arr, int i, int length){
        int temp = arr[i]; //先取出当前元素的值，保存在临时变量中
        //开始调整堆
        for (int k = 2*i+1; k <length ; k=2*k+1) {  //如果你了解完全二叉树，你就知道i节点的左子节点就是2*i+1，右子节点就是2*i+2，它们的索引相差1
            if (k+1 <length && arr[k] < arr[k+1]){ //防止数组越界  &&  取出当前i节点的左右子节点比较（如果左子节点小于右子节点，指针就指向右子节点）
                k++;  //k++，这样k就指向了右子节点  。把左右子节点最大值取出来，在下一条语句和i比较
            }
            if (arr[k] > temp){  //如果子节点大于父节点
                arr[i] = arr[k];  //就把较大的值赋值给当前i节点
                i = k;   //i指向k，继续循环比较  ，而且还是自下而上（最重要，理解这行代码基本就懂了）对堆调整
            }else {
                break;
            }
        }
        //当for循环结束时，以i为父节点的树的最大值，放在顶部（局部）
        arr[i] = temp; //将temp值放在调整之后的位置
    }
}
