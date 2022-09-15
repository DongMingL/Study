package com.ldm.linkedlist;


import java.util.Stack;

import static com.ldm.linkedlist.SingleLinkedList.*;

/**
 * @author 梁东明
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "ldm", "梁东明01");
        HeroNode hero2 = new HeroNode(2, "ldm", "梁东明02");
        HeroNode hero3 = new HeroNode(3, "ldm", "梁东明03");
        HeroNode hero4 = new HeroNode(4, "ldm", "乱臣贼子0");

        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

         //尾插法添加节点
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero4);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero2);
        System.out.println("尾插法插入链表。。。");
        singleLinkedList.list();

      /* //添加节点
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);*/

      /*  //测试修改节点
        HeroNode newHero = new HeroNode(2, "傻逼", "哈哈");

        singleLinkedList.update(newHero);*/

        /*//删除节点
        singleLinkedList.del(4);
        */

       /* //头插法插入节点
        singleLinkedList.addByHead(hero1);
        singleLinkedList.addByHead(hero2);
        singleLinkedList.addByHead(hero3);
        singleLinkedList.addByHead(hero4);

        singleLinkedList.list();*/
        //测试一下 求单链表中有效节点的个数
        System.out.println("有效的节点个数=" + getLength(singleLinkedList.getHead()));//2

       /* //查找倒数第k个节点
        HeroNode lastReverseNode = findLastReverseNode(singleLinkedList.getHead(), 1);
        System.out.println("lastReverseNode = " + lastReverseNode);*/

       /* //
        System.out.println("反转链表");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();*/
        //
        System.out.println("百度面试题：从头到尾输出链表");
        reversePrint(singleLinkedList.getHead());
    }
}
//定义单链表，管理节点
class SingleLinkedList{
    //初始化一个头节点，不放任何数据
    private HeroNode head = new HeroNode(0,"","");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //思路：当不考虑编号
    //1、找到当前链表的最后节点
    //2、将这个最后节点的next指向新的节点

    //尾插法
    public void add(HeroNode heroNode) {

        //因为head节点不能动，因此我们需要一个辅助遍历 temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while(true) {
            //找到链表的最后
            if(temp.next == null) {//
                break;
            }
            //如果没有找到最后, 将将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next 指向 新的节点
        temp.next = heroNode;
    }
    //头插法
    public void addByHead(HeroNode heroNode) {
       //如果链表为null，则把第一元素赋给head.next
        if (head.next == null) {
            head.next = heroNode;
            return;
        }
        //如果不为null，就定义一个temp来插入数据
        HeroNode temp = head.next;
        head.next = heroNode;
        heroNode.next = temp;
    }

    //指定插入
    public void addByOrder(HeroNode heroNode){
        //按指定顺序插入节点
        HeroNode temp = head;
        boolean flag = false;
        //遍历链表
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no){
                break;
            }else if ( temp.next.no == heroNode.no){
                flag = true;   //说明编号已存在 ，不需要插入
            }
            temp = temp.next;  //后移，相当于遍历当前链表
        }
        //判断flag的值
        if (flag) {  //不能插入
            System.out.println("该节点已存在");
        }else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点，根据no来修改节点
    public void update(HeroNode heroNode){
        //判断节点是否为null
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //定义一个辅助变量
        HeroNode temp = head;
        boolean flag = false;
        //遍历链表
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            //后移
            temp = temp.next;
        }
        //
        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        }else {
            System.out.println("链表上没有该节点，不能修改");
        }

    }
    //删除节点
    public void del(int no){
        //判断节点是否为null
        if ( head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //定义一个辅助变量
        HeroNode temp = head;
        boolean flag = false;
        //遍历链表
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            //后移
            temp = temp.next;
        }
        //
        if (flag) {
           temp.next = temp.next.next;
        }else {
            System.out.println("链表上没有该节点，不能删除");
        }

    }

    //遍历单链表，查看链表数据
    public void list(){
        //判断链表为null
        if (head.next == null) {
            System.out.println("链表为null");
            return;
        }
        //因为头节点，不能动，创建一个辅助变量temp
        HeroNode temp = head.next;
        while (true) {
            //判断是否遍历到链表的最后
            if ( temp  == null) {
                return;
            }
            System.out.println(temp);
            //每输出一个元素就把temp后移一位，直到temp.next == null
            temp = temp.next;
        }
    }
    //方法：获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
    /**
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if(head.next == null) { //空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量, 这里我们没有统计头节点
        HeroNode cur = head.next;
        while(cur != null) {
            length++;
            cur = cur.next; //遍历
        }
        return length;
    }

    //新浪面试题： 打印单链表的倒数第k个节点
    //思路：
    //1、先定义一个k表示倒数第几个节点
    //2、再遍历获取总的链表个数 size
    //3、再用总的个数  size - k 得到第k个元素
    //4、如果找到了就返回，没找打就返回null
    public static HeroNode  findLastReverseNode(HeroNode head,int k){
        //判断链表是否为null
        if ( head.next == null) {
            return null;
        }
        //获取当前链表的总个数size
        int size = getLength(head);
        //第二次遍历，size-reverse位置，就是倒数第k个节点
        if (k <= 0 || k > size) {
            return null;
        }
        //定义一个辅助变量
        HeroNode cur = head.next;
        //遍历cur，直到找到合适的节点
        for (int i = 0; i < (size - k); i++) {
            cur = cur.next;
        }
        return cur;
    }
    //**单链表的反转**
    public static void  reverseList(HeroNode head){
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if(head.next == null || head.next.next == null) {
            return ;
        }

        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;// 指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端
        //动脑筋
        while(cur != null) {
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; //将cur 连接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next 指向 reverseHead.next , 实现单链表的反转
        head.next = reverseHead.next;
    }
    //从尾到头打印单链表：要求1：反向遍历 要求2 ：stack栈
    //方式2：
    //可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
    public static void reversePrint(HeroNode head) {
        if(head.next == null) {
            return;//空链表，不能打印
        }
        //创建要给一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while(cur != null) {
            stack.push(cur);
            cur = cur.next; //cur后移，这样就可以压入下一个节点
        }
        //将栈中的节点进行打印,pop 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop()); //stack的特点是先进后出
        }
    }




}


//定义node节点
class HeroNode{
    public int no;
    public String  name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }




    //为了方便显示，重写toString方法

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
