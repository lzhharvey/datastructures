package com.example.datastructures.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 杨辉三角
 *  a[4]=a[1]+a[2]
 *  a[7]=a[3]+a[4]
 *  a[8]=a[4]+a[5]
 *  1 11 121 1331 14641 15101051
 *
 */
public class YHArray {
    public static void main(String[] args) {

        List<Integer> row = getRow(1);
        System.out.println(row);
        List<Integer> row1 = getRow2(2);
        System.out.println(row1);

    }

    /**
     * 返回第k行元素，k从0开始
     * @param rowIndex
     * @return
     */
    public static List<Integer> getRow(int rowIndex) {
        Integer[] integers = new Integer[rowIndex+1];
        integers[0]=1;
        integers[rowIndex]=1;

        if (rowIndex==0 || rowIndex==1){
            List<Integer> ints = Arrays.asList(integers);
            return ints;
        } else{
            //k=2
//            List<Integer> last = getRow(rowIndex - 1);
//            integers.add(1,last.get(0)+last.get(1));
//            k=3
//            List<Integer> last = getRow(rowIndex - 1);
//            integers.add(1,last.get(0)+last.get(1));
//            integers.add(2,last.get(1)+last.get(2));
//            //k=4
//            List<Integer> last = getRow(rowIndex - 1);
//            integers.add(1,last.get(0)+last.get(1));
//            integers.add(2,last.get(1)+last.get(2));
//            integers.add(3,last.get(2)+last.get(3));

            List<Integer> last = getRow(rowIndex - 1);
            for (int i = 0; i < rowIndex - 1; i++) {
                integers[i+1]=last.get(i)+last.get(i+1);
            }
        }
        List<Integer> ints = Arrays.asList(integers);
        return ints;
    }
    /**
     * 返回第k行元素，k从0开始
     * @param rowIndex
     * @return
     */
    public static List<Integer> getRow2(int rowIndex) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(0,1);

        if (rowIndex==0 || rowIndex==1){
            integers.add(1);
            return integers;
        } else{
            //k=2
//            List<Integer> last = getRow(rowIndex - 1);
//            integers.add(1,last.get(0)+last.get(1));
//            k=3
//            List<Integer> last = getRow(rowIndex - 1);
//            integers.add(1,last.get(0)+last.get(1));
//            integers.add(2,last.get(1)+last.get(2));
//            //k=4
//            List<Integer> last = getRow(rowIndex - 1);
//            integers.add(1,last.get(0)+last.get(1));
//            integers.add(2,last.get(1)+last.get(2));
//            integers.add(3,last.get(2)+last.get(3));

            List<Integer> last = getRow(rowIndex - 1);
            for (int i = 0; i < rowIndex - 1; i++) {
                integers.add(i+1,last.get(i)+last.get(i+1));
            }
            integers.add(1);
        }
        return integers;
    }
}
