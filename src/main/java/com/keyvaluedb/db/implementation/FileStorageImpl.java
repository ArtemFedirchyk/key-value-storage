package com.keyvaluedb.db.implementation;

import com.keyvaluedb.db.FileStorage;
import com.keyvaluedb.model.StorageVO;
import com.keyvaluedb.util.ConvertingHelper;
import com.keyvaluedb.util.PropertiesHelper;
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
