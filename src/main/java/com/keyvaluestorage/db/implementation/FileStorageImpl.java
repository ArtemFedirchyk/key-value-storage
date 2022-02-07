package com.keyvaluestorage.db.implementation;

import com.keyvaluestorage.db.FileStorage;
import com.keyvaluestorage.model.StorageVO;
import com.keyvaluestorage.util.ConvertingHelper;
import com.keyvaluestorage.util.PropertiesHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileStorageImpl implements FileStorage {

    private final PropertiesHelper propertiesHelper;
    private final ConvertingHelper convertingHelper;

    @Override
    public StorageVO saveRecord(StorageVO storageVO) {
        propertiesHelper.setValue(storageVO.getKey(), convertingHelper.convertObjectByteArrayToBase64String(storageVO.getValue()));
        return storageVO;
    }

    @Override
    public StorageVO getRecord(String key) {
        Object value = propertiesHelper.getValue(key) != null
                ? convertingHelper.convertBase64StringToObject(propertiesHelper.getValue(key)) : null;

        return StorageVO.builder()
                .key(key)
                .value(value)
                .build();
    }

    @Override
    public void updateRecord(StorageVO storageVO) {
        propertiesHelper.setValue(storageVO.getKey(), convertingHelper.convertObjectByteArrayToBase64String(storageVO.getValue()));
    }

    @Override
    public void deleteRecord(StorageVO storageVO) {
        if (!propertiesHelper.containsKey(storageVO.getKey())) return;
        propertiesHelper.deleteRecord(storageVO.getKey());
    }
}
