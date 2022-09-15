package com.ldm.floyd;

import java.util.Arrays;

/**
 * @author 梁东明
 * 2022/9/15
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class FloydAlgorithm {
    public static void main(String[] args) {
        // 测试看看图是否创建成功
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };

        //创建 Graph 对象
        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.floyd();
        graph.show();
    }
}

class Graph{
    private char[] data; //存放顶点数组
    private int[][] dis;  //各个顶点到其他顶点的距离
    private int[][] pre;  //保存到达目的顶点的前驱顶点

    public Graph(int length, int[][] matrix, char[] vertex) {
        this.data = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //对pre数组初始化，存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i] ,i);
        }
    }

    /**
     * 显示pre数组和dis数组
     */
    public void show(){
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        
        System.out.println("各个顶点到其他顶点的最短距离");
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                System.out.print(dis[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("各个顶点到达目的顶点的前驱顶点");
        for (int i = 0; i < pre.length; i++) {
            for (int j = 0; j < pre.length; j++) {
                System.out.print(vertex[pre[i][j]] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 弗洛伊德算法实现
     */
    public void floyd(){
        int len = 0; //临时变量保存两点的最短距离
        //k 是中间顶点，对k遍历
        for (int k = 0; k < dis.length; k++) {
            //i是出发顶点，对i遍历
            for (int i = 0; i < dis.length; i++) {
                //j是目的顶点，对j遍历
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j];// => 求出从i 顶点出发，经过 k中间顶点，到达 j 顶点距离
                    //这里用到贪心算法的思想，谁最小选择谁做最小距离
                    if ( len < dis[i][j]){//如果len小于 dis[i][j]
                        dis[i][j] = len;  //更新距离
                        pre[i][j] = pre[k][j]; //更新前驱顶点
                    }
                }
            }
        }
    }
}
