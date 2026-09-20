package com.zy.order.feign;

import com.zy.order.feign.fallback.StockServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "stock-service", fallback = StockServiceFallback.class)
public interface StockService {

    @GetMapping("/stock/deduct/{productId}/{stockCount}")
    String deductStock(@PathVariable("productId") Long productId,
                       @PathVariable("stockCount") Integer stockCount);
}
