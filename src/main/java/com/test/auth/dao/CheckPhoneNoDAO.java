package com.test.auth.dao;


import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CheckPhoneNoDAO {

    private final SqlSessionTemplate sqlSessionTemplate;

    public int service(String phoneNo) throws Exception{
        return sqlSessionTemplate.selectOne("user.checkPhoneNo", phoneNo) ;
    }
}
