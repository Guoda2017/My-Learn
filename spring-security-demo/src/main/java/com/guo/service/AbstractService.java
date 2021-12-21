package com.guo.service;

import com.guo.service.impl.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : fengbo.guo
 * @date : 2021-12-21 17:30
 * @Description :
 */
public abstract class AbstractService {

    @Autowired
    private TestService testService;

    @Autowired
    private SystemConfig systemConfig;

    public String getStr() {
        return "123";
    }
}
