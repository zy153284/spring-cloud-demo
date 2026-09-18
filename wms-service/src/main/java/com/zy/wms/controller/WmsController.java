package com.zy.wms.controller;

import com.zy.wms.service.WmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wms")
public class WmsController {

    @Autowired
    private WmsService wmsService;

    @Autowired
    private Registration registration;

    @PostMapping("/delivery/{userId}/{productId}")
    public String delivery(@PathVariable("userId") Long userId,
                           @PathVariable("productId") Long productId) {
        return wmsService.delivery(userId, productId);
    }

    @GetMapping("/getIpAndPort")
    public String getIpAndPort() {
        return registration.getHost() + ":" + registration.getPort();
    }
}
