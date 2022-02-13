package com.keyvaluestorage.db.implementation;

import com.keyvaluestorage.db.FileStorage;
import com.keyvaluestorage.exception.RecordNotFoundException;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public StorageVO saveRecord(StorageVO storageVO) {
        propertiesHelper.setValue(storageVO.getKey(), convertingHelper.convertObjectByteArrayToBase64String(storageVO.getValue()));
        return storageVO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StorageVO getRecord(String key) throws RecordNotFoundException {
        Object value = propertiesHelper.getValue(key) != null
                ? convertingHelper.convertBase64StringToObject(propertiesHelper.getValue(key)) : null;
        // Case when record is not found in file storage
        if (value == null)
            throw new RecordNotFoundException("Record with key - ".concat(key).concat(" is not found in storage"));

        return StorageVO.builder()
                .key(key)
                .value(value)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateRecord(StorageVO storageVO) {
        propertiesHelper.setValue(storageVO.getKey(), convertingHelper.convertObjectByteArrayToBase64String(storageVO.getValue()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteRecord(StorageVO storageVO) {
        if (!propertiesHelper.containsKey(storageVO.getKey())) return;
        propertiesHelper.deleteRecord(storageVO.getKey());
    }
}
