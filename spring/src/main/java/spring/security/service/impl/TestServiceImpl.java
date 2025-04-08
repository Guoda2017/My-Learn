package main.java.spring.security.service.impl;

import main.java.spring.security.service.AbstractService;
import main.java.spring.security.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author : fengbo.guo
 * @date : 2021-12-21 17:31
 * @Description :
 */
@Service
public class TestServiceImpl extends AbstractService implements TestService {

    @Override
    public String demo() {
        return getStr();
    }
}
