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
        return SmartPhoneAvro.newBuilder()
                .setModel(model.name())
                .setBattery(battery)
                .setSsn(ssn)
                .setMotherBoard(motherBoard)
                .setScreen(screen)
                .setManufacturer(model.getManufacturer())
                .build();
    }

    private ScreenAvro generateScreen(Model model, String ssn) {
        final var random = new Random();
        return ScreenAvro.newBuilder()
                .setManufacturer(model.getManufacturer())
                .setModel(model.name())
                .setSsn(ssn)
                .setBroken(random.nextBoolean())
                .build();

    }

    private MotherBoardAvro generateMotherBoard(Model model, String ssn) {
        final var random = new Random();
        return MotherBoardAvro.newBuilder()
                .setBroken(random.nextBoolean())
                .setModel(model.name())
                .setManufacturer(model.getManufacturer())
                .setSsn(ssn)
                .build();
    }

    private BatteryAvro generateBattery(Model model, String ssn) {
        return BatteryAvro.newBuilder()
                .setBroken(true)
                .setManufacturer(model.getManufacturer())
                .setModel(model.name())
                .setSsn(ssn)
                .build();
    }
}