package com.zy.credit.controller;

import com.zy.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @Autowired
    private Registration registration;

    @GetMapping("/add/{userId}/{points}")
    public String addCredit(@PathVariable("userId") Long userId,
                            @PathVariable("points") Integer points) {
        return creditService.addCredit(userId, points);
    }

    @GetMapping("/getIpAndPort")
    public String getIpAndPort() {
        return registration.getHost() + ":" + registration.getPort();
    }
}
