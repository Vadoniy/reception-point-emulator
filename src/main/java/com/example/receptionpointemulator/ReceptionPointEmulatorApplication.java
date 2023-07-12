package com.example.receptionpointemulator;

import avro.schema.SmartPhoneAvro;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.UUIDSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;
import java.util.UUID;

@SpringBootApplication
@EnableScheduling
public class ReceptionPointEmulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReceptionPointEmulatorApplication.class, args);
    }

    @Bean
    public KafkaProducer<UUID, SmartPhoneAvro> kafkaProducer() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9091,localhost:9092,localhost:9093");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        properties.put("schema.registry.url", "http://localhost:8081");
        properties.put("avro.use.logical.type.converters", true);
        properties.put("avro.remove.java.properties", true);

        return new KafkaProducer<>(properties);
    }
}