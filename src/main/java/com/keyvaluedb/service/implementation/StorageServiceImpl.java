package com.keyvaluedb.service.implementation;

import com.keyvaluedb.db.FileStorage;
import com.keyvaluedb.model.StorageVO;
import com.keyvaluedb.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final FileStorage fileStorage;

    private static Map<String, Object> inMemoryStorage = new HashMap<>();

    @Override
    public StorageVO saveRecord(StorageVO storageVO) {
        inMemoryStorage.put(storageVO.getKey(), storageVO.getValue());
        // Save value to file
        fileStorage.saveRecord(storageVO);
        return storageVO;
    }

    @Override
    public StorageVO getRecord(String key) {

        StorageVO record = fileStorage.getRecord(key);

        return StorageVO.builder()
                .key(key)
                .value(inMemoryStorage.get(key))
                .build();
    }

    @Override
    public StorageVO updateRecord(StorageVO storageVO) {
        if (!inMemoryStorage.containsKey(storageVO.getKey())) return storageVO;
        // Updates in memory storage with new value by it's key
        inMemoryStorage.replace(storageVO.getKey(), storageVO.getValue());
        // Updates storage file with new new value by it's key
        return storageVO;
    }

    @Override
    public void deleteRecord(StorageVO storageVO) {
        if (!inMemoryStorage.containsKey(storageVO.getKey())) return;
        // Deletes record by key
        inMemoryStorage.remove(storageVO.getKey(), storageVO.getValue());
        // Deletes record in storage file
        fileStorage.deleteRecord(storageVO);
    }
}
