package com.test.auth.dao;

import com.test.auth.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoginDAO {

    private final SqlSessionTemplate sqlSessionTemplate;

    public UserVO service(UserVO vo) throws Exception {
        return sqlSessionTemplate.selectOne("user.checkPw", vo);
    }
}
