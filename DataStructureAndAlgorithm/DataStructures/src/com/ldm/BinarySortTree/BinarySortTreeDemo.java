package com.ldm.BinarySortTree;

/**
 * @author 梁东明
 * 2022/9/6
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {

        int[] arr = {7,3,10,12,5,1,9};
        BinarySortTree binarySortTree = new BinarySortTree();
        //遍历数组，将其插入到二叉排序树种
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历二叉树");
        binarySortTree.infixOrder();

        //测试删除节点
        binarySortTree.delNode(10);
        System.out.println("删除节点后~");
        binarySortTree.infixOrder();
    }
}
class BinarySortTree {
    private Node root;

    /**
     * 搜索要删除的节点
     */
    public Node search(int value){
        if ( root == null){
            return null;
        }else {
            return root.search(value);
        }
    }

    /**
     * 搜索父节点
     */
    public Node searchParent(int value){
        if ( root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    /**
     *1、返回以node为根节点的二叉排序树的最小节点的值
     *2、删除node为根节点的二叉排序树的最小节点
     *
     * @param node 传入的节点（当作当前二叉树的根节点）
     * @return 返回以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环查找左子节点，直到找到最小值
        while (target.left != null){
            target = target.left;
        }
        //退出while循环后，最小值就找到了
        //把它删除了
        delNode(target.value);
        return target.value;

    }

    /**
     * 删除节点
     *
     * @param value 要删除节点的值
     */
    public void delNode(int value){
        if ( root == null){
            return;
        }else {
            //1、需要找到要删除的节点 targetNode
            Node targetNode = search(value);
            //如果没有找到就直接返回；
            if (targetNode == null){
                return;
            }
            //如果二叉排序树只有根节点，把根节点置null；
            if ( root.left == null && root.right ==null){
                root = null;
                return;
            }
            //找到要删除的节点的父节点
            Node parent = searchParent(value);
            //第一种情况：如果要删除的是叶子节点
            if (targetNode.left == null && targetNode.right == null){
                //判断要删除的节点是其父节点左节点还是右节点
                if (parent.left != null && parent.left.value == value ){
                    parent.left = null;
                }else if (parent.right != null && parent.right.value == value){
                    parent.right = null;
                }
            } //第三种情况：如果要删除的节点有左右子树
            else if (targetNode.left != null && targetNode.right != null){
                int minValue = delRightTreeMin(targetNode.right);
                targetNode.value = minValue;
            }
            //第二种情况：如果要删除的节点有一个子树
            // 因为第二种情况的条件最复杂，所以用排除法先把第一第三种情况的条件判断，
            // 剩下的就是第二种情况的条件，直接不用if语句判断。无用的小知识又增加了！
            //你也可以把第二种情况的条件语句写出来,反正挺长的，你喜欢好了！
            //if( (targetNode.left != null && targetNode.right ==null) ||
            // (targetNode.right != null && targetNode.left == null) )
            else {
                //如果要删除的节点只有左子树
                if (targetNode.left != null){
                    if ( parent != null){
                        //如果targetNode是parent的左子节点
                        if (parent.left.value == value){
                            parent.left = targetNode.left;
                        } //如果targetNode是parent的右子节点
                        else if (parent.right.value == value){
                            parent.right = targetNode.left;
                        }
                    }else {
                        root = targetNode.left;
                    }

                }
                //如果要删除的节点只有右子树
                else {
                    if (parent != null){
                        //如果targetNode是parent的左子节点
                        if (parent.left.value == value){
                            parent.left = targetNode.right;
                        } //如果targetNode是parent的右子节点
                        else if (parent.right.value == value){
                            parent.right = targetNode.right;
                        }
                    }else {
                        root = targetNode.right;
                    }

                }


            }

        }
    }
    /**
     * 中缀遍历
     */
    public void infixOrder(){
        if (root !=null){
            root.infixOrder();
        }else {
            System.out.println("这是一个空树");
        }
    }

    /**
     * 添加节点的方法
     *
     * @param node 节点
     */
    public void add(Node node){
        if ( root == null){
            root = node;
        }else {
            root.add(node);
        }
    }

}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 添加节点的方法
     *
     *
     * @param node 传入的节点
     */
    public void add(Node node){
        if ( node ==null){ //传入的节点为null，直接返回
            return;
        }
        if ( node.value < this.value){ //传入的节点小于当前节点，就放入当前节点的左子树
            if (this.left == null){ //当前节点的left为null，直接放入
                this.left = node;
            }else { //当前节点的left不为null，递归遍历当前节点的左子树，直到找到某个节点的left为null，插入传入的节点
                this.left.add(node);
            }

        }else { //传入的节点大于于当前节点，就放入当前节点的右子树
            if (this.right == null){//当前节点的right为null，直接放入
                this.right = node;
            }else {//当前节点的right不为null，递归遍历当前节点的左子树，直到找到某个节点的right为null，插入传入的节点
                this.right.add(node);
            }
        }
    }

    /**
     * 中缀遍历
     */
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    /**
     * 搜索要删除的节点
     *
     * @param value 希望删除的节点的值
     * @return 找到就返回该节点，否则就返回null
     */
    public Node search(int value){
        //找到该节点就返回
        if (value == this.value){
            return this;
        }else if ( value < this.value ){
            if (this.left == null){ //树中没有该值的节点，就返回null
                return null;
            }
            return this.left.search(value);
        }else {
            if (this.right == null){//树中没有该值的节点，就返回null
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 搜索父节点
     *
     * @param value 希望删除的节点的值
     * @return 返回的是要删除的节点的父节点。如果没有就返回null
     */
    public Node searchParent(int value){
        //如果当前节点就是要删除的节点的父节点，返回
        if ( (this.left != null && this.left .value == value) ||
                (this.right != null && this.right .value == value)){
            return this;
        }else {
            //如果要查找的值小于当前节点的值，且当前节点的左子树不为null，就递归在左子树查找
            if (value < this.value && this.left != null){
                return this.left.searchParent(value);
            }else if (value >= this.value && this.right != null){
                //如果要查找的值大于或等于当前节点的值，且当前节点的右子树不为null，就递归在右子树查找
                return this.right.searchParent(value);
            }else {
                return null;  //没有找到父节点
            }
        }
    }

}
/*
实现步骤：
1、Node节点类
  1）创建value、left、right、构造器
  2）创建添加节点的方法
  3）中序遍历节点的方法
2、BinarySortTree类
  1）初始化root节点
  2）中序遍历
  3）添加节点的方法
3、main主方法
  1）创建数组
  2）创建二叉排序树的对象
  3）把数组插入二叉排序树
  4）调用中序遍历方法

删除节点的思路：
三种情况：
1、删除叶子节点  2、删除只有一颗子树的非叶子节3、删除有左右子树的非叶子节点

第一种情况：
删除叶子节点
思路：
1）需要找出要删除的节点 targetNode
2）找到targetNode的父节点parent
3）确定targetNode是parent的左子节点、还是右子节点
4）根据3的情况删除对应的节点
   左子节点：parent.left = null;
   右子节点：parent.right = null;

第二种情况：
删除只有一颗子树的节点
思路：
1）需要找出要删除的节点 targetNode
2）找到targetNode的父节点parent
3）确定targetNode的子节点是左子节点还是右子节点
4）确定targetNode是parent的是左子节点还是右子节点
5）如果targetNode有左子节点
   (1)如果targetNode是parent的左子节点
       parent.left = targetNode.left
   (2)如果targetNode是parent的右子节点
       parent.right = targetNode.left

6）如果targetNode有右子节点
   (1)如果targetNode是parent的左子节点
       parent.left = targetNode.right
   (2)如果targetNode是parent的右子节点
       parent.right = targetNode.right


第三种情况
删除有两颗子树的节点
思路
1）需要找出要删除的节点 targetNode
2）找到targetNode的父节点parent
3）从targetNode的右子节树找到最小的节点（或者左子树找到最大的节点）
4）用一个临时变量，将最小（最大）节点的值用temp保存
5）删除该最小（最大）节点
6）targetNode.value = temp
 */