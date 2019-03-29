package com.bill.bill;

/**
 * Created by E on 2018/3/8.
 */
public class DailyBill {

    private long bid = 0L;
    private long bookId = 0L;
    private float amount = 0f;
    private long billTime = 0L;
    private long ctime = 0L;
    private String remarks = "";
    private int marketId = 0; //地点，网店都可以

    private int bigTypeId = 0; //一级分类ID
    private int smallTypeId = 0; //二级分类ID
    private int natureId = 0; //性质ID

    private float dayAmount = 0f;
    private float weekAmount = 0f;
    private float monthAmount = 0f;
    private float yearAmount = 0f;

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public long getBookId() {
        return bookId;
    }
    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getBillTime() {
        return billTime;
    }

    public void setBillTime(long billTime) {
        this.billTime = billTime;
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

    public float getDayAmount() {
        return dayAmount;
    }

    public void setDayAmount(float dayAmount) {
        this.dayAmount = dayAmount;
    }

    public float getWeekAmount() {
        return weekAmount;
    }

    public void setWeekAmount(float weekAmount) {
        this.weekAmount = weekAmount;
    }

    public float getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(float monthAmount) {
        this.monthAmount = monthAmount;
    }

    public float getYearAmount() {
        return yearAmount;
    }

    public void setYearAmount(float yearAmount) {
        this.yearAmount = yearAmount;
    }

    public int getBigTypeId() {
        return bigTypeId;
    }

    public void setBigTypeId(int bigTypeId) {
        this.bigTypeId = bigTypeId;
    }

    public int getSmallTypeId() {
        return smallTypeId;
    }

    public void setSmallTypeId(int smallTypeId) {
        this.smallTypeId = smallTypeId;
    }

    public int getNatureId() {
        return natureId;
    }

    public void setNatureId(int natureId) {
        this.natureId = natureId;
    }
}
