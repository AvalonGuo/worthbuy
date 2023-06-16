package com.shop.worth2buy.domainTests;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.shop.worth2buy.domain.Good;
import com.shop.worth2buy.domain.MyGood;
import com.shop.worth2buy.mapper.GoodMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.*;

@SpringBootTest
public class GoodMapperTests {
    @Autowired
    GoodMapper goodMapper;
    @Test
    public void JSONStr2Map(){

        //构造一个map{2002=790, 2001=780}
        Map<String,Object> map = new HashMap<>();
        map.put("2","780");
        map.put("3","790");
        map.put("4","770");
        map.put("5","790");
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
        //从数据库查商品,并得到JSONStr
        Good good = goodMapper.SelectByID(6);
        String jsonStr = good.getHtry_price();
        System.out.println(jsonStr);
        Map<String, String> jsonMap = JSON.parseObject(jsonStr, new TypeReference<HashMap<String, String>>() {
        });
        System.out.println(jsonMap);
        JSONObject sqlObject = JSONObject.parseObject(JSON.toJSONString(jsonMap));
        //assert (map.equals(jsonMap)):"Map are different";
        //assert (jsonObject.equals(sqlObject)):"JSONObject are different";
        for(Map.Entry<String,Object> entry:sqlObject.entrySet()){
            System.out.println(entry.getKey().getClass().getTypeName() + " : " + entry.getValue().getClass().getTypeName());
        }
    }
    @Test
    public void test(){
        Good good = goodMapper.SelectByGdname("苹果14pro手机壳");
        MyGood myGood = new MyGood(good);
        System.out.println(myGood);
    }
    @Test void upLoadNewGoodTest(){
        String str = goodMapper.SelectByID(13).getHtry_price();
        String newstr = "{\"2\": \"17\", \"3\": \"8\", \"4\": \"7\", \"5\": \"8\"}";
        Map<String, Integer> jsonMap = JSON.parseObject(str, new TypeReference<HashMap<String, Integer>>() {
        });
        Map<String, Integer> newjsonMap = JSON.parseObject(newstr, new TypeReference<HashMap<String, Integer>>() {
        });
        System.out.println(jsonMap);
        System.out.println(newjsonMap);
        Iterator<Map.Entry<String,Integer>> iterator = jsonMap.entrySet().iterator();
        Iterator<Map.Entry<String,Integer>> myiterator = newjsonMap.entrySet().iterator();
        while (iterator.hasNext()&&myiterator.hasNext()){
            Map.Entry<String,Integer> entry = iterator.next();
            Map.Entry<String,Integer> myentry = myiterator.next();
            Integer v = entry.getValue();
            Integer myv = myentry.getValue();
            String mkey  = myentry.getKey();
            if (v!=0&&myv!=0){
                Integer q = Integer.min(v,myv);
                newjsonMap.put(mkey,q);
            }else {
                Integer q = v!=0?v:myv;
                newjsonMap.put(mkey,q);
            }
        }
        System.out.println(newjsonMap);
    }
}
