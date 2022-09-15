package com.ldm.search;

import java.util.Arrays;

/**
 * @author 梁东明
 * 2022/8/30
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 * 斐波那契查找法
 */
public class FibonacciSearch {
    public static int maxSize=20;
    public static void main(String[] args) {
        int arr[] = {1,8,10,89,1000,1234};
        fibonacciSearch(arr,10);
    }
    //先定义一个斐波那契数列
    //斐波那契数列的长度一定要大于或者等于arr的长度
    //非递归得到一个斐波那契数列
    public static int[] fibo(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }


    /**编写斐波那契查找算法
     * 最重要的是理解mid值的由来一定要懂，
     * 你已经是个成年人了要学会debug来测试代码了
     *非递归的方式
     * @param arr 数组
     * @param key 需要查找的关键字
     * @return 返回对应的下标，没有就返回-1
     */
    public static int fibonacciSearch(int[] arr, int key){
        int low = 0;
        int high = arr.length - 1;
        int k = 0; //表示斐波那契分割数值的下标
        int mid = 0; //存放mid值
        int f[] = fibo(); //获取到斐波那契数列
        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1){
            k++;
        }
        //因为f[k]的值可能大于a的长度。因此需要使用Arrays类，构造一个新的数组，并指向arr[]
        //不足的部分会用0来填充
        int[] temp = Arrays.copyOf(arr,f[k]);
        //实际上需要使用a数组最后的数填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        //使用while循环，来找到key值
        while (low <= high){
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]){  //遍历数组的左边
                high = mid -1;
                k--;
            }else if (key > temp[mid]){
                low = mid + 1;
                k -=2;
            }else {
                if ( mid <= high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }
}
