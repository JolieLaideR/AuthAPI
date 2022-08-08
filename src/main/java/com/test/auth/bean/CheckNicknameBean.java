package com.test.auth.bean;

import com.test.auth.bean.IF.CheckBeanIF;
import com.test.auth.dao.CheckNicknameDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckNicknameBean implements CheckBeanIF {

    private final CheckNicknameDAO dao;

    @Override
    public boolean service(String nickname) throws Exception {
        return dao.service(nickname) > 0;
    }
}
