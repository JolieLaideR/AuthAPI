package com.test.auth.bean;

import com.test.auth.dao.LoginDAO;
import com.test.auth.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginBean {
    Logger logger = LoggerFactory.getLogger(getClass());

    private final LoginDAO dao;

    public UserVO service(UserVO vo) throws Exception{
        return dao.service(vo);
    }
}
