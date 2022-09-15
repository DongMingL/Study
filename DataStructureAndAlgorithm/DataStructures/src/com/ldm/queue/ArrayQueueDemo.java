package com.ldm.queue;

import java.util.Scanner;

/**
 * @author 梁东明
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {

        //创建一个队列对象
        ArrayQueue arrayQueue = new ArrayQueue(4);
        char key = ' '; //接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("a====show you Queue======");
            System.out.println("b=========exit===========");
            System.out.println("c=======add data to Queue");
            System.out.println("d=====get data from Queue");
            System.out.println("e=show the headData in Queue");
            key = scanner.next().charAt(0); //接受一个字符串
            switch (key) {
                case 'a':
                    arrayQueue.showQueue();
                    break;
                case 'b':
                    scanner.close();
                    loop = false;
                    break;
                case 'c':
                    System.out.println("input a number...");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'd':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.println("取出的数据是= " + res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 'e':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.println("res = " + res);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序已退出。。。");
    }
}
//使用数组模拟队列 - 编写一个ArrayQueue类
class ArrayQueue{
    private int maxSize;  //数组最大容量
    private int front;    //队列的队头
    private int rear;    //队列的队尾
    private int[] arr;   //该数组用来存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;  //指向队头的前一个位置
        rear = -1;  //指向队列的尾部的数据（即就是队列的最后一个数据）

    }
    //判断队列为满
    public boolean isFull(){
        return rear == maxSize - 1;
    }
    //判断队列为null
    public boolean isNull(){
        return rear == front;
    }
    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否为满
        if (isFull()) {
            System.out.println("Queue is full,can't add data");
            return;
        }
        rear ++; //让rear后移
        arr[rear] = n;
    }
    //获取队列的数据  即出队
    public int getQueue(){
        if (isNull()) {
            System.out.println("");
            //return -1;
            //抛出异常
            throw new RuntimeException("sorry, you Queue is null");
        }else {
            front ++; //front后移就能取出数据啦
            return arr[front];
        }
    }
    //显示队列的所有数据
    public void showQueue(){
        //遍历
        if (isNull()) {
            System.out.println("Queue is null,not data");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    //显示队列的头数据
    public int headQueue(){
        //判断队列是否为null
        if (isNull()) {
            //return -1;
            //抛出异常
            throw new RuntimeException("sorry, you Queue is null");
        }
        return arr[front+1];
    }

}
