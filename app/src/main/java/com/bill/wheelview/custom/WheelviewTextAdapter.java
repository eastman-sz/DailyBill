package com.bill.wheelview.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bill.wheelview.adapter.AbstractWheelAdapter;
import com.common.base.CustomFontDigitTextView;
import com.common.base.ViewHolder;
import com.sz.kk.daily.bill.R;
import java.util.ArrayList;
/**
 * Created by E on 2018/3/8.
 */
public class WheelviewTextAdapter extends AbstractWheelAdapter {

    private Context context = null;
    private ArrayList<String> list = null;

    public WheelviewTextAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemsCount() {
        return list.size();
    }

    @Override
    public View getItem(int position, View convertView, ViewGroup parent) {
        if (null == convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.wheelview_text_adapter_view , null);
        }
        CustomFontDigitTextView textView = ViewHolder.getView(convertView , R.id.textView);
        String text = list.get(position);

        textView.setText(text);

        return convertView;
    }
}
