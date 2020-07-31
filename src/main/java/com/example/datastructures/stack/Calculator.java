package com.example.datastructures.stack;

/**
 * @ClassName Calculator
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/7/31 15:04
 * @Version 1.0
 **/
public class Calculator {

    public static void main(String[] args) {
        String expression="70+2*6-4-66";
        //创建两个栈，一个数栈，一个符号栈
        ArrayStack2 numStack2 = new ArrayStack2(10);
        ArrayStack2 operStack2 = new ArrayStack2(10);
        //定义需要的相关变量
        int index=0;
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;
        char ch=' ';//将每次扫描得到char保存到ch
        String keepNum = ""; //用于拼接 多位数

        //开始while循环的扫描expression
        while(true){
            //依次得到表达式的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是数字还是字符
            if(operStack2.isOper(ch)){//是字符
                //判断字符栈是否为空
                if(!operStack2.isEmpty()){
                    //如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符， 就需要从数栈中pop出两个数,
                    //在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if (operStack2.priority(ch)<=operStack2.priority(operStack2.peek())){
                        num1=numStack2.pop();
                        num2=numStack2.pop();
                        oper=operStack2.pop();
                        res=numStack2.cal(num1,num2,oper);
                        //把运算结果入数栈
                        numStack2.push(res);
                        //然后把当前的操作符入符号栈
                        operStack2.push(ch);
                    }else {//如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operStack2.push(ch);
                    }
                }else {
                    //如果为空直接入符号栈
                    operStack2.push(ch);
                }
            }else{//如果是数字
                //numStack.push(ch - 48); //? "1+3" '1' => 1
                //分析思路
                //1. 当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2. 在处理数，需要向expression的表达式的index 后再看一位,如果是数就进行扫描，如果是符号才入栈
                //3. 因此我们需要定义一个变量 字符串，用于拼接

                //处理多位数
                keepNum += ch;

                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack2.push(Integer.parseInt(keepNum));
                }else{

                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    //注意是看后一位，不是index++
                    if (operStack2.isOper(expression.substring(index+1,index+2).charAt(0))) {
                        //如果后一位是运算符，则入栈 keepNum = "1" 或者 "123"
                        numStack2.push(Integer.parseInt(keepNum));
                        //重要的!!!!!!, keepNum清空
                        keepNum = "";

                    }
                }
            }
            //让index+1 并判断是否扫描到expression最后
            index++;
            if (index>=expression.length()){
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop相应的数和符号,并运行
        while(true){
            //如果符号栈为空,则计算到最后的结果，数栈中就只有一个数子
            if(operStack2.isEmpty()){
                break;
            }
            num1=numStack2.pop();
            num2=numStack2.pop();
            oper=operStack2.pop();
            res=numStack2.cal(num1,num2,oper);
            numStack2.push(res);//入栈
        }
        //数栈的最后数，pop出,就是结果
        System.out.printf("表达式%s=%d",expression,numStack2.pop());
    }
}

//数组模拟栈
class ArrayStack2{
    //栈顶
    private int top=-1;
    //栈大小
    private int maxsize;
    //数组模拟栈
    private int[] stack;
    //构造器
    public ArrayStack2(int maxsize){
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
    //查看栈顶的值
    public int peek(){
        return stack[top];
    }
    //返回运算符的优先级，优先级使用数组表示
    //数字越大，优先级越高
    public int priority(int oper){
        if (oper=='*' || oper=='/'){
            return 1;
        }else if (oper=='+' || oper=='-'){
            return 0;
        }else {
            return -1;
        }
    }
    //判断是不是一个运算符
    public boolean isOper(char val){
        return val=='+' ||  val=='-' || val=='*' ||  val=='/';
    }
    //计算方法
    public int cal(int num1,int num2,int oper){
        int res=0;//用于存放计算结果
        switch(oper){
            case '+':
                res=num1+num2;
                break;
            case '-':
                res=num2-num1;
                break;
            case '*':
                res=num1*num2;
                break;
            case '/':
                res=num2/num1;
                break;
            default:
                break;
        }
        return res;
    }
}