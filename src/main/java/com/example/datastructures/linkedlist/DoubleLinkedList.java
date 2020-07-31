package com.example.datastructures.linkedlist;

/**
 * @ClassName DoubleLinkedList
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/7/30 22:11
 * @Version 1.0
 **/
public class DoubleLinkedList {
    public static void main(String[] args) {
        HeroNode1 heroNode1=new HeroNode1(1,"宋江","及时雨");
        HeroNode1 heroNode2=new HeroNode1(2,"宋江2","及时雨2");
        HeroNode1 heroNode3=new HeroNode1(3,"宋江3","及时雨3");
        HeroNode1 heroNode4=new HeroNode1(4,"宋江4","及时雨4");
        HeroNode1 heroNode5=new HeroNode1(5,"宋江4","及时雨4");
        HeroNode1 heroNode6=new HeroNode1(6,"宋江4","及时雨4");

        DoubleLinked doubleLinked=new DoubleLinked();
        doubleLinked.addLast(heroNode1);
        doubleLinked.addLast(heroNode2);
        doubleLinked.addLast(heroNode3);
        doubleLinked.addLast(heroNode5);

        //遍历
        doubleLinked.showLinkedList();

        System.out.println("指定位置添加元素:");
        //指定位置添加元素
        doubleLinked.addByOrder(heroNode4);
        doubleLinked.addByOrder(heroNode6);
        //遍历
        doubleLinked.showLinkedList();

        //修改
        System.out.println("修改后：");
        HeroNode1 heroNodeUpdate=new HeroNode1(4,"宋江44444444","及时雨4");
        doubleLinked.update(heroNodeUpdate);
        //遍历
        doubleLinked.showLinkedList();

        //删除
        System.out.println("删除后");
        doubleLinked.delete(heroNode2);
        //遍历
        doubleLinked.showLinkedList();


    }
}

//双向链表类
class DoubleLinked{
    //初始化一个头结点，不存储数据
    private HeroNode1 head=new HeroNode1(0,"","");

    //获取头结点
    public HeroNode1 getHead() {
        return head;
    }

    //第一种方法在添加英雄时，直接添加到双向链表的尾部
    //找到链表尾部
    //将尾部节点的next指向新的节点
    public void addLast(HeroNode1 heroNode){
        HeroNode1 temp=head;
        while (true){
            //链表尾部
            if (temp.next==null){
                break;
            }
            temp=temp.next;
        }
        //将尾部节点的next指向新的节点
        temp.next=heroNode;
        heroNode.pre=temp;
    }
    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    public void addByOrder(HeroNode1 heroNode){
        //因为头结点不能动，因此需要一个辅助变量来遍历
        HeroNode1 temp=head;
        HeroNode1 next;
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
//            heroNode.next=temp.next;
//            temp.next=heroNode;
            next=temp.next;
            temp.next=heroNode;
            heroNode.next=next;
            if (next!=null){
                next.pre=heroNode;
            }
            heroNode.pre=temp;
        }
    }

    //修改节点，根据no来修改
    public void update(HeroNode1 heroNode){
        //判断节点是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此需要一个辅助变量来遍历
        HeroNode1 temp=head;
        //表示是否找到该节点
        Boolean flag=false;
        while(true){
            if (temp==null){//整个链表就没这个节点
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
    public void delete(HeroNode1 heroNode){
        //判断节点是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此需要一个辅助变量来遍历
        HeroNode1 temp=head.next;
        //表示是否找到该节点的前一个节点
        Boolean flag=false;
        while(true){
            //找到链表的最后一个元素
            if (temp==null){
                break;
            }
            //找到要删出的元素
            if (temp.no==heroNode.no){
                flag=true;
                break;
            }
            //temp后移，遍历
            temp=temp.next;
        }
        if (flag){//删除节点
            temp.pre.next=temp.next;
            //如果是最后一个节点，不需要执行下面代码
            if (temp.next!=null) {
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("没有找到编号%d的节点\n",heroNode.no);
        }
    }

    //遍历双向链表(和单链表的一样)
    public void showLinkedList(){
        //判断节点是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点不能动，因此需要一个辅助变量来遍历
        HeroNode1 temp=head.next;
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
class HeroNode1 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode1 next;
    public HeroNode1 pre;

    //构造器
    public HeroNode1(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode1{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
//                ", next=" + next +
//                ", pre=" + pre +
                '}';
    }
}