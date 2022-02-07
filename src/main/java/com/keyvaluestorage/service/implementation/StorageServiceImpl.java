package com.keyvaluestorage.service.implementation;

import com.keyvaluestorage.db.FileStorage;
import com.keyvaluestorage.model.StorageVO;
import com.keyvaluestorage.service.StorageService;
import com.keyvaluestorage.util.PropertiesHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final FileStorage fileStorage;
    private final PropertiesHelper propertiesHelper;

    private Map<String, Object> inMemoryStorage = new HashMap<>();

    /**
     * Initialises in-memory storage from file on application's startup
     */
    @PostConstruct
    public void initialiseInMemoryStorage() {
        inMemoryStorage = propertiesHelper.getAllKeyValuePairs();
    }

    @Override
    public StorageVO saveRecord(StorageVO storageVO) {
        // Saves value to in-memory storage
        inMemoryStorage.put(storageVO.getKey(), storageVO.getValue());
        // Saves value to file
        fileStorage.saveRecord(storageVO);
        return storageVO;
    }

    @Override
    public StorageVO getRecord(String key) {
        if (inMemoryStorage.containsKey(key)) // Gets value by key in in-memory storage if it's present there
            return StorageVO.builder()
                    .key(key)
                    .value(inMemoryStorage.get(key))
                    .build();
        // Gets value by key in file storage if it's not present in in-memory storage
        return fileStorage.getRecord(key);
    }

    @Override
    public void updateRecord(StorageVO storageVO) {
        if (!inMemoryStorage.containsKey(storageVO.getKey())) return;
        // Updates in memory storage with new value by it's key
        inMemoryStorage.replace(storageVO.getKey(), storageVO.getValue());
        // Updates storage file with new new value by it's key
        fileStorage.updateRecord(storageVO);
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
