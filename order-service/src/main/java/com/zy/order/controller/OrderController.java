package com.zy.order.controller;

import com.zy.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Registration registration;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/create")
    public String createOrder(@RequestParam("productId") Long productId,
                              @RequestParam("userId") Long userId,
                              @RequestParam("stockCount") Integer stockCount,
                              @RequestParam("creditCount") Integer creditCount) {
        return orderService.createOrder(productId, userId, stockCount, creditCount);
    }

    /**
     * 直接调用原生 HTTP 接口（RestTemplate 硬编码 URL）
     */
    @GetMapping("/deductDirect/{productId}/{stockCount}")
    public String deductStockDirect(@PathVariable("productId") Long productId,
                                    @PathVariable("stockCount") Integer stockCount) {
        String url = "http://localhost:8081/stock/deduct/" + productId + "/" + stockCount;
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/getIpAndPort")
    public String getIpAndPort() {
        return registration.getHost() + ":" + registration.getPort();
    }
}
