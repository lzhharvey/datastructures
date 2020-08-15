package com.example.datastructures.algorithm;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法
 */
public class KruskalCase {
    //边的条数
    private int edgeNum;
    //顶点数组
    private char[] vertexs;
    //领接矩阵
    private int[][] matrix;
    //使用INF 表示两个顶点不能连通
    private static  final int INF=Integer.MAX_VALUE;
    //构造函数
    public KruskalCase(char[] vertexs,int[][] matrix) {
        //初始化顶点
        this.vertexs=new char[vertexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i]=vertexs[i];
        }
        //初始化边
        this.matrix=new int[vertexs.length][vertexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            for (int i1 = 0; i1 < vertexs.length; i1++) {
                this.matrix[i][i1]=matrix[i][i1];
            }
        }
        //统计边的条数
        for(int i =0; i < vertexs.length; i++) {
            //j=i+1避免统计0和重复的边
            for(int j = i+1; j < vertexs.length; j++) {
                if(this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }
    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为: \n");
        for(int i = 0; i < vertexs.length; i++) {
            for(int j=0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();//换行
        }
    }
    /**
     *对边进行排序，冒泡排序
     * @param edges 边的集合
     */
    private void sortEdge(EdgeClass[] edges){
        EdgeClass temp;
        for (int i = 0; i < edges.length-1; i++) {
            for (int i1 = 0; i1 < edges.length - 1 - i; i1++) {
                if (edges[i1].weight>edges[i1+1].weight){
                    temp=edges[i1];
                    edges[i1]=edges[i1+1];
                    edges[i1+1]=temp;
                }
            }
        }
    }
    /**
     *获取顶点的下标
     * @param ch 顶点的值，比如‘A’,'B'
     * @return 返回ch顶点对应的下标，如果找不到，返回-1
     */
    private int getPosition(char ch){
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i]==ch) {
                return i;
            }
        }
        return -1;
    }
    /**
     * 功能: 获取图中边，放到EData[] 数组中，后面我们需要遍历该数组
     * 是通过matrix 邻接矩阵来获取
     * EData[] 形式 [['A','B', 12], ['B','F',7], .....]
     * @return
     */
    private EdgeClass[] getEdges() {
        int index = 0;
        EdgeClass[] edges = new EdgeClass[edgeNum];
        for(int i = 0; i < vertexs.length; i++) {
            //j=i+1,就可以获取到所有的边了
            //如果使用j=0，会出现重复的边，比如：边AB和BA,都是同一条边
            for(int j=i+1; j <vertexs.length; j++) {
                if(matrix[i][j] != INF) {
                    edges[index++] = new EdgeClass(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为i的顶点的终点，用于后面判断两个顶点的终点是否相同
     * @param ends:记录了各个顶点对应的终点是哪个，ends数组是在遍历过程中，逐步形成
     * @param i:表示传入的顶点对应的下标
     * @return 返回的就是下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends,int i){
        while(ends[i] !=0){
            i=ends[i];
        }
        return i;
    }
    /**
     * 克鲁斯卡尔算法
     */
    public void kruskal(){
        //表示最后结果数组的索引
        int index = 0;
        //用于保存"已有最小生成树" 中的每个顶点在最小生成树中的终点
        int[] ends = new int[edgeNum];
        //创建结果数组, 保存最后的最小生成树
        EdgeClass[] rets = new EdgeClass[edgeNum];
        //获取所有边
        EdgeClass[] edges = getEdges();
        //按照边的权值大小进行排序(从小到大)
        sortEdge(edges);
        //遍历边数组，将边添加到最小生成树中，判断准备加入的边是否形成了回路，如果没有，就加入rets，否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取边的第一个点的索引
            int p1 = getPosition(edges[i].start);
            //获取边的另一个点的索引
            int p2 = getPosition(edges[i].end);
            //获取p1顶点的终点
            int m = getEnd(ends, p1);
            //获取p2顶点的终点
            int n = getEnd(ends, p2);
            //是否构成回路
            if (m!=n){
                //设置m在"当前最小生成树"中的终点。
                //比如：<E,F> [0,0,0,0,5,0,0,0,0,0,0,0]
                //索引4，表示E；值5表示E的终点是F;
                //不需要写ends[n]=m，因为getEnd的时候判断到ends[n]==0，直接返回索引i 比如：边<E,F>的F,直接就返回5
                ends[m]=n;
                //有一条边加入到rets数组
                rets[index++]=edges[i];
            }
        }
        //<E,F> <C,D> <D,E> <B,F> <E,G> <A,B>。
        //统计并打印 "最小生成树", 输出  rets
        System.out.println("最小生成树为");
        for(int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }
    public static void main(String[] args) {
        char[] vertext={'A','B','C','D','E','F','G'};
        int matrix[][] = {
                       /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};

        //创建KruskalCase实例
        KruskalCase kruskalCase=new KruskalCase(vertext,matrix);
        //打印邻接矩阵
        kruskalCase.print();
        //获取没有排序的边
        EdgeClass[] edges = kruskalCase.getEdges();
        System.out.println("未排序的边：");
        System.out.println(Arrays.toString(edges));
        //排序边
        kruskalCase.sortEdge(edges);
        System.out.println("排序后的边：");
        System.out.println(Arrays.toString(edges));
        //测试克鲁斯卡尔算法
        kruskalCase.kruskal();
    }
}
//创建边类
class EdgeClass{
    //边的起点
    char start;
    //边的另外一个点
    char end;
    //边的权值
    int weight;
    //构造器
    public EdgeClass(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    @Override
    public String toString() {
        return "EdgeClass{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}