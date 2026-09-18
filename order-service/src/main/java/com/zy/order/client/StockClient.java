package com.zy.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "stock-service")
public interface StockClient {

    @GetMapping("/stock/deduct/{productId}/{stockCount}")
    String deductStock(@PathVariable("productId") Long productId,
                       @PathVariable("stockCount") Integer stockCount);
}
