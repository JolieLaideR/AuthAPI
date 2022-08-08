package com.test.auth.bean;

import com.test.auth.bean.IF.CheckBeanIF;
import com.test.auth.dao.CheckPhoneNoDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckPhoneNoBean implements CheckBeanIF {

    private final CheckPhoneNoDAO dao;

    @Override
    public boolean service(String phoneNo) throws Exception {
        return dao.service(phoneNo) > 0;
    }
}
