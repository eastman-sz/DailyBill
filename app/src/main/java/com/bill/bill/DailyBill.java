package com.bill.bill;

/**
 * Created by E on 2018/3/8.
 */
public class DailyBill {

    private long bid = 0L;
    private float amount = 0f;
    private long billtime = 0L;
    private long ctime = 0L;
    private String remarks = "";
    private int industryId = 0;//行业一级
    private int marketId = 0; //细分二级

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public long getBilltime() {
        return billtime;
    }

    public void setBilltime(long billtime) {
        this.billtime = billtime;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getIndustryId() {
        return industryId;
    }

    public void setIndustryId(int industryId) {
        this.industryId = industryId;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public float getAmount() {
        return amount;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
}
