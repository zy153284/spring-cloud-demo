package com.zy.order.feign.fallback;

import com.zy.order.feign.StockService;
import org.springframework.stereotype.Component;

@Component
public class StockServiceFallback implements StockService {

    @Override
    public String deductStock(Long productId, Integer stockCount) {
        // TODO 记录降级日志，后续自动补偿
        System.out.println("调用扣库存服务失败，记录日志降级处理：productId=" + productId + ",stockCount=" + stockCount);
        return "error";
    }
}
