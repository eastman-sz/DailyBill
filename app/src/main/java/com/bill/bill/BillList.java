package com.bill.bill;

import android.support.annotation.NonNull;

import com.handmark.base.IGroup;
import com.utils.lib.ss.common.DateHepler;
/**
 * Created by E on 2018/3/9.
 */
public class BillList extends IGroup implements Comparable<BillList>{

    private int superType = 0 ;//超级大类 0代表支出 1代表收入
    private long bid = 0L;
    private float amount = 0f;
    private long billtime = 0L;//唯一
    private long ctime = 0L;
    private String remarks = "";
    private int marketId = 0; //商场
    private int bigTypeId = 0; //一级分类ID
    private int smallTypeId = 0; //二级分类ID

    private float dayAmount = 0f;//某一天总额
    private float weekAmount = 0f;//周总额
    private float monthAmount = 0f;//月总额
    private float yearAmount = 0f;//年总额

    public BillList() {
    }

    public static BillList fromBill(DailyBill bill){
        BillList billList = new BillList();
        billList.setBid(bill.getBid());
        billList.setAmount(bill.getAmount());
        billList.setBilltime(bill.getBillTime());
        billList.setCtime(bill.getCTime());
        billList.setRemarks(bill.getRemarks());
        billList.setMarketId(bill.getMarketId());
        billList.setSuperType(bill.getSuperType());
        billList.setBigTypeId(bill.getBigTypeId());
        billList.setSmallTypeId(bill.getSmallTypeId());
        return billList;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
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

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
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

    public int getSuperType() {
        return superType;
    }

    public void setSuperType(int superType) {
        this.superType = superType;
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

    @Override
    protected int getIgroup() {
        return DateHepler.getYear(billtime) + DateHepler.getMonthOfYear(billtime);
    }

    @Override
    public int compareTo(@NonNull BillList o) {
        if (o.getBilltime() > this.getBilltime()){
            return 1;
        }else if (o.getBilltime() == this.getBilltime()){
            return 0;
        }
        return -1;
    }
}
