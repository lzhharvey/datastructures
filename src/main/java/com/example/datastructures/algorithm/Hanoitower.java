package com.example.datastructures.algorithm;

/**
 * 分治算法-解决汉诺塔问题
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');
    }

    /**
     *使用分治算法解决汉诺塔问题
     * @param num 盘子的数量
     * @param a A塔
     * @param b B塔
     * @param c C塔
     */
    public static void hanoiTower(int num,char a,char b,char c){
        //如果只有一个盘子
        if (num==1){
            System.out.println("第1个盘从"+a+"->"+c);
        }else{
            //如果我们有n>=2个盘子，可以看做是两个盘
            //1.最下边的一个盘 2.上面的所有盘
            //上面的所有盘 A->B，移动过程会使用到c
            hanoiTower(num-1,a,c,b);
//            把最下边的盘 A->C
            System.out.println("第"+num+"个盘从"+a+"->"+c);
//            把B塔的所有盘 从 B->C
            hanoiTower(num-1,b,a,c);
        }

    }
}
