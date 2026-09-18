package com.zy.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "wms-service")
public interface WmsClient {

    @PostMapping("/wms/delivery/{userId}/{productId}")
    String delivery(@PathVariable("userId") Long userId,
                    @PathVariable("productId") Long productId);
}
