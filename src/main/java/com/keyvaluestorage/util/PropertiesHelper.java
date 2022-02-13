package com.keyvaluestorage.util;

import lombok.RequiredArgsConstructor;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PropertiesHelper {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesHelper.class);

    private static final String FILE_PATH = "storage.properties";

    private PropertiesConfiguration propertiesConfiguration;

    private final ConvertingHelper convertingHelper;

    @PostConstruct
    private void initialisePropertiesConfiguration() {
        try {
            this.propertiesConfiguration = new PropertiesConfiguration(FILE_PATH);
        } catch (ConfigurationException e) {
            logger.error(e.getMessage());
        }
    }

    public String getValue(String key) {
        return propertiesConfiguration.getString(key);
    }

    public void setValue(String key, String value) {
        propertiesConfiguration.setProperty(key, value);
        try {
            propertiesConfiguration.save();
        } catch (ConfigurationException e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteRecord(String key) {
        propertiesConfiguration.clearProperty(key);
        try {
            propertiesConfiguration.save();
        } catch (ConfigurationException e) {
            logger.error(e.getMessage());
        }
    }

    public Map<String, Object> getAllKeyValuePairs() {
        Map<String, Object> mapWithValues = new HashMap<>();
        Iterator<String> keys = propertiesConfiguration.getKeys();
        keys.forEachRemaining(key -> mapWithValues.put(key, convertingHelper.convertBase64StringToObject(getValue(key))));

        return mapWithValues;
    }

    public boolean containsKey(String key) {
        return propertiesConfiguration.containsKey(key);
    }
}
