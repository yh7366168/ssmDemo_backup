package test;

import com.yh.util.exception.YhSimpleException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Test {

    public static void main(String[] args) {
        byte a = 127;
        byte b = (byte)(a+1);
        System.out.println(b);

    }

    public static List<Map> test(){
        //三层遍历循环
        List<Map> oneList = new ArrayList<>();
        Map<String, Object> oneMap = new HashMap<>();

        List<Map> twoList = new ArrayList<>();
        List<Map> twoList2 = new ArrayList<>();
        Map<String, Object> twoMap = new HashMap<>();
        Map<String, Object> twoMap2 = new HashMap<>();

        List<Map> threeList = new ArrayList<>();
        List<Map> threeList2 = new ArrayList<>();
        List<Map> threeList3 = new ArrayList<>();
        Map<String, Object> threeMap = new HashMap<>();
        Map<String, Object> threeMap2 = new HashMap<>();
        Map<String, Object> threeMap3 = new HashMap<>();
        threeMap.put("查询", true);
        threeMap.put("新增", true);
        threeMap.put("修改", false);
        threeMap.put("删除", true);
        threeList.add(threeMap2);
        threeMap2.put("查询", true);
        threeMap2.put("新增", false);
        threeMap2.put("修改", false);
        threeMap2.put("删除", false);
        threeList2.add(threeMap2);
        threeMap3.put("查询", true);
        threeMap3.put("新增", false);
        threeMap3.put("修改", false);
        threeMap3.put("审核", true);
        threeList3.add(threeMap3);

        List<Map> threeList10 = new ArrayList<>();
        List<Map> threeList11 = new ArrayList<>();
        Map<String, Object> threeMap10 = new HashMap<>();
        Map<String, Object> threeMap11 = new HashMap<>();
        threeMap10.put("查询", true);
        threeMap10.put("新增", false);
        threeMap10.put("修改", false);
        threeMap10.put("删除", true);
        threeList10.add(threeMap10);
        threeMap11.put("查询", true);
        threeMap11.put("新增", false);
        threeMap11.put("修改", false);
        threeMap11.put("删除", false);
        threeList11.add(threeMap11);

        twoMap.put("用户管理", threeList);
        twoMap.put("菜单管理", threeList2);
        twoMap.put("角色管理", threeList3);
        twoList.add(twoMap);

        twoMap2.put("黑名单管理", threeList10);
        twoMap2.put("白名单管理", threeList10);
        twoList2.add(twoMap2);

        oneMap.put("系统管理", twoList);
        oneMap.put("功能管理", twoList2);
        oneList.add(oneMap);
        return oneList;
    }

    public void backup(){
        //三层遍历循环
        List<Map> oneList = new ArrayList<>();
        Map<String, Object> oneMap = new HashMap<>();

        List<Map> twoList = new ArrayList<>();
        Map<String, Object> twoMap = new HashMap<>();

        List<Map> threeList = new ArrayList<>();
        List<Map> threeList2 = new ArrayList<>();
        List<Map> threeList3 = new ArrayList<>();
        Map<String, Object> threeMap = new HashMap<>();
        Map<String, Object> threeMap2 = new HashMap<>();
        Map<String, Object> threeMap3 = new HashMap<>();
        threeMap.put("查询", true);
        threeMap.put("新增", true);
        threeMap.put("修改", false);
        threeMap.put("删除", true);
        threeList.add(threeMap2);
        threeMap2.put("查询", true);
        threeMap2.put("新增", false);
        threeMap2.put("修改", false);
        threeMap.put("删除", false);
        threeList2.add(threeMap2);
        threeMap3.put("查询", true);
        threeMap3.put("新增", false);
        threeMap3.put("修改", false);
        threeMap3.put("审核", true);
        threeList3.add(threeMap3);

        twoMap.put("用户管理", threeList);
        twoMap.put("菜单管理", threeList2);
        twoMap.put("角色管理", threeList2);
        twoList.add(twoMap);

        oneMap.put("系统管理", twoList);
        oneList.add(oneMap);
    }
}
