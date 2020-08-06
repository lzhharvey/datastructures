package com.example.datastructures.binarysorttree;

/**
 * @ClassName BinarySortTree
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/8/6 17:31
 * @Version 1.0
 **/
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int arr[]={ 7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        //构造二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        //中序遍历二叉排序树
        //1,3,5,7,9,10,12
        binarySortTree.midOrder();

    }
}
//二叉排序树
class BinarySortTree{
    private Node root;
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
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}