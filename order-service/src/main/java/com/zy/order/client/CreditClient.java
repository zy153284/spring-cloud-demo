package com.zy.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "credit-service")
public interface CreditClient {

    @GetMapping("/credit/add/{userId}/{points}")
    String addCredit(@PathVariable("userId") Long userId,
                     @PathVariable("points") Integer points);
}

