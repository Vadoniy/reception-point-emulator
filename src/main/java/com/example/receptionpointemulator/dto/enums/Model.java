package com.example.receptionpointemulator.dto.enums;

import avro.schema.enums.ManufacturerAvro;

public interface Model {

    ManufacturerAvro getManufacturer();

    String name();
}
