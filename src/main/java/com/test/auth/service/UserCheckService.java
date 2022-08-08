package com.test.auth.service;

import com.test.auth.bean.CheckEmailBean;
import com.test.auth.bean.CheckNicknameBean;
import com.test.auth.bean.CheckPhoneNoBean;
import com.test.auth.util.EncDecBean;
import com.test.auth.vo.ResultVO;
import com.test.auth.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCheckService {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final CheckPhoneNoBean phoneNoBean;
    private final CheckEmailBean emailBean;
    private final CheckNicknameBean nicknameBean;

    private final EncDecBean encDecBean;

    public ResultVO service(UserVO vo) throws Exception{
        logger.info("UserCheckService UserVO : {}",vo);
        ResultVO resultVO = new ResultVO();

        if(phoneNoBean.service(encDecBean.encodeStr(vo.getPhoneno()))){
            resultVO.setResult(false);
            resultVO.setResultMsg("이미 가입된 전화번호입니다.");

            return resultVO;
        }

        if(emailBean.service(vo.getEmail())){
            resultVO.setResult(false);
            resultVO.setResultMsg("이미 가입된 메일입니다.");

            return resultVO;
        }

        if(nicknameBean.service(vo.getNickname())){
            resultVO.setResult(false);
            resultVO.setResultMsg("이미 존재하는 닉네임입니다.");

            return resultVO;
        }

        resultVO.setResultCode(0);

        return resultVO;
    }
}
