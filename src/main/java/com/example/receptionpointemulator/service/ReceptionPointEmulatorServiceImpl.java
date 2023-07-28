package com.example.receptionpointemulator.service;

import avro.schema.SmartPhoneAvro;
import com.example.receptionpointemulator.dto.enums.Manufacturer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceptionPointEmulatorServiceImpl {
    private static final String RECEPTION_POINT_TOPIC = "reception-point-topic";
    private final SmartphoneGenerator smartphoneGenerator;
    private final KafkaProducer<UUID, SmartPhoneAvro> kafkaProducer;

    @Scheduled(cron = "0/3 * * * * *")
    public void getNewSmartphone() {
//        removeAllTopics();
        final var smartPhone = smartphoneGenerator.generateSmartphone(Manufacturer.getRandomManufacturer());
        final var record = new ProducerRecord<>(RECEPTION_POINT_TOPIC, UUID.fromString(smartPhone.getSsn().toString()), smartPhone);
        kafkaProducer.send(record);
        log.info("SmartPhone {} - {} with ssn {}, is broken: {}",
                smartPhone.getManufacturer().name(),
                smartPhone.getModel(),
                smartPhone.getSsn(),
                isSmartphoneBroken(smartPhone));
    }

    private String isSmartphoneBroken(SmartPhoneAvro smartPhoneAvro) {
        return String.format("MotherBoard is %s, screen is %s",
                smartPhoneAvro.getMotherBoard().getBroken() ? "broken" : "ok",
                smartPhoneAvro.getScreen().getBroken() ? "broken" : "ok");
    }

    private void removeAllTopics() {
        try {
            final Map<String, Object> adminConfig = Map.of(
                    AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9091,localhost:9092,localhost:9093");
            final var kafkaAdmin = Admin.create(adminConfig);
            final var listTopicsResult = kafkaAdmin.listTopics().names().get();
            kafkaAdmin.deleteTopics(listTopicsResult);
            log.info("All topics are removed");
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
