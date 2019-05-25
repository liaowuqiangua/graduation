package com.tianen.chen.common;

import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

/**
 * @author :tianen
 * @version : $version$
 * @date :Created in 2019/3/24 18:50
 * @description : periodic watermark generate
 */
public class AssignerWithPeriodicWatermarksImpl<T> implements AssignerWithPeriodicWatermarks<T> {
    private static final long LEG = 1000L;
    @Override
    public Watermark getCurrentWatermark() {
        return new Watermark(System.currentTimeMillis() - LEG);
    }
    @Override
    public long extractTimestamp(T element, long previousElementTimestamp) {
        return previousElementTimestamp;
    }
}
