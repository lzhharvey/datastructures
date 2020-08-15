package com.example.datastructures.leetcode;
/**
 * 判断一个数组是不是有效的山脉数组
 */
public class MountainArray {
    public static void main(String[] args) {
//        int[] mountainArray=new int[]{2,1};
//        int[] mountainArray=new int[]{3,5,5};
//        int[] mountainArray=new int[]{2,0,2};
//        int[] mountainArray=new int[]{0,3,2,1};
//        int[] mountainArray=new int[]{0,1,2,3,4,5,6,7,8,9};
        int[] mountainArray=new int[]{9,8,7,6,5,4,3,2,1};

        boolean mountainArray1 = getMountainArray(mountainArray);
        System.out.println(mountainArray1);
    }
    /**
     * 获取数组的最大值，对应的下标
     * @param array
     * @return
     */
    public static int getMax(int[] array){
        int max=array[0];
        int index=0;
        //获取集合中的最大值
        for (int i = 0; i < array.length; i++) {
            if (max<array[i]){
                max=array[i];
                index=i;
            }
        }
        return index;
    }

    /**
     * 原始想法
     * @param array
     * @return
     */
    public static boolean  getMountainArray(int[] array){
        if (array.length<3){
            return false;
        }
        //获取数组中的最大值对应的下标
        int index = getMax(array);
        //如果最大值的小标就等于数组长度-1 或者最大值就是第一个元素
        if (index==array.length-1 || index==0){
            return false;
        }
        //当i小于max的index
        for (int i = 0; i < index; i++) {
            if (i<=index-1 && array[i]<array[i+1]){
                continue;
            }else if (i<=index-1 && array[i]>=array[i+1]){
                return false;
            }
        }
        //当i大于max的index
        for (int i = index; i < array.length; i++) {
            if (i<array.length-1 && array[i]>array[i+1]){
                continue;
            }else if (i<array.length-1 && array[i]<=array[i+1]){
                return false;
            }
        }
        return true;
    }
    /**
     * 优化
     */
    public static boolean getMountainArray2(int[] A ){
        int length=A.length;
        int i=0;
        //当前连续递减时，找到最大值的下标
        while(i<length-1 && A[i]<A[i+1]){
            i++;
        }
        //但是最大值不能是第一个或者最后一个，即不能是单调递增或者单调递减的
        if (i==0 || i==length-1)
        {
            return false;
        }
        //从最大值往后应该都是递减的
        while(i<length-1 && A[i]>A[i+1]){
            i++;
        }
        return true;
    }

}
