package com.example.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    //存储顶点集合
    private ArrayList<String> vertexList;
    //存储图对应的领结矩阵
    private int[][] edges;
    //表示边的数目
    private int numOfEdges;
    //构造函数
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges=new int[n][n];
        vertexList=new ArrayList<>(n);
        numOfEdges=0;
    }
    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    /**
     *添加边
     * @param v1 表示点的下标，即是第几个顶点
     * @param v2 表示第二个对应的下标
     * @param weight 表示边的权值
     */
    public void insertEdges(int v1,int v2,int weight){
        edges[v1][v2]=weight;
        edges[v2][v1]=weight;
        numOfEdges++;
    }
    /**
     * 返回图中节点个数
     * @param
     */
    public int getNumofVertex(){
        return vertexList.size();
    }
    /**
     * 返回边的条数
     * @param
     */
    public int getNumOfEdges(){
        return numOfEdges;
    }
    /**
     * 返回节点i对应的数据，0->"A" 1->"B"
     * @param
     */
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    /**
     * 返回边【v1，v2】的权值
     * @param
     */
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    /**
     * 显示图对应的矩阵
     * @param
     */
    public void showGraph(){
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    public static void main(String[] args) {
        //测试图是否创建成功

        //节点
        String[] vertexs={"A","B","C","D","E"};

        //创建图对象
        Graph graph = new Graph(vertexs.length);

        //添加节点
        for (int i = 0; i < vertexs.length; i++) {
            graph.insertVertex(vertexs[i]);
        }

        //添加边
        //边AB
        graph.insertEdges(0,1,1);
        //边AC
        graph.insertEdges(0,2,1);
        //BC
        graph.insertEdges(1,2,1);
        //BD
        graph.insertEdges(1,3,1);
        //BE
        graph.insertEdges(1,4,1);

        //显示邻接矩阵
        graph.showGraph();
    }
}
