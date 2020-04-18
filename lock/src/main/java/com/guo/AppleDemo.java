package com.guo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: guofengbo
 * @create: 2020-03-30 11:13
 **/
@Component
public class AppleDemo {

    @Autowired
    private RestTemplate restTemplate;



}
