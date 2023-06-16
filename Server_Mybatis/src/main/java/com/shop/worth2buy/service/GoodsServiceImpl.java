package com.shop.worth2buy.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.shop.worth2buy.Utils.MyChartUtil;
import com.shop.worth2buy.domain.Good;
import com.shop.worth2buy.domain.MyGood;
import com.shop.worth2buy.domain.User;
import com.shop.worth2buy.mapper.GoodMapper;
import com.shop.worth2buy.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

@Service
public class GoodsServiceImpl implements GoodsService{
    private final Logger logger = Logger.getLogger("GoodsServiceImpl");
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodMapper goodMapper;
    @Override
    public String htryPriceGet(HttpServletRequest request){
        Integer id =  Integer.parseInt(request.getParameter("Id"));
        DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
        Good good =  goodMapper.SelectByID(id);
        String jsonStr = good.getHtry_price();
        Map<String, String> jsonMap = JSON.parseObject(jsonStr, new TypeReference<HashMap<String, String>>() {
        });
        for (Map.Entry<String, String> entry:jsonMap.entrySet()){
            Integer key = Integer.parseInt(entry.getKey());
            Double value = Double.parseDouble(entry.getValue());
            mDataset.addValue(value,"1",key);
        }
        String png = "lineChart"+id+".png";
        String save_path = "D:\\Program Files (x86)\\Shop\\images\\"+png;
        String re_path = "../../images/"+png;
        MyChartUtil myChartUtil = new MyChartUtil();
        myChartUtil.createLineChart(mDataset,save_path);
        good.setLine(re_path);
        goodMapper.UpdateGood(good);
        return re_path;
    }
    @Override
    public List<MyGood> getFollows(HttpServletRequest request){
        String follows;
        String uid = request.getParameter("uid");
        User user =  userMapper.SelectByUid(uid);
        follows=user.getFollows();
        String[] list = follows.split(",");
        Integer[] integers = new Integer[list.length];
        for(int i=0;i<list.length;i++){
            integers[i] = Integer.parseInt(list[i]);
        }
        List<MyGood> goodList = new ArrayList<>();
        for (int i=0;i<integers.length;i++){
            Good good = goodMapper.SelectByID(integers[i]);
            MyGood myGood = new MyGood(good);
            goodList.add(myGood);
        }
        return goodList;
    }
    @Override
    public boolean getStarInfo(HttpServletRequest request){
        String uid = request.getParameter("uid");
        Integer goodID = Integer.parseInt(request.getParameter("goodID"));
        Integer star = Integer.parseInt(request.getParameter("star"));
        String follows;
        User user =  userMapper.SelectByUid(uid);
        follows=user.getFollows();
        String[] list = follows.split(",");

        if (star==1){
            follows = follows+goodID+',';
            user.setFollows(follows);
            userMapper.UpdateUser(user);
            return true;
        }else {
            String new_follows="";
            for (int i=0;i< list.length;i++){
                if (Integer.parseInt(list[i])!=goodID){
                    new_follows = new_follows+list[i]+',';
                }
            }
            user.setFollows(new_follows);
            userMapper.UpdateUser(user);
            return false;
        }
    }
    @Override
    public List<MyGood> getMatchGoods(HttpServletRequest request){
        String match = request.getParameter("match");
        List<Good> goodList = goodMapper.SelectByWord(match);
        List<MyGood> myGoodList = new ArrayList<>();
        for(Iterator<Good> it=goodList.iterator(); it.hasNext();){
            Good good = it.next();
            MyGood myGood = new MyGood(good);
            myGoodList.add(myGood);
        }
        return myGoodList;
    }
    @Override
    public Integer upLoadGood(HttpServletRequest request){
        String uid = request.getParameter("uid");
        String com = request.getParameter("input");
        String gdname= request.getParameter("gdname");
        Integer currentPrice = Integer.parseInt(request.getParameter("currentPrice"));
        String htryPrice = request.getParameter("htryPrice");
        Good good = new Good(gdname,currentPrice,htryPrice);
        goodMapper.InsertNewGood(good);
        return 1;
    }
    @Override
    public void upLoadNewGood(HttpServletRequest request){
        String gdname = request.getParameter("gdname");
        Good selector = goodMapper.SelectByGdname(gdname);
        if (selector==null){
            upLoadGood(request);
        }else{
            String htryPrice = request.getParameter("htryPrice");
            String str = selector.getHtry_price();
            Map<String, Integer> jsonMap = JSON.parseObject(str, new TypeReference<HashMap<String, Integer>>() {
            });
            Map<String, Integer> htryMap = JSON.parseObject(htryPrice, new TypeReference<HashMap<String, Integer>>() {
            });
            Iterator<Map.Entry<String,Integer>> iterator1 = jsonMap.entrySet().iterator();
            Iterator<Map.Entry<String,Integer>> iterator2 = htryMap.entrySet().iterator();
            while (iterator1.hasNext()&&iterator2.hasNext()){
                Map.Entry<String,Integer> entry1 = iterator1.next();
                Map.Entry<String,Integer> entry2 = iterator2.next();
                Integer v1 = entry1.getValue();
                Integer v2 = entry2.getValue();
                String k = entry2.getKey();
                if (v1!=0&&v2!=0){
                    Integer q = Integer.min(v1,v2);
                    htryMap.put(k,q);
                }else {
                    Integer q = v1!=0?v1:v2;
                    htryMap.put(k,q);
                }
            }
            htryPrice = JSON.toJSONString(htryMap);
            goodMapper.UpdatePrice(htryPrice,gdname);
        }
    }
    @Override
    public boolean upLoadPic(HttpServletRequest request, @RequestParam("file")MultipartFile file) throws IOException {
        String upLoad_path = "D:/Program Files (x86)/Shop/images/GoodPic";
        String originalName = file.getOriginalFilename();
        String ext = "";
        String gdname = request.getParameter("gdname");
        String i = request.getParameter("i");
        if (originalName.endsWith(".jpg")){
            ext = ".jpg";
        }else if (originalName.endsWith(".png")){
            ext = ".png";
        }
        String filename = gdname+i+ext;
        Good good = goodMapper.SelectByGdname(gdname);
        String first_pic = "../../images/GoodPic/"+gdname+"0"+ext;
        String pic_src = "";
        if (i.equals("0")){
            pic_src=first_pic;
        }else {
            pic_src = good.getPics_src();
            pic_src+=","+"../../images/GoodPic/"+gdname+i+ext;
        }
        good.setPics_src(pic_src);
        good.setFirst_pic(first_pic);
        goodMapper.UpdateGood(good);
        File targetFile = new File(upLoad_path,filename);
        FileUtil.writeBytes(file.getBytes(),targetFile);
        System.out.println("成功");
        return  false;
    }
    @Override
    public Integer[] getMyDeserve(HttpServletRequest request){
        String uid = request.getParameter("uid");
        User user = userMapper.SelectByUid(uid);
        String deserve_list = user.getDeserve_list();
        if (deserve_list!=""){
            String[] strings = deserve_list.split(",");
            Integer[] deserve = new Integer[strings.length];
            for(int i=0;i<strings.length;i++){
                deserve[i] = Integer.parseInt(strings[i]);
            }
            return  deserve;
        }
        else {
            Integer[] deserve = new Integer[0];
            return deserve;
        }

    }
    @Override
    public void UpdateDeserve(HttpServletRequest request){
        Integer deserve = Integer.parseInt(request.getParameter("deserve"));
        String uid = request.getParameter("uid");
        Integer GoodID = Integer.parseInt(request.getParameter("GoodID"));
        User user = userMapper.SelectByUid(uid);
        String deserve_list = user.getDeserve_list();
        deserve_list = deserve_list+GoodID+',';
        user.setDeserve_list(deserve_list);
        goodMapper.UpdateDeserve(deserve,GoodID);
        userMapper.UpdateMyDeserve(user);
    }
    @Override
    public Integer[] getNotDeserve(HttpServletRequest request){
        String uid = request.getParameter("uid");
        User user = userMapper.SelectByUid(uid);
        String not_deserve_list = user.getNot_deserve_list();
        if (not_deserve_list!=""){
            String[] strings = not_deserve_list.split(",");
            Integer[] not_deserve = new Integer[strings.length];
            for(int i=0;i<strings.length;i++){
                not_deserve[i] = Integer.parseInt(strings[i]);
            }
            return  not_deserve;
        }
        else{
            Integer[] not_deserve=new Integer[1];
            return not_deserve;
        }

    }
    @Override
    public void UpdateNotDeserve(HttpServletRequest request){
        System.out.println(request.getParameter("not_deserve"));
        Integer not_deserve = Integer.parseInt(request.getParameter("not_deserve"));

        String uid = request.getParameter("uid");
        Integer GoodID = Integer.parseInt(request.getParameter("GoodID"));
        User user = userMapper.SelectByUid(uid);
        String not_deserve_list = user.getNot_deserve_list();
        not_deserve_list = not_deserve_list+GoodID+",";
        user.setNot_deserve_list(not_deserve_list);
        userMapper.UpdateNotDeserve(user);
        goodMapper.UpdateNotDeserve(not_deserve,GoodID);
    }
}
