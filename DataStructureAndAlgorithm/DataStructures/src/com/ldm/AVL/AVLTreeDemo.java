package com.ldm.AVL;

/**
 * @author 梁东明
 * 2022/9/7
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr= {4,3,6,5,7,8};
        //int[] arr = {10,12,8,9,7,6};
        int[]  arr = {10,11,7,6,8,9};

        AVLTree avlTree = new AVLTree();

        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历~");
        avlTree.infixOrder();
        System.out.println("树的高度是：" + avlTree.getRoot().getHeight());
        System.out.println("左子树的高度是：" + avlTree.getRoot().getLeftHeight());
        System.out.println("右子树的高度是：" + avlTree.getRoot().getRightHeight());
        System.out.println("当前根节点是：" + avlTree.getRoot());

    }
}
class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

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

    /**
     * 返回左子树的高度
     */
    public int getLeftHeight(){
        if (left == null){
            return 0;
        }
        return left.getHeight();

    }

    /**
     * 返回右子树的高度
     */
    public int getRightHeight(){
        if (right == null){
            return 0;
        }
        return  right.getHeight();

    }

    /**
     * 返回树的高度
     */
    public int getHeight(){

        return Math.max(left == null ? 0 : left.getHeight(), right == null ? 0 : right.getHeight()) + 1;
    }

    /**
     * 左旋转
     */
    public void leftRotate(){
        //创建新节点，以根节点的值
        Node newNode = new Node(value);
        //把新节点的左子树设置为当前节点的左子树
        newNode.left = left;
        //把新节点的右子树设置为当前节点的右子树的左子树
        newNode.right = right.left;
        //把当前节点的值转换成右子节点的值
        value = right.value;
        //把当前节点的右子树设置成右子树的右子树
        right=right.right;
        //把当前节点的左子树设置成新节点
        left = newNode;
    }
    /**
     * 右旋转
     */
    public void rightRotate() {
        //创建新节点，以根节点的值
        Node newNode = new Node(value);
        //创建一个新的节点newNode（以根节点的值创建），创建一个新的节点
        //值等于当前根节点的值
        //把新节点的左子树设置为当前节点的左子树的右子树
        newNode.left = left.right;
        //把新节点的右子树设置为当前节点的右子树
        newNode.right = right;
        //把当前节点的值转换成左子节点的值
        value = left.value;
        //把当前节点的左子树设置成左子树左子树
        left = left.left;
        //把当前节点的右子树设置成新节点
        right = newNode;
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
        //rightHeight()-leftHeight() > 1,不再是一颗AVL树
        //可以进行旋转处理使其变成AVL树
        if ( getRightHeight() - getLeftHeight() > 1 ){
            //如果它的右子树 的左子树的高度大于左子树
            if (right != null && right.getLeftHeight() > right.getRightHeight()){
                //先对当前节点的右节点（右子树）-》右旋
                right.rightRotate();
                //再对当前节点进行右旋
                leftRotate();
            }else {
                leftRotate();
            }
            return; //这个步骤必须要
        }
        //leftHeight()-rightHeight() > 1,不再是一颗AVL树
        //可以进行旋转处理使其变成AVL树
        if (  getLeftHeight()-getRightHeight()  > 1 ){
            //如果它的左子树  的右子树的高度左子树
            if (left != null && left.getRightHeight() > left.getLeftHeight()){
                //先对当前节点的左节点（左子树）-》左旋
                left.leftRotate();
                //再对当前节点进行右旋
                rightRotate();
            }else {
                rightRotate();
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