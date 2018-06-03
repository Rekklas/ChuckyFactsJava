package com.rekklesdroid.android.chuckyfactsjava.entity;

import java.util.List;

public class JsonResult {

    private String type;

    private List<Value> value = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Value> getValue() {
        return value;
    }

    public void setValue(List<Value> value) {
        this.value = value;
    }

}
