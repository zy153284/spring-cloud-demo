package com.zy.order.service;

import com.zy.order.client.CreditClient;
import com.zy.order.client.StockClient;
import com.zy.order.client.WmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private StockClient stockClient;

    @Autowired
    private CreditClient creditClient;

    @Autowired
    private WmsClient wmsClient;

    public String createOrder(Long productId, Long userId, Integer stockCount, Integer creditCount) {
        System.out.println("创建订单成功");
        String stockResult = stockClient.deductStock(productId, stockCount);
        String creditResult = creditClient.addCredit(userId, creditCount);
        String wmsResult = wmsClient.delivery(userId, productId);
        return String.format("success，%s；%s；%s", stockResult, creditResult, wmsResult);
    }
}
