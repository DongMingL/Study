package com.ldm.sort;

import java.util.Arrays;

/**
 * @author 梁东明
 * 2022/8/28
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args){
        /*//随机生成20个100以内的数插入数组中
        int arr[] = new int[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = (int)(Math.random()*100);
        }*/
        int[] arr = {2,56,12,56,63,45,88,46,13,78,25};
        System.out.println("排序前~\n"+ Arrays.toString(arr));
        quickSort1(arr, 0, arr.length-1);
        System.out.println("排序后~\n"+ Arrays.toString(arr));
    }

    //这里的代码太繁杂了,我上网上找了另外一份更好理解的代码并做了更详细的注解
/*    public static void quickSort(int[] arr,int low,int high){

        int i = low;  //保存数组的第一个索引
        int j = high; //保存数组最后一个索引
        int middle = arr[(low+high) / 2];  //我的基准值是中间值,你可以自己随意设置,你喜欢就好
        int temp;  //辅助节点,用来交换节点的

        //while循环的意义是遍历数组
        //比middle大的数放在右边
        //比middle小的数放在左边
        while (i<j) {
            //先看右边，依次往左递减
            while (middle<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (middle>=arr[i]&&i<j) {
                i++;
            }
            if(i>=j){  //如果low大于high
                break;
            }
            //如果满足条件则交换

            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            if (arr[i] == middle){
                j -= 1;
            }
            if (arr[j] == middle){
                i += 1;
            }

        }
        if (i == j){
            i += 1;
            j -= 1;
        }
        //递归调用左半数组
        if (low < j){
            quickSort(arr, low, j);
        }
        //递归调用右半数组
        if (high > i){
            quickSort(arr, i, high);
        }
    }*/
    public static void quickSort1(int[] arr, int left, int right){
        int i ;   //保存数组的第一个索引
        int j;   //保存数组最后一个索引
        //判断什么时候退出方法,当然是
        if (left > right){
            return;
        }
        i = left;
        j= right;
        int temp = 0;   //辅助节点,用来交换节点的
        int x;          //基准值

        x = arr[left];  //我的基准值是第一个值,你可以自己随意设置,你喜欢就好
        //while循环的意义是让 i 和 j 同时遍历数组
        //比middle大的数放在右边
        //比middle小的数放在左边
        while (i < j){
            //j遍历数组右边  j--,右边的数要大于基准值
            while (x <= arr[j] && i < j){
                j--;
            }
            //i遍历数组左边  i++,左边的数要小于基准值
            while (x >= arr[i] && i < j){
                i++;
            }
            //当前两个while循环退出,说明出现了右边的值比middle小,左边的值比middle大
            //所以要交换这两个节点,交换之前,需要判断数组是否越界,借助临时变量temp来交换节点
            if (i < j){
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }

        }
        //当第一个while循环遍历完成,说明第一遍遍历完成了,接下来就要开始递归啦!!!
        //递归前,需要将基准值修改,将基准值和i j 相等的地方数字交换
        arr[left] = arr[i];
        arr[i] = x;

        //递归调用左边数组
        quickSort1(arr,left, j-1);
        //递归调用右边数组
        quickSort1(arr,j + 1, right);
    }

}
