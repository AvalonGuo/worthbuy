package com.shop.worth2buy.Utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.File;

//工具类生成折线图
public class MyChartUtil {
    /**
     *生成折线图
     * @author changqing
     * @param dataset
     * @param save_filepath
     * @return
     */
    public void createLineChart(DefaultCategoryDataset dataset,String save_filepath){
        try{
            JFreeChart chart= ChartFactory.createLineChart("xx历史价格","月份","价格",
                    dataset, PlotOrientation.VERTICAL,false,true,true);
            //x轴y轴字体
            Font xyfont = new Font("宋体",Font.BOLD,12);
            Font titlefont = new Font("宋体",Font.BOLD,20);
            chart.getTitle().setFont(titlefont);         //设置表格标题字体
            chart.setBackgroundPaint(Color.white); //设置表格背景颜色
            CategoryPlot categoryPlot = chart.getCategoryPlot();
            //xy轴字体设置
            categoryPlot.getDomainAxis().setLabelFont(xyfont);
            categoryPlot.getRangeAxis().setLabelFont(xyfont);
            //设置plot的背景色
            categoryPlot.setBackgroundPaint(Color.white);
            //设置y轴网格线颜色
            categoryPlot.setRangeGridlinePaint(Color.lightGray);
            categoryPlot.setNoDataMessage("没有历史价格");
            ChartUtilities.saveChartAsPNG(new File(save_filepath),chart,356,275);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
