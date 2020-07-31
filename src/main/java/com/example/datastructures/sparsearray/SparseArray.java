package com.example.datastructures.sparsearray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName SparseArray
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/7/29 21:18
 * @Version 1.0
 **/
public class SparseArray {
    //构造二维数组
    private static int[][]  createArray(){
        //1.创建一个原始的二维数组 11*11
        //0表示没有棋子 1表示黑子 2表示蓝子
        int[][] ints = new int[11][11];
        ints[1][2]=1;
        ints[2][3]=2;
        ints[9][9]=2;
        return ints;
    }
    //根据二维数组构造稀疏数组
    private static int[][] createSparseArray(int[][] array){
        //将二维数组转成稀疏数组
        //1.先遍历数组得到非零数据的个数
        int num=0;
        for (int[] anInt : array) {
            for (int i : anInt) {
                if(i!=0){
                    num++;
                }
            }
        }
        System.out.println("数组非零个数："+num);

        //2.创建稀疏数组
        int[][] sparseArray = new int[num + 1][3];
        //给稀疏数组赋值
        sparseArray[0][0]=11;
        sparseArray[0][1]=11;
        sparseArray[0][2]=num;
        //用于记录第几个非零数据
        int count=0;
        for (int i=0;i<array.length;i++){
            for (int j=0;j<array[0].length;j++){
                if (array[i][j]!=0){
                    count++;
                    sparseArray[count][0]=i;
                    sparseArray[count][1]=j;
                    sparseArray[count][2]=array[i][j];
                }
            }
        }
        return sparseArray;
    }

    //将稀疏数组转回原二维数组
    private static int[][] tranSparse(int[][] sparseArray){
        //构造原二维数组
        int[][] ints = new int[sparseArray[0][0]][sparseArray[0][1]];
        //往原二维数组添加数据
        //遍历稀疏数组
        for (int i = 1; i < sparseArray.length; i++) {
                ints[sparseArray[i][0]][sparseArray[i][1]]=sparseArray[i][2];
        }
        return ints;
    }


    public static void main(String[] args) {
        //构造二维数组
        int[][] array = createArray();
        //遍历数组
        System.out.println("原始的二维数组：");
        for (int[] anInt : array) {
            for (int i : anInt) {
                //\t相当于输出一个Tab的作用。
                //%d和%f分别用来表示输出时，替换整型输出和浮点型输出的占位符。
                //%n是换行的格式字符串,只能用在print输出语句中
                //\n是回车字符, 可以用在所有的字符串中.
                System.out.printf("%d\t",i);
            }
            System.out.println();
        }
        //构造稀疏数组
        int[][] sparseArray = createSparseArray(array);
        //遍历稀疏数组
        System.out.println();
        System.out.println("稀疏数组：");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
        }



        //将稀疏数组转回二维数组
        int[][] ints = tranSparse(sparseArray);
        System.out.println();
        System.out.println("恢复后的二维数组：");
        for (int[] anInt : ints) {
            for (int i : anInt) {
                //\t相当于输出一个Tab的作用。
                //%d和%f分别用来表示输出时，替换整型输出和浮点型输出的占位符。
                //%n是换行的格式字符串,只能用在print输出语句中
                //\n是回车字符, 可以用在所有的字符串中.
                System.out.printf("%d\t",i);
            }
            System.out.println();
        }
    }
}
