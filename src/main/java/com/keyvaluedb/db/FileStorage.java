package com.keyvaluedb.db;

import com.keyvaluedb.model.StorageVO;

public interface FileStorage {

    StorageVO saveRecord(StorageVO storageVO);

    StorageVO getRecord(String key);

    void updateRecord(StorageVO storageVO);

    void deleteRecord(StorageVO storageVO);
}
