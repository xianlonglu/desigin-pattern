package com.lxl.kafkaspringboot;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.lxl.kafka.KafkaProperties;

@SpringBootApplication
public class KafkaPracticeApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(KafkaPracticeApplication.class, args);
		GpKafkaProducer kp = context.getBean(GpKafkaProducer.class);
		for (int i = 0; i < 5; i++) {
			kp.send(KafkaProperties.TOPIC2, i + "", "msgData" + i);
			kp.send("test", i + "", "msg" + i);
			TimeUnit.SECONDS.sleep(2);
		}
	}

}
