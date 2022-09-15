package com.ldm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author 梁东明
 * 2022/9/7
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class Graph {
    private ArrayList<String> vertexList;  //存储顶点
    private int[][] edges;  //存储图对应的邻接矩阵
    private int numOfEdges;  //表示对应的数目
    //定义给数组boolean[],记录某个结点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 5;

        String VertexValue[] = {"A","B","C","D","E"};
        Graph graph = new Graph(n);
        //循环添加顶点
        for (String str: VertexValue) {
            graph.insertVertex(str);
        }
        //添加边
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        graph.showGraph();

        System.out.println("深度遍历");
        graph.dfs();
        System.out.println();
        System.out.println("广度遍历");
        graph.bfs();


    }
    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>();
        numOfEdges = 0;

    }
    //得到第一个邻接结点的下标w
    public int getFirstNeighbor(int index){
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0){
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻接结点的下标得到下一个邻接结点的下标
    public int getNextNeighbor(int v1, int v2){
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }

    /**
     *深度优先遍历
     * @param isVisited ,记录某个结点是否被访问
     * @param i         某个结点的下标
     */
    public void dfs(boolean[] isVisited, int i){
        //输出访问的结点
        System.out.print( getValueByIndex(i)+"->");
        //将被访问过的结点置为已访问
        isVisited[i] = true;
        //查找结点i的第一个邻接结点
        int w = getFirstNeighbor(i);
        //如果存在就继续访问
        while (w != -1){
            //还需要判断这个结点是否被访问过
            if ( ! isVisited [w] ){
                //没被访问过，那我们就递归,
                dfs(isVisited,w);
            }
            //如果已经被访问过了，就对w的下一个邻接结点进行查找
            w = getNextNeighbor(i,w);
        }
    }
    //对dfs重载，遍历所有的结点
    public void dfs(){
        isVisited = new boolean[5];
        //遍历所有的结点，进行dfs回溯
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }
    //对一个结点进行广度优先遍历
    public void bfs(boolean[] isVisited, int i){
        int u;  //队头结点的suoyin
        int w;  //邻接结点w
        //创建队列
        LinkedList queue = new LinkedList<>();
        //访问结点
        System.out.print(getValueByIndex(i)+"=>");
        //标记为已访问
        isVisited[i] = true;
        //将结点接入队列
        queue.addLast(i);
        //如果队头元素不为null，就继续遍历
        while ( !queue.isEmpty()){
            //取出队头的结点下标
            u = (Integer) queue.removeFirst();
            //得到u的下一个结点w
            w = getFirstNeighbor(u);
            while ( w != -1){
                //判断是否访问过
                if (!isVisited[w]){
                    //没被访问过就访问
                    System.out.print(getValueByIndex(w)+"=>");

                    //标记为已访问
                    isVisited[w] = true;
                    //入队列
                    queue.addLast(w);
                }
                w= getNextNeighbor(u,w);
            }

        }
    }
    //对bfs重载
    public void bfs(){
        isVisited = new boolean[5];
        //遍历所有的结点，进行dfs回溯
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }



    //返回节点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //得到的边数
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回结点i对应下标的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //遍历显示矩阵
    public void showGraph(){
        for (int[] link : edges){
            System.out.println(Arrays.toString(link));
        }
    }

    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     v1 第一个顶点
     * @param v2     v2 第二个顶点
     * @param weight 权值为1 则赋边
     */
    public void insertEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

}
