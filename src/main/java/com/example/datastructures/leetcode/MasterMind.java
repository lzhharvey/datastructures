package com.example.datastructures.leetcode;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 珠玑妙算
 */
public class MasterMind {
    public static void main(String[] args) {
        String solution="YBBY";
        String guess="GYYB";
//        BRBB"
//        "RBGY"

//        "YBBY"
//        "GYYB"
        int[] ints = masterMind(solution, guess);
        System.out.println(Arrays.toString(ints));

    }
    public static int[] masterMind(String solution, String guess) {
        ArrayList<Character> solutionList = new ArrayList<>();
        ArrayList<Character> guessList = new ArrayList<>();
        int guessNum=0;
        int all=0;

        for (int i = 0; i < solution.length(); i++) {
            //猜中次数
            if (solution.charAt(i)==guess.charAt(i)){
                guessNum++;
            }
            //将字符串solution中的所有元素放到集合solutionList中
            solutionList.add(solution.charAt(i));
            //将字符串guess中的所有元素放到集合guessList中
            guessList.add(guess.charAt(i));
        }
        //遍历集合solutionList
        for (int i = 0; i < solutionList.size(); i++) {
                //如果集合guessList中包含solutionList中当前的元素，则all++
                if (guessList.contains(solutionList.get(i))){
                    all++;
                    //避免重复统计
                    guessList.remove(solutionList.get(i));
                }
        }
        return new int[] {guessNum,all-guessNum};
    }
}
