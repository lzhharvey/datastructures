package com.example.datastructures.hashtable;

import java.util.Scanner;

/**
 * @ClassName HashTableDemo
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/8/3 22:25
 * @Version 1.0
 **/
public class HashTableDemo {
    public static void main(String[] args) {
        //创建一个HashTab
        HashTab hashTab = new HashTab(7);
        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("delete: 删除雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    Emp emp1= hashTab.findEmpById(id);
                    System.out.println("雇员信息："+emp1.id+" "+emp1.name);
                    break;
                case "delete":
                    System.out.println("请输入要删出的id");
                    id = scanner.nextInt();
                    hashTab.deleteEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }
}
//创建HashTable
class HashTab{
    //链表数组
    private EmpLinkedList[] empLinkedLists;
    private int size;

    //构造器
    public HashTab(int size){
        this.size=size;
        empLinkedLists=new EmpLinkedList[size];
        //初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedLists[i]=new EmpLinkedList();
        }
    }
    //添加雇员
    public void add(Emp emp){
        //根据员工id，确定员工应该放到哪条链表中
        int empLinkedListNO = hashFun(emp.id);
        empLinkedLists[empLinkedListNO].addLast(emp);
    }
    //遍历所有的链表
    public void list(){
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i].list(i);
        }
    }
    //根据输入的id，查询雇员
    public Emp findEmpById(int id){
        //根据员工id，确定员工应该从哪条链表中找
        int empLinkedListNO = hashFun(id);
        Emp empById = empLinkedLists[empLinkedListNO].findEmpById(id);
        return empById;
    }
    //根据输入的id，删出雇员
    public void  deleteEmpById(int id){
        //根据员工id，确定员工应该从哪条链表中找
        int empLinkedListNO = hashFun(id);
        empLinkedLists[empLinkedListNO].deleteEmp(id);
    }

    //编写散列函数，使用简单的取模法
    public int hashFun(int id){
        return id%size;
    }
}
//表示雇员
class Emp{
    public int id;
    public String name;
    //默认是null
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
//创建EmpLinkedList,表示链表
class EmpLinkedList{
    //头指针，指向第一个Emp,默认是null
    private Emp head;
    //添加雇员到链表
    //假定，当添加雇员时，id是自增，即id总是从小到大
    //因此，雇员直接加入到本链表的最后即可
    public void addLast(Emp emp){
        if(head==null){
            head=emp;
            return;
        }
        //如果不是第一个雇员，则使用辅助指针，帮助定位到最后
        Emp curEmp=head;
        while(true){
            if (curEmp.next==null){
                break;
            }
            curEmp=curEmp.next;
        }
        //emp加入到链表的最后
        curEmp.next=emp;
    }
    //遍历链表的雇员信息
    public void list(int no){
        //说明链表为空
        if(head==null){
            System.out.println("第"+no+"链表为空");
            return;
        }
        System.out.print("第"+no+"链表:");
        //辅助指针
        Emp temp=head;
        while(true){
            System.out.print(" =>id:"+temp.id+" name:"+temp.name);
            //temp到最后节点
            if (temp.next==null){
                break;
            }
            //后移，遍历
            temp=temp.next;
        }
        System.out.println();
    }

    //根据id查找雇员
    public Emp findEmpById(int id){
        //说明链表为空
        if(head==null){
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp=head;
        while (true){
            //找到雇员
            if (curEmp.id==id){
                break;
            }
            //找不到雇员，从这跳出循环
            if(curEmp.next==null){
                curEmp=null;
                break;
            }
            //后移
            curEmp=curEmp.next;
        }
        return curEmp;
    }
    //删出链表中的元素
    public void deleteEmp(int id){
        //说明链表为空
        if(head==null){
            System.out.println("链表为空");
            return ;
        }
        //辅助指针
        Emp curEmp=head;

        while(true){
            //如果头结点就是要删出的元素
            if (head.id==id){
                //删出头结点
                head=curEmp.next;
                break;
            }
            //非头结点为要删出的元素
            //获取要删出的元素的前一个元素
            if (curEmp.next.id==id){
                //删出元素
                curEmp.next=curEmp.next.next;
                break;
            }
            //没有要删出的元素
            if (curEmp.next==null){
                System.out.println("要删出的元素%d"+id+"不存在");
                break;
            }
            //指针后移
            curEmp=curEmp.next;
        }
    }
}