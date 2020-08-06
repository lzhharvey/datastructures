package com.example.datastructures.huffmantree;

import com.sun.jmx.snmp.SnmpOid;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @ClassName HuffmanCode
 * @Description TODO
 * @Author liuzhihui
 * @Date 2020/8/5 23:16
 * @Version 1.0
 **/
public class HuffmanCode {
    public static void main(String[] args) {
        //40字节
        String string="i like like like java do you like a java";
        //字符串转字节数组
        byte[] contentsBytes = string.getBytes();
        System.out.println(contentsBytes.length);

        //将字节数组转成集合形式
//        List<Node1> node = getNode(contentsBytes);
//        System.out.println(node);

//        //测试创建的赫夫曼树
//        System.out.println("赫夫曼树");
//        Node1 huffman = createHuffman(node);
//        System.out.println("前序遍历");
//        preOrder(huffman);
//
//        //测试是否生成了哈夫曼编码
//        Map<Byte, String> code = getCode(huffman);
//        System.out.println("生成的哈夫曼编码表：");
//        System.out.println(huffmanCodes);
//
//        //测试使用赫夫曼编码生成的赫夫曼编码数据
//        byte[] zip = zip(contentsBytes, code);
//        System.out.println("赫夫曼字节数组："+ Arrays.toString(zip)); //17
        //将前面的方法封装
        byte[] bytes = huffmanZip(contentsBytes);
        System.out.println("压缩后的结果："+ Arrays.toString(bytes));//压缩后从原来的40字节。变成了17
    }
    /**
     * 接受一个字节数组，返回list形式的数据
     */
    private static List<Node1> getNode(byte[] bytes){
        //1.创建一个集合
        ArrayList<Node1> node1List = new ArrayList<>();

        //2.遍历bytes数组,统计每一个byte出现的次数
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte aByte : bytes) {
            Integer count = counts.get(aByte);
            //Map中还没有这个字符，第一次添加字符到map
            if (count==null){
                counts.put(aByte,1);
            }else {
                counts.put(aByte,1+count);
            }
        }
        //3.把每个键值对转成一个Node对象，并加入到集合中
        counts.forEach((k,v)->{
            node1List.add(new Node1(k,v));
        });
        return node1List;
    }
    //通过list创建一个赫夫曼树
    public static Node1 createHuffman(List<Node1> list){
        while(list.size()>1){
            //1.排序,从小到大排
            Collections.sort(list);
            //2.取出根节点权值最小的两颗二叉树
            Node1 left = list.get(0);
            Node1 right = list.get(1);

            //3.构建一个新的二叉树
            Node1 node1 = new Node1(null, left.weight + right.weight);
            node1.left=left;
            node1.right=right;

            //4.删除集合集合中的两个子节点，添加新创建的二叉树
            list.remove(left);
            list.remove(right);
            list.add(node1);
        }
        return list.get(0);
    }
    //前序遍历
    public static void preOrder(Node1 root){
        if (root==null){
            System.out.println("赫夫曼树为空");
        }
        root.preOrder();
    }
    //生成赫夫曼树对应的赫夫曼编码
    //思路:
    //1. 将赫夫曼编码表存放在 Map<Byte,String> 形式
    //生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    static Map<Byte, String> huffmanCodes = new HashMap<Byte,String>();
    //2. 在生成赫夫曼编码表示，需要去拼接路径, 定义一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();
    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     * @param node  传入结点
     * @param code  路径： 左子结点是 0, 右子结点 1,根节点传“”
     * @param stringBuilder 用于拼接路径
     */
    private static void getCode(Node1 node,String code,StringBuilder stringBuilder){
        //每个节点都有自己的路径，用StringBuilder表示
        StringBuilder path = new StringBuilder(stringBuilder);
        //将code添加到路径中
        path.append(code);
        //如果node===null，不处理
        //空树不处理，即根节点为空时或者节点的左/右子节点为空
        if (node!=null){
            //data属性为空，则说明是非叶子节点
            if (node.data==null){
                //向左递归
                getCode(node.left,"0",path);
                //向右递归
                getCode(node.right,"1",path);
            }else {
                //说明是叶子节点
                huffmanCodes.put(node.data,path.toString());
            }
        }
    }
    //为了调用方便，重载getCode
    private static Map<Byte, String> getCode(Node1 node) {
        getCode(node,"",stringBuilder);
        return huffmanCodes;
    }


    /**
     *使用赫夫曼编码生成的赫夫曼编码数据
     * 编写一个方法，将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
     * @param bytes 这时原始的字符串对应的 byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的 byte[]
     * 举例： String content = "i like like like java do you like a java"; =》 byte[] contentBytes = content.getBytes();
     * 返回的是 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes  ，即 8位对应一个 byte,放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] =  10101000(补码) => byte  [推导  10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     * huffmanCodeBytes[1] = -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        //1.利用哈夫曼编码表huffmanCodes将字节数组bytes转成字符串1010100010111111110010...
        StringBuilder stringBuilder = new StringBuilder();
        //2.遍历byte数组
        for (byte aByte : bytes) {
            stringBuilder.append(huffmanCodes.get(aByte));
        }
        //3.将字符串1010100010111111110010...转成字节数组
        //获取哈夫曼字节数组长度
        int len=stringBuilder.length()%8==0?stringBuilder.length()/8:(stringBuilder.length()/8+1);

        //创建赫夫曼byte数组
        byte[] huffmanCodeBytes = new byte[len];
        //记录是第几个byte
        int index=0;
        //因为每8位对应一个byte,步长为8
        for (int i = 0; i < stringBuilder.length(); i+=8) {
            String str;
            //不够8位
            if (stringBuilder.length()<i+8){
                str=stringBuilder.substring(i);
            }
            else {
                str=stringBuilder.substring(i,i+8);
            }
            huffmanCodeBytes[index]=(byte) Integer.parseInt(str,2);
            index++;
        }
        return huffmanCodeBytes;
    }
    /**
     *使用一个方法，将前面的方法封装起来，便于我们的调用.
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过 赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node1> nodes = getNode(bytes);
        //根据 nodes 创建的赫夫曼树
        Node1 huffmanTreeRoot = createHuffman(nodes);
        //对应的赫夫曼编码(根据 赫夫曼树)
        Map<Byte, String> huffmanCodes = getCode(huffmanTreeRoot);
        //根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;

    }
    }
//创建节点node
class Node1 implements Comparable<Node1>{
    //存放数据本身，比如：“a”=>97 ' '=>32
    Byte data;
    //权值，表示字符出现的次数
    int weight;
    Node1 left;
    Node1 right;

    public Node1(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }
    //写一个前序遍历
    public void preOrder(){
        //输出当前节点
        System.out.println(this);
        //向左子树遍历
        if (this.left!=null){
            this.left.preOrder();
        }
        //向右子树遍历
        if(this.right!=null){
            this.right.preOrder();
        }
    }
    @Override
    public int compareTo(Node1 o) {
        //从下到大排序
        return this.weight-o.weight;
    }
    @Override
    public String toString() {
        return "Node1{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}