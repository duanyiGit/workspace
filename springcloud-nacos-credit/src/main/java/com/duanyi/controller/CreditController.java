package com.duanyi.controller;

import com.duanyi.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    CreditService creditService;

    @GetMapping("/add/{productId}/{score}")
    public String addScore(@PathVariable("productId") long productId,
                            @PathVariable("score") int socre){
        return creditService.addScore(productId,socre);
    }
}
