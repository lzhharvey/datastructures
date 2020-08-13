package com.example.datastructures.algorithm;

import java.util.Arrays;

/**
 *迪杰斯特拉(Dijkstra)算法
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        // 表示不可以连接
        final int N = 65535;
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        //创建 Graph对象
        DijkstraGraph graph = new DijkstraGraph(vertex, matrix);
        //测试, 看看图的邻接矩阵是否ok
        graph.showGraph();
        //测试迪杰斯特拉算法
        graph.dij(6);
        //显示结果
        graph.showDij();
    }
}
//图
class DijkstraGraph{
    //顶点数组
    private char[] vertex;
    //领接矩阵
    private int[][] matrix;
    //已访问顶点集合
    private VisitedVertex vv;
    //构造器
    public DijkstraGraph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }
    //显示图
    public void showGraph(){
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
    //显示迪杰斯特拉结果
    public void showDij(){
        vv.show();
    }

    /**
     * 迪杰斯特拉算法实现
     * @param index 出发点对应的下标
     */
    public void dij(int index){
        vv= new VisitedVertex(vertex.length, index);
        //更新index下标对应的顶点，到周围顶点的距离和周围顶点的前驱顶点
        update(index);
        //i=1,因为第一个顶点index已经访问过了，接下来访问其他节点
        for (int i = 1; i < vertex.length; i++) {
            //选择并返回新的访问顶点
            index= vv.updateArr();
            //更新index顶点到周围顶点的距离和前驱顶点
            update(index);
        }
    }
    /**
     * 更新index下标对应的顶点，到周围顶点的距离和周围顶点的前驱顶点
     * @param index
     */
    private void update(int index){
        int len=0;
        //遍历领接矩阵的matrix[index]行
        for (int i = 0; i < matrix[index].length; i++) {
            //len含义是:出发顶点到index顶点的距离+从index顶点到i顶点的距离和
            len=vv.getDis(index)+matrix[index][i];
            //如果i顶点没有被访问过，并且len小于出发顶点到i顶点的距离，就需要更新
            //从出发点到index的路径可能多条，从index顶点到i顶点是固定的同一条
            //因此，len可能和出发顶点到i顶点的距离，不同
            //vv.in(i): 判断index顶点是否被访问过
            //len <vv.getDis(i): 判断len是否小于出发顶点到i顶点的距离
            if (!vv.in(i) && len <vv.getDis(i)){
                //更新i顶点的前驱节点为index顶点
                vv.updatePre(i,index);
                //更新出发顶点到i顶点的距离
                vv.updateDis(i,len);
            }

        }
    }

}
//已访问顶点集合
class VisitedVertex{
    //记录各个顶点是否访问过  1表示访问过,0未访问,会动态更新
    public int[] already_arr;
    //每个下标对应的值为前一个顶点下标, 会动态更新
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离,比如：G为出发顶点，
    //就会记录G到其它顶点的距离，会动态更新，求的最短距离就会存放到dis
    public int[] dis;
    //构造函数
    public VisitedVertex(int length,int index) {
        this.already_arr=new int[length];
        this.pre_visited=new int[length];
        this.dis=new int[length];
        //初始化dis数组
        Arrays.fill(dis,65535);
        //设置出发顶点被访问过
        this.already_arr[index]=1;
        //设置出发顶点的访问距离为0，比如：G为出发点，它到他自己的距离为0
        this.dis[index]=0;
    }
    /**
     * 判断index顶点是否被访问过
     * @param index
     * @return 如果访问过返回true,否则返回 false
     */
    public boolean in(int index){
        return already_arr[index]==1;
    }
    /**
     * 更新出发顶点到其他index顶点的距离
     * @param index
     * @param len
     */
    public void updateDis(int index,int len){
        dis[index]=len;
    }
    /**
     * 更新顶点的前驱为index的顶点
     * @param pre
     * @param index
     */
    public void updatePre(int pre,int index){
        pre_visited[pre]=index;
    }
    /**
     * 返回出发点到index顶点的距离
     * @param index
     * @return
     */
    public int getDis(int index){
        return dis[index];
    }
    /**
     *继续选择并返回新的访问顶点， 比如这里的G 完后，就是 A点作为新的访问顶点(注意不是出发顶点)
     * @return
     */
    public int updateArr(){
        int min=65535,index=-0;
        for (int i = 0; i < already_arr.length; i++) {
            //already_arr[i]==0判断是否访问过
            //dis[i]<min 判断出发点到当前顶点的距离是不是小于min，其实就是为了获取
            //未访问过的与出发顶点相连的顶点最短的边
            if (already_arr[i]==0 && dis[i]<min){
                min=dis[i];
                index=i;
            }
        }
        //更新index，顶点被访问过
        already_arr[index]=1;
        return index;
    }
    //显示最后的结果
    //即将三个数组的情况输出
    public void show(){
        System.out.println("===================================");
        //输出already_arr
        for (int i = 0; i < already_arr.length; i++) {
            System.out.print(already_arr[i]+" ");
        }
        System.out.println();
        //输出pre_visited
        for (int i : pre_visited) {
            System.out.print(i+" ");
        }
        System.out.println();
        //输出dis
        for (int i : dis) {
            System.out.print(i+" ");
        }
        System.out.println();
        //为了好看最后的最短距离，我们处理
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "("+i+") ");
            } else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();
    }
}