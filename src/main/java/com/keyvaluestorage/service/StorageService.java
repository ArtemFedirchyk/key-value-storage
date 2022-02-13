package com.keyvaluestorage.service;

import com.keyvaluestorage.exception.RecordAlreadyPresentException;
import com.keyvaluestorage.exception.RecordNotFoundException;
import com.keyvaluestorage.model.StorageVO;

public interface StorageService {
    /**
     * Saves incoming data to in-memory storage and to storage file
     *
     * @param storageVO - incoming model value object(VO) with key value data that should be saved in key-value storage
     * @return - model VO with saved key-value data
     * @throws RecordAlreadyPresentException - exception in case when record by incoming is already present in storage
     */
    StorageVO saveRecord(StorageVO storageVO) throws RecordAlreadyPresentException;

    /**
     * Finds key-value record first in in-memory storage and then(if record will not be found in in-memory DB) in storage file
     *
     * @param key - incoming key of String type that should be used for finding the key-value record
     * @return - model VO with found key-value data
     * @throws RecordNotFoundException - exception in case when record will not be found in storage file by incoming key
     */
    StorageVO getRecord(String key) throws RecordNotFoundException;

    /**
     * Updates incoming record with new value in in-memory and file storage
     *
     * @param storageVO - incoming model value object(VO) with key value data that should be updated in key-value storage
     * @return - model VO with found key-value data
     * @throws RecordNotFoundException - exception in case when record will not be found in storage file by incoming key
     */
    StorageVO updateRecord(StorageVO storageVO) throws RecordNotFoundException;

    /**
     * Deletes incoming record from in-memory and file storage
     *
     * @param storageVO - incoming model value object(VO) with key value data that should deleted from˚ key-value storage
     * @return - model VO with found key-value data
     * @throws RecordNotFoundException - exception in case when record will not be found in storage file by incoming key
     */
    StorageVO deleteRecord(StorageVO storageVO) throws RecordNotFoundException;
}
