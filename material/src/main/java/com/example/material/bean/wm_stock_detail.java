package com.example.material.bean;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
@Data
public class wm_stock_detail {
    public Long id;

    public String store;

    public String positionCode;

    public String materialCode;

    public String materialName;

    public Integer stockNum;

    public String specUnit;

    public Integer stockSpecNum;

    public String standardUnit;

    public String batchNum;

    public Date wranTime;

    public Integer isLock;

    public Integer type;

    public String productionLicense;

    public String productionRegistration;

    public Integer pExpirationDate;

    public String mMachineLicence;

    public String mMachineEbcr;

    public String meName;

    public String meDmpLicense;

    public String mePrCertificate;

    public String meBatchNumber;

    public Date meManufactureTime;

    public Integer shelfLife;

    public String seMdpLicense;

    public String supplierMaterialCode;

    public String supplierMaterialName;

    public String stCondition;

    public Long creator;

    public Date createTime;

    public Long updater;

    public Date updateTime;

    public Integer status;

    public Integer deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store == null ? null : store.trim();
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode == null ? null : positionCode.trim();
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode == null ? null : materialCode.trim();
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    public String getSpecUnit() {
        return specUnit;
    }

    public void setSpecUnit(String specUnit) {
        this.specUnit = specUnit == null ? null : specUnit.trim();
    }

    public Integer getStockSpecNum() {
        return stockSpecNum;
    }

    public void setStockSpecNum(Integer stockSpecNum) {
        this.stockSpecNum = stockSpecNum;
    }

    public String getStandardUnit() {
        return standardUnit;
    }

    public void setStandardUnit(String standardUnit) {
        this.standardUnit = standardUnit == null ? null : standardUnit.trim();
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum == null ? null : batchNum.trim();
    }

    public Date getWranTime() {
        return wranTime;
    }

    public void setWranTime(Date wranTime) {
        this.wranTime = wranTime;
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getProductionLicense() {
        return productionLicense;
    }

    public void setProductionLicense(String productionLicense) {
        this.productionLicense = productionLicense == null ? null : productionLicense.trim();
    }

    public String getProductionRegistration() {
        return productionRegistration;
    }

    public void setProductionRegistration(String productionRegistration) {
        this.productionRegistration = productionRegistration == null ? null : productionRegistration.trim();
    }

    public Integer getpExpirationDate() {
        return pExpirationDate;
    }

    public void setpExpirationDate(Integer pExpirationDate) {
        this.pExpirationDate = pExpirationDate;
    }

    public String getmMachineLicence() {
        return mMachineLicence;
    }

    public void setmMachineLicence(String mMachineLicence) {
        this.mMachineLicence = mMachineLicence == null ? null : mMachineLicence.trim();
    }

    public String getmMachineEbcr() {
        return mMachineEbcr;
    }

    public void setmMachineEbcr(String mMachineEbcr) {
        this.mMachineEbcr = mMachineEbcr == null ? null : mMachineEbcr.trim();
    }

    public String getMeName() {
        return meName;
    }

    public void setMeName(String meName) {
        this.meName = meName == null ? null : meName.trim();
    }

    public String getMeDmpLicense() {
        return meDmpLicense;
    }

    public void setMeDmpLicense(String meDmpLicense) {
        this.meDmpLicense = meDmpLicense == null ? null : meDmpLicense.trim();
    }

    public String getMePrCertificate() {
        return mePrCertificate;
    }

    public void setMePrCertificate(String mePrCertificate) {
        this.mePrCertificate = mePrCertificate == null ? null : mePrCertificate.trim();
    }

    public String getMeBatchNumber() {
        return meBatchNumber;
    }

    public void setMeBatchNumber(String meBatchNumber) {
        this.meBatchNumber = meBatchNumber == null ? null : meBatchNumber.trim();
    }

    public Date getMeManufactureTime() {
        return meManufactureTime;
    }

    public void setMeManufactureTime(Date meManufactureTime) {
        this.meManufactureTime = meManufactureTime;
    }

    public Integer getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getSeMdpLicense() {
        return seMdpLicense;
    }

    public void setSeMdpLicense(String seMdpLicense) {
        this.seMdpLicense = seMdpLicense == null ? null : seMdpLicense.trim();
    }

    public String getSupplierMaterialCode() {
        return supplierMaterialCode;
    }

    public void setSupplierMaterialCode(String supplierMaterialCode) {
        this.supplierMaterialCode = supplierMaterialCode == null ? null : supplierMaterialCode.trim();
    }

    public String getSupplierMaterialName() {
        return supplierMaterialName;
    }

    public void setSupplierMaterialName(String supplierMaterialName) {
        this.supplierMaterialName = supplierMaterialName == null ? null : supplierMaterialName.trim();
    }

    public String getStCondition() {
        return stCondition;
    }

    public void setStCondition(String stCondition) {
        this.stCondition = stCondition == null ? null : stCondition.trim();
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}