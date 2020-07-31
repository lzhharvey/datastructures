package com.example.datastructures.linkedlist;

import java.util.Stack;

/**
 * @ClassName SingleLinkedList
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/7/30 15:22
 * @Version 1.0
 **/
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode heroNode1=new HeroNode(1,"宋江","及时雨");
        HeroNode heroNode2=new HeroNode(2,"宋江2","及时雨2");
        HeroNode heroNode3=new HeroNode(3,"宋江3","及时雨3");
        HeroNode heroNode4=new HeroNode(4,"宋江4","及时雨4");
        HeroNode heroNode5=new HeroNode(2,"宋江666666666","及时雨66666");
        HeroNode heroNode6=new HeroNode(6,"宋江666666666","及时雨66666");
        HeroNode heroNode8=new HeroNode(8,"宋江888","及时雨888");
        HeroNode heroNode7=new HeroNode(7,"宋江77","及时雨7");
        HeroNode heroNode9=new HeroNode(9,"宋江9999","及时雨9999");

        SingleLinkedList singleLinkedList=new SingleLinkedList();
        SingleLinkedList singleLinkedList2=new SingleLinkedList();

//        singleLinkedList.addLast(heroNode1);
//        singleLinkedList.addLast(heroNode2);
//        singleLinkedList.addLast(heroNode3);
//        singleLinkedList.addLast(heroNode4);

        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode7);
        singleLinkedList.addByOrder(heroNode9);
        //显示链表
        singleLinkedList.showLinkedList();

        singleLinkedList2.addByOrder(heroNode4);
        singleLinkedList2.addByOrder(heroNode6);
        singleLinkedList2.addByOrder(heroNode8);
        //显示链表
        singleLinkedList2.showLinkedList();

        System.out.println("A---------------");
        HeroNode hb = hb(singleLinkedList.getHead(), singleLinkedList2.getHead());
        System.out.println(hb);

//        System.out.println("反序打印链表");
//        reverseAndPrint(singleLinkedList.getHead());

//        System.out.println("反转链表");
//        reverse(singleLinkedList.getHead());
//        //显示链表
//        singleLinkedList.showLinkedList();

//        //修改链表
//        singleLinkedList.update(heroNode5);
//        singleLinkedList.update(heroNode6);//修改一个不存在的元素
//        System.out.println("修改后的链表:");
//        //显示链表
//        singleLinkedList.showLinkedList();
//
//        //删除链表
//        singleLinkedList.delete(heroNode1);
//        singleLinkedList.delete(heroNode4);
//        singleLinkedList.delete(heroNode6);//删除一个不存在的元素
//        System.out.println("删除后的链表:");
//        //显示链表
//        singleLinkedList.showLinkedList();
//
//        //获取有效节点个数
//        System.out.println("当前链表有效节点个数："+getLength(singleLinkedList.getHead()));
//
//        HeroNode heroNode = findHeroNode(singleLinkedList.getHead(), 3);
//        System.out.println(heroNode);

    }
    //获取单链表的有效节点的个数(有头结点要去除头结点)
    public static int getLength(HeroNode head){
        int length=0;
        if (head.next==null){
            return length;
        }
        HeroNode temp=head;
        while(temp.next!=null){
            length++;
            temp=temp.next;
        }
        return length;
    }
    //查找单链表中的倒数第K个节点[新浪面试题]
    public static HeroNode findHeroNode(HeroNode heroNode,int k)
    {   //链表为空
        if (heroNode.next==null){
            return null;
        }
        //获取单链表的有效长度
        int size = getLength(heroNode);
        //先做校验
        if (k<=0 || k>size){
            return null;
        }
        HeroNode temp=heroNode.next;
        for (int i=0;i<size-k;i++){
            temp=temp.next;
        }
        return temp;
    }
    //单链表的反转【腾讯面试题】
    //遍历链表，获取最后一个元素
    public static void reverse(HeroNode heroNode){
        //链表为空或者只有一个元素
        if (heroNode.next==null || heroNode.next.next==null ){
            return ;
        }
        HeroNode temp = heroNode.next;
        HeroNode reverseNode=new HeroNode(0,"","");
        //把原链表要移动的节点的下一个节点存起来
        HeroNode next = null;

        while(temp!=null){
            //得先把要移动的节点的下一个节点存起来，因为下一步会把要移动的节点的下一个节点改变
            next=temp.next;
            //从原链表取出的节点，next指向新链表的最前端，所以上一步要做一个存储的动作
            temp.next=reverseNode.next;
            //新链表的头结点指向这个节点
            reverseNode.next=temp;
            //原链表的头结点指向下一个节点
            temp=next;
        }
        heroNode.next=reverseNode.next;
    }
    //从尾到头打印单链表 【百度】
    public static void reverseAndPrint(HeroNode heroNode){
        //链表为空
        if (heroNode.next==null ){
            return ;
        }
        Stack<HeroNode> stack=new Stack<>();
        HeroNode temp = heroNode.next;
        while(temp!=null){
            stack.push(temp);
            temp=temp.next;
        }
        while(stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    public static HeroNode hb(HeroNode heroNode1,HeroNode heroNode2){
        //新链表
        HeroNode newHeroNode=new HeroNode(0,"","");
        if (heroNode1.next==null && heroNode2.next==null){
            return null;
        }else if (heroNode1.next==null && heroNode2.next!=null){
            newHeroNode.next=heroNode2.next;
        }else if (heroNode1.next!=null && heroNode2.next==null){
            newHeroNode.next=heroNode1.next;
        }
        //获取单链表1的第一个节点
        HeroNode heroNode1Next = heroNode1.next;
        //把链表1要移动的节点的下一个节点存起来
        HeroNode next1=null;
        //获取单链表2的第一个节点
        HeroNode heroNode2Next = heroNode2.next;
        //把链表2要移动的节点的下一个节点存起来
        HeroNode next2=null;
        //获取单链表3的第一个节点
        HeroNode heroNode3Temp = newHeroNode;

        while(true){
            //链表1节点大于链表2节点
            if (heroNode1Next!=null && heroNode2Next!=null && heroNode1Next.no>heroNode2Next.no ){
                //得先把要移动的节点的下一个节点存起来，因为下一步会把要移动的节点的下一个节点改变
                next2=heroNode2Next.next;
                //要移动的节点的下一个节点设置为null
                heroNode2Next.next=null;
                //新链表的尾节点添加元素
                heroNode3Temp.next=heroNode2Next;
                //链表2的头结点指向下一个节点
                heroNode2Next=next2;
            }
            //链表2节点大于链表1节点
            else if (heroNode1Next!=null && heroNode2Next!=null && heroNode1Next.no<heroNode2Next.no){
                //得先把要移动的节点的下一个节点存起来，因为下一步会把要移动的节点的下一个节点改变
                next1=heroNode1Next.next;
                //要移动的节点的下一个节点设置为null
                heroNode1Next.next=null;
                //新链表的尾节点添加元素
                heroNode3Temp.next=heroNode1Next;
                //链表1的头结点指向下一个节点
                heroNode1Next=next1;
            }
            //链表1的元素先添加完，链表2的元素没有添加完
            else if (heroNode1Next==null && heroNode2Next!=null){
                //新链表的尾节点直接接入链表2的剩余的所有元素
                heroNode3Temp.next=heroNode2Next;
                //链表2的头结点指向null
                heroNode2Next=null;
            }
            else if (heroNode1Next!=null && heroNode2Next==null){
                //新链表的尾节点直接接入链表1的剩余的所有元素
                heroNode3Temp.next=heroNode1Next;
                //链表1的头结点指向null
                heroNode1Next=null;
            }
            else if (heroNode1Next!=null && heroNode2Next!=null && heroNode1Next.no==heroNode2Next.no){
                System.out.println("相等不处理");
            }else if (heroNode2Next==null && heroNode1Next==null){
                break;
            }
            heroNode3Temp=heroNode3Temp.next;
        }
        return newHeroNode;
    }
}
//定义一个单链表管理各个英雄节点
class  SingleLinkedList{
    //初始化一个头结点，不存储数据
    private HeroNode head=new HeroNode(0,"","");

    //获取头结点
    public HeroNode getHead() {
        return head;
    }

    //第一种方法在添加英雄时，直接添加到链表的尾部
    //找到链表尾部
    //将尾部节点的next指向新的节点
    public void addLast(HeroNode heroNode){
        HeroNode temp=head;
        while (true){
            //链表尾部
            if (temp.next==null){
                break;
            }
            temp=temp.next;
        }
        //将尾部节点的next指向新的节点
        temp.next=heroNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    public void addByOrder(HeroNode heroNode){
        //因为头结点不能动，因此需要一个辅助变量来遍历
        HeroNode temp=head;
        //标志要插入的节点是否已经存在
        boolean flag=false;
        while(true){
            //找到要插入的位置，也有可能是链表的尾部
            if (temp.next==null || temp.next.no>heroNode.no){
                break;
            }else if (temp.next.no==heroNode.no){//节点已经存在
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){
            System.out.printf("准备插入的英雄%d已经存在",heroNode.no);
            System.out.println();
        }else{
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }
    //修改节点，根据no来修改
    public void update(HeroNode heroNode){
        //判断节点是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此需要一个辅助变量来遍历
        HeroNode temp=head;
        //表示是否找到该节点
        Boolean flag=false;
        while(true){
            if (temp.next==null){//整个链表就没这个节点
                break;
            }
            //找到到该节点
            if (heroNode.no==temp.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag==true){
            temp.name=heroNode.name;
            temp.nickName=heroNode.nickName;
        }else {
            System.out.printf("没有找到编号%d的节点\n",heroNode.no);
        }
    }
    //删除节点
    public void delete(HeroNode heroNode){
        //判断节点是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此需要一个辅助变量来遍历
        HeroNode temp=head;
        //表示是否找到该节点的前一个节点
        Boolean flag=false;
        while(true){
            //找到要删除的前一个节点
            if (temp.next==null){
                break;
            }
            if (temp.next.no==heroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if (flag){//删除节点
            temp.next=temp.next.next;
        }else {
            System.out.printf("没有找到编号%d的节点\n",heroNode.no);
        }
    }



    //遍历链表
    public void showLinkedList(){
        //判断节点是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此需要一个辅助变量来遍历
        HeroNode temp=head.next;
        while(true){
            //到了链表最后
            if (temp==null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移
            temp=temp.next;
        }
    }
}
//英雄节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;
    //构造器
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", next=" + next +
                '}';
    }
}