package com.properties;

import java.util.Properties;

public interface PanelWithProperties {
    void setProperties(Properties p);
    Properties getProperties();
    void writeProperties();
}
