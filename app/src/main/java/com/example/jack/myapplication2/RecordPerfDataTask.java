package com.example.jack.myapplication2;

import android.os.AsyncTask;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.jack.myapplication2.okhttp.RequestManager;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by jack on 2017/4/26.
 */

public class RecordPerfDataTask extends AsyncTask {

    CopyOnWriteArrayList<HashMap<String, String>> requestResources;
    HashMap<String, String> timeData;

    public RecordPerfDataTask(CopyOnWriteArrayList<HashMap<String, String>> requestResources) {
        this.requestResources = requestResources;
    }

    public RecordPerfDataTask(CopyOnWriteArrayList<HashMap<String, String>> requestResources, HashMap<String, String> timeData) {
        this.requestResources = requestResources;
        this.timeData = timeData;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        recordPerfData(requestResources);
        recordTimeData(timeData);
        return null;
    }

    private void recordTimeData(HashMap<String, String> timeData) {
        Log.i("TAGH5", "recordTimeData=" + JSON.toJSONString(timeData));
        RequestManager requestManager = new RequestManager();
        requestManager.requestSyn("/postTestTimeData", RequestManager.TYPE_POST_JSON, timeData);
    }


    /**
     * 记录性能数据，写入server的数据库中
     *
     * @param requestResources
     */

    private void recordPerfData(CopyOnWriteArrayList<HashMap<String, String>> requestResources) {
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
