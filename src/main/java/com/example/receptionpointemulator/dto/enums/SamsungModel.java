package com.example.receptionpointemulator.dto.enums;

import avro.schema.enums.ManufacturerAvro;

import java.util.Arrays;
import java.util.List;

public enum SamsungModel implements Model {

    S8,
    S8_PLUS,
    S9,
    S9_PLUS,
    S10_E,
    S10,
    S10_PLUS,
    S20,
    S20_PLUS,
    S20_ULTRA,
    S21,
    S21_PLUS,
    S21_ULTRA,
    S22,
    S22_PLUS,
    S22_ULTRA,
    S23,
    S23_PLUS,
    S23_ULTRA;

    public static List<Model> getModels() {
        return Arrays.asList(SamsungModel.values());
    }

    @Override
    public ManufacturerAvro getManufacturer() {
        return ManufacturerAvro.SAMSUNG;
    }
}
