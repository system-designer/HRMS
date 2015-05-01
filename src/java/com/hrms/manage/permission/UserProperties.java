package com.hrms.manage.permission;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.jplus.util.LoadProperties;

public class UserProperties {

    private static Properties mapping_props = null;
    private static UserProperties user_props = null;

    public static UserProperties getInstance() {
        if (user_props == null) {
            user_props = new UserProperties();
        }
        return user_props;
    }

    private UserProperties() {
        if (mapping_props == null) {
            mapping_props = new LoadProperties("webfilter.properties").getProps();
        }
    }

    public String getLoginUrl() {
        return mapping_props.getProperty("loginUrl");
    }

    public String getMainUrl() {
        return mapping_props.getProperty("mainUrl");
    }

    public List<String> getUserModulelist() {
        return Arrays.asList(mapping_props.getProperty("userModulelist").split("\\,"));
    }
}
