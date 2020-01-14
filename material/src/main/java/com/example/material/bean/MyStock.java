package com.example.material.bean;
public class MyStock {
    //门店编码
    public String werks;
    //物料名字
    public String matnrName;
    //物料编码
    public String matnrCode;
    //物料批次
    public String batchNum;
    //数量
    public Integer stockStandardNum;
    //库位
    public String location;
    //FP0001总数
    public int sum1;
    //FP0002总数
    public int sum2;
    //储位
    public String positionCode;

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getMatnrName() {
        return matnrName;
    }

    public void setMatnrName(String matnrName) {
        this.matnrName = matnrName;
    }

    public String getMatnrCode() {
        return matnrCode;
    }

    public void setMatnrCode(String matnrCode) {
        this.matnrCode = matnrCode;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public Integer getStockStandardNum() {
        return stockStandardNum;
    }

    public void setStockStandardNum(Integer stockStandardNum) {
        this.stockStandardNum = stockStandardNum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSum1() {
        return sum1;
    }

    public void setSum1(int sum1) {
        this.sum1 = sum1;
    }

    public int getSum2() {
        return sum2;
    }

    public void setSum2(int sum2) {
        this.sum2 = sum2;
    }

    @Override
    public String toString() {
        return "MyStock{" +
                "werks='" + werks + '\'' +
                ", matnrName='" + matnrName + '\'' +
                ", matnrCode='" + matnrCode + '\'' +
                ", batchNum='" + batchNum + '\'' +
                ", stockStandardNum=" + stockStandardNum +
                ", location='" + location + '\'' +
                ", sum1=" + sum1 +
                ", sum2=" + sum2 +
                ", positionCode='" + positionCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        MyStock mystock=(MyStock)obj;
        if("".equals(mystock.getLocation())||null==mystock.getLocation()){
            return this.batchNum.equals(mystock.getBatchNum())
                    &&this.matnrCode.equals(mystock.getMatnrCode())
                    &&this.sum2==mystock.getSum2()
                    &&this.werks.equals(mystock.getWerks());
        }else{
            return this.batchNum.equals(mystock.getBatchNum())
                    &&this.matnrCode.equals(mystock.getMatnrCode())
                    &&this.location.equals(mystock.getLocation())
                    &&this.sum2==mystock.getSum2()
                    &&this.werks.equals(mystock.getWerks());
        }

    }
}
