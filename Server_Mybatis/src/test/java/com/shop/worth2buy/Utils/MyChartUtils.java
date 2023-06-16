package com.shop.worth2buy.Utils;

import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.jupiter.api.Test;

public class MyChartUtils {
    @Test
    public void creatMyLineChart(){
        DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
        mDataset.addValue(1,"12","2001");
        mDataset.addValue(1,"12","2002");
        mDataset.addValue(2,"12","2003");
        mDataset.addValue(4,"12","2004");
        mDataset.addValue(4,"1","2001");
        mDataset.addValue(5,"1","2002");
        mDataset.addValue(8,"1","2003");
        mDataset.addValue(2,"1","2004");
        String save_filePath = "D:\\Program Files (x86)\\Image\\zhe.png";
        MyChartUtil myChartUtil = new MyChartUtil();
        myChartUtil.createLineChart(mDataset,save_filePath);

    }
}
