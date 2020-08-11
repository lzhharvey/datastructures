package com.example.datastructures.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
/**
 * 贪心算法
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //1.创建广播电台，放入到一个Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //2.allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<String>();
        broadcasts.forEach((k,v)->{
            v.forEach(a->{
                allAreas.add(a);
            });
        });

        //3.贪心算法，获取选择的电台集合
        ArrayList<String> selects = greedy(broadcasts, allAreas);
        System.out.println("得到的选择结果是："+selects);
    }
    /**
     * 贪心算法实现
     * @param broadcasts 广播电台集合
     * @param allAreas 存放所有的地区的集合
     * @return 返回选择的电台集合
     */
    public static ArrayList<String> greedy(HashMap<String,HashSet<String>> broadcasts, HashSet<String> allAreas){
        //3.创建ArrayList, 存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //4.定义一个临时的集合， 在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<String>();

        //5.定义一个maxKey,保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        //如果maxKey不为null,则会加入到selects
        String maxKey=null;
        //辅助指针，用于保存上一个的广播电台覆盖城市数量
        int maxTemp=0;

        //当allAreas不为空时
        while(allAreas.size()!=0){
            //每进行一次while,需要maxKey置空，不然好指向着上一次循环的最大覆盖城市数量的广播电台
            maxKey = null;
            //每进行一次while,需要maxKey置0，不然好保存着上一次循环的最大的广播电台覆盖城市数量
            maxTemp = 0;

            //遍历广播电台，确定maxkey指向哪个广播电台
            for(String key : broadcasts.keySet()){
                //每进行一次循环，tempSet都要清一下
                tempSet.clear();
                //获取到当前广播电台覆盖的城市
                HashSet<String> areas = broadcasts.get(key);
                //将这些城市放到临时集合里存起来
                tempSet.addAll(areas);
                //将当前广播电台覆盖城市的集合和所有地区的集合做一个交集
                tempSet.retainAll(allAreas);
                //tempSet.size()>maxTemp 当前广播电台的未覆盖的城市比上一个广播电台的多
                //体现出贪心算法的特点,每次都选择最优的
                if(tempSet.size()>0 && (maxKey==null || tempSet.size()>maxTemp)){
                    //将maxKey指向当前广播电台
                    maxKey=key;
                    //将当前广播电台覆盖城市数量保存(这里的城市数量是已经做过交集的)
                    maxTemp=tempSet.size();
                }
            }
            //maxKey != null
            if (maxKey!=null){
                //将maxKey 加入selects
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区，从 allAreas 去掉
                allAreas.removeAll(broadcasts.get(maxKey));
                //删除已经加入了selects集合的广播电台，没必要在去遍历
                broadcasts.remove(maxKey);
            }
        }
        return selects;
    }
}