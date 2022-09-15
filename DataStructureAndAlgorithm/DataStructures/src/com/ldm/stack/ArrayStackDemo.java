package com.ldm.stack;

import java.util.Scanner;

/**
 * @author 梁东明
 * 2022/8/23
 * 86139
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(6);
        String key = "";
        //控制是否退出菜单
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop){
            System.out.println("show: 表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:添加数据到栈中");
            System.out.println("pop： 从栈中取出数据");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "exit":
                    scanner.close();
                    loop =false;
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("取出的数据是："+ res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序已退出。。。");

    }
}
class ArrayStack{
    private int maxSize;     //栈的大小
    private int[] stack;      //存放栈的数据
    private int top = -1;     //初始化top = -1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize]; //初始化数组栈的容量
    }

    //判断栈满
    public boolean isFull(){

        return top == maxSize - 1;
    }
    //判断栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int value){
        //判断栈满
        if (isFull()){
            System.out.println("栈满，无法插入元素");
            return;
        }
        top ++;
        stack[top] = value;
    }
    //出栈
    public int pop(){
        //判断是否空栈
        if (isEmpty()){
            throw new RuntimeException("栈空，无法出栈");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //显示栈元素遍历栈，从栈顶开始
    public void list(){
        //判断栈是否为null
        if (isEmpty()){
            System.out.println("没有元素");
            return;
        }
        //从栈顶显示元素
        for (int i = top; i > 0; i--) {
            System.out.println("第" + i +"个元素是："+stack[i]);
        }
    }
}