package com.vomiter.rangedjs.item.model;

import java.util.HashMap;
import java.util.Map;

public class ModelOverride {
    final Map<String, Float> predicate = new HashMap<>();
    String model;

    public void setModel(String model){
        this.model = model;
    }
    ModelOverride(){

    }
}
