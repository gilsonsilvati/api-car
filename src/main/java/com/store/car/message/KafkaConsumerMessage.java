package com.store.car.message;

import com.store.car.dto.CarDTO;
import com.store.car.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerMessage {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerMessage.class);

    @Autowired
    protected CarService carService;

    @KafkaListener(topics = "car-topic", groupId = "store-posts-group")
    public void listening(CarDTO carDTO) {
        LOG.info("CAR STORE - Received Car Post information: {}", carDTO);

        carService.newPostDetails(carDTO);
    }
}
