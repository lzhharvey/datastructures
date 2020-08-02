package com.example.datastructures.sort;

import java.util.Arrays;

/**
 * 快排，以左边为基准
 */
public class QuickSort2 {
    public static void main(String[] args) {
        int[] arr={-9,78,0,23,-567,70};
        sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int arr[],int left,int right){
        //当只剩下两个元素的时候，一个为轴值，无论是左边递归还是右边递归
        //都是left>right
        if(left>right){
            return;
        }
        //左下标
        int l=left;
        //右下标
        int r=right;
        //轴值
        int pivot=arr[left];
        //辅助变量
        int temp;

        //当l与r还没碰头
        while (l<r){
            //判断右下标遇到的数是否小于轴值
            //大于轴值，r前移
            //小于轴值，跳出循环，准备交换
            //l不能大于r，顶多重合
            while(arr[r]>=pivot && l<r){
                //右下标遇到的数大于轴值，右下标前移
                r--;
            }
            //判断左下标遇到的数是否大于轴值
            //小于轴值，l前移
            //大于轴值，跳出循环，准备交换
            while(arr[l]<=pivot && l<r)
            {
                //左下标遇到的数小于轴值，左下标前移
                l++;
            }
            if (l<r){
                //交换
                temp = arr[r];
                arr[r] = arr[l];
                arr[l] = temp;
            }
        }
        //说明l与r碰头了，轴值和l交换
        arr[left]=arr[l];
        arr[l]=pivot;

        //轴值左边递归
        sort(arr,left,l-1);
        //轴值右边递归
        sort(arr,r+1,right);
    }
}
