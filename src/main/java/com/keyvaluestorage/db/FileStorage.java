package com.keyvaluestorage.db;

import com.keyvaluestorage.exception.RecordNotFoundException;
import com.keyvaluestorage.model.StorageVO;

public interface FileStorage {
    /**
     * Saves incoming key-value data to storage file
     *
     * @param storageVO - incoming model value object(VO) with key value data that should be saved in key-value storage
     * @return - model VO with saved key-value data
     */
    StorageVO saveRecord(StorageVO storageVO);

    /**
     * Finds key-value record first in storage file
     *
     * @param key - incoming key of String type that should be used for finding the key-value record
     * @return - model VO with found key-value data
     * @throws RecordNotFoundException - exception in case when record will not be found in storage file by incoming key
     */
    StorageVO getRecord(String key) throws RecordNotFoundException;

    /**
     * Updates incoming record with new value file storage
     *
     * @param storageVO - incoming model value object(VO) with key value data that should be updated in key-value file storage
     */
    void updateRecord(StorageVO storageVO);

    /**
     * Deletes incoming record from file storage
     *
     * @param storageVO - incoming model value object(VO) with key value data that should deleted from˚ key-value storage
     */
    void deleteRecord(StorageVO storageVO);
}
