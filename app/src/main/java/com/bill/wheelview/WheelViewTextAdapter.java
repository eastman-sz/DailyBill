package com.bill.wheelview;

import android.content.Context;
import android.view.View;
import com.common.base.CustomFontDigitTextView;
import com.common.base.ViewHolder;
import com.sz.kk.daily.bill.R;
import com.wheelview.adapter.BaseWheelAdapter;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by E on 2018/3/8.
 */
public class WheelViewTextAdapter extends BaseWheelAdapter<String> {

    public WheelViewTextAdapter(Context context, ArrayList<String> list) {
        super(context , list , R.layout.wheelview_text_adapter_view);
    }

    @Override
    public void getConvertView(View convertView, List<String> list, int position) {
        CustomFontDigitTextView textView = ViewHolder.getView(convertView , R.id.textView);
        String text = list.get(position);

        textView.setText(text);
    }
}
