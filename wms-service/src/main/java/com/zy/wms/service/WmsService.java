package com.zy.wms.service;

import org.springframework.stereotype.Service;

@Service
public class WmsService {

    public String delivery(Long userId, Long productId) {
        String result = String.format("用户userId=%d的商品productId=%d：生成发货单", userId, productId);
        System.out.println(result);
        return result;
    }
}
