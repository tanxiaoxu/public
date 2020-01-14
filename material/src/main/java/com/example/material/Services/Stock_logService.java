package com.example.material.Services;

import com.example.material.Dao.wm_stock_logMapper;
import com.example.material.bean.wm_stock_log;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

@Service
public class Stock_logService {
    @Resource
    public wm_stock_logMapper wm_stock_logMapper;
    public Map<String, List<wm_stock_log>> SelectStock_Log(
            String werks,String material,String batchNum,String shipmentNo){
        /**
         * 过滤库位
         *
         */
        Predicate<wm_stock_log> logsfilter=(logs)->logs.getLocation()!=null;
        /**
         *
         * 过滤储位
         */
        Predicate<wm_stock_log> Dlogsfilter=(logs)->logs.getPosition()!=null;
        List<wm_stock_log> wm_stock_logs = wm_stock_logMapper.selectLogByWerks(werks,material,batchNum,shipmentNo);
        /**
         * 将库位查询的结果进行分组
         *
         */
        List<wm_stock_log> list_map = wm_stock_logs.stream().
                filter(logsfilter).collect(toList());
        /**
         * 将储位查询的结果进行分组
         *
         */
        List list_dmap = wm_stock_logs.stream().
                filter(Dlogsfilter).collect(toList());
        Map<String,List<wm_stock_log>>result=new HashMap<String,List<wm_stock_log>>();
        result.put("logsList",list_map);
        result.put("DlogsList",list_dmap);
        return result;
        //return map;
    }

    /**
     *
     * 查询库存总计
     * @param werks
     * @param material
     * @param batchNum
     * @param shipmentNo
     * @return
     */
    public Map<String, Object> SelectStock_sum_Log(
            String werks,String material,String batchNum,String shipmentNo){
        /**
         * 过滤库位
         *
         */
        Predicate<wm_stock_log> logsfilter=(logs)->{
            if(logs.getLocation()!=null){
                return true;
            }else{
                return false;
            }
        };
        /**
         *
         * 过滤储位
         */
        Predicate<wm_stock_log> Dlogsfilter=(logs)->{
            if(logs.getPosition()!=null){
                return true;
            }else{
                return false;
            }
        };
        List<wm_stock_log> wm_stock_logs = wm_stock_logMapper.selectLogByWerks(werks,material,batchNum,shipmentNo);
        /**
         * 将库位查询的结果进行分组
         *
         */
/*        Function<wm_stock_log,wm_stock_log> function=(t1)->{
            IntSummaryStatistics statistics= wm_stock_logs.stream().
                    filter((t)->t.getMaterialCode()==t1.getMaterialCode())
                    .mapToInt(wm_stock_log::getFloatNum).summaryStatistics();
            t1.setStockcount((int) statistics.getSum());
            return t1;
        };
        List<wm_stock_log> list_map = wm_stock_logs.stream().
                filter(logsfilter).map(function).collect(toList());*/

        Map<String, Map<String, Map<String, Map<String, Integer>>>> map = wm_stock_logs.stream().filter(logsfilter).collect(
                groupingBy(wm_stock_log::getShipmentNo,
                        groupingBy(wm_stock_log::getLocation,
                                groupingBy(wm_stock_log::getMaterialCode,
                                        groupingBy(wm_stock_log::getBatchNum,
                                                summingInt(wm_stock_log::getFloatNum))))));
        /**
         * 将储位查询的结果进行分组
         *
         */
        //List list_dmap = wm_stock_logs.stream().filter(Dlogsfilter).collect(toList());
        Map<String, Map<String, Map<String, Map<String, Integer>>>> dmap = wm_stock_logs.stream().filter(Dlogsfilter).collect(
                groupingBy(wm_stock_log::getShipmentNo,
                        groupingBy(wm_stock_log::getMaterialCode,
                                groupingBy(wm_stock_log::getBatchNum,
                                        groupingBy(wm_stock_log::getPosition,
                                                summingInt(wm_stock_log::getFloatNum))))));
/*        *//**
         * 库位将map组合成list
         *
         *//*
        List<wm_stock_log> logsList = new ArrayList<>();
        BiConsumer<String,Map<String,Map<String,Map<String, Integer>>>>
                mapTolist=(ShipmentNo,map1)->{
                    map1.forEach((MaterialCode,map2)->{
                        map2.forEach((BatchNum,map3)->{
                            map3.forEach((Location,floatNum)->{
                                wm_stock_log logs=new wm_stock_log();
                                logs.setWerks(werks);
                                logs.setMaterialCode(MaterialCode);
                                logs.setBatchNum(BatchNum);
                                logs.setShipmentNo(ShipmentNo);
                                logs.setLocation(Location);
                                logs.setFloatNum(floatNum);
                                logsList.add(logs);
                            });
                        });
                    });};
        *//**
         * 储位将map组合成list
         *
         *//*
        List<wm_stock_log> DlogsList = new ArrayList<>();
        BiConsumer<String,Map<String,Map<String,Map<String,Integer>>>>
                DmapTolist=(ShipmentNo,map1)->{
            map1.forEach((MaterialCode,map2)->{
                map2.forEach((BatchNum,map3)->{
                    map3.forEach((Position,floatNum)->{
                        wm_stock_log Dlogs=new wm_stock_log();
                        Dlogs.setWerks(werks);
                        Dlogs.setMaterialCode(MaterialCode);
                        Dlogs.setBatchNum(BatchNum);
                        Dlogs.setShipmentNo(ShipmentNo);
                        Dlogs.setPosition(Position);
                        Dlogs.setFloatNum(floatNum);
                        DlogsList.add(Dlogs);
                    });
                });
            });};*/

/*        map.forEach(mapTolist);
        dmap.forEach(DmapTolist);*/
        System.out.println(map);
        System.out.println(dmap);

        Map<String,Object> result= new HashMap();
        result.put("logsList",map);
        result.put("DlogsList",dmap);
        return result;
        //return map;
    }
}
