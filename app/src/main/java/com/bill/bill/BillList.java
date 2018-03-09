package com.bill.bill;

import com.handmark.base.IGroup;
import com.utils.lib.ss.common.DateHepler;
/**
 * Created by E on 2018/3/9.
 */
public class BillList extends IGroup {

    private float amount = 0f;
    private long billtime = 0L;
    private long ctime = 0L;
    private String remarks = "";

    public BillList() {
    }

    public static BillList fromBill(DailyBill bill){
        BillList billList = new BillList();
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

    @Override
    protected int getIgroup() {
        return DateHepler.getMonthOfYear(billtime);
    }
}
