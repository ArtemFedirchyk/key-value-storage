package com.keyvaluestorage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class PropertiesHelper {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesHelper.class);


    private static final String FILE_PATH = "storage.properties";

    private FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH, true);
    private FileInputStream fileInputStream = new FileInputStream(FILE_PATH);

    private final Properties propertiesForReading = new Properties();
    private final Properties propertiesForModification = new Properties();

    private final ConvertingHelper convertingHelper;

    public PropertiesHelper(ConvertingHelper convertingHelper) throws FileNotFoundException {
        this.convertingHelper = convertingHelper;
    }

    public String getValue(String key) {
        initialisePropertiesForReading();
        return propertiesForReading.getProperty(key);
    }

    public void setValue(String key, String value) {
        propertiesForModification.put(key, value);
        storePropertiesForModification();
    }

    public void deleteRecord(String key) {
        propertiesForModification.remove(key);
        storePropertiesForModification();
    }

    public Map<String, Object> getAllKeyValuePairs() {
        Map<String, Object> mapWithValues = new HashMap<>();
        initialisePropertiesForReading();
        propertiesForReading.forEach((key, value) ->
                mapWithValues.put(key.toString(), convertingHelper.convertBase64StringToObject(value.toString())));

        return mapWithValues;
    }

    public boolean containsKey(String key) {
        initialisePropertiesForReading();
        return propertiesForReading.containsKey(key);
    }

    private void initialisePropertiesForReading() {
        try {
            propertiesForReading.load(fileInputStream);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void storePropertiesForModification() {
        try {
            propertiesForModification.store(fileOutputStream, null);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
