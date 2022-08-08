package com.test.auth.bean;

import com.test.auth.bean.IF.CheckBeanIF;
import com.test.auth.dao.CheckEmailDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckEmailBean implements CheckBeanIF {

    private final CheckEmailDAO dao;

    @Override
    public boolean service(String email) throws Exception {
        return dao.service(email) > 0;
    }
}
