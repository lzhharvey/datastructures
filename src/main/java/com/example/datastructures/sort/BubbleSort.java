package com.example.datastructures.sort;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName BubbleSort
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/8/1 14:27
 * @Version 1.0
 **/
public class BubbleSort {
    public static void main(String[] args) {
        //测试一下冒泡排序的速度O(n^2), 给80000个数据，测试
        //创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for(int i =0; i < 80000;i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

//        int arr[]={3, 9, -1, 10, -2};
//        System.out.println("排序前的数组：");
//        System.out.println(Arrays.toString(arr));
        int[] ints = bubbleSort(arr);
//        System.out.println("排序后的数组：");
//        System.out.println(Arrays.toString(ints));

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println(date2Str);
    }
    public static int[] bubbleSort(int[] arr){
        //辅助变量，用于值的交换
        int temp=0;
        //标识变量，表示是否进行过交换
        boolean flag=false;
        //一共要进行(arr.length-1)趟排序，每趟排序都把此次排序的最大值找出来，然后放到对应的位置
        //比如：第一趟排序，找出最大值，就把最大值放到倒数第一的位置
        //第二趟排序，找出此次排序的最大值，就把最大值放到倒数第二的位置，以此类推
        for (int j=0;j<arr.length-1;j++){
            for (int i=0;i<arr.length-1-j;i++){//每趟排序要比较的次数，每经过一趟排序，比较的次数就减一
                if(arr[i]>arr[i+1]){
                    flag=true;
                    temp=arr[i];
                    arr[i]=arr[i+1];
                    arr[i+1]=temp;
                }
            }
            if (flag==true){
                flag=false;
            }else {
                //表明在某一趟排序时，已经整体有序了，不需要在继续排序了
                break;
            }
        }
        return arr;
    }
}
