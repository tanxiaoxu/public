package com.example.material.Services;

import com.example.material.Dao.wm_stockMapper;
import com.example.material.Dao.wm_stock_detailMapper;
import com.example.material.bean.MyStock;
import com.example.material.bean.wm_stock;
import com.example.material.util.MyFunction;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.stream.Stream;
import static java.util.stream.Collectors.*;


@Service
public class MaterialServices {
    @Resource
    public wm_stockMapper wm_stockMapper;
    @Resource
    public wm_stock_detailMapper wm_stock_detailMapper;
    @Autowired
    StockServices stockServices;
    @Autowired
    Stock_DetailServices stock_detailServices;
    final DefaultEventExecutor executor = new DefaultEventExecutor();
/*    List<MyStock> result2=new ArrayList<MyStock>();

    List<MyStock> result4=new ArrayList<MyStock>();*/
    MyFunction MyFunction=new MyFunction();

    public String test(){
        wm_stock li=wm_stockMapper.selectByPrimaryKey(111671755349098496l);
        return li.toString();
    }
    //通过门店查询物料信息
    public List<MyStock> selectWerk(String works){
        List<MyStock> result=new ArrayList<MyStock>();
         BiConsumer<String, Map<String ,Map<String,Long>>>
                C_biconnect =(Location, Map)->{
            Map.forEach((matnrCode,map)->{
                map.forEach((batchNum,sum1)->{
                    MyStock myStock=new MyStock();
                    myStock.setWerks("1261");
                    myStock.setLocation(Location);
                    myStock.setBatchNum(batchNum);
                    myStock.setMatnrCode(matnrCode);
                    myStock.setSum1((int) sum1.longValue());
                    result.add(myStock);
                });
            });
        };
        List<wm_stock> li  =  wm_stockMapper.selectByWerks(works);
        Stream stream= li.stream();
        //查询库存，数量不为0 的库存门店批次和物料编码重复的数据
        Map<String,Map<String ,Map<String,Long>>> map =
                (Map<String, Map<String, Map<String, Long>>>)
                        stream.map(MyFunction.function).
                filter(a -> ((MyStock) a).getStockStandardNum() != null)
                .collect(groupingBy(MyStock::getLocation,
                        groupingBy(MyStock::getMatnrCode,
                                groupingBy(MyStock::getBatchNum,counting()))));
        map.forEach(C_biconnect);
        //查询重复值
        List<MyStock> collect = result.stream().filter(list -> {
            if (list.getSum1() > 2) {
                return true;
            } else {
                return false;
            }
        }).collect(toList());
        System.out.println(collect.toString());
        return collect;
    }
    //储位库存合并查询
    public Map<String, List<MyStock>> selectAllByWerks(String werks){

        //储位集合
        //final SimpleThreadPoolTaskExecutor simpleThreadExecutor = new SimpleThreadPoolTaskExecutor();

        Callable runnable =new Callable<List<MyStock>>() {
            @Override
            public List<MyStock> call() {
                //库位集合
                List<MyStock> list =new ArrayList<MyStock>();
                list=stockServices.selectStockByWerks(werks);
                return list;
            }
        };
        Callable runnable2 =new Callable() {
            @Override
            public List<MyStock> call() {
                //储位集合
                List<MyStock> list2=new ArrayList<MyStock>();
                list2=stock_detailServices.selectStock_DetailByWerks(werks);
                return list2;
            }
        };
       Future future=executor.submit(runnable);
       Future future2=executor.submit(runnable2);
        Map<String,List<MyStock>> map=new HashMap<String,List<MyStock>>();
        List listall=new ArrayList();
        future.addListener(new FutureListener() {
            @Override
            public void operationComplete(Future future) throws Exception {
                if (future.isSuccess()){
                    map.put("list1", (List<MyStock>) future.get());
                    listall.add(future.get());
                    System.out.println("list执行完毕！！！");
                    //
                }
            }
        });
        future2.addListener(new FutureListener() {
            @Override
            public void operationComplete(Future future) throws Exception {
                if(future.isSuccess()){
                    map.put("list2", (List<MyStock>) future.get());
                    //listall.add(future.get());
                    System.out.println("list2执行完毕！！！");
                }
            }
        });
        //库位
        //List<MyStock> list = stockServices.selectStockByWerks(werks);
        //储位
        //List<MyStock> list2=stock_detailServices.selectStock_DetailByWerks(werks);
        while(true){
            if(future.isSuccess()&&future2.isSuccess()){
                break ;
            }
        }
        //合并返回
        System.out.println(map.toString());
        return map;
    }

    //查询储位差异数量
    public Map<String, List<MyStock>> selectDefByWerks(String werks){

        /* BiConsumer<String, Map<String ,Map<String,Long>>>
                C_biconnect =(Location, Map)->{
            Map.forEach((matnrCode,map)->{
                map.forEach((batchNum,sum1)->{
                    MyStock myStock=new MyStock();
                    myStock.setWerks(werks);
                    myStock.setLocation(Location);
                    myStock.setBatchNum(batchNum);
                    myStock.setMatnrCode(matnrCode);
                    myStock.setSum1((int) sum1.longValue());
                    result.add(myStock);
                });
            });
        };
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
         BiConsumer<String, Map<String ,Map<String,Integer>>>
                D_biconnect =(MatnrCode, Map)->{
            Map.forEach((BatchNum,map)->{
                map.forEach((PositionCode,sum1)->{
                    MyStock myStock=new MyStock();
                    myStock.setWerks(werks);
                    myStock.setLocation(PositionCode);
                    myStock.setBatchNum(BatchNum);
                    myStock.setMatnrCode(MatnrCode);
                    myStock.setSum2(sum1);
                    result3.add(myStock);
                });
            });
        };
        List<wm_stock> li  =  wm_stockMapper.selectByWerks(werks);
       Stream stream= li.stream();
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
       //查询物料库存组合统计总和数量
       Map<String,Map<String ,Map<String,Integer>>> map =
               (Map<String, Map<String, Map<String, Integer>>>) stream
                .map(MyFunction.function)
                .filter(a -> ((MyStock) a).getStockStandardNum() != null)
                .collect(groupingBy(MyStock::getLocation,
                        groupingBy(MyStock::getMatnrCode,
                        groupingBy(MyStock::getBatchNum,
                                summingInt(MyStock::getStockStandardNum)))));
        map.forEach(biconnect);
        //查询储位组合数量分组统计总和
        List<wm_stock_detail> list=wm_stock_detailMapper.selectDetilByWerks(werks);
        Map<String, Map<String, Map<String, Integer>>> map2 = list.stream().filter(a -> a.getStockNum() != null)
                .map(MyFunction.D_function)
                .collect(groupingBy(MyStock::getMatnrCode,
                        groupingBy(MyStock::getBatchNum,
                                groupingBy(MyStock::getPositionCode,
                                        summingInt(MyStock::getStockStandardNum)))));
        map2.forEach(Dbiconnect);*/
        //库位
        List<MyStock> list2=stock_detailServices.selectStock_DetailByWerks(werks);
        List<MyStock> list1=stockServices.selectStockByWerks(werks);

        //储位
        List<MyStock> collect2 = list2.stream().distinct().collect(toList());
        //库位
        List<MyStock> collect1 = list1.stream().distinct().collect(toList());
        //将库位组合成储位统计
        List<MyStock> result3=new ArrayList<MyStock>();
        BiConsumer<String, Map<String ,Map<String,Integer>>>
                D_biconnect =(MatnrCode, Map)->{
            Map.forEach((BatchNum,map)->{
                map.forEach((PositionCode,sum1)->{
                    MyStock myStock=new MyStock();
                    myStock.setWerks(werks);
                    myStock.setLocation(PositionCode);
                    myStock.setBatchNum(BatchNum);
                    myStock.setMatnrCode(MatnrCode);
                    myStock.setSum2(sum1);
                    result3.add(myStock);
                });
            });
        };
        Map<String, Map<String, Map<String, Integer>>> map3 = collect1.stream().map(c1 -> {
                    if (c1.getLocation() == "1001"||c1.getLocation().equals("1001")) {
                        c1.setPositionCode("FP0001");
                        c1.setSum2(c1.getSum1());
                        return c1;
                    } else {
                        c1.setPositionCode("FP0002");
                        c1.setSum2(c1.getSum1());
                        return c1;
                    }
                }
        ).collect(groupingBy(MyStock::getMatnrCode,
                groupingBy(MyStock::getBatchNum,
                        groupingBy(MyStock::getPositionCode,
                                summingInt(MyStock::getSum2)))));
        map3.forEach(D_biconnect);
        //库位组合成储位的结果
        List<MyStock> collect3 = result3.stream().distinct().collect(toList());
        List<MyStock> Tmast=new ArrayList<MyStock>();
        //求去重求出储位和库位的差异 储位-库位 储位和库位有差异的储位信息
        collect3.forEach(c3->{
           if(collect2.contains(c3)){
               collect2.remove(c3);
               Tmast.add(c3);
           }
        });
        Map<String,List<MyStock>> mystock=new HashMap<String,List<MyStock>>();
        //差异信息
        mystock.put("collect2",collect2);
        //储位信息
        mystock.put("collect3",collect3);
        return mystock;
    }

    //查询储位差异数量
    public Map<String, List<MyStock>> selectSumDefByWerks(String werks){
        //库位
        List<MyStock> list = stockServices.selectStockByWerks(werks);
        //储位
        List<MyStock> list2=stock_detailServices.selectStock_DetailByWerks(werks);
        //库位统计
        Map<String, Map<String, Integer>> collect = list.stream().
                collect(groupingBy(MyStock::getMatnrCode,
                        groupingBy(MyStock::getBatchNum, summingInt(MyStock::getSum1))));
        //储位
        Map<String, Map<String, Integer>> collect2 = list2.stream().
                collect(groupingBy(MyStock::getMatnrCode,
                        groupingBy(MyStock::getBatchNum, summingInt(MyStock::getSum2))));
        //将库位组合成储位统计
        //库位
        List<MyStock> result3=new ArrayList<MyStock>();
        //储位
        List<MyStock> result4=new ArrayList<MyStock>();
        //库位转换成List
        BiConsumer<String ,Map<String,Integer>>
                D_biconnect =(MatnrCode, Map)->{
            Map.forEach((BatchNum,sum)->{
                MyStock myStock=new MyStock();
                myStock.setWerks(werks);
                myStock.setBatchNum(BatchNum);
                myStock.setMatnrCode(MatnrCode);
                myStock.setSum2(sum);
                result3.add(myStock);
            });
        };
        //储位转换成List
        BiConsumer<String ,Map<String,Integer>>
                C_biconnect =(MatnrCode, Map)->{
            Map.forEach((BatchNum,sum)->{
                MyStock myStock=new MyStock();
                myStock.setWerks(werks);
                myStock.setBatchNum(BatchNum);
                myStock.setMatnrCode(MatnrCode);
                myStock.setSum2(sum);
                result4.add(myStock);
            });
        };
        //库位
        collect.forEach(D_biconnect);
        //储位
        collect2.forEach(C_biconnect);
        //
        List<MyStock> DTmast=new ArrayList<MyStock>(result4);
        List<MyStock> Tmast=new ArrayList<MyStock>(result3);
        //求去重求出储位和库位的差异 储位-库位 储位和库位有差异的储位信息
        result3.forEach(c3->{
            if(result4.contains(c3)){
                result4.remove(c3);
            }
        });
        Tmast.forEach(c2->{
            if(result3.contains(c2)){
                result3.remove(c2);
            }
        });
        result4.addAll(result3);
        System.out.println(collect2);
        Map<String,List<MyStock>> mystock=new HashMap<String,List<MyStock>>();
        //库位
        mystock.put("collect1",Tmast);
        //差异信息
        mystock.put("collect2",result4);
        //储位信息
        mystock.put("collect3",DTmast);
        return mystock;
    }


    //查询整个公司库存
    public String selectAllWerk(String works){
    /*            List<wm_stock> li  =  wm_stockMapper.selectAll();
                Stream stream= li.stream();
                //查询整个公司库存，数量不为0 的库存门店批次和物料编码重复的数据
                Map<String,Map<String ,Map<String,Long>>> map =
                        (Map<String, Map<String, Map<String, Long>>>)
                                stream.map(MyFunction.function).
                                        filter(a -> ((MyStock) a).getStockStandardNum() != null)
                                        .collect(groupingBy(MyStock::getLocation,
                                                groupingBy(MyStock::getMatnrCode,
                                                        groupingBy(MyStock::getBatchNum,counting()))));
                map.forEach(C_biconnect);
                List<MyStock> collect = result.stream().filter(list -> {
                    if (list.getSum1() > 2) {
                        return true;
                    } else {
                        return false;
                    }
                }).collect(toList());
                System.out.println(collect.toString());
                return collect;*/
                return  "";
            }



    }
