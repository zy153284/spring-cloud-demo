package com.zy.order.feign;

import com.zy.order.feign.fallback.CreditServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "credit-service", fallback = CreditServiceFallback.class)
public interface CreditService {

    @GetMapping("/credit/add/{userId}/{points}")
    String addCredit(@PathVariable("userId") Long userId,
                     @PathVariable("points") Integer points);
}
