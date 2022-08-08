package com.test.auth.service;

import com.test.auth.bean.InsertUserBean;
import com.test.auth.util.EncDecBean;
import com.test.auth.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsertUserService {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final InsertUserBean bean;

    private final EncDecBean encDecBean;

    private final PasswordEncoder passwordEncoder;

    public boolean service(UserVO vo) throws Exception {
        logger.info("InsertUserService.service()");
        vo.setUserpw(passwordEncoder.encode(vo.getUserpw()));
        vo.setPhoneno(encDecBean.encodeStr(vo.getPhoneno()));
        return bean.service(vo);
    }
}
