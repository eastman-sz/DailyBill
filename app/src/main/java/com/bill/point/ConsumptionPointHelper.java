package com.bill.point;

import android.util.SparseArray;

import java.util.ArrayList;

/**
 * Created by E on 2018/3/12.
 */
public class ConsumptionPointHelper {

    public static void add(String marketName){
        ConsumptionPointDbHelper.add(marketName);
    }

    public static ArrayList<ConsumptionPoint> getAllConsuptionPoints(){
        return ConsumptionPointDbHelper.getAllConsuptionPoints();
    }

    public static SparseArray<ConsumptionPoint> getAllConsuptionPointsArray(){
        return ConsumptionPointDbHelper.getAllConsuptionPointsArray();
    }

    public static ArrayList<String> getAllConsuptionPointNames(){
        return ConsumptionPointDbHelper.getAllConsuptionPointNames();
    }



}
