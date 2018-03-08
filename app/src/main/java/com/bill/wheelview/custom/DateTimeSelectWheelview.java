package com.bill.wheelview.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import com.bill.wheelview.HorizontalWheelView;
import com.bill.wheelview.OnWheelScrollListener;
import com.bill.wheelview.WheelView;
import com.common.base.BaseRelativeLayout;
import com.sz.kk.daily.bill.R;
import com.utils.lib.ss.common.DateHepler;
import java.util.ArrayList;
/**
 * Created by E on 2018/3/8.
 */
public class DateTimeSelectWheelview extends BaseRelativeLayout {

    private WheelView yearWheelview = null;
    private WheelView monthWheelview = null;
    private WheelView dayWheelview = null;
    private WheelView hoursWheelview = null;
    private WheelView minutesWheelview = null;
    private WheelView secondsWheelview = null;

    private ArrayList<String> years = new ArrayList<>();
    private ArrayList<String> months = new ArrayList<>();
    private ArrayList<String> days = new ArrayList<>();
    private ArrayList<String> hours = new ArrayList<>();
    private ArrayList<String> minutes = new ArrayList<>();
    private ArrayList<String> seconds = new ArrayList<>();

    public DateTimeSelectWheelview(Context context) {
        super(context);

        init();
    }

    public DateTimeSelectWheelview(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public DateTimeSelectWheelview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @Override
    protected void initViews() {
        LayoutInflater.from(context).inflate(R.layout.date_time_select_wheelview , this);

        yearWheelview = findViewById(R.id.yearWheelview);
        monthWheelview = findViewById(R.id.monthWheelview);
        dayWheelview = findViewById(R.id.dayWheelview);
        hoursWheelview = findViewById(R.id.hoursWheelview);
        minutesWheelview = findViewById(R.id.minutesWheelview);
        secondsWheelview = findViewById(R.id.sencondsWheelview);

        initData();
    }

    private void initData(){
        //year
        years.add("2014");
        years.add("2015");
        years.add("2016");
        years.add("2017");
        years.add("2018");
        years.add("2019");
        years.add("2020");
        years.add("2021");
        years.add("2022");

        //month
        initList(months , 12);
        //hour
        initList1(hours , 23);
        //minute
        initList1(minutes , 59);
        //second
        initList1(seconds , 59);
    }

    private String cDate = null;

    public void setTimestamp(long timestamp){
        this.cDate = DateHepler.timestampFormat(timestamp,"yyyy-MM-dd-HH-mm-ss");
        freshViews();
    }

    private void freshViews(){
        final String date[] = cDate.split("-");

        WheelviewTextAdapter yearAdapter = new WheelviewTextAdapter(context , years);
        yearWheelview.setViewAdapter(yearAdapter);
        yearWheelview.setVisibleItems(5);
        yearWheelview.setCurrentItem(culItem(years , date[0]));

        WheelviewTextAdapter monthAdapter = new WheelviewTextAdapter(context , months);
        monthWheelview.setViewAdapter(monthAdapter);
        monthWheelview.setVisibleItems(5);
        monthWheelview.setCurrentItem(culItem(months , date[1]));
        monthWheelview.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(HorizontalWheelView wheel) {

            }
            @Override
            public void onScrollingFinished(HorizontalWheelView wheel) {
                onMonthScrollChg(date ,wheel.getCurrentItem());
            }
        });

        onMonthScrollChg(date ,3);

        WheelviewTextAdapter hourAdapter = new WheelviewTextAdapter(context , hours);
        hoursWheelview.setViewAdapter(hourAdapter);
        hoursWheelview.setVisibleItems(5);
        hoursWheelview.setCurrentItem(culItem(hours , date[3]));

        WheelviewTextAdapter minuteAdapter = new WheelviewTextAdapter(context , minutes);
        minutesWheelview.setViewAdapter(minuteAdapter);
        //setViewAdapter需要放在setCurrentItem之前
        minutesWheelview.setVisibleItems(5);
        minutesWheelview.setCurrentItem(culItem(minutes , date[4]));

        WheelviewTextAdapter secondAdapter = new WheelviewTextAdapter(context , seconds);
        secondsWheelview.setViewAdapter(secondAdapter);
        secondsWheelview.setVisibleItems(5);
        secondsWheelview.setCurrentItem(culItem(seconds , date[5]));
    }

    private void onMonthScrollChg(String[] date ,int month){
        days.clear();
        int dayCount = getDayCountOfMonth(month);
        initList(days , dayCount);

        WheelviewTextAdapter dayAdapter = new WheelviewTextAdapter(context , days);
        dayWheelview.setViewAdapter(dayAdapter);
        dayWheelview.setVisibleItems(5);
        dayWheelview.setCurrentItem(culItem(days , date[2]));
    }

    private void initList(ArrayList<String> list , int count){
        for (int i = 1; i <= count ; i++ ){
            String text = i < 10 ? ("0" + i) : String.valueOf(i);
            list.add(text);
        }
    }

    private void initList1(ArrayList<String> list , int count){
        for (int i = 0; i <= count ; i++ ){
            String text = i < 10 ? ("0" + i) : String.valueOf(i);
            list.add(text);
        }
    }

    private int getDayCountOfMonth(int month){
        if (month == 2){
            return 28;
        }
        if (month == 1
                || month == 3
                || month == 5
                || month == 7
                || month == 8
                || month == 10
                || month == 12){
            return 31;
        }
        return 30;
    }

    private int culItem(ArrayList<String> list, String text){
        int item = list.indexOf(text);
        Log.e("ilog" , "----item---: " + item);
        return item;
    }

    public String getCurrentDate(){
        String year = years.get(yearWheelview.getCurrentItem());
        String month = months.get(monthWheelview.getCurrentItem());
        String day = days.get(dayWheelview.getCurrentItem());
        String hour = hours.get(hoursWheelview.getCurrentItem());
        String minute = minutes.get(minutesWheelview.getCurrentItem());
        String second = seconds.get(secondsWheelview.getCurrentItem());

        String dateTime = year+ "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        Log.e("ilog" , "----dateTime---: " + dateTime);
        return dateTime;
    }


}
