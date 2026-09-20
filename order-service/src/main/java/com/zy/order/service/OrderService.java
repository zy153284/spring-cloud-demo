package com.zy.order.service;

import com.zy.order.feign.CreditService;
import com.zy.order.feign.StockService;
import com.zy.order.feign.WmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private StockService stockService;

    @Autowired
    private CreditService creditService;

    @Autowired
    private WmsService wmsService;

    @Transactional
    public String createOrder(Long productId, Long userId, Integer stockCount, Integer creditCount) {
        System.out.println("创建订单成功");
        String stockResult = stockService.deductStock(productId, stockCount);
        String creditResult = creditService.addCredit(userId, creditCount);
        String wmsResult = wmsService.delivery(userId, productId);
        return "success";
    }
}
