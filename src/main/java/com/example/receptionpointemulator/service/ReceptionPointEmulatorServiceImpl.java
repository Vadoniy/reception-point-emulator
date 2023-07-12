package com.example.receptionpointemulator.service;

import avro.schema.SmartPhoneAvro;
import com.example.receptionpointemulator.dto.enums.Manufacturer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceptionPointEmulatorServiceImpl {
    private static final String RECEPTION_POINT_TOPIC = "reception-point-topic";
    private final SmartphoneGenerator smartphoneGenerator;
    private final KafkaTemplate<UUID, SpecificRecord> kafkaTemplate;
//    private final KafkaProducer<UUID, SmartPhoneAvro> kafkaProducer;

    @Scheduled(cron = "0/10 * * * * *")
    public void getNewSmartphone() {
        final var smartPhone = smartphoneGenerator.generateSmartphone(Manufacturer.getRandomManufacturer());
        final var record = new ProducerRecord<>(RECEPTION_POINT_TOPIC, UUID.fromString(smartPhone.getSsn().toString()), smartPhone);
//        final var record = new ProducerRecord<>(RECEPTION_POINT_TOPIC, UUID.fromString(smartPhone.getSsn().toString()), smartPhone);
//        kafkaTemplate.send(RECEPTION_POINT_TOPIC, smartPhone);
//        kafkaProducer.send(record);
        Message<SmartPhoneAvro> build = MessageBuilder.withPayload(smartPhone)
                .setHeader(KafkaHeaders.TOPIC, RECEPTION_POINT_TOPIC)
                .setHeader(KafkaHeaders.KEY, UUID.fromString(smartPhone.getSsn().toString()))
                .build();
        kafkaTemplate.send(build);
        log.info("SmartPhone {} - {} with ssn {} was sent to {}",
                smartPhone.getManufacturer().name(),
                smartPhone.getModel(),
                smartPhone.getSsn(),
                RECEPTION_POINT_TOPIC);
    }
}
