package com.keyvaluestorage.util;

import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.Base64;

@Component
public class ConvertingHelper {

    public String convertObjectByteArrayToBase64String(Object object) {
        return Base64.getEncoder().encodeToString(SerializationUtils.serialize(object));
    }

    public Object convertBase64StringToObject(String value) {
        return SerializationUtils.deserialize(Base64.getDecoder().decode(value));
    }
}
