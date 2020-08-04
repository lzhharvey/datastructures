package com.example.datastructures.tree;

/**
 * @ClassName BinaryTreeDemo
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/8/4 10:50
 * @Version 1.0
 **/
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //构造节点
        HeroNode root=new HeroNode(1,"宋江");
        HeroNode node1=new HeroNode(2,"吴用");
        HeroNode node2=new HeroNode(3,"卢俊");
        HeroNode node3=new HeroNode(4,"林冲");
        HeroNode node4=new HeroNode(5,"关胜");
        root.left=node1;
        root.right=node2;
        node2.right=node3;
        node2.left=node4;
        //创建一颗二叉树
        BinaryTree binaryTree=new BinaryTree(root);

//        System.out.println("测试前序遍历：");
//        binaryTree.binaryTreePreOrder();
//
//        System.out.println("测试中序遍历：");
//        binaryTree.binaryTreeMidOrder();
//
//        System.out.println("测试后序遍历：");
//        binaryTree.binaryTreePostOrder();
//
//        System.out.println("前序遍历找到指定节点：");
//        HeroNode node = binaryTree.preOrderSearch(1);
//        if (node!=null){
//            System.out.println("id="+node.no+" name="+node.name);
//        }else{
//            System.out.println("没有找到该英雄");
//        }
//
//        System.out.println("中序遍历找到指定节点：");
//        node = binaryTree.midOrderSearch(1);
//        if (node!=null){
//            System.out.println("id="+node.no+" name="+node.name);
//        }else{
//            System.out.println("没有找到该英雄");
//        }
//
//        System.out.println("后序遍历找到指定节点：");
//        node = binaryTree.postOrderSearch(1);
//        if (node!=null){
//            System.out.println("id="+node.no+" name="+node.name);
//        }else{
//            System.out.println("没有找到该英雄");
//        }

        System.out.println("删除前：");
        binaryTree.binaryTreePreOrder();
        System.out.println("删除后：");
        binaryTree.delNode(3);
        binaryTree.binaryTreePreOrder();
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
    //构造函数
    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }
    //前序遍历
    public void preOrder(){
        //先输出父节点
        System.out.println(this);
        //递归左子树前序遍历
        if (this.left!=null){
            this.left.preOrder();
        }
        //递归右子树前序遍历
        if(this.right!=null){
            this.right.preOrder();
        }
    }
    //中序遍历
    public void midOrder(){
        //递归左子树中序遍历
        if(this.left!=null){
            this.left.midOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归右子树中序遍历
        if(this.right!=null){
            this.right.midOrder();
        }
    }
    //后序遍历
    public void postOrder(){
        //递归左子树后序遍历
        if(this.left!=null){
            this.left.postOrder();
        }
        //递归右子树后序遍历
        if(this.right!=null){
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }
    /**
     * 前序遍历查找指定节点
     * @param no 查找no节点
     * @return 如果找到就返回Node，否则返回null
     */
    public HeroNode findHeroByPreOrder(int no){
        System.out.println("进入前序遍历");
        //找到指定节点
        if (this.no==no){
            return this;
        }
        //递归左子树前序遍历查找指定节点
        if (this.left!=null){
            HeroNode heroByPreOrder = this.left.findHeroByPreOrder(no);
            //左子树找到了
            if (heroByPreOrder!=null){
                return heroByPreOrder;
            }
        }
        //递归右子树前序遍历查找指定节点
        if(this.right!=null){
            HeroNode heroByPreOrder = this.right.findHeroByPreOrder(no);
            //右子树找到了
            if (heroByPreOrder!=null){
                return heroByPreOrder;
            }
        }
        //找不到
        return null;
    }
    /**
     * 中序遍历查找指定节点
     * @param no 查找no节点
     * @return 如果找到就返回Node，否则返回null
     */
    public HeroNode findHeroByMidOrder(int no){
        //递归左子树中序遍历查找指定节点
        if (this.left!=null){
            HeroNode heroByMidOrder = this.left.findHeroByMidOrder(no);
            //左子树找到了
            if (heroByMidOrder!=null){
                return heroByMidOrder;
            }
        }
        System.out.println("进入中序遍历");
        //找到指定节点
        if (this.no==no){
            return this;
        }
        //递归右子树中序遍历查找指定节点
        if(this.right!=null){
            HeroNode heroByMidOrder = this.right.findHeroByMidOrder(no);
            //右子树找到了
            if (heroByMidOrder!=null){
                return heroByMidOrder;
            }
        }
        //找不到
        return null;
    }
    /**
     * 后序遍历查找指定节点
     * @param no 查找no节点
     * @return 如果找到就返回Node，否则返回null
     */
    public HeroNode findHeroByPostOrder(int no){
        //递归左子树后序遍历查找指定节点
        if (this.left!=null){
            HeroNode heroByPostOrder = this.left.findHeroByPostOrder(no);
            //左子树找到了
            if (heroByPostOrder!=null){
                return heroByPostOrder;
            }
        }
        //递归右子树后序遍历查找指定节点
        if(this.right!=null){
            HeroNode heroByPostOrder = this.right.findHeroByPostOrder(no);
            //右子树找到了
            if (heroByPostOrder!=null){
                return heroByPostOrder;
            }
        }
        System.out.println("进入后序遍历");
        //找到指定节点
        if (this.no==no){
            return this;
        }
        //找不到
        return null;
    }
    //递归删除节点
    public void deleteNode(int no){
		/*思路:
		 * 	1. 因为我们的二叉树是单向的，所以我们是判断当前结点的子结点是否需要删除结点，而不能去判断当前这个结点是不是需要删除结点.
			2. 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
			3. 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
			4. 如果第2和第3步没有删除结点，那么我们就需要向左子树进行递归删除
			5.  如果第4步也没有删除结点，则应当向右子树进行递归删除.
		 */
        //左子节点就是要删出的子节点(不管是叶子结点还是子树)
        if (this.left!=null && this.left.no==no){
            this.left=null;
            return;
        }
        //右子节点就是要删出的子节点(不管是叶子结点还是子树)
        if (this.right!=null && this.right.no==no){
            this.right=null;
            return;
        }
        //左子节点和右子节点都不是要删出的子节点，递归左子节点
        if (this.left!=null){
            //这个递归的结束条件， 假设递归到了叶子结点，this.left==null
            //所有判断都进不去，什么都没干，就走完deleteNode方法，返回上层
            //deleteNode方法，去递归右子树，右子树同样分析
            this.left.deleteNode(no);
        }
        //递归左子节点也没有要删出的元素，递归右子节点
        if(this.right!=null){
            this.right.deleteNode(no);
        }
    }
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
//定义二叉树
class BinaryTree{
    private HeroNode root;

    public BinaryTree(HeroNode root) {
        this.root = root;
    }
    //前序遍历
    public void  binaryTreePreOrder(){
        if (this.root!=null) {
            root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //中序遍历
    public void  binaryTreeMidOrder(){
        if (this.root!=null) {
            root.midOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void  binaryTreePostOrder(){
        if (this.root!=null) {
            root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //前序遍历找到指定节点
    public HeroNode  preOrderSearch(int no){
        if (this.root!=null) {
             return root.findHeroByPreOrder(no);
        }else {
            return null;
        }
    }
    //中序遍历找到指定节点
    public HeroNode  midOrderSearch(int no){
        if (this.root!=null) {
            return root.findHeroByMidOrder(no);
        }else {
            return null;
        }
    }
    //后序遍历找到指定节点
    public HeroNode  postOrderSearch(int no){
        if (this.root!=null) {
            return root.findHeroByPostOrder(no);
        }else {
            return null;
        }
    }
    //删出节点
    public void delNode(int no){
        if (root==null)
        {
            System.out.println("空树不能删除");
        }
        //要删出的节点就是root节点，等同于删除了整颗数
        if (root.no==no){
            root=null;
            return;
        }
        //递归删除
        root.deleteNode(no);
    }
}