package com.example.datastructures.recursion;

/**
 * @ClassName MiGong
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/7/31 23:56
 * @Version 1.0
 **/
public class MiGong {
    public static void main(String[] args) {
        //创建一个二维数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //1表示墙
        //上下全部置位1
        for(int i=0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        //左右全部置位1
        for (int i=0;i<8;i++){
            map[i][0]=1;
            map[i][6]=1;
        }
        //设置挡板，1表示
        map[3][1]=1;
        map[3][2]=1;

        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int i1 = 0; i1 < 7; i1++) {
                System.out.print(map[i][i1]+" ");
            }
            System.out.println();
        }

        //输出新的地图, 小球走过，并标识过的递归
        System.out.println("小球走过，并标识过的 地图的情况");
        boolean b = setWay(map, 1, 1);
        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int i1 = 0; i1 < 7; i1++) {
                System.out.print(map[i][i1]+" ");
            }
            System.out.println();
        }

    }

    /**
     *使用递归回溯来给小球招路
     * map[i][j]为0表示没有走过；为2表示通路可走；为3表示走过，但是不通；为1表示墙；
     * 在走迷宫前，需要确定一个策略：先 下->右->上->左，如果该点走不通，再回溯
     * @param map 表示地图
     * @param （i，j） 表示从地图哪个位置开始出发,(6,5)位置为终点
     * @return 能找到通路true，否则false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        // 通路已经找到ok
        if(map[6][5] == 2) {
            return true;
        } else {
            if(map[i][j] == 0) { //如果当前这个点还没有走过
                //按照策略 下->右->上->左  走
                map[i][j] = 2; // 假定该点是可以走通.
                if(setWay(map, i+1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j+1)) { //向下走走不通，向右走
                    return true;
                } else if (setWay(map, i-1, j)) { //向右走也走不通，向上
                    return true;
                } else if (setWay(map, i, j-1)){ //向上走还走不通，向左走
                    return true;
                } else {
                    //说明该点是走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果map[i][j] != 0 , 可能是 1， 2， 3
                return false;
            }
        }
    }
}
//1 1 1 1 1 1 1
//1 2 0 0 0 0 1
//1 2 2 2 0 0 1
//1 1 1 2 0 0 1
//1 0 0 2 0 0 1
//1 0 0 2 0 0 1
//1 0 0 2 2 2 1
//1 1 1 1 1 1 1
/**
 * 结果分析：
 * 第一次递归：setWay(map, 1, 1);if(map[i][j] == 0)为true；map[1][1]设置2；先尝试向下走；
 * 第二次递归：setWay(map, 2, 1);if(map[i][j] == 0)为true；map[2][1]设置2；尝试向下走；
 * 第三次递归：setWay(map, 3, 1);if(map[i][j] == 0)为fasle；直接返回到第二次递归；
 * 第二次递归：因为向下走，是墙，走不通，尝试下右走
 * 第四次递归：setWay(map, 2, 2);if(map[i][j] == 0)为true；map[2][2]设置2；尝试向下走；
 * 第五次递归：setWay(map, 3, 2);if(map[i][j] == 0)为fasle；直接返回到第四次递归；
 * 第四次递归：因为向下走，是墙，走不通，尝试下右走
 * 。。。。
 * 经过不断的尝试，当走到（6,5）时，设置map[6,5]=2,然后在次递归，尝试往下走时，发现满足了条件
 *        if(map[6][5] == 2) {
 *             return true;
 *         }
 *于是整个递归调用层层返回。
 *
 */

