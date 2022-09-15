package com.ldm.linkedlist;

/**
 * @author 梁东明
 * 2022/8/21
 * 86139
 * 单链表使用头插法和尾插法插入节点
 */
public class InsertLinked {
    public static void main(String[] args) {


        //创建节点类对象
        Node1 node1 = new Node1("head", 1);
        Node1 node2 = new Node1("middle", 2);
        Node1 node3 = new Node1("last", 3);
        Node1 node4 = new Node1("lastOne", 4);

        //创建单链表对象
        SingleNode singleNode = new SingleNode();
        /*  尾插法
        singleNode.addLast(node1);
        singleNode.addLast(node2);
        singleNode.addLast(node3);
        singleNode.addLast(node4);
        singleNode.show();
        */
        /* 头插法
        singleNode.addHead(node1);
        singleNode.addHead(node2);
        singleNode.addHead(node3);
        singleNode.addHead(node4);*/

        //指定顺序插入节点
        Node1 order1 = new Node1("head1", 1);
        Node1 order2 = new Node1("middle2", 2);
        Node1 order3 = new Node1("last3", 3);
        Node1 order4 = new Node1("lastOne4", 4);

        singleNode.addByOrder(order4);
        singleNode.addByOrder(order2);
        singleNode.addByOrder(order3);
        singleNode.addByOrder(order1);

        singleNode.show();
    }
}

class SingleNode{

    //初始化头节点，不放任何数据
    private  Node1 head = new Node1("",0);

    //尾插法
    //不管什么插入法，都需要把要插入的节点传入方法中
    public void addLast(Node1 node){
        //定义辅助变量来插入数据
        Node1 temp = head;
        //退出循环
        while (temp.next != null) {
            //遍历到单链表的尾部
            temp = temp.next;
        }
        //退出循环表示找到链表的最后一个节点，就在该节点插入传入的节点
        temp.next = node;
    }

    //判断单链表是否为null
    //单链表头插法
    public void addHead(Node1 node) {
        //如果链表为null，则把传入的第一个元素赋给head.next
        if (head.next == null){
            head.next = node;
            return;
        }
        //如果链表不为null，就定义一个temp来辅助插入节点
        Node1 temp = head.next;
        //把传入的节点插入单链表，注意两个步骤
        //1、head.next  指向    传入的节点
        //2、传入的节点.next  指向  temp
        head.next = node;
        node.next = temp;
    }

    //按照指定顺序插入节点
    public void addByOrder(Node1 node){
        //定义一个辅助变量temp
        Node1 temp = head;
        //定义一个标记，用来寻找 节点 要 插入的位置
        boolean flag = false;
        //遍历链表
        while (true) {
            //如果链表为null就退出循环
            if (temp.next == null) {
                //符合条件 退出循环
                break;
            }
            //比如我插入的节点的编号为4 (node.no==4)，原来temp的后一个节点编号为5 (temp.next.no==5)
            //所以temp后一个节点的就不再指向编号为5的节点，而是指向编号为4的节点（也就是我当前插入的节点）
            //  5 > 4
            if (temp.next.no > node.no){
                //符合条件 退出循环
                break;
            }else if ( temp.next.no == node.no){
                flag = true;   //说明编号已存在 ，不需要插入
                //继续循环遍历，直到找到合适的位置
            }
            temp = temp.next;  //后移，相当于遍历当前链表
        }
        //判断flag的值
        //可以写成flag == true
        if (flag) {  //不能插入
            System.out.println("该节点已存在");
        }else {
            //插入当前节点
            node.next = temp.next;
            temp.next = node;
        }
    }

    //遍历链表，输出链表
    public void show(){
        //判断链表为null
        if (head.next == null) {
            System.out.println("链表为null");
            return;
        }
        //注意这里的链表是不存放节点的，所以和add的方法的遍历有所不同
        Node1 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class Node1{
    //存放数据
    public String data;
    public int no;


    //指向当前节点的下一个节点，默认为null
    public Node1 next;

    public Node1(String data,int no) {
        this.data = data;
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + data + '\'' +
                '}';
    }
}
