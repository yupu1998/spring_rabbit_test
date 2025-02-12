package com.example.demo.schedule;

import com.example.demo.cache.AtomicRecord;
import com.example.demo.producer.DataProducer;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Component
public class ScheduleData {

    @Resource
    private DataProducer dataProducer;
    @Resource
    private AtomicRecord atomicRecord;

    @Scheduled(fixedDelay = 1000)
    public void keystoneCache(){

        //生成数据
        String content = String.join("!!!", Collections.nCopies(1000, "Hello World"));
        byte[] data = content.getBytes();

        //推送数据给output1
        for (int i = 0; i < 1000; i++) {
            dataProducer.sendOutput1(data);
        }
    }

    private Long count1 = 0L;
    private Long count2 = 0L;
    private Long count3 = 0L;


    @Scheduled(fixedDelay = 1000)
    public void logConsumer(){

        boolean c1 = false;
        boolean c2 = false;
        boolean c3 = false;
        if(atomicRecord.getCount1()!= count1){
            c1 = true;
            count1 = (long) atomicRecord.getCount1();
        }
        if(atomicRecord.getCount2()!= count2){
            c2 = true;
            count2 = (long) atomicRecord.getCount2();
        }
        if(atomicRecord.getCount2()!= count3){
            c3 = true;
            count3 = (long) atomicRecord.getCount3();
        }
        System.out.println(String.format("input1:%s; input2:%s; input3:%s",c1,c2,c3));

    }
}
