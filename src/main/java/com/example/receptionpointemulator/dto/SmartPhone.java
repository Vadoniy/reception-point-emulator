//package com.example.receptionpointemulator.dto;
//
//import com.example.receptionpointemulator.dto.detail.Battery;
//import com.example.receptionpointemulator.dto.detail.MotherBoard;
//import com.example.receptionpointemulator.dto.detail.Screen;
//import com.example.receptionpointemulator.dto.enums.Model;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class SmartPhone {
//    private Screen screen;
//    private MotherBoard motherBoard;
//    private Battery battery;
//    private Model model;
//    private String ssn;
//
//    public SmartPhone(Screen screen, MotherBoard motherBoard, Battery battery, Model model, String ssn) {
//        this.screen = screen;
//        this.motherBoard = motherBoard;
//        this.battery = battery;
//        this.model = model;
//        this.ssn = ssn;
//    }
//
//    public Screen getScreen() {
//        return screen;
//    }
//
//    public MotherBoard getMotherBoard() {
//        return motherBoard;
//    }
//
//    public Battery getBattery() {
//        return battery;
//    }
//
//    public Model getModel() {
//        return model;
//    }
//
//    public boolean isBroken() {
//        return screen.isBroken() && motherBoard.isBroken();
//    }
//
//    public String toJson() {
//        try {
//            return new ObjectMapper().writeValueAsString(this);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
