package com.example.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 9 7 10 8
 * 8 7 10 9
 * 8 7 9 10
 *
 * @ClassName QuickSort
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/8/2 15:44
 * @Version 1.0
 **/
public class QuickSort {
    public static void main(String[] args) {
        //创建要给80000个的随机的数组

        int[] arr = new int[8000000];
        for(int i =0; i < 8000000;i++) {
            arr[i] = (int)(Math.random() * 1000000); //生成一个[0, 8000000) 数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

//        int[] arr={-9,78,0,23,-567,70};
        quickSort(arr,0,arr.length-1);
//        System.out.println("第一轮排序："+ Arrays.toString(arr));

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是="+date2Str);
//        排序前的时间是=2020-08-02 18:09:06
//        排序后的时间是=2020-08-02 18:09:07
    }
    //第一轮排序：{-9,78,0,23,-567,70} 把小于0的都放到0的左边，大于0的都放到0的右边
    public static void quickSort(int[] arr,int left,int right){
        //左下标
        int l = left;
        //右下标
        int r = right;
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        //临时变量，作为交换时使用
        int temp = 0;
        //while循环的目的是让比pivot 值小放到左边
        //比pivot 值大放到右边
        while( l < r) {
            //在pivot的左边一直找,找到大于等于pivot值,才退出
            while( arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找,找到小于等于pivot值,才退出
            while(arr[r] > pivot) {
                r -= 1;
            }
            //如果l >= r说明pivot 的左右两的值，已经按照左边全部是
            //小于等于pivot值，右边全部是大于等于pivot值
            if( l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot值 相等r--， 前移
            //因为arr[l]不小于privot,所以l不能前移，只能原地不动
            //arr[r]交换后必然大于privot，会不断往前移动，如果在不断的前移过程中，arr[r]始终大于pivot，
            //那么，迟早r移动到轴值处，arr[r] == pivot，此时l<r,不能退出循环，进入死循环
            //所以需要下面的代码，让arr[l] == pivot时，r继续前移，直到l==r,然后退出循环
            if(arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值 相等 l++， 后移
            if(arr[r] == pivot) {
                l += 1;
            }
        }
        if (l==r){
            l++;
            r--;
        }
        //向左递归
        if (left<r){
            quickSort(arr,left,r);
        }
        //向右递归
        if (l<right){
            quickSort(arr,l,right);
        }
    }
}
