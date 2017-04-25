package com.example.jack.myapplication2;

import java.util.Map;

/**
 * Created by jack on 2017/4/25.
 */

public class H5RequestSource {
    String tid;
    String testUrl;
    Integer index;// 请求先后的序号
    Long startTimestamp;// 测试开始时间戳
    Long timestamp;// 请求的时间戳
    Integer timeCost; // 当前请求时间戳 - 测试开始时间戳
    String url;
    String method;//GET,POST
    Map<String, String> headers;

    @Override
    public String toString() {
        return "H5RequestSource{" +
                "tid=" + tid +
                ", testUrl='" + testUrl + '\'' +
                ", index=" + index +
                ", startTimestamp=" + startTimestamp +
                ", timestamp=" + timestamp +
                ", timeCost=" + timeCost +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", headers=" + headers +
                '}';
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTestUrl() {
        return testUrl;
    }

    public void setTestUrl(String testUrl) {
        this.testUrl = testUrl;
    }

    public Long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(Integer timeCost) {
        this.timeCost = timeCost;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
