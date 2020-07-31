package com.example.datastructures.linkedlist;

/**
 * @ClassName Josepfu
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/7/31 11:16
 * @Version 1.0
 **/
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        //创建环形队列
//        circleSingleLinkedList.addBoy(5);
//        //遍历环形队列
//        circleSingleLinkedList.showJosepfu();

        //测试小孩出圈
        circleSingleLinkedList.countBoy(1,2,5);
    }

}
//创建一个单向的环形链表
class CircleSingleLinkedList{
    //创建一个first节点，没有编号
    private Boy first=new Boy(-1);

    //添加节点，创建环形链表
    public void addBoy(int nums){
        if (nums<1){
            System.out.println("nums的值不正确");
            return;
        }
        //用来保存当前环形链表的最后一个节点
        //因为新添加节点时，需要从最后一个节点连接到新节点上
        //所以需要把环形链表的尾节点存起来
        Boy curBoy=null;
        for (int i = 1; i <=nums; i++) {
            Boy boy = new Boy(i);
            //第一个小孩，自己先构成一个环
            if (i==1){
                //first称为第一个节点，并且之后不能动
                first=boy;
                //构成环
                first.setNext(first);
                //辅助指正指向第一个小孩
                curBoy=first;
            }else{
                //尾节点指向新节点
                curBoy.setNext(boy);
                //新节点指向头结点，形成环
                boy.setNext(first);
                //之前的尾节点已经不是尾节点了，新节点才是尾节点
                //需要移动到新节点上
                curBoy=boy;
            }
        }
    }
    //遍历当前的环形链表
    public void showJosepfu(){
        if (first==null){
            System.out.println("没有任何小孩");
            return;
        }
        Boy temp=first;
        //遍历完毕的条件
        do {
            System.out.printf("小孩的编号%d\n",temp.getNo());
            //temp后移
            temp=temp.getNext();
        }while (temp!=first);
    }
    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     * @param startNo 表示第几个小孩开始数
     * @param countNum 数几下
     * @param nums 环形链表有几个小孩
     */
    public void countBoy(int startNo,int countNum,int nums){
        //校验数据
        if (first==null|| startNo<1 || startNo>nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建环形队列
        addBoy(nums);
        //1.创建辅助指针，用于记录需要出圈的小孩的前一个节点
        Boy helper=first;
        //2.事先应该让helper指向链表的最后一个节点
        while(true){
            //说明指向了最后一个节点
            if (helper.getNext()==first){
                break;
            }
            helper=helper.getNext();
        }
        //3.小孩报数前，先让first和helper移动k-1次
        for (int i = 0; i < startNo - 1; i++) {
            first=first.getNext();
            helper= helper.getNext();
        }
        //4.当小孩报数时，让first和helper指针同时移动countNum-1次
        //然后出圈，这里是一个循环操作，直到圈中只有一个节点
        while(true){
            //圈中只有一个节点
            if (first==helper){
                break;
            }
            //让first和helper指针同时移动countNum-1次
            for (int i = 0; i < countNum - 1; i++) {
                first=first.getNext();
                helper= helper.getNext();
            }
            //这是first指向要出圈的小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            //小孩出圈
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩%d\n",first.getNo());
    }

}
//创建一个Boy类，表示一个节点
class Boy{
    //编号
    private int no;
    //指向下一个节点，默认null
    private Boy next;
    public Boy(int no) {
        this.no = no;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public Boy getNext() {
        return next;
    }
    public void setNext(Boy next) {
        this.next = next;
    }
}