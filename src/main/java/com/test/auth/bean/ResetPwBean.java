package com.test.auth.bean;

import com.test.auth.dao.ResetPwDAO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResetPwBean {
    Logger logger = LoggerFactory.getLogger(getClass());

    private final ResetPwDAO dao;

    public boolean service(String userPw) throws Exception {
        logger.info("ResetPwBean.service()");
        return dao.service(userPw);
    }
}
