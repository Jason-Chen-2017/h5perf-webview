package com.example.jack.myapplication2;

import android.os.AsyncTask;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.jack.myapplication2.okhttp.RequestManager;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by jack on 2017/4/26.
 */

public class RecordPerfDataTask extends AsyncTask {

    CopyOnWriteArrayList<HashMap<String, String>> requestResources;
    HashMap<String, String> timeData;

    String tid;

    public RecordPerfDataTask(CopyOnWriteArrayList<HashMap<String, String>> requestResources, HashMap<String, String> timeData, String tid) {
        this.requestResources = requestResources;
        this.timeData = timeData;
        this.tid = tid;
    }

    @Override
    protected Object doInBackground(Object[] params) {

        try {
            Thread.sleep(10000, 0);//等待10s，记录测试性能结果
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        recordPerfData(requestResources);
        recordTimeData(timeData);
        syncTestTaskStatus(tid);

        return null;
    }

    private void syncTestTaskStatus(String tid) {

        try {
            Thread.sleep(1000, 0);//等待1s，同步测试任务执行状态
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        HashMap<String, String> testTaskStatusData = new HashMap<>();
        testTaskStatusData.put("tid", tid);
        testTaskStatusData.put("status", "1");

        Log.i("TAGH5", "syncTestTaskStatus=" + JSON.toJSONString(testTaskStatusData));
        RequestManager requestManager = new RequestManager();
        requestManager.requestSyn("/updateTestTask", RequestManager.TYPE_POST_JSON, testTaskStatusData);

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
//        Log.i("TAGH5", "requestResources=" + JSON.toJSONString(requestResources));
        int count = 1;
        for (HashMap<String, String> map : requestResources) {
            Log.i("TAGH5", "map=" + JSON.toJSONString(map));
            RequestManager requestManager = new RequestManager();
            requestManager.requestSyn("/postRequestResource", RequestManager.TYPE_POST_JSON, map);
            Log.i("TAGH5", "第" + (count++) + "个recordPerfData:");
        }
    }
}
