package com.keyvaluestorage.service;

import com.keyvaluestorage.model.StorageVO;
import com.keyvaluestorage.util.PropertiesHelper;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StorageServiceTest {

    @Autowired
    private StorageService storageService;
    @Autowired
    private PropertiesHelper propertiesHelper;

    private static final String testKey = "test-key";
    private static final String testValue = "test value";

    @Test
    @Order(1)
    public void testSaveRecord() {
        storageService.saveRecord(getStorageVO());
        // Calls function for getting saved value from in-memory and file storage
        StorageVO savedRecord = storageService.getRecord(testKey);
        assertEquals(testKey, savedRecord.getKey());
        assertEquals(testValue, savedRecord.getValue());
    }

    @Test
    @Order(2)
    public void testGetRecord() {
        StorageVO record = storageService.getRecord(testKey);
        assertEquals(testKey, record.getKey());
        assertEquals(testValue, record.getValue());
    }

    @Test
    @Order(3)
    public void testUpdateRecord() {
        final String modifiedValue = "test value modified";
        StorageVO storageVO = getStorageVO();
        storageVO.setValue(modifiedValue);
        // Calls function for updating record by key in storage with new value
        storageService.updateRecord(storageVO);
        StorageVO updatedRecord = storageService.getRecord(storageVO.getKey()); // Gets updated record
        assertEquals(modifiedValue, updatedRecord.getValue());
    }

    @Test
    @Order(4)
    public void testDeleteRecord() {
        StorageVO storageVO = getStorageVO();
        storageService.deleteRecord(storageVO);
        assertFalse(propertiesHelper.containsKey(storageVO.getKey()));
    }

    private StorageVO getStorageVO() {
        return StorageVO.builder()
                .key(testKey)
                .value(testValue)
                .build();
    }
}