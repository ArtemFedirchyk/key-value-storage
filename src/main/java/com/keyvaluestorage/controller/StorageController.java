package com.keyvaluestorage.controller;

import com.keyvaluestorage.model.StorageVO;
import com.keyvaluestorage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @PostMapping
    public ResponseEntity<StorageVO> saveRecord(@RequestBody StorageVO storageVO) {
        return new ResponseEntity<>(storageService.saveRecord(storageVO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<StorageVO> getRecord(@RequestParam("key") String key) {
        return new ResponseEntity<>(storageService.getRecord(key), HttpStatus.OK);
    }

    @PutMapping
    public void updateRecord(@RequestBody StorageVO storageVO) {
        storageService.updateRecord(storageVO);
    }

    @DeleteMapping
    public void deleteRecord(@RequestBody StorageVO storageVO) {
        storageService.deleteRecord(storageVO);
    }
}
