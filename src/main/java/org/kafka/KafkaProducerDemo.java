package org.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaProducerDemo {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(KafkaProducerDemo.class.getResourceAsStream("../../resources/kafkaconfig.properties"));
        } catch (java.io.IOException e) {
            System.out.println(e.getStackTrace());
        }

        System.out.println(properties);

        KafkaProducer<String, String> producer = null;
        try {
            producer = new KafkaProducer<String, String>(properties);
            for (int i = 0; i < 10; ++i) {
                String value = String.format("message id:%d.", i);
                producer.send(new ProducerRecord<String, String>("test", value));
                Thread.sleep(1000);
                System.out.println("send message:" + value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
