package com.keyvaluedb.service;

import com.keyvaluedb.model.StorageVO;

import java.io.IOException;

public interface StorageService {

    StorageVO saveRecord(StorageVO storageVO) throws IOException;

    StorageVO getRecord(String key) throws IOException;

    StorageVO updateRecord(StorageVO storageVO);

    void deleteRecord(StorageVO storageVO);
}
