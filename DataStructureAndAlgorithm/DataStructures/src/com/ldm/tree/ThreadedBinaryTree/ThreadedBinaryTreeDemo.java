package com.ldm.tree.ThreadedBinaryTree;

/**
 * @author 梁东明
 * 2022/8/31
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 */
@SuppressWarnings({"all"})
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        Node root = new Node(1, "钱一");
        Node node1 = new Node(3, "赵二");
        Node node2 = new Node(6, "张三");
        Node node3 = new Node(8, "李四");
        Node node4 = new Node(10, "王五");
        Node node5 = new Node(14, "陈六");

        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试
        Node left = node4.getLeft();
        System.out.println("10号节点的前驱节点是"+left);
        Node right = node4.getRight();
        System.out.println("10号节点的后继节点是" + right);

        //非递归遍历线索二叉树
        System.out.println("非递归遍历线索二叉树");
        threadedBinaryTree.threadedList();

    }
}

//定义ThreadedbinaryTree 二叉树
class ThreadedBinaryTree{
    private Node root;  //定义根节点

    //为了线索化，需要创建要给指向当前节点的前驱节点的指针
    private Node pre = null;

    public void setRoot(Node root) {
        this.root = root;
    }

    public void threadedNodes(){
        this.threadedNodes(root);
    }
    public void threadedList(){
        Node node = root;  //先获取到根节点，准备开始遍历
        while (node != null){  //根节点不为null，开始遍历，这时

            //这层遍历是找出node.leftType == 1 的节点
            //因为当leftType==1，说明该节点就是按照线索化的
            //前驱节点
            while (node.getLefType() == 0){
                node = node.getLeft();
            }
            //打印这个节点
            System.out.println(node);
            //这层遍历是找出node.rightType == 1 的节点
            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的节点
            node = node.getRight();
        }
    }

    /**
     * 螺纹节点
     * 螺纹节点编写对二叉树进行中序线索化的方法
     *
     * @param node 需要线索化的节点
     */
   /* public void threadedNodes(Node node){
        //如果node为null，不能进行线索化
        if (node == null){
            return;
        }
        //第一步，线索化左子树
        threadedNodes(node.getLeft());
        //第二步：线索化当前节点

        //处理当前节点的前驱节点
        if (node.getLeft() ==null){
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型,当前左指针指向的不再是左子树，而是前驱节点
            node.setLefType(1);
        }
        //处理后继节点
        if (pre != null &&pre.getRight() ==null){

            pre.setRight(node);
            pre.setRightType(1);
        }
        //很重要的一行代码，每处理一个节点，让当前节点是下一个节点的前驱
        pre = node;
        //第三步：线索化右子树
        threadedNodes(node.getRight());
    }*/

    //这个是我为了加强记忆手写的，全程无注解，你可以看前面有注解的
    public void threadedNodes(Node node){
        if ( node ==null){
            return;
        }
        threadedNodes(node.getLeft());

        if ( node.getLeft() ==null){
            node.setLeft(pre);
            node.setLefType(1);
        }
        if (pre != null && pre.getRight()==null){
            pre.setRight(node);
            pre .setRightType(1);
        }
        pre  =node;

        threadedNodes(node.getRight());
    }


    //删除节点
    public void delete(int no){
        if (this.root != null){
            //需要判断该树是否只有根节点，是的话只需要删除根节点即可(将root置null）
            if (root.getNo() == no){
                root = null;
            }else {
                this.root.delete(no);
            }
        }else {
            System.out.println("空树，无法删除");
        }
    }
    //前序遍历
    public void preorder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为null，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为null，无法遍历");
        }
    }
    //后序遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为null，无法遍历");
        }
    }
    //前序查找
    public Node preOrderSearch(int no){
        if (this.root != null){
            return root.preOrderSearch(no);
        }else {
           return null;
        }
    }
    //中序查找
    public Node infixOrderSearch(int no){
        if (this.root != null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序查找
    public Node postOrderSearch(int no){
        if (this.root != null){
            return root.postOrderSearch(no);
        }else {
            return null;
        }
    }

}
//先创建节点
class Node{
    private int no;
    private String name;
    private Node left;  //默认为null
    private Node right;  //默认为null

    //1、如果leftType==0表示指向的是左子树，leftType==1表示指向前驱节点
    //2、如果rightType==0表示指向的是右子树，rightType==1表示指向后继节点
    private int lefType;
    private int rightType;
    /**
     * 构造器
     * @param no   编号
     * @param name 名字
     */
    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLefType() {
        return lefType;
    }

    public void setLefType(int lefType) {
        this.lefType = lefType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
    //递归删除节点
    //1、如果删除的是叶子节点，则删除该节点
    //2、如果删除的是非叶子节点，则删除该子树
    public void delete(int no){
        if (this.left != null && this.left.no == no){
            //如果删除的节点就是该节点的左子节点，让他的左子节点置null
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no){
            //如果删除的节点就是该节点的右子节点，让他的右子节点置null
            this.right = null;
            return;
        }
        //如果删除的节点不是当前节点，需要向当前节点的左子树进行递归遍历，找到它并删除删除
        if (this.left != null){
            this.left.delete(no);
        }

        //不要怀疑为什么是当前节点，因为刚开始递归的时候当前节点就是根节点
        //如果删除的节点不是当前节点并且不是其左子节树的节点，需要向其右子树进行递归遍历，找到它并删除删除
        if (this.right != null){
            this.right.delete(no);
        }
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this); //先输出父节点
        //递归向左子树的遍历
        if (this.left !=null){
            this.left.preOrder();
        }
        //递归向左子树的遍历
        if (this.right !=null){
            this.right.preOrder();
        }

    }
    //中序遍历
    public void infixOrder(){
        //递归向左子树的遍历
        if (this.left !=null){
            this.left.infixOrder();
        }
        System.out.println(this); //先输出父节点
        //递归向左子树的遍历
        if (this.right !=null){
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        //递归向左子树的遍历
        if (this.left !=null){
            this.left.postOrder();
        }
        //递归向左子树的遍历
        if (this.right !=null){
            this.right.postOrder();
        }
        System.out.println(this); //先输出父节点
    }

    /**
     * 前序遍历查找
     *
     * @param no 编号
     * @return 如果找到就返回Node，如果没有找到就返回null
     */
    public Node preOrderSearch(int no){

        System.out.println("遍历查找次数");
        //比较当前节点的no等于传入的no
        if (this.no == no){
            return this;
        }
        //1、判断当前节点是否为null，如果不为null，就前序递归遍历
        //2、如果左递归前序查找，找到节点，直接返回
        Node resNode = null;
        if ( this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null){  //说明左递归找打节点了
            return resNode;
        }
        //1、左递归前序查找，找到节点，则返回，否则继续判断
        //2、当前节点的右子节点是否为null，如果不为null，则继续向右递归前序查找
        if (this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;

    }

    /**
     * 中缀顺序搜索
     *
     * @param no 编号
     * @return 如果找到就返回Node，如果没有找到就返回null
     */
    public Node infixOrderSearch(int no){

        //1、判断当前节点的左子节点是否为null，如果不为null，就递归中序查找
        Node resNode = null;
        if ( this.left != null){
            resNode = this.left.infixOrderSearch(no);

        }
        //2、如果左递归前序查找，找到节点，直接返回
        if (resNode != null){  //说明左递归找打节点了
            return resNode;
        }
        System.out.println("遍历查找次数");
        //如果找到，则返回，如果没有找到，就和当前节点比较，如果是则返回当前节点
        if (this.no == no){
            return this;
        }

        //否则继续进行右递归的中序查找
        if (this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;

    }
    /**
     * 后序查找
     *
     * @param no 编号
     * @return 如果找到就返回Node，如果没有找到就返回null
     */
    public Node postOrderSearch(int no){

        //1、判断当前节点的左子节点是否为null，如果不为null，就递归后序遍历
        Node resNode = null;
        if ( this.left != null){
            resNode = this.left.postOrderSearch(no);

        }
        if (resNode != null){  //说明左递归找打节点了
            return resNode;
        }

        //如果左子树没有找到，则向右子树进行后序遍历查找
        if (this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null){  //说明左递归找打节点了
            return resNode;
        }
        System.out.println("遍历查找次数");
        //如果左右子树都没有找到，就比较当前节点
        //比较当前节点的no等于传入的no
        if (this.no == no){
            return this;
        }
        //树中没有该节点就返回null
        return null;

    }


}
