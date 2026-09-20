package com.zy.order.feign;

import com.zy.order.feign.fallback.WmsServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "wms-service", fallback = WmsServiceFallback.class)
public interface WmsService {

    @PostMapping("/wms/delivery/{userId}/{productId}")
    String delivery(@PathVariable("userId") Long userId,
                    @PathVariable("productId") Long productId);
}
