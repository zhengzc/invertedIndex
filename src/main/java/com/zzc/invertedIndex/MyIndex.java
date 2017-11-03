package com.zzc.invertedIndex;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @auther Created by johnson.zheng
 * @pray Code is far away from bug with the animal alpaca protecting. amen!
 * @date 2017/11/3
 */
@Slf4j
public class MyIndex {
    //自动生成编号
    private AtomicLong count = new AtomicLong(0);

    //原始数据
    private Map<Long, String> datas = new HashMap<Long, String>();
    //索引文件
    private TreeMap<String, Set<Long>> indexs = new TreeMap<>();

    /**
     * 添加被索引的数据
     * @param str
     */
    public void add(String str) {
        //自动生成编号，因为编号是自动生成的，所以不支持修改已索引的数据
        long id = count.getAndIncrement();
        datas.put(id, str);

        //分词后处理每个word
        for (String tmp : splitWord(str)) {
            //为每个word的前缀创建倒排索引
            for(int i = 1 ; i <= tmp.length() ; i++) {
                String key = tmp.substring(0, i).toLowerCase();
                if (indexs.containsKey(key)) {
                    indexs.get(key).add(id);
                } else {
                    Set<Long> list = new HashSet<>();
                    list.add(id);
                    indexs.put(key, list);
                }
            }
        }

//        log.debug("{}",indexs);
    }

    /**
     * 分词
     * @param str
     * @return
     */
    private String[] splitWord(String str) {
        String replaceStr =  StringUtils.replaceEach(str, new String[]{",", ".", "?"}, new String[]{" ", " ", " "});
        return replaceStr.split(" ");
    }

    public List<String> search(String key) {
        key = key.toLowerCase();
        Set<Long> rets = indexs.get(key);

        List<String> lists = new ArrayList<>();
        for (long tmp : rets) {
            lists.add(datas.get(tmp));
        }
        return lists;
    }
}
