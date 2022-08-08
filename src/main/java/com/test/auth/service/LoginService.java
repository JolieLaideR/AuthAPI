package com.test.auth.service;

import com.test.auth.bean.LoginBean;
import com.test.auth.util.EncDecBean;
import com.test.auth.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    Logger logger = LoggerFactory.getLogger(getClass());

    private final LoginBean bean;
    private final PasswordEncoder passwordEncoder;
    private final EncDecBean encDecBean;

    public JSONObject service(UserVO vo) throws Exception {
        UserVO userVO = new UserVO();
        userVO = bean.service(vo);
        logger.info("userVO : {}",userVO);
        if(passwordEncoder.matches(vo.getUserpw(),userVO.getUserpw())){
            JSONObject result = new JSONObject();
            result.put("UserName(이름)", userVO.getUsername());
            result.put("NickName(닉네임)", userVO.getNickname());
            result.put("Email(이메일)", userVO.getEmail());
            result.put("PhoneNo(전화번호)", encDecBean.decodeStr(userVO.getPhoneno()));
            logger.info("result : {}",result);
            return result;
        }
        return null;
    }
}
