package com.example.datastructures.algorithm;

/**
 * 二分查找算法(非递归)
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr= new int[]{1, 3, 8, 10, 11, 67, 100};
        int i = binarySearch(arr, 011);
        System.out.println(i);

    }
    /**
     *二分查找的非递归实现
     * @param arr 待查找的数组，arr升序排列
     * @param target 需要查找的数
     * @return 返回对应的下标,-1表示没有找到
     */
    public static int binarySearch(int[] arr,int target){
        //左边界
        int left=0;
        //右边界
        int right=arr.length-1;

        while(left<=right){
            //阈值
            int mid=(left+right)/2;
            //找到需要查找的值
            if (arr[mid]==target){
                return mid;
            }
            //查找的值在阈值的左边
            else if (arr[mid]>target){
                right=mid-1;
            }
            //查找的值在阈值的右边
            else if (arr[mid]<target){
                left=mid+1;
            }
        }
        return -1;
    }

}
