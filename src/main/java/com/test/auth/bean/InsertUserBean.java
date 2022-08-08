package com.test.auth.bean;

import com.test.auth.bean.IF.UserIF;
import com.test.auth.dao.InsertUserDAO;
import com.test.auth.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InsertUserBean implements UserIF {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final InsertUserDAO dao;

    public boolean service(UserVO vo) throws Exception {
        logger.info("InsertUserBean.service()");
        return dao.service(vo);
    }
}
