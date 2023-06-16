package com.shop.worth2buy.service;

import com.shop.worth2buy.domain.Good;
import com.shop.worth2buy.domain.MyGood;
import com.shop.worth2buy.domain.Report;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GoodsService {
    public abstract String htryPriceGet(HttpServletRequest request);
    public abstract List<MyGood> getFollows(HttpServletRequest request);
    public abstract boolean getStarInfo(HttpServletRequest request);
    public abstract List<MyGood> getMatchGoods(HttpServletRequest request);
    public abstract Integer upLoadGood(HttpServletRequest request);
    public abstract void upLoadNewGood(HttpServletRequest request);
    public abstract boolean upLoadPic(HttpServletRequest request, MultipartFile file) throws IOException;
    public abstract Integer[] getMyDeserve(HttpServletRequest request);
    public abstract void UpdateDeserve(HttpServletRequest request);
    public abstract Integer[] getNotDeserve(HttpServletRequest request);
    public abstract void UpdateNotDeserve(HttpServletRequest request);
}
