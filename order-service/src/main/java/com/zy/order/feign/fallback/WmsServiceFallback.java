package com.zy.order.feign.fallback;

import com.zy.order.feign.WmsService;
import org.springframework.stereotype.Component;

@Component
public class WmsServiceFallback implements WmsService {

    @Override
    public String delivery(Long userId, Long productId) {
        // TODO 记录降级日志，后续自动补偿
        System.out.println("调用物流服务失败，记录日志降级处理：userId=" + userId + ",productId=" + productId);
        return "error";
    }
}
