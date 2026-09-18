package com.zy.order.service;

import com.zy.order.client.CreditClient;
import com.zy.order.client.StockClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {

    @Autowired
    private StockClient stockClient;

    @Autowired
    private CreditClient creditClient;

    private final AtomicLong orderIdGenerator = new AtomicLong(1);

    public String createOrder(Long userId, Long productId, Integer stockCount) {
        String stockResult = stockClient.deductStock(productId, stockCount);
        if (!stockResult.contains("成功")) {
            return "下单失败：" + stockResult;
        }

        String creditResult = creditClient.addCredit(userId, stockCount * 10);
        long orderId = orderIdGenerator.getAndIncrement();

        return String.format("下单成功，订单号：%d，%s，%s", orderId, stockResult, creditResult);
    }
}
