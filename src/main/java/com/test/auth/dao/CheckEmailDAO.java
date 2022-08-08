package com.test.auth.dao;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CheckEmailDAO {

    private final SqlSessionTemplate sqlSessionTemplate;

    public int service(String email) throws Exception{
        return sqlSessionTemplate.selectOne("user.checkEmail", email) ;
    }

}
