package com.zy.stock.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StockService {

    private final Map<Long, AtomicInteger> stockMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        stockMap.put(1L, new AtomicInteger(100));
        stockMap.put(2L, new AtomicInteger(50));
    }

    public String deductStock(Long productId, Integer stockCount) {
        AtomicInteger stock = stockMap.get(productId);
        if (stock == null) {
            return "商品不存在";
        }
        int remain = stock.addAndGet(-stockCount);
        if (remain < 0) {
            stock.addAndGet(stockCount);
            return "库存不足";
        }
        return "扣减成功，剩余库存：" + remain;
    }
}
