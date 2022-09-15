package com.ldm.tree;

/**
 * @author 梁东明
 * 2022/9/1
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 * 顺序二叉树
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 5, 6, 7 };

        //创建一个ArrBinaryTree的对象
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
    }
}
//编写一个ArrBinaryTree，实现顺序二叉树遍历
class ArrBinaryTree{
    private int[] arr; //存储数据节点的数组

    //构造器
    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }
    //把前序遍历重载，这样在主方法中就不需要传入参数
    public void preOrder(){
        this.preOrder(0);
    }

    /**
     * 完成顺序二叉树的前序遍历
     *
     * @param index 数组下标
     *
     */
    public void preOrder(int index){
        //如果数组为null，或者arr,length = 0
        if (arr ==null ||arr.length == 0){
            System.out.println("数组为null，不能按照二叉树的前序遍历");
        }
        //输出当前节点
        System.out.print("\t"+arr[index]);
        //向左递归遍历
        if ( (index * 2 +1) < arr.length){
            preOrder(index * 2 + 1);
        }
        //向右递归遍历
        //向左递归遍历
        if ( (index * 2 + 2) < arr.length){
            preOrder(index * 2 + 2);
        }
    }
   /* public void preOrder(int index){
        if (arr == null || arr.length == 0){
            System.out.println("这是空数组，无法插入树");
            return;
        }
        System.out.println(arr[index]);
        if ((2*index + 1) <arr.length ){
            preOrder(2*index + 1);
        }
        if ((2*index + 2) <arr.length ){
            preOrder(2*index + 2);
        }
    }*/
}
