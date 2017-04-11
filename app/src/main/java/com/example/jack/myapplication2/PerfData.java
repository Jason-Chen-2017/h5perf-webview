package com.example.jack.myapplication2;

/**
 * Created by jack on 2017/4/11.
 */

public class PerfData {
    /**
     * 开始测试时间戳
     */
    Long StartTimestamp;
    /**
     * domC时间戳
     */
    Long domcTimestamp;
    /**
     * load时间戳
     */
    Long loadimestamp;

    /**
     * 白屏时间
     */
    Long whiteScreenTime;
    /**
     * domContentLoaded时间
     */
    Long domContentLoadedTime;
    /**
     * 文档load时间
     */
    Long loadTime;

    @Override
    public String toString() {
        return "PerfData{" +
                "StartTimestamp=" + StartTimestamp +
                ", domcTimestamp=" + domcTimestamp +
                ", loadimestamp=" + loadimestamp +
                ", whiteScreenTime=" + whiteScreenTime +
                ", domContentLoadedTime=" + domContentLoadedTime +
                ", loadTime=" + loadTime +
                '}';
    }

    public Long getStartTimestamp() {
        return StartTimestamp;
    }

    public void setStartTimestamp(Long startTimestamp) {
        StartTimestamp = startTimestamp;
    }

    public Long getDomcTimestamp() {
        return domcTimestamp;
    }

    public void setDomcTimestamp(Long domcTimestamp) {
        this.domcTimestamp = domcTimestamp;
    }

    public Long getLoadimestamp() {
        return loadimestamp;
    }

    public void setLoadimestamp(Long loadimestamp) {
        this.loadimestamp = loadimestamp;
    }

    public Long getWhiteScreenTime() {
        return whiteScreenTime;
    }

    public void setWhiteScreenTime(Long whiteScreenTime) {
        this.whiteScreenTime = whiteScreenTime;
    }

    public Long getDomContentLoadedTime() {
        return domContentLoadedTime;
    }

    public void setDomContentLoadedTime(Long domContentLoadedTime) {
        this.domContentLoadedTime = domContentLoadedTime;
    }

    public Long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(Long loadTime) {
        this.loadTime = loadTime;
    }
}
