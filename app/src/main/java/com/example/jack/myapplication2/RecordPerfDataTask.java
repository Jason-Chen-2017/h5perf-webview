package com.example.jack.myapplication2;

import android.os.AsyncTask;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.jack.myapplication2.okhttp.RequestManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jack on 2017/4/26.
 */

public class RecordPerfDataTask extends AsyncTask {

    List<HashMap<String, String>> requestResources;

    public RecordPerfDataTask(List<HashMap<String, String>> requestResources) {
        this.requestResources = requestResources;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        recordPerfData(requestResources);
        return null;
    }


    /**
     * 记录性能数据，写入web的数据库中
     */

    private void recordPerfData(List<HashMap<String, String>> requestResources) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i("TAGH5", "requestResources=" + JSON.toJSONString(requestResources));

        for (HashMap<String, String> map : requestResources) {

            Log.i("TAGH5", "map=" + JSON.toJSONString(map));

            callWriteRequestResourceHttpApi(map);
        }

    }

    void callWriteRequestResourceHttpApi(HashMap<String, String> paramsMap) {

        Log.i("TAGH5-paramsMap", JSON.toJSONString(paramsMap));

        RequestManager requestManager = new RequestManager();
        requestManager.requestSyn("/postRequestResource", RequestManager.TYPE_POST_JSON, paramsMap);
    }
}
