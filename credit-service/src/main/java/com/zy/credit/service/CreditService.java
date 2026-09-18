package com.zy.credit.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CreditService {

    private final Map<Long, AtomicInteger> creditMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        creditMap.put(1L, new AtomicInteger(0));
        creditMap.put(2L, new AtomicInteger(0));
    }

    public String addCredit(Long userId, Integer points) {
        AtomicInteger credit = creditMap.computeIfAbsent(userId, k -> new AtomicInteger(0));
        int total = credit.addAndGet(points);
        return "积分增加成功，当前积分：" + total;
    }
}
