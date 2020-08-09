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
    //记录某个节点是否被访问
    private boolean[] isVisited;

    //构造函数
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges=new int[n][n];
        vertexList=new ArrayList<>(n);
        numOfEdges=0;
        isVisited=new boolean[n];
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
    public int getNumOfVertex(){
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
    /**
     * 获取第一个领接节点的下标
     * @param index
     * @return 存在，返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i]!=0){
                return i;
            }
        }
        return -1;
    }
    /**
     * 根据前一个领接节点的下标，获取下一个领接节点
     * 比如:节点v1的第一个领接节点已经被访问过了，可以通过该方法
     * 传入第一个领接节点，获取节点v1的第二个领接节点
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeighbor(int v1,int v2){
        for (int i = v2+1; i < vertexList.size(); i++) {
            if (edges[v1][i]>0){
                return i;
            }
        }
        return -1;
    }
    //深度优先遍历算法
    //i 第一次就是 0
    private void dfs(boolean[] isVisited, int i) {
        System.out.print(getValueByIndex(i)+"=>");
        //标记为已经访问
        isVisited[i]=true;
        //获取初始节点的第一个邻接节点
        int firstNeighbor = getFirstNeighbor(i);
        //领接节点存在
        while (firstNeighbor!=-1){
            //判断领接节点是否已经被访问
            if(!isVisited[firstNeighbor]){
                //领接节点没有被访问,领接节点作为初始节点，继续递归
                dfs(isVisited,firstNeighbor);
            }
            //获取当前初始节点的下一个领接节点
            firstNeighbor = getNextNeighbor(i, firstNeighbor);
        }
    }
    //对dfs进行一个重载, 遍历我们所有的结点，并进行dfs
    //重载是考虑了不连通图，例如a-b-c连通，d-e连通，但是abcde整体并不连通，只用上边的方法并不能完整遍历，需要用到下边的重载方法！
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        //遍历所有的结点，进行dfs[回溯]
        for(int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
//        dfs(isVisited,0);
    }
    public static void main(String[] args) {
        //测试图是否创建成功

        //节点
        //测试数据一
        String[] vertexs={"A","B","C","D","E"};
        //测试数据二
//        String[] vertexs={"0","1","2","3","4","5","6","7","8","9","10"};

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
//        graph.insertEdges(1,3,1);
        //BE
//        graph.insertEdges(1,4,1);
        //DE
        graph.insertEdges(3,4,1);

//        graph.insertEdges(0,1,1);
//        graph.insertEdges(0,2,1);
//        graph.insertEdges(0,3,1);
//        graph.insertEdges(0,4,1);
//        graph.insertEdges(1,4,1);
//        graph.insertEdges(1,7,1);
//        graph.insertEdges(1,9,1);
//        graph.insertEdges(3,5,1);
//        graph.insertEdges(3,6,1);
//        graph.insertEdges(4,5,1);
//        graph.insertEdges(7,8,1);
//        graph.insertEdges(7,10,1);

        //显示邻接矩阵
        graph.showGraph();
        //广度优先遍历
        graph.dfs();
    }
}
