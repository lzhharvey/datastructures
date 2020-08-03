package com.example.datastructures.sort;

import java.util.Arrays;

/**
 * 自己练习
 */
public class MergetSort2 {
    public static void main(String[] args) {
        int arr[] = { 8, 4, 5, 7, 1, 3, 6, 2 };
        int temp[] = new int[arr.length]; //归并排序需要一个额外空间
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("排序后："+ Arrays.toString(arr));
    }
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left<right){
            int mid=(left+right)/2;

            //向左递归
            mergeSort(arr,left,mid,temp);
            //向右递归
            mergeSort(arr,mid+1,right,temp);

            //合并排序
            merge(arr,left,mid,right,temp);

        }


    }
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //初始化i, 左边有序序列的初始索引
        int i=left;
        int j=mid+1;
        int t=0;

        //一
        while(i<=mid && j<=right){
            if (arr[i]<=arr[j]){
                temp[t]=arr[i];
                i++;
                t++;
            }else {
                temp[t]=arr[j];
                j++;
                t++;
            }
        }
        //二
        while(i<=mid){
            temp[t]=arr[i];
            t++;
            i++;
        }
        while(j<=right){
            temp[t]=arr[j];
            j++;
            t++;
        }
        //三
        t=0;
        int tempLeft=left;
        while(tempLeft<=right){
            arr[tempLeft]=temp[t];
            tempLeft++;
            t++;
        }


    }

}
