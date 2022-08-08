package com.test.auth.dao;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ResetPwDAO {
    Logger logger = LoggerFactory.getLogger(getClass());

    private final SqlSessionTemplate sqlSessionTemplate;

    public boolean service(String userPw) throws Exception {
        logger.info("InsertUserDAO.service()");
        return sqlSessionTemplate.update("user.resetPw", userPw) > 0;
    }
}
