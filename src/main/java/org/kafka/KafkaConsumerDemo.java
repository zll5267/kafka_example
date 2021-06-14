package org.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

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
        //kafka_consumer.subscribe(Collections.singletonList("test"));
        Collection<String> topics = new ArrayList<>();
        topics.add("test");
        topics.add("test2");
        kafka_consumer.subscribe(topics);

        while (true) {
            ConsumerRecords<String, String> records = kafka_consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(String.format("topic:%s, offset:%d, value:%s",
                        record.topic(),
                        record.offset(),
                        record.value()));
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.println("try to consume message, " + df.format(new Date()));// new Date()为获取当前系统时间
        }
    }
}
