package com.ldm.search;

/**
 * @author 梁东明
 * 2022/8/29
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 * 线性查找
 */
public class SeqSearch {
    public static void main(String[] args) {

        int[] arr = {1,33,23,56,12,46,23,86,35,25};
        int index = seqSearch(arr,25);
        if (index ==-1){
            System.out.println("数组中没有这个值...");
        }else {
            System.out.println("该值的下标是:"+index);
        }
    }

    /**
     *线性查找,找到满足条件的值就返回下标
     * @param arr 数组
     * @param value  要查找的值
     * @return 返回的值的下标,没有就返回-1
     */
    public static int seqSearch(int[] arr,int value){
        //线性查找是逐一比对,发现有相同值,就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                return i;
            }
        }
        return -1;
    }
}
