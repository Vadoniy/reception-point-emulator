package com.example.receptionpointemulator.dto.enums;

import avro.schema.enums.ManufacturerAvro;

import java.util.Arrays;
import java.util.List;

public enum IphoneModel implements Model {
    IPHONE_12_MINI,
    IPHONE_12_PRO,
    IPHONE_12_PRO_MAX,
    IPHONE_13_MINI,
    IPHONE_13_PRO,
    IPHONE_13_PRO_MAX,
    IPHONE_14,
    IPHONE_14_PLUS,
    IPHONE_14_PRO,
    IPHONE_14_PRO_MAX;

    public static List<Model> getModels() {
        return Arrays.asList(IphoneModel.values());
    }

    @Override
    public ManufacturerAvro getManufacturer() {
        return ManufacturerAvro.APPLE;
    }
}
