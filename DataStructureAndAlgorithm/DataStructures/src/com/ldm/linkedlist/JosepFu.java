package com.ldm.linkedlist;

/**
 * @author 梁东明
 * 2022/8/22
 * 86139
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class JosepFu {
    public static void main(String[] args) {

        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.addNode(5);
        circleLinkedList.show();

        //测试出链表是否正确

        circleLinkedList.countNode(3,2,5);
    }
}
//环形链表
class CircleLinkedList{
    //定义头节点,先占着位置，不拉屎。
    private Node first = null;

    //添加节点的方法
    public void addNode(int nums){
        //判断链表是否为null
        if (nums < 1) {
            System.out.println("这是一个空链表");
            return;
        }
        //辅助变量
        Node cur = null;  //先让cur指向first
        //for循环创建链表
        for (int i = 1; i <= nums; i++) {
            //根据编号创建节点
            Node node = new Node(i);
            if (i == 1) {
                first = node;
                //私有属性不能通过  first.next = first来赋值
                first.setNext(first); //自己指向自己，循环链表开始雏形
                cur = first;
            }else {
                cur.setNext(node);
                node.setNext(first);
                cur = node;
                //本来cur =  cur.next  来遍历的
                //但是38行的代码已经让cur.next = node
                //所以 cur = node  就行了
            }
        }
    }
    //遍历环形单链表
    public void show(){
        //判断链表是否为null
        if (first == null) {
            System.out.println("没有任何节点");
            return;
        }
        //辅助变量
        Node cur = first;
        while (true) {
            System.out.println("节点的编号是："+cur.getNo());
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();

        }
    }

    //根据用户输入，判断节点抛出链表的顺序

    /**
     *
     * @param k      表示从第几个节点开始数数
     * @param m      表示数多少下,数到谁，谁就抛出链表
     * @param nums   表示一开始链表有多少个节点
     */
    public void countNode(int k,int m,int nums){
        //对链表检验
        //这里怕麻烦，就不让nums > k ,否则要取模很麻烦
        if (first == null || k < 1 || k > nums ) {
            System.out.println("参数有误，请重新输入");
            return;
        }
        //创建辅助变量
        Node helper = first;
        //helper应该事先指向环形链表的最后一个节点
        while (true) {
            if (helper.getNext() == first) {//说明helper已经指向最后一个节点
                break;
            }
            helper = helper.getNext();
        }
        //在m开始数数之前，先让helper和first移动 k - 1 次
        for (int i = 0; i < k-1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //m开始数数的时候，让helper和first移动 m - 1 次
        //这样就把数到的那个节点移出链表  这是一个循环操作，直到链表只剩下最后一个节点
        int count = 0;
        while (true) {
            if (helper == first) {  //说明链表只剩下一个节点，跳出循环，该节点获胜
                break;
            }
            //m开始数数的时候，让helper和first移动 m - 1 次
            for (int j = 0; j < m - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();

                count ++;
            }
            //当退出for循环表示第k个节点开始从1开始报数，数到m这个节点，就被淘汰
            //这时，first指向的节点，就是要被淘汰的节点
            //这段是可以输出第几个被淘汰的节点，遗憾的是我不会处理 m为1的情况。所以尽量别用
            System.out.println("第"+(count / (m-1))+"个被淘汰的节点是：" + first.getNo());
            //用这个就没有/ by zero 的异常了。
            //System.out.println("被淘汰的节点是：" + first.getNo());
            //这时候，把first指向被淘汰的节点
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("胜利节点为"  +helper.getNo());
    }


}


//节点类
class Node{
    //看清楚，我这里两个属性都是私有的，等下被调用时只能通过set、get方法获取或者赋值
    private int  no;
    //看清楚，我这里两个属性都是私有的，等下被调用时只能通过set、get方法获取或者赋值

    private Node next;

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
