package com.example.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName ShellSort
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/8/1 19:48
 * @Version 1.0
 **/
public class ShellSort {
    public static void main(String[] args) {
        //创建要给80000个的随机的数组
        int[] arr = new int[10000000];
        for(int i =0; i < 10000000;i++) {
            arr[i] = (int)(Math.random() * 1000000); //生成一个[0, 8000000) 数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

//        int[] arr={8,9,1,7,2,3,5,4,6,0};
//        System.out.println("排序前："+ Arrays.toString(arr));
        shellSort(arr);//800w 3秒
//        shellSort2(arr);//800w 3秒
//        System.out.println("排序后："+ Arrays.toString(arr));

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是="+date2Str);

//        排序前的时间是=2020-08-02 14:15:01
//        排序后的时间是=2020-08-02 14:15:08
    }
    //交换式
    public  static void shellSort(int[] arr){
        int temp=0;
        for (int z=arr.length/2;z>=1;z=z/2){
            //arr.length个数，分成了z组
            for (int i=z;i<arr.length;i++){
                //遍历各组中的元素，共z组，每组arr.length/z个元素，步长z
                //为啥是j-=z？
                //i=8,z=2;j=i-2=6->6和8元素交换;之后(j-=2)都进不去if(arr[j]>arr[j+2])
                //因为之前的i=2,4,6已经交换过了，因此，只会交换一次，之后的都是空循环，可优化
                for (int j=i-z;j>=0;j-=z){
                    //如果当前元素大于加上步长后的那个元素，交换
                    if (arr[j]>arr[j+z]){
                        temp=arr[j];
                        arr[j]=arr[j+z];
                        arr[j+z]=temp;
                    }
                    else {
                        break;
                    }
                }
            }
        }
//        //推导过程
//        int temp=0;
//
//        //第一轮排序，将10个数分成了5组
//        for (int i=5;i<arr.length;i++){
//            //遍历各组中的元素，共5组，每组两个元素，步长5
//            //为啥是j-=5?
//            //i=5,7,8,9;(j=i-5 j-=5)之后,j<0,这样就限定了内层循环只能进行一次
//            for (int j=i-5;j>=0;j-=5){
//                //如果当前元素大于加上步长后的那个元素，交换
//                if (arr[j]>arr[j+5]){
//                    temp=arr[j];
//                    arr[j]=arr[j+5];
//                    arr[j+5]=temp;
//                }
//            }
//        }
//
//        System.out.println("第一轮排序后："+ Arrays.toString(arr));
//
//        //第二轮排序，将10个数分成了5/2=2组
//        for (int i=2;i<arr.length;i++){
//            //遍历各组中的元素，共2组，每组五个元素，步长2
//            //为啥是j-=2?
//            //i=2,4,6,8;
//            //i=8;j=i-2=6->6和8元素交换;之后(j-=2)都进不去if(arr[j]>arr[j+2])
//            //因为之前的i=2,4,6已经交换过了，因此，只会交换一次，之后的都是空循环，可优化
//            for (int j=i-2;j>=0;j-=2){
//                //如果当前元素大于加上步长后的那个元素，交换
//                if (arr[j]>arr[j+2]){
//                    temp=arr[j];
//                    arr[j]=arr[j+2];
//                    arr[j+2]=temp;
//                }else {
//                    break;
//                }
//            }
//        }
//        System.out.println("第二轮排序后："+ Arrays.toString(arr));
//
//        //第三轮排序，将10个数分成了2/2=1组
//        for (int i=1;i<arr.length;i++){
//            //遍历各组中的元素，共1组，每组10个元素，步长1
//            for (int j=i-1;j>=0;j-=1){
//                //如果当前元素大于加上步长后的那个元素，交换
//                if (arr[j]>arr[j+1]){
//                    temp=arr[j];
//                    arr[j]=arr[j+1];
//                    arr[j+1]=temp;
//                }
//            }
//        }
//        System.out.println("第三轮排序后："+ Arrays.toString(arr));
    }

    //位移式
    public  static void shellSort2(int[] arr){
        for (int z=arr.length/2;z>=1;z=z/2){
            //arr.length个数，分成了z组
            for (int i=z;i<arr.length;i++){
                //待插入的元素
                int insertValue=arr[i];
                //组内的第一个元素的索引
                int inserIndex=i-z;
                //insertValue<arr[inserIndex]还满足这个条件
                //说明没找着位置
                while(inserIndex >=0 && insertValue<arr[inserIndex]){
                    arr[inserIndex+z]=arr[inserIndex];
                    inserIndex-=z;
                }
                //说明找着位置了
                arr[inserIndex+z]=insertValue;
            }
        }
    }

}
