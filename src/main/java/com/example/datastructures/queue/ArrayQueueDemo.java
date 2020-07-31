package com.example.datastructures.queue;


import com.sun.deploy.uitoolkit.impl.fx.AppletStageManager;

import java.util.Scanner;

/**
 * 数组实现队列
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试一把
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while(loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addData(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.getData();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.showHeader();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}
//使用数组模拟队列 编写一个ArrayQueue类
class  ArrayQueue{
    //表示数组的最大容量
    private int maxSize;
    //队列头
    private int front;
    //队列尾
    private int rear;
    //该数组用于存放数据，模拟队列
    private int[] arr;

    //创建队列的构造器
    public   ArrayQueue(int maxSize){
        this.maxSize=maxSize;
        //指向队列头部，分析出front是指向队列头的前一个位置
        front=-1;
        //指向队列尾，指向队列尾的数据，即队列最后一个数据
        rear=-1;
        arr=new int[maxSize];
    }
    //判断队列是否满了
    public boolean isFull(){
        return rear==maxSize-1;
    }
    //判断队列是否是空的
    public boolean isEmpty(){
        return rear==front;
    }
    //添加元素
    public void addData(int n){
        //是否满了
        if (isFull()){
            System.out.println("队列满了");
            return;
        }
        //rear后移
        rear++;
        arr[rear]=n;
    }
    //取出数据
    public int getData(){
        //是否空
        if (isEmpty()){
            System.out.println("队列为空");
        }
        //front后移
        front++;
        return arr[front];
    }
    //显示队列所有数据
    public void showQueue(){
        //是否空
        if (isEmpty()){
            System.out.println("队列为空");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    //显示队列的头数据
    public int showHeader(){
        //是否空
        if (isEmpty()){
            System.out.println("队列为空");
        }
        return arr[front+1];
    }
}
