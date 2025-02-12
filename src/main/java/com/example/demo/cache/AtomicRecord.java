package com.example.demo.cache;

import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class AtomicRecord {

    private AtomicInteger count1 = new AtomicInteger(0);
    private AtomicInteger count2 = new AtomicInteger(0);
    private AtomicInteger count3 = new AtomicInteger(0);

    public void increment1() {
        count1.incrementAndGet(); // 原子递增
    }

    public int getCount1() {
        return count1.get();
    }
    public void increment2() {
        count2.incrementAndGet(); // 原子递增
    }

    public int getCount2() {
        return count2.get();
    }
    public void increment3() {
        count3.incrementAndGet(); // 原子递增
    }

    public int getCount3() {
        return count3.get();
    }
}
