package com.interview.qa.mq;

import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

/**
 * 同步生产者
 */
@RequiredArgsConstructor
public class RocketMQSyncProducer {

    private final RocketMQTemplate rocketMQTemplate;

    public void send(String topic, String message) {
        rocketMQTemplate.convertAndSend(topic, message);
    }
}
