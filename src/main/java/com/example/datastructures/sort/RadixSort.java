package com.example.datastructures.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 基数排序
 */
public class RadixSort {
    public static void main(String[] args) {
        //测试快排的执行速度
        // 创建要给80000个的随机的数组
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }
        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
//        int arr[]={53, 3, 542, 748, 14, 214};

        radixSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
    }

    public static void radixSort(int[] arr){
        //找出最大值
        int max=arr[0];
        for (int i=0;i<arr.length;i++){
            if (arr[i]>max){
                max=arr[i];
            }
        }
        //获取最大数是几位数
        int maxLength = (max + "").length();

        //定义一个二维数组，表示10个桶, 每个桶就是一个一维数组
        //说明
        //1. 二维数组包含10个一维数组
        //2. 为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
        //3. 很明显，基数排序是使用空间换时间的经典算法
        int[][] bucket=new int[10][arr.length];
        //为了记录每个桶中，实际存放了多少个数据,我们定义一个一维数组来记录各个桶的每次放入的数据个数
        //可以这里理解
        //比如：bucketElementCounts[0] , 记录的就是  bucket[0] 桶的放入数据个数
        int[] bucketElementCounts=new int[10];

        for (int k = 0,n=1; k < maxLength; k++,n*=10) {
            //第k轮排序(针对每个元素的对应的位进行排序处理)，比如：第一轮排序，对应个位
            for (int i = 0; i < arr.length; i++) {
                //取出每个元素的个位的值
                int diffOfElement = arr[i] /n% 10;
                int bucketElementCount = bucketElementCounts[diffOfElement];
                //放入对应的桶中
                bucket[diffOfElement][bucketElementCount]=arr[i];
                bucketElementCounts[diffOfElement]++;
            }
            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
            int index=0;
            //遍历每一桶，就将桶中是数据，放入到原数组
            for (int i = 0; i < bucket.length; i++) {
                //如果桶中，有数据，我们才放入到原数组
                if (bucketElementCounts[i]!=0){
                    //循环该桶即第i个桶(即第i个一维数组), 放入
                    for (int i1 = 0; i1 < bucketElementCounts[i]; i1++) {
                        //取出元素放入到arr
                        arr[index]=bucket[i][i1];
                        index++;
                    }
                }
                //第i+1轮处理后，需要将每个bucketElementCounts【i】=0
                bucketElementCounts[i]=0;
            }
//            System.out.println("第"+(k+1)+"轮："+ Arrays.toString(arr));
        }



        //推导过程

        //第一轮排序(针对每个元素的个位进行排序处理)
//        for (int i = 0; i < arr.length; i++) {
//            //取出每个元素的个位的值
//            int diffOfElement = arr[i] % 10;
//            int bucketElementCount = bucketElementCounts[diffOfElement];
//            //放入对应的桶中
//            bucket[diffOfElement][bucketElementCount]=arr[i];
//            bucketElementCounts[diffOfElement]++;
//        }
//        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
//        int index=0;
//        //遍历每一桶，就将桶中是数据，放入到原数组
//        for (int i = 0; i < bucket.length; i++) {
//            //如果桶中，有数据，我们才放入到原数组
//            if (bucketElementCounts[i]!=0){
//                //循环该桶即第k个桶(即第k个一维数组), 放入
//                for (int i1 = 0; i1 < bucketElementCounts[i]; i1++) {
//                    //取出元素放入到arr
//                    arr[index]=bucket[i][i1];
//                    index++;
//                }
//            }
//            //第一轮处理后，需要将每个bucketElementCounts【i】=0
//            bucketElementCounts[i]=0;
//        }
//        System.out.println("第一轮："+ Arrays.toString(arr));
//
//        //第二轮(针对每个元素的个位进行排序处理)
//        for (int i = 0; i < arr.length; i++) {
//            //取出每个元素的十位的值
//            int diffOfElement = arr[i]/10% 10;
//            int bucketElementCount = bucketElementCounts[diffOfElement];
//            //放入对应的桶中
//            bucket[diffOfElement][bucketElementCount]=arr[i];
//            bucketElementCounts[diffOfElement]++;
//        }
//        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
//        index=0;
//        //遍历每一桶，就将桶中是数据，放入到原数组
//        for (int i = 0; i < bucket.length; i++) {
//            //如果桶中，有数据，我们才放入到原数组
//            if (bucketElementCounts[i]!=0){
//                //循环该桶即第k个桶(即第k个一维数组), 放入
//                for (int i1 = 0; i1 < bucketElementCounts[i]; i1++) {
//                    //取出元素放入到arr
//                    arr[index]=bucket[i][i1];
//                    index++;
//                }
//            }
//            //第二轮处理后，需要将每个bucketElementCounts【i】=0
//            bucketElementCounts[i]=0;
//        }
//        System.out.println("第二轮："+ Arrays.toString(arr));
//
//        //第三轮(针对每个元素的个位进行排序处理)
//        for (int i = 0; i < arr.length; i++) {
//            //取出每个元素的百位的值
//            int diffOfElement = arr[i]/100 % 10;
//            int bucketElementCount = bucketElementCounts[diffOfElement];
//            //放入对应的桶中
//            bucket[diffOfElement][bucketElementCount]=arr[i];
//            bucketElementCounts[diffOfElement]++;
//        }
//        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
//        index=0;
//        //遍历每一桶，就将桶中是数据，放入到原数组
//        for (int i = 0; i < bucket.length; i++) {
//            //如果桶中，有数据，我们才放入到原数组
//            if (bucketElementCounts[i]!=0){
//                //循环该桶即第k个桶(即第k个一维数组), 放入
//                for (int i1 = 0; i1 < bucketElementCounts[i]; i1++) {
//                    //取出元素放入到arr
//                    arr[index]=bucket[i][i1];
//                    index++;
//                }
//            }
//            //第二轮处理后，需要将每个bucketElementCounts【i】=0
//            bucketElementCounts[i]=0;
//        }
//        System.out.println("第三轮："+ Arrays.toString(arr));
    }
}
