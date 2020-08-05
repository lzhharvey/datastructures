package com.example.datastructures.tree.threaded;

/**
 * @ClassName ThreadedBinaryTreeDemo
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/8/4 23:02
 * @Version 1.0
 **/
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "tom1");
        HeroNode node3 = new HeroNode(6, "to2");
        HeroNode node4 = new HeroNode(8, "tom3");
        HeroNode node5 = new HeroNode(10, "tom4");
        HeroNode node6 = new HeroNode(14, "tom5");

        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.left=node2;
        root.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;

        //测试中序线索化二叉树
        BinaryTree binaryTree = new BinaryTree(root);
        binaryTree.threadedNodes(root);

//        HeroNode left = node5.left;
//        HeroNode right = node5.right;
//        System.out.println("10号节点的前驱节点是："+left);
//        System.out.println("10号节点的后继节点是："+right);

        //遍历中序线索二叉树
        binaryTree.threadedList();
    }
}
//创建数节点
class HeroNode{
    public int no;
    public String name;
    //默认null
    public HeroNode left;
    //默认null
    public HeroNode right;
    //说明
    //如果leftType==0表示指向的是左子树，如果1，则表示指向前驱节点
    //如果rightType==0表示指向的是右子树，如果1，则表示指向后继节点
    public int leftType;
    public int rightType;

    //构造函数
    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
//定义线索化二叉树
class BinaryTree{
    private  HeroNode root;

    //为了实现线索化，需要创建一个指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre总是保留一个节点
    private HeroNode pre=null;

    public BinaryTree( HeroNode root) {
        this.root = root;
    }

    //编写二叉树进行中序线索化的方法
    public void threadedNodes(HeroNode node){
        //如果node是null,不能线索化
        if (node==null){
            return;
        }
        //1.先线索化左子树
        threadedNodes(node.left);
        //2.线索化当前节点
        //处理当前节点的前驱节点
        //如果当前节点没有左子节点
        //那么左指针指向前驱节点
        if (node.left==null){
            //指针指向前驱节点
            node.left=pre;
            //left指针类型，是指向前驱节点
            node.leftType=1;
        }
        //处理当前节点的后继节点
        //给当前节点设置后继节点的时候，实际上，是通过下一个节点来设置的
        //当前节点对于下一个节点来说是上一个节点
        if(pre != null &&  pre.right==null){
            //前驱节点的右指针指向当前节点
            pre.right=node;
            //修改前驱节点的右指针的类型为1
            pre.rightType=1;
        }
        //每处理过一个节点，让当前节点是下一个节点的前驱节点
        pre=node;
        //3.线索化右子树
        threadedNodes(node.right);
    }

    //遍历中序线索二叉树
//遍历线索化二叉树的方法
    public void threadedList() {
        //定义一个变量，存储当前遍历的结点，从root开始
        HeroNode node = root;
        while(node != null) {
            //循环的找到leftType == 1的结点，第一个找到就是8结点
            //后面随着遍历而变化,因为当leftType==1时，说明该结点是按照线索化
            //处理后的有效结点
            while(node.leftType == 0) {
                node = node.left;
            }

            //打印当前这个结点
            System.out.println(node);
            //如果当前结点的右指针指向的是后继结点
            while(node.rightType == 1) {
                //直接获取到当前结点的后继结点
                node = node.right;
                //输出后继节点
                System.out.println(node);
            }
            //如果当前节点的right不是后继节点，是子树
            //替换这个遍历的结点
            node = node.right;
        }
    }
}

