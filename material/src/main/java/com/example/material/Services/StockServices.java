package com.example.material.Services;

import com.example.material.Dao.wm_stockMapper;
import com.example.material.Dao.wm_stock_detailMapper;
import com.example.material.bean.MyStock;
import com.example.material.bean.wm_stock;
import com.example.material.util.MyFunction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
@Service
public class StockServices {
    @Resource
    public wm_stockMapper wm_stockMapper;
    @Resource
    public wm_stock_detailMapper wm_stock_detailMapper;
    MyFunction MyFunction=new MyFunction();
    //查询库存明细
    public List<MyStock> selectStockByWerks(String werks){
        List<MyStock> result=new ArrayList<MyStock>();
        BiConsumer<String, Map<String ,Map<String,Integer>>>
                biconnect =(Location, Map)->{
            Map.forEach((matnrCode,map)->{
                map.forEach((batchNum,sum1)->{
                    MyStock myStock=new MyStock();
                    myStock.setWerks(werks);
                    myStock.setLocation(Location);
                    myStock.setBatchNum(batchNum);
                    myStock.setMatnrCode(matnrCode);
                    myStock.setSum1(sum1);
                    result.add(myStock);
                });
            });
        };
        List<wm_stock> li  =  wm_stockMapper.selectByWerks(werks);
        Stream stream= li.stream();
        //查询物料库存组合统计总和数量
        Map<String,Map<String , Map<String,Integer>>> map =
                (Map<String, Map<String, Map<String, Integer>>>) stream
                        .map(MyFunction.function)
                        .filter(a -> ((MyStock) a).getStockStandardNum() != null)
                        .collect(groupingBy(MyStock::getLocation,
                                groupingBy(MyStock::getMatnrCode,
                                        groupingBy(MyStock::getBatchNum,
                                                summingInt(MyStock::getStockStandardNum)))));
        map.forEach(biconnect);
        System.out.println(map.toString());
       return result;
    }
}
