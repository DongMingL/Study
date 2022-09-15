package com.ldm.sort;

import java.util.Arrays;

/**
 * @author 梁东明
 * 2022/8/27
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 *
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {

        //int arr[] = {89,12,44,14,51,28,87,1};

        int arr[] = new int[8000];
        //在数组随机插入8000条数据看一下执行时间
        for (int i = 0; i < 8000; i++) {
            arr[i] = (int)(Math.random()*100); //每个数的大小范围在100之内
        }



        //千万不要与8000条数据的数组执行这条输出语句,否则你的控制台会输出8000个字符，影响美观
        //System.out.println(Arrays.toString(arr));
        //看一下将8000条数据排序需要多少时间吧
        long start = System.currentTimeMillis();
        insertSort(arr); //在这里设置一个断点，然后debug看一下程序的执行路径
        long end = System.currentTimeMillis();
        //System.out.println(Arrays.toString(arr));
        System.out.println("排序的时间是：" + (end-start)+ "毫秒");

        //System.out.println(Arrays.toString(arr));
    }

    /**
     * 这个是我按照解题思路自己敲的代码,没有注解,总共才12行.
     * 你可以看下一个方法,有非常详细的注解
     * @param arr
     */
    public static void insertSort2(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];
            int insertIndex = i - 1 ;
            while(insertIndex >=0 && insertValue < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i){
                arr[insertIndex + 1] = insertValue;
            }
        }
    }

    /**
     * 插入排序
     * @param arr 传入一个一维数组
     * 建议找张纸跟着步骤画一下图,不然真的很难理解的.
     *
     */
    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {  //i是从1开始的喔，要记住哦！
            int insertVal = arr[i];  //要插入的数，i之后的数是无序的哦！
            int insertIndex = i - 1;  //i之前的元素都是有序的哦！，默认是arr[0]是有序的,容量为1.
            /*
            //while循环的意义是：每次循环都把无序表的第一个元素
            //通过与有序表的元素比较，找到合适插入的位置,退出循环
            //insertIndex >= 0 防止数组越界
            //insertVal < arr[insertIndex] 就是按照小到大的顺序排序
             */
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex]; //arr[insertIndex]后移
                //因为要插入的数要插入被比较过的数的前面,对数组数据进行插入时需要将后面的元素后移,这就是arr[insertIndex]后移的原因

                insertIndex--;  //这是让insertVal与有序表中的数进行再次比较,
                //当insertVal > arr[insertIndex] 就会退出while循环
                // insertIndex--; 意味着有序表是逆序遍历的.
                //韩老师的代码我起码看了七八遍才懂,这里你一下子看不懂也是很正常的.所以,如果想真的弄懂的话
                //要花点苦功夫去韩老师的教程去认真的学,学习最主要的就是迎难而上,如果看不懂就放弃了,我觉得这是意见很悲哀的事情
                //因为知识是免费的,你现在学不会,以后可能就要为学不进去而付出代价.
            }

            //当退出while循环,说明插入的位置已经找到,这时候让insertVal放在有序表中insertIndex的后面啦!
            //插入之前要判断是否需要赋值,只有insertIndex + 1 == i需要赋值,那我们就不让它等于i顺便帮它赋值
            if (insertIndex + 1 != i){
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
