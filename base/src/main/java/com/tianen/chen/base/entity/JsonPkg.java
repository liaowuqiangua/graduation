package com.tianen.chen.base.entity;

/**
 * @author :tianen
 * @version : $version$
 * @date :Created in 2019/4/21 12:51
 * @description :${description}
 */
public class JsonPkg {
    private JsonType type;
    private String json;

    public JsonPkg(JsonType type, String json) {
        this.type = type;
        this.json = json;
    }

    public JsonPkg() {
    }

    public JsonType getType() {
        return type;
    }

    public void setType(JsonType type) {
        this.type = type;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
