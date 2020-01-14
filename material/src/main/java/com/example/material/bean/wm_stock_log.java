package com.example.material.bean;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Data
public class wm_stock_log {
    public Long id;
    //物料编码
    public String materialCode;
    //物料名字
    public String materialName;
    //原门店编码
    public String werks;
    //目标门店
    public String tarWerks;
    //原门店库位
    public String location;
    //目标库位
    public String tarLocation;
    //原门店储位
    public String position;
    //目标储位
    public String tarPosition;
    //批次
    public String batchNum;
    //数量
    public int floatNum;
    //单号
    public String shipmentNo;

    public Long creator;

    public Date createTime;

    public Long updater;

    public Date updateTime;

    public Byte status;

    public Byte deleteFlag;

    public int stockcount;

    public int getStockcount() {
        return stockcount;
    }

    public void setStockcount(int stockcount) {
        this.stockcount = stockcount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getTarWerks() {
        return tarWerks;
    }

    public void setTarWerks(String tarWerks) {
        this.tarWerks = tarWerks;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTarLocation() {
        return tarLocation;
    }

    public void setTarLocation(String tarLocation) {
        this.tarLocation = tarLocation;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTarPosition() {
        return tarPosition;
    }

    public void setTarPosition(String tarPosition) {
        this.tarPosition = tarPosition;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public int getFloatNum() {
        return floatNum;
    }

    public void setFloatNum(int floatNum) {
        this.floatNum = floatNum;
    }

    public String getShipmentNo() {
        return shipmentNo;
    }

    public void setShipmentNo(String shipmentNo) {
        this.shipmentNo = shipmentNo;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createTime);
        calendar.add(Calendar.HOUR_OF_DAY,-8);
        String format = simpleDateFormat.format(calendar.getTime());
        return format;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "wm_stock_log{" +
                "materialCode='" + materialCode + '\'' +
                ", werks='" + werks + '\'' +
                ", tarWerks='" + tarWerks + '\'' +
                ", location='" + location + '\'' +
                ", tarLocation='" + tarLocation + '\'' +
                ", position='" + position + '\'' +
                ", tarPosition='" + tarPosition + '\'' +
                ", batchNum='" + batchNum + '\'' +
                ", floatNum=" + floatNum +
                ", shipmentNo='" + shipmentNo + '\'' +
                ", stockcount=" + stockcount +
                '}';
    }
}