package com.ldm.stack;

import java.util.Scanner;

/**
 * @author 梁东明
 * 2022/8/23
 * 86139
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class LinkedListStackDemo {
    public static void main(String[] args) {

        LinkedListStack stack = new LinkedListStack();

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
                    stack.show();
                    break;
                case "exit":
                    scanner.close();
                    loop =false;
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    Node node = new Node(value);
                    stack.push(node);
                    break;
                case "pop":
                    try {
                        stack.pop();
                        System.out.println("取出的数据是：");
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

//注意，链栈和数组栈的区别是，数组栈有最大容量限制，而链栈则没有，想插入多少数据就插入多少数据
class LinkedListStack{
    private Node top = new Node(-1);


    //判断栈空
    public boolean isEmpty(){
        return top.getNext() == null;
    }
    //入栈
    public void push(Node node){
        //如果栈中没有元素就插入第一个元素
        if (isEmpty()){
            top.setNext(node);
            return;
        }
        //如果栈中有元素了，就使用头插法的方式入栈
        //定义一个辅助变量指向头节点的下一个节点
        Node temp = top.getNext();

        top.setNext(node);
        node.setNext(temp);

    }

    //出栈
    public void pop(){
        //判断栈是否为null
        if (top == null) {
            System.out.println("栈为null，无法出栈。。。");
            return;
        }
        System.out.println("出栈节点为：" + top.getNext().getData());
        top = top.getNext();
    }
    //遍历链表栈
    public void show(){
        //判断栈是否为null
        if (isEmpty()) {
            System.out.println("栈为null，没有元素。。。");
            return;
        }
        Node temp = top;
        while (true) {
            // 将temp后移， 一定小心空指针异常，我就是没有这个if语句，害我找bug花了两个小时
            if (temp.getNext() != null){
                // 输出节点的信息
                System.out.println(temp.getNext().getData());
                temp = temp.getNext();
            }
        }
    }


}

class Node{
    private int data;
    private  Node next;


    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
