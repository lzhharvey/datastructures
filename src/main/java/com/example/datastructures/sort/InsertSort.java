package com.example.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName InsertSort
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/8/1 16:45
 * @Version 1.0
 **/
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for(int i =0; i < 80000;i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
        }
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
        insertSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);

//        System.out.println(Arrays.toString(arr));

    }

    //插入排序
    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            //待插入的数
            int insertValue=arr[i];
            //待插入的数arr[i]的前面这个数的下标
            int insertIndex=i-1;
            //1.insertIndex >= 0 保证在给insertVal 找插入位置，不越界
            //2.insertVal < arr[insertIndex] 待插入的数小于前面的数
            //待插入的数，还没有找到插入位置
            while(insertIndex>=0 && insertValue<arr[insertIndex]){
                //因为待插入的数的前面这个数大于待插入的数
                //所以这个数，要往后移动
                //为什么要后移？因为要腾空间，待插入的数迟早会找到一个比它小的值，比它大的值都
                //往后移动了一位，那相当于比它大的数和比它小的数之间腾出了一个空位给它了
                arr[insertIndex+1]=arr[insertIndex];
                //待插入的数的前面这个数的下标，往前移动
                insertIndex--;
            }
            //出了while循环，说明待插入的数，大于前面的数，找到位置了
            //小于它的数的后一个位置给待插入的数
            arr[insertIndex+1]=insertValue;
        }

        //推导过程
        //第一轮：{101, 34, 99, 1}->{34, 101, 99, 1}
        //定义待插入的数
//        int insertValue=arr[1];
//        //arr[1]的前面这个数的下标
//        int insertIndex=1-1;
//        //1.insertIndex >= 0 保证在给insertVal 找插入位置，不越界
//        //2.insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
//        while(insertIndex>=0 && insertValue<arr[insertIndex]  ){
//            arr[insertIndex+1]=arr[insertIndex];
//            //往前移动
//            insertIndex--;
//        }
//        arr[insertIndex+1]=insertValue;
//        System.out.println("第一轮插入：");
//        System.out.println(Arrays.toString(arr));
//
//        //第二轮：{34, 101, 99, 1}->{34, 99, 101, 1}
//        //定义待插入的数
//        insertValue=arr[2];
//        //arr[2]的前面这个数的下标
//        insertIndex=2-1;
//        //1.insertIndex >= 0 保证在给insertVal 找插入位置，不越界
//        //2.insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
//        while(insertIndex>=0 && insertValue<arr[insertIndex]  ){
//            arr[insertIndex+1]=arr[insertIndex];
//            insertIndex--;
//        }
//        arr[insertIndex+1]=insertValue;
//        System.out.println("第二轮插入：");
//        System.out.println(Arrays.toString(arr));
//
//        //第三轮：{34, 99, 101, 1}->{1，34, 99, 101}
//        //定义待插入的数,arr[3]=1
//        insertValue=arr[3];
//        //arr[3]的前面这个数的下标
//        insertIndex=3-1;
//        //insertValue，把arr[3]=1先存起来
//        //先是{34, 99, 101, 1}->{34, 99, 101, 101}
//        //然后{34, 99, 101, 101}->{34, 99, 99, 101}
//        //最后{34, 99, 99, 101}->{34, 34, 99, 101}
//        //1.insertIndex >= 0 保证在给insertVal 找插入位置，不越界
//        //2.insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
//        while(insertIndex>=0 && insertValue<arr[insertIndex]){
//            arr[insertIndex+1]=arr[insertIndex];
//            insertIndex--;
//        }
//        //{34, 34, 99, 101}->{1, 34, 99, 101}
//        arr[insertIndex+1]=insertValue;
//        System.out.println("第三轮插入：");
//        System.out.println(Arrays.toString(arr));
    }
}
