package com.example.datastructures.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 **/
public class BinarySerach {
    public static void main(String[] args) {
        //二分查找的数组必须是有序的数组
        int arr[]={1,8, 10, 89, 1000,1000};
//        int i = binarySerach(arr, 0, arr.length - 1, 1000);
//
//        if (i==-1){
//            System.out.println("数组中不存在该元素");
//        }else {
//            System.out.printf("找到元素arr[%d]=%d",i,arr[i]);
//        }

//        List<Integer> list = binarySerach2(arr, 0, arr.length - 1, 1000);
        List<Integer> list = binarySerach3(arr, 0, arr.length - 1, 1000);
        System.out.println(list);
    }
    /**
     *二分查找
     * @param arr 有序数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findValue 要查找的值
     * @return 找到就返回下标，找不到就返回-1
     */
    public static int binarySerach(int arr[],int left,int right,int findValue){
        //整个数组都没找到findValue元素，结束查找
        if (left>right){
            return -1;
        }
        int mid=(left+right)/2;
        //向右递归，找到就返回，不让程序往下走
        if (arr[mid]<findValue){
            return binarySerach(arr, mid + 1, right, findValue);
        }
        //向左递归，找到就返回
        else if (arr[mid]>findValue){
            return  binarySerach(arr,left,mid-1,findValue);
        }
        //直接就找到
        else if (arr[mid]==findValue){
            return mid;
        }
        return -1;
    }
    /**
     *二分查找改进
     * @param arr 有序数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findValue 要查找的值
     * @return 找到就返回下标，找不到就返回-1
     */
    public static List<Integer> binarySerach2(int arr[],int left,int right,int findValue){
        List<Integer> list=new ArrayList<>();

        //整个数组都没找到findValue元素，结束查找
        if (left>right){
            return null;
        }
        int mid=(left+right)/2;
        //向右递归，找到就返回，不让程序往下走
        if (arr[mid]<findValue){
            return binarySerach2(arr, mid + 1, right, findValue);
        }
        //向左递归，找到就返回
        else if (arr[mid]>findValue){
            return binarySerach2(arr, left, mid - 1, findValue);
        }
        //直接就找到
        else if (arr[mid]==findValue){
            //向左递归，继续找有没有相等的元素
            List<Integer> list2 = binarySerach2(arr, left, mid - 1, findValue);
            if (list2!=null) {
                list.addAll(list2);
            }
            list.add(mid);
            //向右递归，继续找有没有相等的元素
            List<Integer> list1 = binarySerach2(arr, mid + 1, right, findValue);
            if (list1!=null){
                list.addAll(list1);
            }
            return list;
        }
        return null;
    }
    /**
     *二分查找改进
     * @param arr 有序数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findValue 要查找的值
     * @return 找到就返回下标，找不到就返回-1
     */
    public static List<Integer> binarySerach3(int arr[],int left,int right,int findValue){
        List<Integer> list=new ArrayList<>();

        //整个数组都没找到findValue元素，结束查找
        if (left>right){
            return null;
        }
        int mid=(left+right)/2;
        //向右递归，找到就返回，不让程序往下走
        if (arr[mid]<findValue){
            return binarySerach3(arr, mid + 1, right, findValue);
        }
        //向左递归，找到就返回
        else if (arr[mid]>findValue){
            return binarySerach3(arr, left, mid - 1, findValue);
        }
        //直接就找到
        else if (arr[mid]==findValue){
            //* 思路分析
//			 * 1. 在找到mid 索引值，不要马上返回
//			 * 2. 向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 3. 向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
//			 * 4. 将Arraylist返回

            List<Integer> resIndexlist = new ArrayList<Integer>();
            //向mid 索引值的左边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            int temp = mid - 1;
            while(true) {
                //因为数组是有序的，arr[temp] != findVal，如果不相等，说明左边已经没有相等的了
                if (temp < 0 || arr[temp] != findValue) {
                    //退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                //temp左移
                temp -= 1;
            }
            resIndexlist.add(mid);

            //向mid 索引值的右边扫描，将所有满足 1000， 的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while(true) {
                //因为数组是有序的，arr[temp] != findVal，如果不相等，说明右边已经没有相等的了
                if (temp > arr.length - 1 || arr[temp] != findValue) {
                    //退出
                    break;
                }
                //否则，就temp 放入到 resIndexlist
                resIndexlist.add(temp);
                //temp右移
                temp += 1;
            }

            return resIndexlist;
        }
        return null;
    }
}
