package com.example.receptionpointemulator.dto.enums;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Random;

@AllArgsConstructor
public enum Manufacturer {
    APPLE(IphoneModel.getModels()),
    SAMSUNG(SamsungModel.getModels()),
    XIAOMI(XiaomiModel.getModels());

    private final List<Model> models;

    public Model getRandomModel() {
        final var random = new Random();
        return models.get(random.nextInt(this.models.size()));
    }

    public boolean containsModel(Model model) {
        return this.models.contains(model);
    }

    public static Manufacturer getRandomManufacturer() {
        final var random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
