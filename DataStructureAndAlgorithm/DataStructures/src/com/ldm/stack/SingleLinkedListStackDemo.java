package com.ldm.stack;

/**
 * @author 梁东明
 * 2022/8/23
 * 86139
 * 点击setting在Editor 的File and Code Templates 修改
 *///单链表模拟栈
public class SingleLinkedListStackDemo {

    public static void main(String[] args) {

        Node1 node1 = new Node1(1);
        Node1 node2 = new Node1(2);
        Node1 node3 = new Node1(3);
        Node1 node4 = new Node1(4);
        SingleLinkedListStack singleLinkedListStack = new SingleLinkedListStack();
        singleLinkedListStack.push(node1);
        singleLinkedListStack.push(node2);
        singleLinkedListStack.push(node3);
        singleLinkedListStack.push(node4);
        singleLinkedListStack.pop();
        singleLinkedListStack.show();

    }

}

class SingleLinkedListStack {
    private Node1 top = new Node1(-1);// 定义一个头指针

    // 判断栈是否为空
    public boolean isEmpty() {
        return top.getNext() == null;
    }

    // 入栈，入栈的时候采用头插法
    public void push(Node1 node) {

        if (top.getNext() == null) {// 第一个节点的插入
            top.setNext(node);
            return;
        }
        // 头插法
        Node1 temp = top.getNext();// 定义一个临时变量使其指向top节点的下一个节点
        top.setNext(node);
        node.setNext(temp);

    }

    // 出栈
    public void pop() {
        if (top.getNext() == null) {
            System.out.println("栈为空！不能出栈！");
            return;
        }
        System.out.println("出栈节点为：" + top.getNext().getValue());
        top = top.getNext();
    }

    // 遍历栈
    public void show() {
        if (isEmpty()) {
            System.out.println("栈为空！");
            return;
        }

        Node1 temp = top;
        while (temp.getNext() != null) {
            System.out.println("节点为：" + temp.getNext().getValue());
            temp = temp.getNext();
        }
    }

}

//定义链表的节点
class Node1 {
    private int value;
    private Node1 next;

    public Node1(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node1 getNext() {
        return next;
    }

    public void setNext(Node1 next) {
        this.next = next;
    }
}

