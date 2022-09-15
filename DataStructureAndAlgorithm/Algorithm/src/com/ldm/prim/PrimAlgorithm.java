package com.ldm.prim;

import java.util.Arrays;

/**
 * @author 梁东明
 * 2022/9/13
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试看看图是否创建ok
        char[] data = {'A','B','C','D','E','F','G'};
        int verxs = data.length;  //顶点的个数就是data的长度
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int [][]weight={
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},};

        //创建Graph对象
        Graph graph = new Graph(verxs);
        //创建一个MinTree对象
        Tree tree = new Tree();
        tree.createGraph(graph, verxs, data, weight);

        Tree.prim(graph,0);

    }


}

//创建树
class Tree{
    /**
     * 创建图的邻接矩阵
     *
     * @param graph    图
     * @param vertexes 顶点
     * @param data     图各个顶点的值
     * @param weight   图的邻接矩阵
     */
    public void createGraph(Graph graph,int vertexes,char[] data, int[][] weight){

        for (int i = 0; i < vertexes; i++) { //顶点
            graph.data[i] = data[i]; //把传进来的顶点的值赋值给图
            for (int j = 0; j < vertexes; j++) {
                graph.weight[i][j] = weight[i][j]; //把传进来的邻接矩阵赋给图
            }
        }
    }
    //显示图的邻接矩阵
    public void showGraph(Graph graph){
        //把二维数组（邻接矩阵）的每一行输出来
        //这是增强for循环，跟普通的for循环没啥两样，就是代码简介一点而已
        for (int[]  link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }

    }
    /**
     * 普利姆算法
     * 把图 转成 最小生成树
     *
     * @param graph 图
     * @param v   表示图从哪一个结点开始生成最小生成树
     */
    public static void prim(Graph graph, int v){

        int totalWeight = 0;  //记录最小生成树的总最小权值
        //创建一个数组，用来表示顶点是否被访问过，
        //1 表示已访问过  0 表示没有被访问过
        int[] isVisited = new int[graph.vertexes];
        //把传进来的第一个顶点设置为已访问
        isVisited[v] = 1;

        //设置两个临时变量来保存接下来循环中用到的两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        //将最小值设置为10000，表示两个顶点之间没有边，二者不可达
        int minWeight = 10000;

        //最小生成树的一个很关键的特点
        //n个顶点的图会生成n-1 条边的最小生成树 ，所以遍历图的顶点的时候要从1开始
        for (int k = 1; k < graph.vertexes; k++) {

            //对已访问的顶点和未被访问的顶点开始for循环遍历
            for (int i = 0; i < graph.vertexes; i++) {//设i为已访问过的顶点
                for (int j = 0; j < graph.vertexes; j++) {//设j为未被访问过的顶点
                    //graph.weight[i][j] < minWeight 两个顶点之间的边
                    //只要两个顶点只要有边，其值graph.weight[i][j]肯定小于minWeight
                    //因为初始化的minWeight为10000，超级无敌大
                    if (isVisited[i] == 1 && isVisited[j] ==0
                            && graph.weight[i][j] < minWeight){
                        //for循环刚开始 二者有边，它们的值就先保存下来
                        minWeight = graph.weight[i][j];

                        //保存二者的下标
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            totalWeight += minWeight; //每退出前面两个for循环就把minWeight保存
            System.out.println("边< " + graph.data[h1] +","+graph.data[h2] +" > 权值为： " +minWeight);
            //退出前面两个for循环之后，找到已被访问顶点和其周边未被访问的顶点的 最小权值的边
            //把未被访问的顶点设置为已被访问
            isVisited[h2] = 1;
            //将最小值设重新置为10000，方便第一个for循环重新构成最小生成树
            minWeight = 10000;
        }
        System.out.println("该最小生成树的路径为 "+totalWeight);
    }

}

class Graph{
    int vertexes;   //存放边
    char[] data;    //存放结点数据
    int[][] weight; //存放边，就是邻接矩阵

    public Graph(int vertexes) {
        this.vertexes = vertexes;
        data = new char[vertexes];
        weight = new int[vertexes][vertexes];
    }


}
