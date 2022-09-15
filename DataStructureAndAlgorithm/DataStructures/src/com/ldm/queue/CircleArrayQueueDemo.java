package com.ldm.queue;

import java.util.Scanner;

/**
 * @author 梁东明
 * 点击setting在Editor 的File and Code Templates 修改
 */
@SuppressWarnings({"all"})
public class CircleArrayQueueDemo {
    public static void main(String[] args) {

        //创建一个队列对象
        ArrayQueue01 arrayQueue = new ArrayQueue01(4);
        char key = ' '; //接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("a====显示队列======");
            System.out.println("b======退出=======");
            System.out.println("c=====添加数据=====");
            System.out.println("d=====取出数据=====");
            System.out.println("e=====查看队头=====");
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
class ArrayQueue01{
    private int maxSize;  //数组最大容量
    private int front;    //队列的队头
    private int rear;    //队列的队尾
    private int[] arr;   //该数组用来存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue01(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }
    //判断队列为满
    public boolean isFull(){
        return (rear+1)%maxSize == front;
    }
    //判断队列为null
    public boolean isNull(){
        return rear == front;
    }
    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否为满
        if (isFull()) {
            System.out.println("队列已满，不能添加数据");
            return;
        }
        arr[rear] = n;
        //将rear后移，但是必须考虑取模
        rear = (rear+1)%maxSize;
    }
    //获取队列的数据  即出队
    public int getQueue(){
        if (isNull()) {
            System.out.println("");
            //return -1;
            //抛出异常
            throw new RuntimeException("队列为null，请添加数据");
        }
        //分析front是指向队列的第一个元素
        //1、先把front对应的值保存在一个临时变量
        //将临时保存的变量返回
        int temp = arr[front];
        //将front后移
        front = (front + 1) % maxSize ;
        return temp;


    }
    //显示队列的所有数据
    public void showQueue(){
        //遍历
        if (isNull()) {
            System.out.println("队列为null，请添加数据");
        }
        //先求出当前循环队列有多少个元素再把它们遍历出来
        for (int i = 0; i < front + Size(); i++) {
            System.out.println("第"+(i%maxSize)+"个元素是"+arr[i]);
        }
    }
    //先求出当前循环队列有多少个元素再把它们遍历出来
    public int Size(){

        // 如果不是循环队列当前元素就有 rear-front 个;
        return (rear+maxSize-front) % maxSize;
    }


    //显示队列的头数据
    public int headQueue(){
        //判断队列是否为null
        if (isNull()) {
            //return -1;
            //抛出异常
            throw new RuntimeException("队列为null，请添加数据");
        }
        return arr[front];
    }

}

