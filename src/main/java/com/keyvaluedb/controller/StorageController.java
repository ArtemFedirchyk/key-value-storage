package com.keyvaluedb.controller;

import com.keyvaluedb.model.StorageVO;
import com.keyvaluedb.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @PostMapping
    public ResponseEntity<StorageVO> saveRecord(@RequestBody StorageVO storageVO) throws IOException {
        return new ResponseEntity<>(storageService.saveRecord(storageVO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<StorageVO> getRecord(@RequestParam("key") String key) throws IOException {
        return new ResponseEntity<>(storageService.getRecord(key), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StorageVO> updateRecord(@RequestBody StorageVO storageVO) {
        return new ResponseEntity<>(storageService.updateRecord(storageVO), HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteRecord(@RequestBody StorageVO storageVO) {
        storageService.deleteRecord(storageVO);
    }
}
