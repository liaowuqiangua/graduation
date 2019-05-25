package com.tianen.chen.base.pojo;

import java.util.Objects;

/**
 * @author :tianen
 * @version : $version$
 * @date :Created in 2019/4/26 16:37
 * @description :${description}
 */
public class Access {

    private String ipAddress;

    private String city;

    private String request;

    private long timestamp;


    public Access() {
    }

    public Access(String ipAddress, String city, String request, long timestamp) {
        this.ipAddress = ipAddress;
        this.city = city;
        this.request = request;
        this.timestamp = timestamp;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Access{");
        sb.append("ipAddress='").append(ipAddress).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", request='").append(request).append('\'');
        sb.append(", timestamp=").append(timestamp);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Access access = (Access) o;
        return timestamp == access.timestamp &&
                Objects.equals(ipAddress, access.ipAddress) &&
                Objects.equals(city, access.city) &&
                Objects.equals(request, access.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipAddress, city, request, timestamp);
    }
}
