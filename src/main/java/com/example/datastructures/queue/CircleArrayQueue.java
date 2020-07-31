package com.example.datastructures.queue;

import java.util.Scanner;

/**
 * 循环数组
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        //测试一把
        //创建一个队列
        CircleQueue queue = new CircleQueue(4);
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
                        int res = queue.getQueue();
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
class  CircleQueue{
    //表示数组的最大容量
    private int maxSize;
    //队列头 初始值0
    private int front;
    //队列尾 初始值0
    private int rear;
    //该数组用于存放数据，模拟队列
    private int[] arr;

    //创建队列的构造器
    public   CircleQueue(int maxSize){
        this.maxSize=maxSize;
        //指向队列头部，分析出front是指向队列头的前一个位置
        front=0;
        //指向队列尾，指向队列尾的数据，即队列最后一个数据
        rear=0;
        arr=new int[maxSize];
    }
    //判断队列是否满了
    public boolean isFull(){
        return (rear+1)%maxSize==front;
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
        arr[rear]=n;
        //rear后移 rear++会出现数组越界
        rear=(rear+1)%maxSize;
    }
    // 获取队列的数据, 出队列
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        // 这里需要分析出 front是指向队列的第一个元素
        // 1. 先把 front 对应的值保留到一个临时变量
        // 2. 将 front 后移, 考虑取模
        // 3. 将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;

    }
    //显示队列所有数据
    public void showQueue(){
        //是否空
        if (isEmpty()){
            System.out.println("队列为空");
        }
        //条件front+size()，从front开始遍历，遍历size()个元素
        for (int i = front; i < front+size(); i++) {
            //i%maxSize防止数组越界
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }
    //求出当前队列有效元素的个数
    public  int size(){
        return (rear+maxSize-front)%maxSize;
    }
    //显示队列的头数据
    public int showHeader(){
        //是否空
        if (isEmpty()){
            System.out.println("队列为空");
        }
        return arr[front];
    }
}