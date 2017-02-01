package com.github.idm.drivers;

import java.util.HashMap;
import java.util.Map;

public class Config {

    private static final String DEFAULT_VALUE = "na";
    private Map<String, String> configMap;
    private String configString;

    public Config() {

        this.configMap = new HashMap<String, String>();
        this.configString = null;

    }

    public Config(String configString) {

        this.configMap = new HashMap<String, String>();
        this.configString = configString;

        parseConfigString();
    }

    public void setConfigString(String configString) {

        this.configString = configString;
        parseConfigString();

    }

    public String configString() {

        return this.configString;


    }

    public String get(String key) {
        return (configMap.containsKey(key)) ? configMap.get(key) : DEFAULT_VALUE;
    }

    public void set(String key, String value) {
        configMap.put(key, value);
    }

    private void parseConfigString() {

        if (configString.isEmpty()) return;

        String[] configList = configString.split(" ");
        String[] keyValue = null;

        for (int i = 0; i < configList.length; i++) {
            keyValue = configList[i].split("=");
            set(keyValue[0], keyValue[1]);
        }

    }

}

