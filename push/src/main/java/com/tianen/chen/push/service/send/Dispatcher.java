package com.tianen.chen.push.service.send;

/**
 * @author :tianen
 * @version : $version$
 * @date :Created in 2019/4/25 9:56
 * @description :${description}
 */
public interface Dispatcher<T>{
    T dispatch(T t);
}
