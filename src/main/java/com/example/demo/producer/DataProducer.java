package com.example.demo.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class DataProducer {

    @Autowired
    private StreamBridge streamBridge;


    public void sendOutput1(byte[] bytes) {

        streamBridge
                .send("output1-out-0",
                        MessageBuilder.withPayload(bytes).build());

    }
    public void sendOutput2(byte[] bytes) {

        streamBridge
                .send("output2-out-0",
                        MessageBuilder.withPayload(bytes).build());

    }
    public void sendOutput3(byte[] bytes) {

        streamBridge
                .send("output3-out-0",
                        MessageBuilder.withPayload(bytes).build());

    }

}
