package com.bill.util;

import com.bill.application.IApplication;
import com.utils.lib.ss.common.PkgHelper;
import com.utils.lib.ss.info.DeviceInfo;
import com.utils.lib.ss.net.BaseHttpImp2;
import java.io.InputStream;
import java.util.HashMap;
/**
 * Created by E on 2017/4/25.
 */
public class NetHelper2 extends BaseHttpImp2 {

    private static NetHelper2 netHelper2 = null;

    public static NetHelper2 getInstance(){
        if (null == netHelper2){
            netHelper2 = new NetHelper2();
        }
        return netHelper2;
    }

    @Override
    public HashMap<String, Object> specialHandleParams(HashMap<String, Object> params) {
        if (null == params) {
            return params;
        }
        params.putAll(getCommonParams());
        String jsonString = JSONHelper.toJSON(params);
        params.clear();
        params.put("data", jsonString);
        params.put("appId", 10001);
        return params;
    }

    private HashMap<String, Object> getCommonParams(){
        HashMap<String, Object> params = new HashMap<>();
        params.put("platform", 2);
        params.put("mac", DeviceInfo.getLocalMacAddress(IApplication.Companion.getContext()));
        params.put("rtime", System.currentTimeMillis()/1000);
        params.put("version", PkgHelper.getVersionName(IApplication.Companion.getContext()));
        params.put("osversion", DeviceInfo.getSystemVersion());
        params.put("brand", DeviceInfo.getBrand());
        params.put("model", DeviceInfo.getModel());
        return params;
    }

    @Override
    protected String read2String(InputStream inStream) throws Exception {
        String result = super.read2String(inStream);
        return result;
    }
}
