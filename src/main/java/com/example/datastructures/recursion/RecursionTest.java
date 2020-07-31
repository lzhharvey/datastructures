package com.example.datastructures.recursion;

/**
 * @ClassName RecursionTest
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/7/31 22:41
 * @Version 1.0
 **/
public class RecursionTest {

    public static void main(String[] args) {
//        test(4);
        System.out.println(factorial(3));
    }

    //打印问题
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n=" + n);
    }
    //阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n; // 1 * 2 * 3
        }
    }
}
