package com.keyvaluestorage.db;

import com.keyvaluestorage.model.StorageVO;

public interface FileStorage {

    StorageVO saveRecord(StorageVO storageVO);

    StorageVO getRecord(String key);

    void updateRecord(StorageVO storageVO);

    void deleteRecord(StorageVO storageVO);
}
