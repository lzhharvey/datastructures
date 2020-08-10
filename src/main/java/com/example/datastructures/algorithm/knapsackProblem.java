package com.example.datastructures.algorithm;

/**
 * @ClassName knapsackProblem
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/8/10 9:32
 * @Version 1.0
 **/
public class knapsackProblem {
    public static void main(String[] args) {
        //物品重量
        int[] w={1,4,3};
        //物品价值
        int[] val={1500,3000,2000};
        //背包重量
        int m=4;
        //物品的个数
        int n=val.length;

        //创建二维数组，
        //v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v=new int[n+1][m+1];
        //为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n+1][m+1];

        //初始化第一行第一列
        for (int i = 0; i < v.length; i++) {
            //将表的第一列置位0
            v[i][0]=0;
        }
        for (int i = 0; i < v[0].length; i++) {
            //将表的第一行置位0
            v[0][i]=0;
        }
        //根据公式来动态规划处理
        //不处理第一行
        for (int i = 1; i < v.length; i++) {
            //不处理第一列
            for (int j= 1; j< v[i].length; j++) {
                //当准备加入新增的商品的容量大于当前背包的容量时，就直接使用上一个单元格的装入策略
                //但是这里i-1,因为i从1开始， int[] w={1,4,3};
                if (w[i-1]>j){
                    v[i][j]=v[i-1][j];
                }else{
                    //说明:
                    //因为我们的i 从1开始的， 因此公式需要调整成
                    //v[i][j]=Math.max(v[i-1][j], val[i-1]+v[i-1][j-w[i-1]]);
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    //为了记录商品存放到背包的情况，我们不能直接的使用上面的公式，需要使用if-else来体现公式
                    if(v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //把当前的情况记录到path，标记商品能放入背包
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //输出一下v 看看目前的情况
        for(int i =0; i < v.length;i++) {
            for(int j = 0; j < v[i].length;j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("============================");
        //查看商品放入背包的情况
        //行的最大下标
        int i = path.length - 1;
        //列的最大下标
        int j = path[0].length - 1;
        //从path的最后开始找
        while(i > 0 && j > 0 ) {
            //该商品能放入背包
            if(path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                //表示当找到一个新增的商品的容量小于当前背包的容量的情况，先用背包容量-当前商品的重量
                //剩下的空间再去找一个合适的商品放入
                j -= w[i-1];
            }
            //尝试上一个商品能不能放入背包
            i--;
        }

    }
}
