package com.tianen.chen.common;

import com.tianen.chen.base.pojo.TimeScaleWarning;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;

/**
 * @author :tianen
 * @version : $version$
 * @date :Created in 2019/4/2 9:28
 * @description :${description}
 */
public class BroadcastDescriptor {
    public static final MapStateDescriptor<String,String> groupDescriptor = new MapStateDescriptor<>(
            "investorControlGroup",
            BasicTypeInfo.STRING_TYPE_INFO,
            BasicTypeInfo.STRING_TYPE_INFO);
    public static final MapStateDescriptor<String, TimeScaleWarning> mapStateDescriptor = new MapStateDescriptor<>(
            "TimeScaleWarnRule",
            BasicTypeInfo.STRING_TYPE_INFO,
            TypeInformation.of(TimeScaleWarning.class));
}
