package com.ldm.sort;


import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args) {
        int arr[] = { 5, 183, 84, 94, 63};

        radixSort(arr);

    }

    //基数排序方法
    public static void radixSort(int[] arr) {

        //得到数组最大的数的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max ){
                max = arr[i];
            }
        }
        //得到最大数的位数
        int maxLength = String.valueOf(max).length();



        //定义一个二维数组,表示10个桶,每个桶就是一个一维数组
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候,数据溢出,则每个桶,大小定为arr.length
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶,实际插入多少个数据,我们定义一个一维数组来记录每个桶插入的上局个数
        int[] bucketElementCounts =new int[10];

        //使用循环将代码处理
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //针对每个元素的对应的位进行排序处理,第一次是个位,第二次是百位....

            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的个位数
                int digitOfElement = arr[j] / n % 10;
                //放入对应的对应的桶
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                //预防下一次还是这个位置有别元素的个位数相同而插不进来
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序(一维数组的下标依次取出数据,放入原来的数组)
            int index = 0;
            //遍历每一个桶,并将桶中的数据,放入原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据,才放入到原数组中
                if (bucketElementCounts[k] != 0){
                    //循环该桶即第k个桶(即第k个一维数组),放入数据
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出数据,放入原数组中
                        arr[index++] = bucket[k][l];

                    }
                }
                //第i+1轮处理之后需要将每个bucketElementCounts[k] == 0
                bucketElementCounts[k] = 0;
            }
            System.out.println("第"+(i+1)+"轮排序后~\n"+ Arrays.toString(arr));
        }


      /*  //第一轮(针对每个元素的个位数进行排序处理
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位数
            int digitOfElement = arr[j] % 10;
            //放入对应的对应的桶
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            //预防下一次还是这个位置有别元素的个位数相同而插不进来
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据,放入原来的数组)
        int index = 0;
        //遍历每一个桶,并将桶中的数据,放入原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据,才放入到原数组中
            if (bucketElementCounts[k] != 0){
                //循环该桶即第k个桶(即第k个一维数组),放入数据
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出数据,放入原数组中
                    arr[index++] = bucket[k][l];

                }
            }
            //第一轮处理之后需要将每个bucketElementCounts[k] == 0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第一轮排序后~\n"+ Arrays.toString(arr));

        //第2轮
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位数
            int digitOfElement = arr[j] / 10 % 10;
            //放入对应的对应的桶
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            //预防下一次还是这个位置有别元素的个位数相同而插不进来
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据,放入原来的数组)
        index = 0;
        //遍历每一个桶,并将桶中的数据,放入原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据,才放入到原数组中
            if (bucketElementCounts[k] != 0){
                //循环该桶即第k个桶(即第k个一维数组),放入数据
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出数据,放入原数组中
                    arr[index++] = bucket[k][l];

                }
            }
            //第一轮处理之后需要将每个bucketElementCounts[k] == 0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第二轮排序后~\n"+ Arrays.toString(arr));


        //第3轮
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位数
            int digitOfElement = arr[j] / 100;
            //放入对应的对应的桶
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            //预防下一次还是这个位置有别元素的个位数相同而插不进来
            bucketElementCounts[digitOfElement]++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据,放入原来的数组)
        index = 0;
        //遍历每一个桶,并将桶中的数据,放入原数组
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据,才放入到原数组中
            if (bucketElementCounts[k] != 0){
                //循环该桶即第k个桶(即第k个一维数组),放入数据
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出数据,放入原数组中
                    arr[index++] = bucket[k][l];

                }
            }
            //第一轮处理之后需要将每个bucketElementCounts[k] == 0
            bucketElementCounts[k] = 0;
        }
        System.out.println("第三轮排序后~\n"+ Arrays.toString(arr));
*/

    }
}
