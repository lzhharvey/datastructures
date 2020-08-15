package com.example.datastructures.algorithm;

/**
 * 暴力匹配算法-解决字符串匹配问题
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1= "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2="尚硅谷你尚硅你1";
        int i = violenceMatch(str1, str2);
        System.out.println(i);
    }

    //暴力匹配算法实现
    public static int violenceMatch(String str1,String str2){
        //字符串1转为字符数组
        char[] chars = str1.toCharArray();
        //字符串2转为字符数组
        char[] chars1 = str2.toCharArray();
        //字符串1长度
        int str1Len = str1.length();
        //字符串2长度
        int str2Len = str2.length();
        //指针1指向字符串1
        int i=0;
        //指针2指向字符串2
        int j=0;

        //如果j==str2Len,表示找到了str2匹配str1的位置
        //如果i==str1Len，表示字符串str1配置不到字符串str2
        while(i<str1Len && j<str2Len){
            if (chars[i]==chars1[j]){
                i++;
                j++;
            }
            else {
                //i回溯,如果i=i-j的话，回溯回到最开始匹配到的位置，i还是原来的位置,应该是要到下一个位置
                //所以i-(j-1)
                i=i-(j-1);
                j=0;
            }
        }
        if (j==str2Len){
            return i-j;
        }else {
            return -1;
        }

    }

}
