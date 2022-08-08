package com.test.auth.dao;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CheckNicknameDAO {

    private final SqlSessionTemplate sqlSessionTemplate;

    public int service(String nickname) throws Exception{
        return sqlSessionTemplate.selectOne("user.checkNickname", nickname) ;
    }
}
