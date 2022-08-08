package com.test.auth.service;

import com.test.auth.bean.ResetPwBean;
import com.test.auth.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetPwService {
    Logger logger = LoggerFactory.getLogger(getClass());

    private final ResetPwBean bean;

    private final PasswordEncoder passwordEncoder;

    public boolean service(UserVO vo) throws Exception {
        logger.info("InsertUserService.service()");
        vo.setUserpw(passwordEncoder.encode(vo.getUserpw()));
        return bean.service(vo.getUserpw());
    }
}
