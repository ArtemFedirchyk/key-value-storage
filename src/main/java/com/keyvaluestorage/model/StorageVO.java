package com.keyvaluestorage.model;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StorageVO implements Serializable {
    private String key;
    private Object value;
}
