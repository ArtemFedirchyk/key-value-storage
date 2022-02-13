package com.keyvaluestorage.controller;

import com.keyvaluestorage.exception.RecordAlreadyPresentException;
import com.keyvaluestorage.exception.RecordNotFoundException;
import com.keyvaluestorage.model.StorageVO;
import com.keyvaluestorage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/storage", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @PostMapping
    public ResponseEntity saveRecord(@RequestBody StorageVO storageVO) {
        try {
            return new ResponseEntity<>(storageService.saveRecord(storageVO), HttpStatus.CREATED);
        } catch (RecordAlreadyPresentException exception) {
            return new ResponseEntity<>(convertErrorMessage(exception.getMessage()), HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity getRecord(@RequestParam("key") String key) {
        try {
            return new ResponseEntity<>(storageService.getRecord(key), HttpStatus.OK);
        } catch (RecordNotFoundException exception) {
            return new ResponseEntity<>(convertErrorMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity updateRecord(@RequestBody StorageVO storageVO) {
        try {
            return new ResponseEntity<>(storageService.updateRecord(storageVO), HttpStatus.OK);
        } catch (RecordNotFoundException exception) {
            return new ResponseEntity<>(convertErrorMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteRecord(@RequestBody StorageVO storageVO) {
        try {
            return new ResponseEntity<>(storageService.deleteRecord(storageVO), HttpStatus.OK);
        } catch (RecordNotFoundException exception) {
            return new ResponseEntity<>(convertErrorMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    private String convertErrorMessage(String errorMessage) {
        return String.format("{\"error\":\"%s\"}", errorMessage);
    }
}
