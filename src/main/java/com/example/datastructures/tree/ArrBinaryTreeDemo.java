package com.example.datastructures.tree;

/**
 * @ClassName ArrBinaryTreeDemo
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/8/4 20:02
 * @Version 1.0
 **/
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
//        arrBinaryTree.preOrder(0);
//        arrBinaryTree.midOrder(0);
        arrBinaryTree.postOrder(0);
    }

}

//编写一个ArrBinaryTree，实现顺序存储二叉树遍历
class ArrBinaryTree{
    //存储数据节点的数组
    private int[] arr;
    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }
    /**
     * 编写一个方法，完成顺序存储二叉树的前序遍历
     * @param index 数组的下标
     */
    public void preOrder(int index){
        if(arr==null || arr.length==0){
            System.out.println("数组为空，不能进行二叉树的前序遍历");
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向左遍历
        if ((2*index+1)<arr.length) {
            preOrder(2 * index + 1);
        }
        //向右遍历
        if ((2*index+2)<arr.length) {
            preOrder(2 * index + 2);
        }
    }
    /**
     * 编写一个方法，完成顺序存储二叉树的中序遍历
     * @param index 数组的下标
     */
    public void midOrder(int index){
        if(arr==null || arr.length==0){
            System.out.println("数组为空，不能进行二叉树的前序遍历");
        }
        //向左遍历
        if ((2*index+1)<arr.length) {
            midOrder(2 * index + 1);
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向右遍历
        if ((2*index+2)<arr.length) {
            midOrder(2 * index + 2);
        }
    }

    /**
     * 编写一个方法，完成顺序存储二叉树的后序遍历
     * @param index 数组的下标
     */
    public void postOrder(int index){
        if(arr==null || arr.length==0){
            System.out.println("数组为空，不能进行二叉树的前序遍历");
        }
        //向左遍历
        if ((2*index+1)<arr.length) {
            postOrder(2 * index + 1);
        }
        //向右遍历
        if ((2*index+2)<arr.length) {
            postOrder(2 * index + 2);
        }
        //输出当前元素
        System.out.println(arr[index]);
    }

}
