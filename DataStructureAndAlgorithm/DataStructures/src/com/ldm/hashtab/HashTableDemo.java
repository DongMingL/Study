package com.ldm.hashtab;

import java.util.Scanner;

/**
 * @author 梁东明
 * 2022/8/31
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class HashTableDemo {
    public static void main(String[] args) {
        //创建hashtable
        HashTab hashTab = new HashTab(7);

        //创建菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加员工");
            System.out.println("list:显示员工");
            System.out.println("find:查找员工");
            System.out.println("exit:退出程序");

            key = scanner.next();
            switch (key) {
                case "add" :
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建员工
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list" :
                    hashTab.list();
                    break;
                case "find" :
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit" :
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}
//创建Hashtable 管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray ;
    private int size;  //表示有多少条链表

    //构造器
    public HashTab(int size) {
        this.size = size;
        //初始化empLinkedListArrays
        empLinkedListArray = new EmpLinkedList[size];
        //分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }
    /**
     * 添加雇员的方法
     */
    public void add(Emp emp){
        //根据员工id得到该员工应当添加到那一条链表
        int empLikedListNo = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[empLikedListNo].add(emp);

    }
    /**ad
     * 遍历哈希表
     */
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    /**
     * 通过id查找员工
     *
     * @param id id
     */
    public void findEmpById(int id){
        //使用散列函数确定到哪一条链表
        int empLinkedListNo = hashFun(id);
         Emp emp = empLinkedListArray[empLinkedListNo].findById(id);
         if (emp != null){ //找到
             System.out.println("在第"+(empLinkedListNo+1)+"条链表找到该员工的信息：: "+" id="+id+"\tname="+emp.name);
         }else { //没有找到
             System.out.println("在哈希表中没有找到该员工");
         }
    }

    public int hashFun(int id){
        return id % size;
    }
}

//表示雇员
class Emp{
    public int id;
    public String  name;
    public Emp next; //默认为null

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
//创建EmpLikedList，表示链表
class EmpLinkedList{
    //头指针，执行第一个Emp，，因此链表的head是指向第一个Emp
    private Emp head;



    /**
     * 添加员工到链表
     *1、假定当添加员工时，id是自增长的
     *2、因此，把雇员直接加入到本链表的最后即可
     * @param emp  传入的员工
     */
    public void add(Emp emp){
        //遍历链表，创建临时变量辅助遍历
        //如果是第一个节点，就把传入的一个员工添加
        if (head == null){
            head = emp;
            return;
        }
        Emp temp = head;
        while (true){
            //到达链表的尾部，退出循环。找到链表的尾部
            if (temp.next ==null){
                break;
            }
            temp = temp.next;
        }
        //退出循环之后，把传入的节点插入到temp.next
        temp.next = emp;
    }
    /**
     * 遍历链表
     */
    public void list(int no){
        //判断链表是否为null
        if (head == null){
            System.out.println("第"+(no+1)+"条链表是null，退出。。。");
            return;
        }
        Emp temp = head;
        while (true){
            System.out.println("第"+(no+1) +"条链表信息:\n" +"=>"+"id="+temp.id+ "\t"+"name="+temp.name);
            if (temp.next ==null){
                break;
            }
            temp = temp.next;
        }
        System.out.println();
    }
    /**
     * 根据id查找员工
     * @param id 传入员工id
     * @return 找到就返回，找不到就返回null
     */
    public Emp findById(int id){
        //判断链表是否为null
        if (head ==null){
            System.out.println("当前链表为null，请返回");
            return null;
        }
        //辅助指针
        Emp temp = head;
        while (true) {
            if (temp.id == id){ //找到就直接返回
                break;
            }//退出循环
            if (temp.next == null){ //说明链表中没有找到该员工
                temp = null;
                break;
            }
            temp = temp.next;
        }
        return temp;
    }
}
