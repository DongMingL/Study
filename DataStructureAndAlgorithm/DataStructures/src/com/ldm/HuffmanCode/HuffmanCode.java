package com.ldm.HuffmanCode;

import javax.tools.FileObject;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * @author 梁东明
 * 2022/9/4
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class HuffmanCode {
    public static void main(String[] args) {
       /* //测试压缩文件
        String srcFile = "D:\\IOTest\\bg.jpg";
        String dstFile = "D:\\IOTest\\a\\bgm.zip";

        zipFile(srcFile,dstFile);
        System.out.println("压缩成功~~");
*/
        /*//测试解压文件
        String zipFile = "D:\\IOTest\\a\\bgm.zip";
        String distFile = "D:\\IOTest\\bgm.jpg";

        unZipFile(zipFile,distFile);
        System.out.println("解压成功~~");
*/
      /*  String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();   ///用byte[] 保存字符串
        System.out.println("字符串的长度是 = " + bytes.length);  //输出字符串的长度

        byte[] huffmanCodeBytes = huffmanZip(bytes);
        System.out.println("压缩后的结果："+Arrays.toString(huffmanCodeBytes) +"\n压缩后字符串的长度为：" +huffmanCodeBytes.length);

        byte[] sourceByte = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("原来的字符串 = " + new String(sourceByte));*/



/*
        List<Node> nodes = getNodes(bytes);  //不妨ctrl+单击 getNodes方法试试
        System.out.println("nodes = " + nodes);

        //测试创建的哈夫曼树
        System.out.println("前序遍历");
        Node huffmanCodeRoot = createHuffmanCode(nodes);
        preOrder(huffmanCodeRoot);

        //测试是否生成对应的哈夫曼编码
        getCodes(huffmanCodeRoot,"",strBuilder);
        System.out.println("huffmanCodeRoot = " + huffmanCodes);

        //测试
        byte[] zip = zip(bytes, huffmanCodes);
        System.out.println("Arrays.toString(zip) = " + Arrays.toString(zip));*/

        //发送HuffmanCodeBytes 数组

    }

    /**
     * //封装前面的方法，便于调用
     *
     * @param bytes 字节
     * @return {@link byte[]}
     */
    private static byte[] huffmanZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);
        //根据nodes创建哈夫曼树
        Node huffmanCodeRoot = createHuffmanTree(nodes);
        //对应的哈夫曼编码
        getCodes(huffmanCodeRoot,"",strBuilder);
        //根据生成的哈夫曼编码，压缩得到压缩后的哈夫曼编码字节数组
        byte[] huffmanCodeByte = zip(bytes, huffmanCodes);
        return  huffmanCodeByte;
    }
    //编写一个方法，完成对压缩文件的解压
    /**
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {

        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和  is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组  huffmanBytes
            byte[] huffmanBytes = (byte[])ois.readObject();
            //读取赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            os.write(bytes);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        } finally {

            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println(e2.getMessage());
            }

        }
    }


    /**
     * zip文件
     *
     * @param srcFile  目标文件
     * @param dstFile 输出文件
     */
    public static void zipFile(String srcFile, String dstFile) {

        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);
            //直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流, 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把 赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes); //我们是把
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码 写入压缩文件
            oos.writeObject(huffmanCodes);


        }catch (Exception e) {

            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                oos.close();
                os.close();
            }catch (Exception e) {

                System.out.println(e.getMessage());
            }
        }

    }



    /**完成数据的解压
     *1、重写先转成 哈夫曼编码对应的二进制字符串
     *2、哈夫曼编码对应的二进制的字符串、  ==》 对照哈夫曼编码
     *编写一个方法，完成对压缩数据的解码
     *编写一个方法，完成对压缩数据的解码
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {

        //1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for(int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询 a->100 100->a
        Map<String, Byte>  map = new HashMap<String,Byte>();
        for(Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建要给集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解成就是索引,扫描 stringBuilder
        for(int  i = 0; i < stringBuilder.length(); ) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;

            while(flag) {
                //1010100010111...
                //递增的取出 key 1
                if ( i+ count > stringBuilder.length()-1  && Integer.parseInt(String.valueOf(i)) == 0){
                    String key = stringBuilder.substring(i, stringBuilder.length());
                }
                String key = stringBuilder.substring(i, i+count);//i 不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if(b == null) {//说明没有匹配到
                    count++;
                }else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i 直接移动到 count
        }
        //当for循环结束后，我们list中就存放了所有的字符  "i like like like java do you like a java"
        //把list 中的数据放入到byte[] 并返回
        byte b[] = new byte[list.size()];
        for(int i = 0;i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;

    }


    /**
     * 将一个byte 转成一个二进制的字符串
     * @param b 传入的 byte
     * @param flag 标志是否需要补高位如果是true ，表示需要补高位，如果是false表示不补, 如果是最后一个字节，无需补高位
     * @return 是该b 对应的二进制的字符串，（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存 b
        int temp = b; //将 b 转成 int
        //如果是正数我们还存在补高位
        if(flag) {
            temp |= 256; //按位与 256  1 0000 0000  | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制的补码
        if(flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }





    /**
     * 前序遍历哈夫曼树
     *
     * @param root 根节点
     */
    private static void preOrder(Node root){
        if (root != null){
            root.preOrder();
        }else {
            System.out.println("该二叉树为null");
        }
    }

    /**
     *
     *
     * @param bytes    原始的字符串对应的byte[] 数组，长度是20
     * @param huffmanCodes 生成的霍夫曼编码
     * @return {@link byte[]} 返回生成的哈夫曼编码处理后的byte[]数组
     * 01010001011011001111110011111110000010110000111011000111111010100110
     *///编写一个方法，将字符串对应的byte[] 数组，通过生成的哈夫曼编码表，返回一个哈夫曼编码，压缩后的byte[]
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        //1.利用 huffmanCodes 将  bytes 转成  赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes 数组
        for(byte b: bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        //System.out.println("测试 stringBuilder~~~=" + stringBuilder.toString());

        //将 "1010100010111111110..." 转成 byte[]

        //统计返回  byte[] huffmanCodeBytes 长度
        //一句话 int len = (stringBuilder.length() + 7) / 8;
        int len;
        if(stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        //创建 存储压缩后的 byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;//记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) { //因为是每8位对应一个byte,所以步长 +8
            String strByte;
            if(i+8 > stringBuilder.length()) {//不够8位
                strByte = stringBuilder.substring(i);
            }else{
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte 转成一个byte,放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //将哈夫曼编码保存在map集合中
    static Map<Byte,String> huffmanCodes = new HashMap<Byte,String>();
    //拼接路径，用来存储某个叶子节点的路径
    static StringBuilder strBuilder = new StringBuilder();

    /**
     * 功能：将传入的node节点的所有叶子节点的哈夫曼编码得到，并放入到huffmanCodes集合中
     *
     * @param node       传入的节点
     * @param code       路径： 0是左子节点  1是右子节点
     * @param strBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder strBuilder){
        StringBuilder strBuilder2 = new StringBuilder(strBuilder);
        //将code拼接到strBuilder2中
        strBuilder2.append(code);
        if (node != null){ //node ==null 空树不处理
            if (node.data == null){ //新创建的父节点没有data值，即： 非叶子节点
                //向左递归
                getCodes(node.left,"0",strBuilder2);
                //向右递归
                getCodes(node.right,"1",strBuilder2);
            }else {  //上面不是叶子节点，那么这里就是叶子节点
                //表示找到叶子节点,将其保存在map集合中
                huffmanCodes.put(node.data,strBuilder2.toString());
            }

        }
    }


    /**
     * 该方法的用处就是用list集合保存node节点
     * 而且每个节点中保存了 字符串中  每个字符的ASCII码值和出现次数（还排好序了）
     * 等下创建哈夫曼编码用到这个list集合的节点
     *
     * @param bytes 传入的字符串数组
     * @return 返回含有data  、weight 的节点
     */
    private static List<Node> getNodes(byte[] bytes){
        //创建一个list集合，用来保存node节点，命名为nodes
        List<Node> nodes = new ArrayList<>();
        //创建一个map集合，用来统计每个字符出现的次数，命名为map
        //k（bytes的ASCII码值）-v（字符出现的次数）
        Map<Byte, Integer> map = new HashMap<>();
        //遍历字符串数组
        for (byte data : bytes) {

           /*
           Integer count = map.get(data);
           if ( count == null){//k（bytes的ASCII码值）-v（字符出现的次数）如果字符出现的次数为null，就将其插入map集合，
                map.put(data,1);
            }else {
                map.put(data, count+1);//如果字符出现过了，就将其出现次数+1
            }*/

            map.merge(data, 1, Integer::sum);
            //不得不感叹一下JAVA的技术迭代的真快，上面6行的代码，这里一行就能说明清楚。
            //看不懂这个merge方法的，尝试 ctrl+单击 看看JDK源码，顺便看一下全英的注解

        }
        //使用Entry遍历map集合，将每个map集合中的元素插入list集合中
        for (Map.Entry<Byte,Integer> entry: map.entrySet()) {
            //list集合里面的是node节点，所以还需要用到map的k-v来构建node节点
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        //返回list集合
        return nodes;
    }

    /**
     * 创建霍夫曼编码
     * 取出list集合最小的两个节点创建一个二叉树，
     * 把取出来的节点删除，把创建的二叉树插入list集合，循环
     * 直到list集合只剩下一个节点，退出循环，该节点就是哈夫曼树的根节点
     *
     * @param nodes 节点
     * @return {@link Node}
     */
    private static Node createHuffmanTree(List<Node> nodes){
        while ( nodes.size() > 1){
            Collections.sort(nodes);

            //取出list集合最小的两个元素
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //构建一个二叉树，不存放字符串，只存放权值。
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //把用过的节点移出集合
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //把新二叉树放回集合中
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
//创建带权值的节点类
class Node implements Comparable<Node>{
    Byte data;
    int weight;
    Node left;
    Node right;

    //构造器


    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null ){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
}
/*
我的实现步骤：
1、创建node节点类，包含data、weight、left、right、包含前序遍历的方法
2、主方法中添加str字符串，得到str对应的byte[] 数组
3、编写一个方法将准备好的byte[] 数组中每个字符统计出现次数和按大小排序
  1）通过hashmap将byte[]数组遍历，统计每个字符出现次数并排好序
  2）把每个键值对k-v以一个Node对象加入到List集合中
4、通过3得到的list集合创建哈夫曼树
  1）取出列表最小的两个值
  2）以这两个节点创建父节点（父节点的data值为null，只有权值weight）
  3）在list集合删除那两个节点，把父节点插入list集合、循环
  4）直到list只剩下一个节点，退出循环。哈夫曼树创建完成
5、主方法中调用前序遍历方法，测试是否正确
6、生成对应的哈夫曼编码表
  1)先创建一个map集合用来保存编码
  2）再创建一个stringBuilder用来拼接编码
  3）创建一个方法，将传入的node节点的所有叶子节点的哈夫曼编码得到，
  并放入到huffmanCodes集合中
  4）测试生成的编码是否成功
7、编写一个方法，将字符串对应的byte[] 数组，通过生成的哈夫曼编码表，
返回一个哈夫曼编码，压缩后的byte[]
8、编写一个方法，对哈夫曼编码进行解压
  1）字节转二进制字符串
  2）将二进制转换字符串
9、对文件压缩
  1）创建一个方法，传入输入流地址、输出流地址
  2）
10、对文件的解压
 */