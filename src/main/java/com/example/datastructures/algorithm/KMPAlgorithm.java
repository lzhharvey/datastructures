package com.example.datastructures.algorithm;

import java.util.Arrays;

/**
 * kmp搜索算法实现
 */
public class KMPAlgorithm {

    public static void main(String[] args) {
        String str1= "BBC ABCDAB ABCDABCDABDE";
        String str2="ABCDABD";

        int[] next = kmpNext(str2);
        System.out.println("next="+ Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index="+index);
    }

    /**
     * kmp搜索算法
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表，是子串对应的部分匹配表
     * @return -1没有匹配到，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1,String str2,int[] next){
        //遍历
        for (int i = 0,j=0; i < str1.length(); i++) {
            //需要处理 str1.charAt(i) ！= str2.charAt(j), 去调整j的大小
            //KMP算法核心点
            while(j>0 && str1.charAt(i)!=str2.charAt(j)){
                //底层推出的公式
                j=next[j-1];
            }

            if (str1.charAt(i)==str2.charAt(j)){
                j++;
            }
            //匹配到了
            if (j==str2.length()){
                return i-j+1;
            }

        }
        return -1;
    }


    //获取到一个字符串(子串) 的部分匹配值表
    public static  int[] kmpNext(String dest) {
        //创建一个next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0; //如果字符串是长度为1 部分匹配值就是0
        for(int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.charAt(j) ，我们需要从next[j-1]获取新的j
            //直到我们发现 有  dest.charAt(i) == dest.charAt(j)成立才退出
            //这时kmp算法的核心点
            while(j > 0 && dest.charAt(i) != dest.charAt(j)) {
                //为什么要j=next[j-1];
                //以ABCDABD为例子，在循环比较的时候，
                //当前循环到charAt(i)=‘D’，charAt(j)=‘C’时，发现并不相等
                //因此这个字符串不存在为3的共有长度，不存在3的共有长度，但不代表不存在2或者1或者0的共有长度
                //那么我们对于此时的这个字符串“ABC”的三个字符应该寻找长度短一点最长共有长度
                //next[j-1]获取到“AB”的共有长度为0，然后让charAt(i)与charAt(j)比较，因为charAt(i)可能
                //是为A的，但是这里为D,如果是A,那么“ABCDABA”的共有长度为1，如果是D,那么以“ABCDABD”共有长度为0

                //再举例，字符串“abaabab”
                //当循环匹配abaa与abab时，发现两个字符串最后元素‘a’与‘b’不相等
                //因此这个字符串不存在为4的共有长度
                //那么我们对于此时的这个字符串“abaa”的四个字符应该寻找长度短一点最长共有长度
                //next[j-1]获取到“aba”的共有长度为1，然后让charAt(i)与charAt(j)比较，charAt(j)='b'的
                //因为charAt(i)可能是为b的，这里确实为b,因此，“abaabab”共有长度为2

                //当公式记吧
                j = next[j-1];
            }

            //当dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值就是+1
            if(dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
