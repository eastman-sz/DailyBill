package com.bill.bill;

import android.support.annotation.NonNull;

import com.handmark.base.IGroup;
import com.utils.lib.ss.common.DateHepler;
/**
 * Created by E on 2018/3/9.
 */
public class BillList extends IGroup implements Comparable<BillList>{

    private long bid = 0L;
    private float amount = 0f;
    private long billtime = 0L;//唯一
    private long ctime = 0L;
    private String remarks = "";

    public BillList() {
    }

    public static BillList fromBill(DailyBill bill){
        BillList billList = new BillList();
        billList.setBid(bill.getBid());
        billList.setAmount(bill.getAmount());
        billList.setBilltime(bill.getBilltime());
        billList.setCtime(bill.getCtime());
        billList.setRemarks(bill.getRemarks());
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

    @Override
    protected int getIgroup() {
        return DateHepler.getMonthOfYear(billtime);
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
