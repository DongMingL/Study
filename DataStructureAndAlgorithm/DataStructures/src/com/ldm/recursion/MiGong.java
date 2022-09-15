package com.ldm.recursion;

/**
 * @author 梁东明
 * 2022/8/25
 * 86139
 * 点击setting在Editor 的File and Code Templates 修改
 */

//小老鼠迷宫找出路
public class MiGong {
    public static void main(String[] args) {
        int[][] map = new int[8][7];

        //第1行和第8行的全部数据置为1
        for(int i = 0 ; i <7 ; i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //第1列和第7的值置为1
        for (int i = 1; i < 8;i++){
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //第4行的第1-5列置为1
        for (int i = 1; i < 5; i++) {
            map[3][i] = 1;
        }
        //第6行的3-6列置为1
        for (int i = 2; i < 6; i++) {
            map[5][i] = 1;
        }
        map[2][3] = 1;


        System.out.println("===迷宫的情况如下====");
        //遍历输出二维数组
        for (int i = 0 ; i < map.length; i++){
            for (int j = 0; j< map[i].length; j++){
                System.out.print(map[i][j]+"  ");
            }
            System.out.println();
        }

        T t = new T();
        t.findWay(map, 1,1);
        System.out.println("===找路的情况如下====");
        //遍历输出二维数组
        for (int i = 0 ; i < map.length; i++){
            for (int j = 0; j< map[i].length; j++){
                System.out.print(map[i][j]+"  ");
            }
            System.out.println();
        }


    }
}
class T{
    //findWay方法专门找迷宫的出路
    //1、如果找到就置为true
    //map是二维数组，并表示迷宫
    //i，j表示老鼠 初始的位置
    //0表示可以走，1表示障碍物  2表示可以走 3表示走过但是走不通的死路
    //当map[6][2] == 2,表示走出迷宫
    //确定老鼠走路的策略
    public boolean findWay(int[][] map,int i,int j ){

        if (map[6][5] ==2){ //找到出路
            return true;
        }else {
            if (map[i][j] == 0){  //当前位置为0，此路可走
                //假定可以走的通
                map[i][j] = 2;
                //开始判断哪一条路可以走
                if (findWay(map, i+1, j)){ //如果上面的节点为0，置为true。继续向上走
                    return true;
                }else if (findWay(map, i, j+1)){  //如果右边的节点为0，置为true。继续向右走
                    return true;
                }else if (findWay(map, i-1, j)){  //如果下面的节点为0，置为true。继续向下走
                    return true;
                }else if (findWay(map, i, j-1)){  //如果左边的节点为0，置为true。继续向左走
                    return true;
                }else {     //如果走不通的节点置为3，表示走不通
                    map[i][j] = 3;
                    return false;
                }
            }else {
                return false;
            }
        }

    }
}