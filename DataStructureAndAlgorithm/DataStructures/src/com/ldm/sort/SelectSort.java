package com.ldm.sort;

import java.util.Arrays;

/**
 * @author 梁东明
 * 2022/8/26
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class SelectSort {
    public static void main(String[] args) {
        int arr[] = {55,33,22,75,21,2};
        System.out.println("排序前~");
        System.out.println(Arrays.toString(arr));
        //selectSort(arr);
        setSort(arr);
        System.out.println("排序后~");
        System.out.println(Arrays.toString(arr));
    }
   /* public static void selectSort(int[] arr){

        //选择排序的时间复杂度是O(n^2)
        //抱歉！因为本身就会选择排序，所以没有啥注解
        //建议去bi站搜韩顺平数据结构 第58p有详细注解
        for (int i = 0; i < arr.length -1; i++) {

            int minIndex = i;
            int min = arr[i];
            //把i = 0 这个数取出来之后再从数组取一个数出来比较
            //如果存在比min小的数，交换节点，继续遍历第 i+1 个节点直到
            //找到当前比较中最小的节点。让她和arr[i]交换
            for (int j =i + 1; j < arr.length; j++) {
                if (min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }
                if (minIndex != i) {
                    arr[minIndex] = arr[i];
                    arr[i] = min;
                }
//        System.out.println("第"+(i+1)+"轮后");
//        System.out.println(Arrays.toString(arr));
        }
    }
*/
    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i; //用来定位最小值的下标
            int min = arr[i]; //默认初始最小值，就是当前循环的第一个节点
            //j = i + 1 是因为取出从i开始到 arr.length 的节点与i比较大小，自然要+1啦
            for (int j = i + 1; j < arr.length; j++) {
                if (min >arr[j]){  //如果最小值比循环中任意一个节点大，说明他不是最小值
                    min = arr[j];  //把比最小值还小的值赋给最小值
                    minIndex = j;  //所以最小值的下标记得变成  真最小值 的下标
                    //记住啦，这时候 最小值已经是 arr[j] 啦！ 也就是说arr[minIndex] = arr[j]
                }
                //但这个for循环结束之后，最小值通过不断的比较，已经找到啦，
                //就是arr[minIndex] 至于为什么不是arr[j]是因为j会++变而minIndex不会变
            }
            //如果minIndex不是i说明交换过节点，
            //所以，需要重新让最小值和arr[i]交换节点
            //再把arr[i]置为最小值
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
    public static void setSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
