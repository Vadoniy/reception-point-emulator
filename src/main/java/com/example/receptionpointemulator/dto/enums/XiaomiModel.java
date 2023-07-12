package com.example.receptionpointemulator.dto.enums;

import avro.schema.enums.ManufacturerAvro;

import java.util.Arrays;
import java.util.List;

public enum XiaomiModel implements Model {

    MI9,
    MI9_SE,
    MI9_LITE,
    MI9_PRO,
    MI10,
    MI10_ULTRA,
    MI10_LITE,
    MI10_PRO,
    MI11,
    MI11_ULTRA,
    MI11_LITE,
    MI11_PRO,
    MI12,
    MI12_X,
    MI12_PRO;

    public static List<Model> getModels() {
        return Arrays.asList(XiaomiModel.values());
    }

    @Override
    public ManufacturerAvro getManufacturer() {
        return ManufacturerAvro.XIAOMI;
    }
}
