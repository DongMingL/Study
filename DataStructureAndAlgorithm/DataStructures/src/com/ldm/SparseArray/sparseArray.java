package com.ldm.SparseArray;

/**
 * @author 梁东明
 *点击setting在Editor 的File and Code Templates 修改
 */
public class sparseArray {


        public static void main(String[] args) {
            // TODO Auto-generated method stub
            //创建一个原始的二维数组
            //注意  没有赋值的默认为  0
            int Arr1[][] =new int[6][7];
            Arr1[0][3] = 22;
            Arr1[0][6] = 15;
            Arr1[1][1] = 11;
            Arr1[1][5] = 17;
            Arr1[2][3] = -6;
            Arr1[3][5] = 39;
            Arr1[4][0] = 91;
            Arr1[5][2] = 28;
            System.out.println("原始的二维数组");
            //增强for循环遍历输出原数组
            for (int[] row : Arr1){
                for (int data : row){
                    System.out.print(data+"\t");
                }
                System.out.println();
            }

            //将原始二维数组转换成稀疏数组
            //1、先遍历二维数组，得到非0的数据的个数
            int sum = 0;
            for (int[] row : Arr1) {
                for (int data : row) {
                    if (data != 0){
                        sum ++;
                    }
                }
            }

            //创建对应的稀疏数组
            //count + 1 是为了多记录一条原始数组的大小
            int[][] sparseArr = new int[sum + 1][3];
            //给稀疏数组赋值
            sparseArr[0][0] = 6;
            sparseArr[0][1] = 7;
            sparseArr[0][2] = sum;

            //遍历二维数组，将非0的  值存储在稀疏数组 中
            int count = 0;
            for (int i = 0; i < Arr1.length; i++) {
                for (int j = 0; j < Arr1[i].length; j++) {
                    if (Arr1[i][j] != 0){
                        count ++;
                        sparseArr[count][0] = i;
                        sparseArr[count][1] = j;
                        sparseArr[count][2] = Arr1[i][j];
                    }
                }
            }
            System.out.println();
            //遍历输出稀疏数组
            System.out.println("得到的稀疏数组是");
            for (int i = 0; i < sparseArr.length; i++) {
                System.out.print("\n"+sparseArr[i][0]+"\t"+sparseArr[i][1]+"\t"+sparseArr[i][2]);
            }

            //将稀疏数组还原 原二维数组
            /*
            1、先读取稀疏数组的第一行，根据第一行的信息创建原始数组
            2、再读取稀疏数组的其他行，给原数组的元素赋值
             */
            //1、先读取稀疏数组的第一行，根据第一行的信息创建原始数组
            //注意 sparseArr[0][0] = 6， sparseArr[0][1] = 7；
            //所以下行语句等价于             int Arr2[][] =new int[6]7];
            int Arr2[][] =new int[sparseArr[0][0]][sparseArr[0][1]];


            //2、再读取稀疏数组的其他行，给原数组的元素赋值
            for (int i = 1; i < sparseArr.length; i++) {
                Arr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
            }


            System.out.println();
            System.out.println("恢复后的二维数组");
            for (int[] row : Arr2){
                for (int data : row){
                    System.out.print(data+"\t");
                }
                System.out.println();
            }



        }
}
