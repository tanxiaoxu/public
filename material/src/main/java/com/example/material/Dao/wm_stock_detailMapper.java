package com.example.material.Dao;

import com.example.material.bean.wm_stock;
import com.example.material.bean.wm_stock_detail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface wm_stock_detailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(wm_stock_detail record);

    int insertSelective(wm_stock_detail record);

    wm_stock_detail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(wm_stock_detail record);

    int updateByPrimaryKey(wm_stock_detail record);

    List<wm_stock_detail> selectDetilByWerks(String werks);
}