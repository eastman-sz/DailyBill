package com.bill.wheelview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;

import com.bill.util.ILog;
import com.common.base.BaseRelativeLayout;
import com.sz.kk.daily.bill.R;
import com.utils.lib.ss.common.DateHepler;
import com.wheelview.HorizontalWheelView;
import com.wheelview.OnWheelScrollListener;
import com.wheelview.WheelView;
import java.util.ArrayList;
/**
 * Created by E on 2018/3/8.
 */
public class DateTimeSelectWheelView extends BaseRelativeLayout {

    private WheelView yearWheelView = null;
    private WheelView monthWheelView = null;
    private WheelView dayWheelView = null;
    private WheelView hoursWheelView = null;
    private WheelView minutesWheelView = null;
//    private WheelView secondsWheelView = null;

    private ArrayList<String> years = new ArrayList<>();
    private ArrayList<String> months = new ArrayList<>();
    private ArrayList<String> days = new ArrayList<>();
    private ArrayList<String> hours = new ArrayList<>();
    private ArrayList<String> minutes = new ArrayList<>();
    private ArrayList<String> seconds = new ArrayList<>();

    public DateTimeSelectWheelView(Context context) {
        super(context);

        init();
    }

    public DateTimeSelectWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public DateTimeSelectWheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    @Override
    protected void initViews() {
        LayoutInflater.from(context).inflate(R.layout.date_time_select_wheelview , this);

        yearWheelView = findViewById(R.id.yearWheelview);
        monthWheelView = findViewById(R.id.monthWheelview);
        dayWheelView = findViewById(R.id.dayWheelview);
        hoursWheelView = findViewById(R.id.hoursWheelview);
        minutesWheelView = findViewById(R.id.minutesWheelview);
//        secondsWheelView = findViewById(R.id.sencondsWheelview);

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
    private long cTimeStamp = 0;

    public void setTimestamp(long timestamp){
        if (0 == timestamp){
            timestamp = System.currentTimeMillis()/1000;
        }
        this.cTimeStamp = timestamp;
        this.cDate = DateHepler.timestampFormat(timestamp,"yyyy-MM-dd-HH-mm-ss");
        freshViews();
    }

    private void freshViews(){
        final String date[] = cDate.split("-");

        YearWheelViewAdapter yearAdapter = new YearWheelViewAdapter(context , years);
        yearWheelView.setViewAdapter(yearAdapter);
        yearWheelView.setVisibleItems(5);
        yearWheelView.setCurrentItem(culItem(years , date[0]));
        yearWheelView.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(HorizontalWheelView horizontalWheelView) {
            }
            @Override
            public void onScrollingFinished(HorizontalWheelView horizontalWheelView) {
                onMonthScrollChg(date ,DateHepler.getMonthOfYear(cTimeStamp));
            }
        });

        MonthWheelViewAdapter monthAdapter = new MonthWheelViewAdapter(context , months);
        monthWheelView.setViewAdapter(monthAdapter);
        monthWheelView.setVisibleItems(5);
        monthWheelView.setCurrentItem(culItem(months , date[1]));
        monthWheelView.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(HorizontalWheelView wheel) {
            }
            @Override
            public void onScrollingFinished(HorizontalWheelView wheel) {
                onMonthScrollChg(date ,wheel.getCurrentItem() + 1);
            }
        });

        onMonthScrollChg(date ,DateHepler.getMonthOfYear(cTimeStamp));

        WheelViewTextAdapter hourAdapter = new WheelViewTextAdapter(context , hours);
        hoursWheelView.setViewAdapter(hourAdapter);
        hoursWheelView.setVisibleItems(5);
        hoursWheelView.setCurrentItem(culItem(hours , date[3]));

        WheelViewTextAdapter minuteAdapter = new WheelViewTextAdapter(context , minutes);
        minutesWheelView.setViewAdapter(minuteAdapter);
        //setViewAdapter需要放在setCurrentItem之前
        minutesWheelView.setVisibleItems(5);
        minutesWheelView.setCurrentItem(culItem(minutes , date[4]));

//        WheelViewTextAdapter secondAdapter = new WheelViewTextAdapter(context , seconds);
//        secondsWheelView.setViewAdapter(secondAdapter);
//        secondsWheelView.setVisibleItems(5);
//        secondsWheelView.setCurrentItem(culItem(seconds , date[5]));
    }

    private void onMonthScrollChg(String[] date ,int month){
        days.clear();
        int dayCount = getDayCountOfMonth(month);
        initList(days , dayCount);

        ILog.e("--------年--- " + getYear() + "    ---月: " + getMonth());

        DayOfMonthWheelViewAdapter dayAdapter = new DayOfMonthWheelViewAdapter(context , days , getYear() , getMonth());
        dayWheelView.setViewAdapter(dayAdapter);
        dayWheelView.setVisibleItems(5);
        dayWheelView.setCurrentItem(culItem(days , date[2]));
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
        return item;
    }

    private String getYear(){
        return years.get(yearWheelView.getCurrentItem());
    }

    private String getMonth(){
        return months.get(monthWheelView.getCurrentItem());
    }

    public String getCurrentDate(){
        String year = years.get(yearWheelView.getCurrentItem());
        String month = months.get(monthWheelView.getCurrentItem());
        String day = days.get(dayWheelView.getCurrentItem());
        String hour = hours.get(hoursWheelView.getCurrentItem());
        String minute = minutes.get(minutesWheelView.getCurrentItem());
        String second = "00";

        String dateTime = year+ "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        Log.e("ilog" , "----dateTime---: " + dateTime);
        return dateTime;
    }


}
