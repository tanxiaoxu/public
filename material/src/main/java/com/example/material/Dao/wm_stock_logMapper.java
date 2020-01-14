package com.example.material.Dao;

import com.example.material.bean.wm_stock_log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface wm_stock_logMapper {
    int deleteByPrimaryKey(Long id);

    int insert(wm_stock_log record);

    int insertSelective(wm_stock_log record);

    wm_stock_log selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(wm_stock_log record);

    int updateByPrimaryKey(wm_stock_log record);

    List<wm_stock_log> selectLogByWerks(String werks,String material,String batchNum,String shipmentNo);

    List<wm_stock_log> selectDetilByWerks(String werks,String material);

    /*List<wm_stock_log> selectDetilByWerksBatch(String werks,wm_);*/

    List<wm_stock_log> selectLogByOrder(String Order);
}