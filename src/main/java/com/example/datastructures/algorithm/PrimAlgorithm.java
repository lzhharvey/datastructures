package com.example.datastructures.algorithm;

import java.util.Arrays;
/**
 * 普里姆算法
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试看看图是否创建ok
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000这个大数，表示两个点不联通
        int[][] weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}};
        //创建图对象
        Graph graph=new Graph(verxs);
        //创建最小生成树对象
        MinTree minTree=new MinTree();
        //创建图
        minTree.createGraph(graph,verxs,data,weight);
        //显示图的邻接矩阵
        minTree.showGraph(graph);
        //测试普利姆算法
        minTree.prim(graph,0);
    }
}
//创建最小生成树->村庄的图
class MinTree{
    /**
     *创建图的邻接矩阵
     * @param graph 图对象
     * @param verxs 图对应的顶点个数
     * @param data 图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(Graph graph, int verxs, char data[], int[][] weight) {
        for (int i = 0; i < verxs; i++) {
            //顶点
            graph.data[i]=data[i];
            //边
            for (int i1 = 0; i1 < verxs; i1++) {
                graph.weight[i][i1]=weight[i][i1];
            }
        }
    }
    //显示图的邻接矩阵
    public void showGraph(Graph graph) {
        for(int[] link: graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }
    /**
     * 编写prim算法，得到最小生成树
     * @param graph 图
     * @param v 表示从图的第几个顶点开始生成 'A'->0 'B'->1...
     */
    public void prim(Graph graph,int v){
        //visited[] 标记结点(顶点)是否被访问过
        int[] visited=new int[graph.verx];
        //把当前这个结点标记为已访问
        visited[v]=1;
        //h1 和 h2 记录两个顶点的下标
        int h1=-1;
        int h2=-1;
        //将 minWeight 初始成一个大数，后面在遍历过程中，会被替换
        int minWeight=10000;
        //因为有graph.verxs个顶点，普利姆算法结束后，有graph.verxs-1边
        for (int k=1;k<graph.verx;k++){
            //这个是确定每一次生成的子图 ，和哪个结点的距离最近
            //i结点表示被访问过的结点
            for (int i=0;i<graph.verx;i++){
                //j结点表示还没有访问过的结点
                for (int j=0;j<graph.verx;j++){
                    if (visited[i]==1 && visited[j]==0 && graph.weight[i][j]<minWeight){
                        //替换minWeight(寻找已经访问过的结点和未访问过的结点间的权值最小的边)
                        minWeight=graph.weight[i][j];
                        h1=i;
                        h2=j;
                    }
                }
            }
            //找到一条权值最小的边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
            //将当前这个结点标记为已经访问
            visited[h2]=1;
            //minWeight 重新设置为最大值 10000
            minWeight=10000;
        }
    }
}
//图
class Graph{
    //表示图的节点个数
    int verx;
    //存放节点数据
    char[] data;
    //存放边，就是我们的领接矩阵
    int[][] weight;
    public Graph(int verx) {
        this.verx = verx;
        data=new char[verx];
        weight=new int[verx][verx];
    }
}