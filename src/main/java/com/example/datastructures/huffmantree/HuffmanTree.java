package com.example.datastructures.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName HuffmanTree
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/8/5 16:33
 * @Version 1.0
 **/
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[]={13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }
    //编写一个前序遍历的方法
    public static void preOrder(Node root){
        if (root==null){
            System.out.println("树为空，不能遍历");
        }
        root.preOrder();
    }
    /**
     * 创建赫夫曼树方法
     * @param arr 需要创建成赫夫曼树的数组
     * @return 创建好后的赫夫曼树的root节点
     */
    public static Node createHuffmanTree(int arr[]){
        //1.为了操作方便
        //遍历数组，将每个元素构成一个Node,并将Node放入集合中
        List<Node> nodelist=new ArrayList<>();
        for (int i=0;i<arr.length;i++){
            nodelist.add(new Node(arr[i]));
        }
        while(nodelist.size()>1) {
            //2.排序,因为每次要取出权值最小的两个节点
            //所以先给排个序
            Collections.sort(nodelist);

            //3.取出根节点权值最小的两颗二叉树
            Node left = nodelist.get(0);
            Node right = nodelist.get(1);

            //4.构建一个新的二叉树
            Node parentNode = new Node(left.value + right.value);
            parentNode.left = left;
            parentNode.right = right;

            //5.从集合中删除处理过的节点,并把parentNode添加进去
            nodelist.remove(left);
            nodelist.remove(right);
            nodelist.add(parentNode);
        }
        //赫夫曼树构建完毕，集合中只剩一个元素，就是根节点
        return nodelist.get(0);
    }
}
//创建节点类
//为了让Node对象持续排序Colelctions集合排序
//让Node实现Compareable接口
class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;
    public Node(int value) {
        this.value = value;
    }
    //写一个前序遍历
    public void preOrder(){
        //输出当前节点
        System.out.println(this);
        //向左子树遍历
        if (this.left!=null){
            this.left.preOrder();
        }
        //向右子树遍历
        if(this.right!=null){
            this.right.preOrder();
        }
    }
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
    @Override
    public int compareTo(Node o) {
        //表示从小到大排
        return this.value-o.value;
    }
}