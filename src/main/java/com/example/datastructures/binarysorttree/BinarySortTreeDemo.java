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
        int arr[]={ 7, 3, 10, 12, 5, 1, 9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //构造二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        //中序遍历二叉排序树
        //1,3,5,7,9,10,12
        binarySortTree.midOrder();

        //测试删除叶子节点
//        binarySortTree.delNode(2);
//        binarySortTree.delNode(5);
//        binarySortTree.delNode(9);
//        binarySortTree.delNode(12);
//        System.out.println("删除节点后");
//        binarySortTree.midOrder();

        //测试删除只有一个孩子的非叶子节点
//        binarySortTree.delNode(1);
//        System.out.println("删除节点后");
//        binarySortTree.midOrder();

        //测试删除有两个孩子的非叶子节点
        binarySortTree.delNode(3);
        System.out.println("删除节点后");
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
    //查找指定的节点
    public Node searchNode(int value){
        if (root!=null) {
            return root.searchNode(value);
        }else {
            return null;
        }
    }
    //查找指定节点的父节点
    public Node searchParent(int value){
        if (root!=null) {
            return root.searchParent(value);
        }else {
            return null;
        }
    }
    /**
     *获取二叉树中权值最小的节点，并删除
     * @param node 根节点
     * @return 树中权值最小的节点
     */
    public Node delRightTreeMid(Node node){
        Node temp=node;
        //方式一，使用递归
        //根节点就是权值最下的节点
//        if (node.left==null){
//             //删除最小节点
//             delNode(temp.value);
//             return temp;
//        }
//        else {
//         return delRightTreeMid(node.left);
//        }
        //方式二：使用循环
        while(temp.left!=null){
            temp=temp.left;
        }
        delNode(temp.value);
        return temp;
    }
    /**
     *获取二叉树中权值最大的节点，并删除
     * @param node 根节点
     * @return 树中权值最大的节点
     */
    public Node delLeftTreeMax(Node node){
        Node temp=node;
        while(temp.right!=null){
            temp=temp.right;
        }
        delNode(temp.value);
        return temp;
    }

    //删除节点
    public void delNode(int value){
        if (root ==null){
            return;
        }else {
           //1.先去查找指定的节点
            Node targetNode = searchNode(value);
            //要删除的元素不存在
            if (targetNode==null){
                return;
            }
            //找到了要删除的元素，发现当前这颗二叉排序树只有一个节点
            if(root.left==null && root.right==null){
                root=null;
                return;
            }
            //2.先去查找target节点的父节点
            Node parent= searchParent(value);
            //3.如果要删除的节点是叶子节点
            if(targetNode.left==null && targetNode.right==null){
                //要删除的节点是左子节点
                if (parent.left!=null && parent.left.value==targetNode.value){
                    parent.left=null;
                }//要删除的节点是右子节点
                else if (parent.right!=null && parent.right.value==targetNode.value){
                    parent.right=null;
                }
            }
            //4.要删除的节点是非叶子节点并且存在左子节点和右子节点
            else if (targetNode.left!=null && targetNode.right!=null){
                //删除右子树中，最小节点并返回最小节点
                Node node = delRightTreeMid(targetNode.right);
                targetNode.value=node.value;
                //或者也可以从左子树中找最大的
//                Node node= delLeftTreeMax(targetNode.left);
//                targetNode.value=node.value;
            }
            //5.要删除的节点是非叶子节点，并且该节点只有一个孩子节点
            else{
                //要删除的节点有一个左子节点
                if(targetNode.left!=null){
                    //要删除的节点为父节点的左节点
                    if(parent.left==targetNode){
                        //删除节点
                        parent.left=targetNode.left;
                    }
                    //要删除的节点为父节点的右节点
                    else if(parent.right==targetNode){
                        //删除节点
                        parent.right=targetNode.left;
                    }
                }
                //要删除的节点有一个右子节点
                else if (targetNode.right!=null){
                    //要删除的节点为父节点的左节点
                    if(parent.left==targetNode){
                        //删除节点
                        parent.left=targetNode.right;
                    }
                    //要删除的节点为父节点的右节点
                    else if(parent.right==targetNode){
                        //删除节点
                        parent.right=targetNode.right;
                    }
                }
            }
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
    /**
     * 查找指定的节点
     * @return
     */
    public Node searchNode(int value){
        //要查找的节点就是当前节点
        if (value==this.value){
            return this;
        }
        //要查找的节点在左子树中
        else if (value<this.value){
            //向左递归查找
            if(this.left==null){
                return null;
            }
            return this.left.searchNode(value);
        }
        //要查找的节点在右子树中
        else if (value>=this.value){
            //向右递归查找
            if(this.right==null){
                return null;
            }
            return this.right.searchNode(value);
        }
        return null;
    }
    /**
     * 查找指定节点的父节点
     * @param
     */
    public Node searchParent(int value){
        //要查找的节点就是当前节点
        if ((this.left!=null && value==this.left.value ) || (this.right!=null &&value==this.right.value )){
            //返回父节点
            return this;
        }
        //要查找的节点在左子树中
        else if (value<this.value){
            //向左递归查找
            if(this.left==null){
                return null;
            }
            return this.left.searchParent(value);
        }
        //要查找的节点在右子树中
        else if (value>=this.value){
            //向右递归查找
            if(this.right==null){
                return null;
            }
            return this.right.searchParent(value);
        }
        return null;
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