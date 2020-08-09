package com.example.datastructures.avl;




public class AvlTreeDemo {
    public static void main(String[] args) {
//        int[] arr={4,3,6,5,7,8};
//        int[] arr={10,12, 8, 9, 7, 6};
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        //创建AVL树
        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //中序遍历树
//        System.out.println("中序遍历树：");
//        avlTree.midOrder();

        //获取树的高度（新）
        System.out.println("在没有进行平衡处理前：");
        System.out.println("树高："+avlTree.getRoot().height());//4
        System.out.println("树的左子树的高度："+avlTree.getRoot().left.height());//1
        System.out.println("树的右子树的高度："+avlTree.getRoot().right.height());//3
        System.out.println(avlTree.getRoot().right.right);
    }
}

class AVLTree{
    private Node root;

    public Node getRoot() {
        return root;
    }
    //添加元素
    public void add(Node node) {
        if (this.root==null) {
            //根节点为空，初始化根节点
            this.root = node;
        }else {
            this.root.addNode(node);
        }
    }
    //中序遍历二叉排序树
    public void midOrder(){
        if (root!=null) {
            root.midOrder();
        }else {
            System.out.println("二叉排序为空，不能为空");
        }
    }
}
//创建节点
class Node{
    int value;
    Node left;
    Node right;
    public Node(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int getLeftHeight(){
        if (left!=null){
            return left.height();
        }else {
            return 0;
        }
    }
    //返回右子树的高度
    public int getRightHeight(){
        if (right!=null){
            return right.height();
        }else {
            return 0;
        }
    }
    //返回当前节点的高度，以该节点为根节点的树的高度
    public int height(){
        //递归左子树获取左子树的高度
        int i = leftDG();
        //递归右子树获取右子树的高度
        int j = rightDG();
        //比较左子树和右子树的高度，最后还要加上当前节点
        int i1 = Math.max(i, j) + 1;
        return i1;
        //或者一步到位
        //return Math.max(left==null?0:this.left.height(),right==null?0:right.height())+1;
    }
    //递归左子树获取左子树的高度
    public int leftDG(){
        //递归出口条件
        if(left==null){
            return 0;
        }else{
            //递归的规律
            return left.height();
        }
    }
    //递归右子树获取右子树的高度
    public int rightDG(){
        //递归出口条件
        if (right==null){
            return 0;
        }else {
            //递归规律
            return  right.height();
        }
    }
    //添加节点
    public void addNode(Node node){
        if (node==null) {
            return;
        }
        //传入的节点的值小于当前节点
        if (node.value<this.value){
            //当前节点的左子节点为空
            if (this.left==null){
                //将传入的节点设置为当前节点的左子节点
                this.left=node;
            }else{
                //当前节点的左子节点不为空，递归左子节点
                this.left.addNode(node);
            }
        }//传入的节点的值大于当前节点的值
        else{
            //当前节点的右子节点为空
            if (this.right==null){
                //将传入的节点设置为当前节点的右子节点
                this.right=node;
            }else{
                //当前节点的右子节点不为空，递归右子节点
                this.right.addNode(node);
            }
        }
        //当添加完一个节点后，如果（右子树高度-左子树高度）>1，左旋转
        if (getRightHeight()-getLeftHeight()>1){
            //如果它的右子树的左子树的高度大于右子树的高度
            if (right!=null && right.getLeftHeight()>right.getRightHeight()) {
                //先对当前节点的右节点进行右旋转
                right.rightRotate();
                //再对当前节点进行左旋转
                this.leftRotate();
            }else {
                //直接左旋转
                this.leftRotate();
            }
            return;//必须要，走完这段代码，树已经平衡了，不让程序往下走，以免出现问题
        }
        //当添加完一个节点后，如果（左子树高度-右子树高度）>1，右旋转
        if (getLeftHeight()-getRightHeight()>1){
            //如果它的左子树的右子树高度大于它的左子树的高度
            if (left!=null && left.getRightHeight()>left.getLeftHeight()){
                //先对当前节点的左节点进行左旋转
                left.leftRotate();
                //再对当前节点进行右旋转
                this.rightRotate();
            }else {
                //直接右旋转
                this.rightRotate();
            }
        }
    }
    //中序遍历
    public  void midOrder(){
        //向左递归
        if(this.left!=null){
            this.left.midOrder();
        }
        //打印当前节点
        System.out.println(this);
        //向右递归
        if(this.right!=null){
            this.right.midOrder();
        }
    }
    //左旋转方法
    private void leftRotate(){
        //1.创建一个新的节点，以根节点的值作为它的值
        Node newNode = new Node(value);
        //2.把新节点的左子树设置为当前节点的左子树
        newNode.left=this.left;
        //3.把新节点的右子树设置为当前节点的右子树的左子树
        newNode.right=this.right.left;
        //4.当前节点的值换为右子节点的值
        value=this.right.value;
        //5.把当前节点的右子树设置为右子树的右子树
        right=right.right;
        //6.把当前节点的左子树设置为新节点
        left=newNode;
    }
    //右旋转方法
    private void  rightRotate(){
        //1.创建一个新的节点，以根节点的值作为它的值
        Node newNode = new Node(value);
        //2.把新节点的右子树设置为当前节点的右子树
        newNode.right=right;
        //3.把新节点的左子树设置为当前节点的左子树的右子树
        newNode.left=left.right;
        //4.把当前节点的值换为左子节点的值
        value=left.value;
        //5.把当前节点的左子树设置成左子树的左子树
        left=left.left;
        //6.把当前节点的右子树设置为新节点
        right=newNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}