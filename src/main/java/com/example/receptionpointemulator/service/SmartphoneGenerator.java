package com.example.receptionpointemulator.service;

import avro.schema.SmartPhoneAvro;
import avro.schema.detail.BatteryAvro;
import avro.schema.detail.MotherBoardAvro;
import avro.schema.detail.ScreenAvro;
import com.example.receptionpointemulator.dto.enums.Manufacturer;
import com.example.receptionpointemulator.dto.enums.Model;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class SmartphoneGenerator {

    public SmartPhoneAvro generateSmartphone(Manufacturer manufacturer) {
        final var ssn = UUID.randomUUID().toString();
        final var model = manufacturer.getRandomModel();
        final var screen = generateScreen(model, ssn);
        final var motherBoard = generateMotherBoard(model, ssn);
        final var battery = generateBattery(model, ssn);
//        return new SmartPhoneAvro(model.getManufacturer(), battery, model.name(), motherBoard, screen, ssn);
        return SmartPhoneAvro.newBuilder()
                .setModel(model.name())
                .setBattery(battery)
                .setSsn(ssn)
                .setMotherBoard(motherBoard)
                .setScreen(screen)
                .setManufacturer(model.getManufacturer())
                .build();
    }

//    public static ManufacturerAvro getRandomManufacturer() {
//        final var random = new Random();
//        return ManufacturerAvro.values()[random.nextInt(ManufacturerAvro.values().length)];
//    }

    private ScreenAvro generateScreen(Model model, String ssn) {
        final var random = new Random();
//        return new ScreenAvro(random.nextBoolean(), model.getManufacturer(), model.name(), ssn);
        return ScreenAvro.newBuilder()
                .setManufacturer(model.getManufacturer())
                .setModel(model.name())
                .setSsn(ssn)
                .setBroken(random.nextBoolean())
                .build();

    }

    private MotherBoardAvro generateMotherBoard(Model model, String ssn) {
        final var random = new Random();
//        return new MotherBoardAvro(random.nextBoolean(), model.getManufacturer(), model.name(), ssn);
        return MotherBoardAvro.newBuilder()
                .setBroken(random.nextBoolean())
                .setModel(model.name())
                .setManufacturer(model.getManufacturer())
                .setSsn(ssn)
                .build();
    }

    private BatteryAvro generateBattery(Model model, String ssn) {
//        return new BatteryAvro(true, model.getManufacturer(), model.name(), ssn);
        return BatteryAvro.newBuilder()
                .setBroken(true)
                .setManufacturer(model.getManufacturer())
                .setModel(model.name())
                .setSsn(ssn)
                .build();
    }
}
