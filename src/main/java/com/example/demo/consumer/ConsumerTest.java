package com.example.demo.consumer;

import com.example.demo.cache.AtomicRecord;
import com.example.demo.producer.DataProducer;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.amqp.ConnectionFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.function.Consumer;

@Service
public class ConsumerTest {


    @Resource
    private DataProducer dataProducer;
    @Resource
    private AtomicRecord atomicRecord;

    @Bean
    ConnectionFactoryCustomizer connectionFactoryCustomizer() {
        return (connectionFactory) -> {
            connectionFactory.setShutdownTimeout(0);
        };
    }
    /**
     * 获取数据并转发给另外两个队列
     * @param消息内容
     */
    @Bean
    public Consumer<Message<byte[]>> input1() throws IOException {
        return message -> {
            atomicRecord.increment1();
            byte[] payload = message.getPayload();
            dataProducer.sendOutput2(payload);
            dataProducer.sendOutput3(payload);
        };
    }

    /**
     * 消费队列中的数据
     * @param消息内容
     */
    @Bean
    public Consumer<Message<byte[]>> input2() throws IOException {
        return message -> {
            atomicRecord.increment2();
            byte[] payload = message.getPayload();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * 消费队列中的数据
     * @param消息内容
     */
    @Bean
    public Consumer<Message<byte[]>> input3() throws IOException {
        return message -> {
            atomicRecord.increment3();
            byte[] payload = message.getPayload();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
