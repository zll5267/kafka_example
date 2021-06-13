package org.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerDemo {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(KafkaConsumerDemo.class.getResourceAsStream("../../resources/kafkaconfig.properties"));
        } catch (java.io.IOException e) {
            System.out.println(e.getStackTrace());
        }

        System.out.println(properties);

        KafkaConsumer<String, String> kafka_consumer = new KafkaConsumer<String, String>(properties);
        kafka_consumer.subscribe(Collections.singletonList("test"));

        while (true) {
            ConsumerRecords<String, String> records = kafka_consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(String.format("topic:%s, offset:%d, value:%s",
                        record.topic(),
                        record.offset(),
                        record.value()));
            }
            System.out.println("try to consume message");
        }
    }
}
