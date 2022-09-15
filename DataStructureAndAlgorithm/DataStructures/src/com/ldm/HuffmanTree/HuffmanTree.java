package com.ldm.HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 梁东明
 * 2022/9/3
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }


    //前序对根节点进行遍历
    public static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("这是一个空数组。。。");
        }
    }

    /**
     * 创建哈夫曼树
     *
     * @param arr 传入的数组
     * @return 返回根节点
     */
    public static Node createHuffmanTree(int[] arr){

        //先创建list集合，命名为nodes
        List<Node> nodes = new ArrayList<>();

        /*看不懂可以用这个普通for循环将arr数组的元素插入list集合中
        for (int i = 0; i < arr.length; i++) {
            nodes.add(new Node(i));
        }*/
        //通过增强for循环将arr数组的元素以节点的形式插入list集合中
        for (int  value : arr) {
            nodes.add(new Node(value));
        }
        //循环list集合，直到list集合只剩一个节点退出循环
        while (nodes.size() > 1){
            //对list集合排序
            Collections.sort(nodes);

            //输出节点
            System.out.println(nodes);

            //取出list集合中的最小两颗权值的二叉树取出来
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //把他们组合成新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //把处理过的两个二叉树（节点）从list集合删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //把新的二叉树（节点）插入到list集合中
            nodes.add(parent);
        }
        //退出循环之后，list集合就只剩下一个节点，将它返回
        return nodes.get(0);

    }
}
//创建节点类，实现comparable接口，重写compareTo方法
class Node implements Comparable<Node>{
    public int value;  //权值
    public Node left;  //左节点
    public Node right;  //右节点

    //构造器alt+invest（ins）快捷键快速创建，必须是idea
    public Node(int value) {
        this.value = value;
    }
    //前序遍历的方法
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
/*
实现步骤：
看不懂没关系，这是我自己做的代码步骤，然后自己思考，最后手敲出来运行成功会有一种莫名的自豪感
你自己也可以总结实现步骤，自己动手写一遍。
1、在主方法创建数组
2、创建节点类、实现comparable接口
3、创建哈夫曼树方法
4、调用list集合，把数组的值插入list中
5、对list集合里面的节点进行排序，用 Collection的sort方法排序;
6、取出集合中权值最小的两颗二叉树
7、构建新的二叉树
8、从集合中删除前面取出来的两颗二叉树
9、将新的二叉树插入回list集合
10、循环执行步骤6、7、8、9直到list集合只剩一个棵树
11、返回哈夫曼树的root节点
12、前序遍历测试
13、代码执行成功，prefect!!!
 */