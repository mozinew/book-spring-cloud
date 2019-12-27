package com.tengol.test;

import java.util.HashMap;
import java.util.Map;

/**
 * HelloTest
 *
 * @author dongrui
 * @date 2019/12/5 14:38
 */
public class HelloTest {
    public static void main(String[] args) {
//        System.out.println("Hello World");
//        Map<String, String> helloMap = new HashMap<>(5, 0.8f);
//        System.out.println(helloMap);
//        //拷贝元素
//
        Map<String, String> nameMap = getDefaultMap();
        Map<String, String> worldMap = new HashMap<>(nameMap);
        worldMap.put("li4_1","lisi");
        worldMap.put("li4_2","lisi2_1");
        System.out.println(worldMap.size());
        worldMap.put("li4_2","lisi2_2");
        System.out.println(worldMap.size());
        worldMap.put("li4_3","lisi");
        worldMap.put("li4_4","lisi");
        worldMap.put("li4_5","lisi");
        System.out.println(worldMap.get("li4_2"));
        //删除
        worldMap.remove("li4_5");

    }

    private static Map<String, String> getDefaultMap() {
        int size = 5;
        Map<String, String> nameMap = new HashMap<>(size);
        for (int i = 1; i <= size; i++) {
            nameMap.put("zh3_" + i, "zhangsan");
        }
        return nameMap;
    }
}
