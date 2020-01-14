package com.example.material.Services;

import com.example.material.Dao.wm_stockMapper;
import com.example.material.Dao.wm_stock_detailMapper;
import com.example.material.bean.MyStock;
import com.example.material.bean.wm_stock_detail;
import com.example.material.util.MyFunction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
@Service
public class Stock_DetailServices {
    @Resource
    public com.example.material.Dao.wm_stockMapper wm_stockMapper;
    @Resource
    public com.example.material.Dao.wm_stock_detailMapper wm_stock_detailMapper;

    com.example.material.util.MyFunction MyFunction=new MyFunction();
    //查询储位明细
    public List<MyStock> selectStock_DetailByWerks(String werks) {
        List<MyStock> result2=new ArrayList<MyStock>();
        BiConsumer<String, Map<String ,Map<String,Integer>>>
                Dbiconnect =(MatnrCode, Map)->{
            Map.forEach((BatchNum,map1)->{
                map1.forEach((PositionCode,sum1)->{
                    MyStock myStock=new MyStock();
                    myStock.setWerks(werks);
                    myStock.setLocation(PositionCode);
                    myStock.setBatchNum(BatchNum);
                    myStock.setMatnrCode(MatnrCode);
                    myStock.setSum2(sum1);
                    result2.add(myStock);
                });
            });
        };
        List<wm_stock_detail> list = wm_stock_detailMapper.selectDetilByWerks(werks);
        Map<String, Map<String, Map<String, Integer>>> map2 = list.stream()
                .filter(a -> a.getStockNum() != null)
                .map(MyFunction.D_function)
                .collect(groupingBy(MyStock::getMatnrCode,
                        groupingBy(MyStock::getBatchNum,
                                groupingBy(MyStock::getPositionCode,
                                        summingInt(MyStock::getStockStandardNum)))));
        map2.forEach(Dbiconnect);
        return result2;
    }
}
