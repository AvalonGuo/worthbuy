package com.shop.worth2buy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shop.worth2buy.domain.MyGood;
import com.shop.worth2buy.service.GoodsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "goodsModule")
public class goodsModule {
    @Autowired
    GoodsService goodsService;
    @GetMapping("test")
    public JSON test(){
        Map<String,Object> map = new HashMap<>();
        map.put("2001","780");
        map.put("2002","790");
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
        return jsonObject;
    }
    @GetMapping("htryPriceGet")
    public String htryPriceGet(HttpServletRequest req){
            return goodsService.htryPriceGet(req);
    }
    @GetMapping("FollowsGet")
    public List<MyGood> getFollows(HttpServletRequest request){
        return goodsService.getFollows(request);
    }
    @GetMapping("getStarInfo")
    public boolean getStarInfo(HttpServletRequest request){
        return goodsService.getStarInfo(request);
    }
    @GetMapping("getMatchGoods")
    public List<MyGood> getMatchGoods(HttpServletRequest request){
        return  goodsService.getMatchGoods(request);
    }
    @GetMapping("upLoadGood")
    public Integer upLoadGood(HttpServletRequest request){
        return goodsService.upLoadGood(request);
    }
    @GetMapping("upLoadNewGood")
    public void upLoadNewGood(HttpServletRequest request){
        goodsService.upLoadNewGood(request);
    }
    @RequestMapping("upLoadPic")
    public boolean upLoadPic(HttpServletRequest request,@RequestParam("file") MultipartFile file) throws IOException {
        return  goodsService.upLoadPic(request,file);
    }
    @GetMapping("getMyDeserve")
    public Integer[] getMyDeserve(HttpServletRequest request){
        return goodsService.getMyDeserve(request);
    }
    @GetMapping("getNotDeserve")
    public Integer[] getNotDeserve(HttpServletRequest request){
        return goodsService.getNotDeserve(request);
    }
    @GetMapping("UpdateDeserve")
    public void UpdateDeserve(HttpServletRequest request){
        goodsService.UpdateDeserve(request);
    }
    @GetMapping("UpdateNotDeserve")
    public void UpdateNotDeserve(HttpServletRequest request){
        goodsService.UpdateNotDeserve(request);
    }

}
