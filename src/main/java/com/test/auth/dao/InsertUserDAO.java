package com.test.auth.dao;

import com.test.auth.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InsertUserDAO {
    Logger logger = LoggerFactory.getLogger(getClass());

    private final SqlSessionTemplate sqlSessionTemplate;

    public boolean service(UserVO vo) throws Exception {
        logger.info("InsertUserDAO.service() vo : {}" ,vo);
        return sqlSessionTemplate.insert("user.insertUser", vo) > 0;
    }
}
