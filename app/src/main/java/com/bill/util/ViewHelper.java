package com.bill.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.common.base.CustomFontTextView;

/**
 * Created by E on 2017/4/26.
 */
public class ViewHelper {

    public static void setLeftAndRightCompoundDrawables(Context context , CustomFontTextView textView , int left_drawable_resid
            , int right_drawable_resid){

        Resources rs = context.getResources();
        Drawable leftDrawable = rs.getDrawable(left_drawable_resid);
        leftDrawable.setBounds(0,0,leftDrawable.getMinimumWidth() , leftDrawable.getMinimumHeight());

        Drawable rightDrawable = rs.getDrawable(right_drawable_resid);
        rightDrawable.setBounds(0,0,rightDrawable.getMinimumWidth() , rightDrawable.getMinimumHeight());

        textView.setCompoundDrawables(leftDrawable , null , rightDrawable ,null);
    }

    public static void setLeftCompoundDrawables(Context context , TextView textView , int drawable_resid){
        if (0 == drawable_resid){
            textView.setCompoundDrawables(null , null , null ,null);

            return;
        }
        Drawable drawable = context.getResources().getDrawable(drawable_resid);
        drawable.setBounds(0,0,drawable.getMinimumWidth() , drawable.getMinimumHeight());
        textView.setCompoundDrawables(drawable , null , null ,null);
    }

    public static void setRightCompoundDrawables(Context context , CustomFontTextView textView , int drawable_resid){
        if (0 == drawable_resid){
            textView.setCompoundDrawables(null , null , null ,null);

            return;
        }
        Drawable drawable = context.getResources().getDrawable(drawable_resid);
        drawable.setBounds(0,0,drawable.getMinimumWidth() , drawable.getMinimumHeight());
        textView.setCompoundDrawables(null , null , drawable ,null);
    }

    public static void setTopCompoundDrawables(Context context , CustomFontTextView textView , int drawable_resid){
        if (0 == drawable_resid){
            textView.setCompoundDrawables(null , null , null ,null);

            return;
        }
        Drawable drawable = context.getResources().getDrawable(drawable_resid);
        drawable.setBounds(0,0,drawable.getMinimumWidth() , drawable.getMinimumHeight());
        textView.setCompoundDrawables(null , drawable , null ,null);
    }

    public static void setBottomCompoundDrawables(Context context , CustomFontTextView textView , int drawable_resid){
        if (0 == drawable_resid){
            textView.setCompoundDrawables(null , null , null ,null);

            return;
        }
        Drawable drawable = context.getResources().getDrawable(drawable_resid);
        drawable.setBounds(0,0,drawable.getMinimumWidth() , drawable.getMinimumHeight());
        textView.setCompoundDrawables(null , null , null ,drawable);
    }

}
