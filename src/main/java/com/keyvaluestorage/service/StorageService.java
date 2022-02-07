package com.keyvaluestorage.service;

import com.keyvaluestorage.model.StorageVO;

import java.io.IOException;

public interface StorageService {

    StorageVO saveRecord(StorageVO storageVO);

    StorageVO getRecord(String key);

    void updateRecord(StorageVO storageVO);

    void deleteRecord(StorageVO storageVO);
}
