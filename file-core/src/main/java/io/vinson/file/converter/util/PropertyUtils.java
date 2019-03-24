package io.vinson.file.converter.util;

import com.sun.star.beans.PropertyValue;

import java.util.Map;

public class PropertyUtils {

    public static PropertyValue property(String name, Object value) {
        PropertyValue propertyValue = new PropertyValue();
        propertyValue.Name = name;
        propertyValue.Value = value;
        return propertyValue;
    }

    public static PropertyValue[] toUnoProperties(Map<String, ?> properties) {
        PropertyValue[] propertyValues = new PropertyValue[properties.size()];
        int i = 0;
        for (Map.Entry<String,?> entry : properties.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Map) {
                Map<String,Object> subProperties = (Map<String,Object>) value;
                value = toUnoProperties(subProperties);
            }
            propertyValues[i++] = property((String) entry.getKey(), value);
        }
        return propertyValues;
    }

}
