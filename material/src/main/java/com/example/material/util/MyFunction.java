package com.example.material.util;

import com.example.material.bean.MyStock;
import com.example.material.bean.wm_stock;
import com.example.material.bean.wm_stock_detail;

import java.util.function.Function;

public class MyFunction {
    public Function<wm_stock, MyStock> function=(wm_stock)->{

        MyStock mystock=new MyStock();
        mystock.setWerks(wm_stock.getWerks());
        mystock.setLocation(wm_stock.getLocation());
        mystock.setBatchNum(wm_stock.getBatchNum());
        mystock.setMatnrCode(wm_stock.getMatnrCode());
        mystock.setMatnrName(wm_stock.getMatnrName());
        mystock.setStockStandardNum(wm_stock.getStockStandardNum());
        //mystock.setSum1(mystock.getSum1());
//        if(mystock.getLocation()=="1001"){
//            int i=mystock.getSum1();
//            mystock.setSum1(++i);
//        }
        return mystock;
    };
    public Function<wm_stock_detail, MyStock> D_function=(wm_stock_detail)->{
        MyStock mystock=new MyStock();
        mystock.setWerks(wm_stock_detail.getStore());
        mystock.setPositionCode(wm_stock_detail.getPositionCode());
        mystock.setBatchNum(wm_stock_detail.getBatchNum());
        mystock.setMatnrCode(wm_stock_detail.getMaterialCode());
        mystock.setMatnrName(wm_stock_detail.getMaterialName());
        mystock.setStockStandardNum(wm_stock_detail.getStockNum());
        //mystock.setSum1(wm_stock_detail.getSum1());
//        if(mystock.getLocation()=="1001"){
//            int i=mystock.getSum1();
//            mystock.setSum1(++i);
//        }
        return mystock;
    };

}
