package com.example.datastructures.search;

/**
 * 线性查找
 */
public class SeqSearch {
    public static void main(String[] args) {
        int arr[]={1,9,11,-1,34,89};
        int i = seqSearch(arr, 11);
        System.out.println(i);
    }

    public static int seqSearch(int[]arr,int value){
        //线性查找就是逐一对比，发现有相同值，就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==value){
                return i;
            }
        }
        return -1;
    }

}
