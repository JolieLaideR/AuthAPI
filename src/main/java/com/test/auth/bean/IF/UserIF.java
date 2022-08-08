package com.test.auth.bean.IF;

import com.test.auth.vo.UserVO;

public interface UserIF {

    public boolean service(UserVO vo) throws Exception;
}
