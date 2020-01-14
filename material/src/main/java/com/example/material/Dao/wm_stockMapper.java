package com.example.material.Dao;

import com.example.material.bean.wm_stock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface wm_stockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(wm_stock record);

    int insertSelective(wm_stock record);

    wm_stock selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(wm_stock record);

    int updateByPrimaryKey(wm_stock record);

    List<wm_stock> selectByWerks(String werks);

    List<wm_stock> selectAll();

}