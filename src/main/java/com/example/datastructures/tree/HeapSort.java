package com.example.datastructures.tree;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
        int arr[]={4,6,8,5,9};

        heapSort(arr);
    }
    //编写一个堆排序方法
    public static void heapSort(int arr[]){
        //第一次调整
//        adjustHeap(arr,1,arr.length);
//        System.out.println(Arrays.toString(arr));
//        //第二次调整
//        adjustHeap(arr,0,arr.length);
//        System.out.println(Arrays.toString(arr)); //[9, 6, 8, 5, 4]

        //1.将无序数组构建成一个大顶堆
        //arr.length/2-1非叶子节点的数量
        for (int i=arr.length/2-1;i>=0;i--){
            //每一次循环都是以一个非叶子节点为根节点，进行调整
            adjustHeap(arr,i,arr.length);
        }
        System.out.println("大顶堆："+Arrays.toString(arr));

        int temp=0;
        //2.将堆顶元素与末尾元素交换，将最大元素沉到数组末端
        //3.重新调整结构，时期满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
        for (int i=arr.length-1;i>=0;i--){
            //交换
            temp=arr[i];
            arr[i]=arr[0];
            arr[0]=temp;
            //交换一次元素就要调整一次树结构
            //这里的length不是arr.length,是i,因为后面的元素已经是排好序的
            //要排序的是前面的元素
            adjustHeap(arr,0,i);
        }
        System.out.println("堆排序："+Arrays.toString(arr));
    }
    /**
     * 将一个数组(二叉树)，调整成一个大顶堆
     * 功能:完成将以i对应的非叶子节点的树调整为一个大顶堆
     * 例： int arr[]={4,6,8,5,9}; i=1  ==> int arr[]={4,9,8,5,6};
     * @param arr 待调整数组
     * @param i 表示非叶子节点在数组中的索引
     * @param lenght 表示对多少个元素进行调整，length是逐渐在减少的
     */
    public  static void  adjustHeap(int arr[],int i,int lenght){
        //取出当前元素的值，保存再临时变量
        int temp=arr[i];

        //k=i*2+1是当前节点i的左子节点
        //k=k*2+1左字节点的左子节点
        for (int k=i*2+1;k<lenght;k=k*2+1){
            //比较节点i的两个子节点看看谁比较大
            //左子节点的值小于右子节点arr[k]<arr[k+1]
            //但前提是右子节点存在(k+1)<lenght
            if ((k+1)<lenght && arr[k]<arr[k+1]){
                //k指向右子节点
                k++;
            }
            //如果子节点大于父节点i的值
            if (arr[k]>temp){
                //子节点替换父节点
                arr[i]=arr[k];
                //i指向子节点k,开启以k节点为起始节点的新一轮的循环比较
                //除非k没有子节点了，那么直接进行下一轮循环的时候
                //k=k*2+1  k<length这个条件是过不去的，也就结束了循环
                i=k;
            }else{
                break;
            }
        }
        //当for循环结束后
        //父节点的值替换子节点的值
        arr[i]=temp;
    }
}
