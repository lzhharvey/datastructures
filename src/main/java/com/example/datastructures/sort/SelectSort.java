package com.example.datastructures.sort;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {
        //创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for(int i =0; i < 80000;i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
//        int[] arr={101, 34, 119, 1};
        selectSort(arr);
//        System.out.println(Arrays.toString(arr));
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是="+date2Str);
    }
    //选择排序
    public static void selectSort(int[] arr){
        for (int i=0;i<arr.length-1;i++){//一共要进行3轮排序
            //假定每轮排序的最小值的索引为i
            int minIndex=i;
            //那么最小值为arr[i]
            int min=arr[i];
            //找到每轮排序中实际的最小值
            for (int j=i+1;j<arr.length;j++){//从i+1开始，是因为排除了假定最小值的那个
                if (arr[j]<min){
                    //重置最小值
                    min=arr[j];
                    //重置最小值索引
                    minIndex=j;
                }
            }
            //找到最小值后，需要和arr[0]交换
            //如果最小值和要交换的值就是同一个值，就不交换了
            if (minIndex!=i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        /*
		//使用逐步推导的方式来，讲解选择排序
		//第1轮
		//原始的数组 ： 	101, 34, 119, 1
		//第一轮排序 :   	1, 34, 119, 101
		//算法 先简单--》 做复杂， 就是可以把一个复杂的算法，拆分成简单的问题-》逐步解决

		//第1轮
		int minIndex = 0;
		int min = arr[0];
		for(int j = 0 + 1; j < arr.length; j++) {
			if (min > arr[j]) { //说明假定的最小值，并不是最小
				min = arr[j]; //重置min
				minIndex = j; //重置minIndex
			}
		}
		//将最小值，放在arr[0], 即交换
		if(minIndex != 0) {
			arr[minIndex] = arr[0];
			arr[0] = min;
		}
		System.out.println("第1轮后~~");
		System.out.println(Arrays.toString(arr));// 1, 34, 119, 101

		//第2轮
		minIndex = 1;
		min = arr[1];
		for (int j = 1 + 1; j < arr.length; j++) {
			if (min > arr[j]) { // 说明假定的最小值，并不是最小
				min = arr[j]; // 重置min
				minIndex = j; // 重置minIndex
			}
		}
		// 将最小值，放在arr[0], 即交换
		if(minIndex != 1) {
			arr[minIndex] = arr[1];
			arr[1] = min;
		}
		System.out.println("第2轮后~~");
		System.out.println(Arrays.toString(arr));// 1, 34, 119, 101

		//第3轮
		minIndex = 2;
		min = arr[2];
		for (int j = 2 + 1; j < arr.length; j++) {
			if (min > arr[j]) { // 说明假定的最小值，并不是最小
				min = arr[j]; // 重置min
				minIndex = j; // 重置minIndex
			}
		}
		// 将最小值，放在arr[0], 即交换
		if (minIndex != 2) {
			arr[minIndex] = arr[2];
			arr[2] = min;
		}
		System.out.println("第3轮后~~");
		System.out.println(Arrays.toString(arr));// 1, 34, 101, 119
		*/
    }
}
