package com.example.datastructures.search;

import java.util.Arrays;

/**
 * @ClassName FibonacciSearch2
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/8/3 19:26
 * @Version 1.0
 **/
public class FibonacciSearch2 {
    public static int maxSize=20;
    public static void main(String[] args) {
        int [] arr = {1,8, 10, 89, 1000, 1234};

        System.out.println("index=" + fibSearch(arr, 1234));// 0
    }
    //斐波那契数列
    public static int[] fib(){
        int arr[]=new int[maxSize];
        arr[0]=1;
        arr[1]=1;
        for (int i=2;i<arr.length;i++){
            arr[i]=arr[i-1]+arr[i-2];
        }
        return arr;
    }
    /**
     *编写斐波那契查找算法
     *由斐波那契数列 `F[k]=F[k-1]+F[k-2]` 的性质，可以得到 `（F[k]-1）=（F[k-1]-1）+（F[k-2]-1）+1`
     *只要顺序表的长度为`F[k]-1`，则可以将该表分成长度为`F[k-1]-1`和`F[k-2]-1`的两段，中间位置为`mid=low+F(k-1)-1`
     * 使用非递归的方式编写算法
     * @param a  数组
     * @param key 我们需要查找的关键码(值)
     * @return 返回对应的下标，如果没有-1
     */
    public static int fibSearch(int[] a, int key) {
        int low=0;
        int high=a.length-1;
        //表示斐波那契分割数值的下标
        int k = 0;
        //存放mid值
        int mid = 0;
        //获取到斐波那契数列
        int f[] = fib();

        //1.数组长度不一定大于f[k]-1,黄金分割点
        while (high>f[k]-1){
            k++;
        }
        //2.因为f[k]的值可能大于a数组的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        //不足的部分会使用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        //3.实际上需求使用a数组最后的数填充 temp
        //举例:
        //temp = {1,8, 10, 89, 1000, 1234, 0, 0}  => {1,8, 10, 89, 1000, 1234, 1234, 1234,}
        for (int i=high+1;i<temp.length;i++){
            temp[i]=a[high];
        }
        //4.使用while来循环处理，找到我们的数key
        while(low<=high){
            mid=low+f[k-1]-1;

            if (key<temp[mid]){
                high=mid-1;
                k--;
            }
            if (key>temp[mid]){
                low=mid+1;
                k-=2;
            }else { //找到
                //需要确定，返回的是哪个下标
                if(mid <= high) {
                    return mid;
                } else {
                    //high是数组arr的最后一个元素的坐标
                    //如果找到的元素的坐标大于high,是因为temp的长度大于原数组
                    //但是大于的部分都是和原数组的最后一个值，是一样的
                    return high;
                }
            }
        }
        return -1;
    }

}
