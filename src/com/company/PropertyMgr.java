package com.company;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyMgr {
//    private static final PropertyMgr INSTANCE = new PropertyMgr();
//    private PropertyMgr(){}
//    public static PropertyMgr getInstance(){
//        return INSTANCE;
//    }
    static Properties props = new Properties();
    static {
        try {
            props.load(Objects.requireNonNull(PropertyMgr.class.getClassLoader().getResourceAsStream("config")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if (props == null) return null;
        return props.get(key);
    }
}
