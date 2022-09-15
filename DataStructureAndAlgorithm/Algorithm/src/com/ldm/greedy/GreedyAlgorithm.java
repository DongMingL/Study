package com.ldm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author 梁东明
 * 2022/9/13
 * 人生建议：看不懂的方法或者类记得CTRL + 点击 看看源码或者注解
 * 点击setting在Editor 的File and Code Templates 修改
 *
 * 贪心算法
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台,放入到Map
        HashMap<String,HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
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

        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<String>();
           allAreas.addAll(hashSet1);
           allAreas.addAll(hashSet2);
           allAreas.addAll(hashSet3);
           allAreas.addAll(hashSet4);
           allAreas.addAll(hashSet5);

        System.out.println("需要广播的地区分别为："+allAreas);
        System.out.println("电台分别为："+broadcasts);

        //创建ArrayList，存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时的集合， 在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<String>();

        //定义maxKey，保存在一次遍历过程中，能够覆盖的最大未覆盖的地区的对应电台的key
        //如果maxKey不为null，则会放入到selects中
        String maxKey = null;
        while ( allAreas.size() != 0){//如果地区没被覆盖完，就不许退出循环

            //每进行一次while,需要
            maxKey = null;
            //遍历电台broadcasts集合,取出每个电台的广播范围的key值
            for (String key : broadcasts.keySet()) {
                //每进行一次for
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet与allAreas的集合 的交集,然后赋给temSet
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区还多
                //就需要重置maxKey

                //这三行代码才是贪心算法的核心
                if (tempSet.size() > 0 &&
                    (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            //maxKey != null，就需要把maxKey放入selects
            if ( maxKey  != null){
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区，从 allAreas 去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }

        }
        System.out.println("得到的选择结果是" + selects);//[K1,K2,K3,K5]

    }
}
