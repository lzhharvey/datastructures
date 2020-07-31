package com.example.datastructures.stack;

import java.util.Scanner;

/**
 * @ClassName ArrayStackDemo
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/7/31 13:59
 * @Version 1.0
 **/
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试一下ArrayStack 是否正确
        //先创建一个ArrayStack对象->表示栈
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while(loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.showStack();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~~");
    }
}
//数组模拟栈
class ArrayStack{
    //栈顶
    private int top=-1;
    //栈大小
    private int maxsize;
    //数组模拟栈
    private int[] stack;
    //构造器
    public ArrayStack(int maxsize){
        this.maxsize=maxsize;
        stack=new int[maxsize];
    }
    //栈满
    public boolean isFull(){
        return top==maxsize-1;
    }
    //栈空
    public boolean isEmpty(){
        return top==-1;
    }
    //入栈
    public void push(int value){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=value;
    }
    //出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //遍历栈
    public void showStack(){
        if (isEmpty()){
            System.out.println("栈空");
        }
        for (int i=top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}