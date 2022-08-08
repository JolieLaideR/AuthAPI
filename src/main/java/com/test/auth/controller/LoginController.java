package com.test.auth.controller;

import com.test.auth.service.InsertUserService;
import com.test.auth.service.LoginService;
import com.test.auth.service.ResetPwService;
import com.test.auth.service.UserCheckService;
import com.test.auth.vo.CheckVO;
import com.test.auth.vo.ResultVO;
import com.test.auth.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final CheckVO checkVO;

    private final InsertUserService insertUserService;

    private final UserCheckService userCheckService;

    private final ResetPwService resetPwService;

    private final LoginService loginService;

    @RequestMapping(value="/numberCheck.do"
            , method = RequestMethod.POST
            , produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResultVO numberCheckAPI(@Valid @RequestBody JSONObject json, HttpServletRequest request) throws Exception{
        logger.info("=============== LoginController.numberCheckAPI phoneNo : {}", json);
        ResultVO vo = new ResultVO();
        JSONObject result = new JSONObject();
        String phoneNo = json.get("phoneNo").toString().replace("-", "");
        logger.info("phoneNo : {}",phoneNo);
        if(phoneNo.length() > 10) {
            String verifyNo = "123456";//RandomUtils.nextInt(100000,1000000);
            vo.setResult(true);
            result.put("verifyNumber", verifyNo);
            vo.setResultMsg(result.toJSONString());
            checkVO.getCheck().put(verifyNo, phoneNo);
        } else {
            vo.setResult(false);
            vo.setResultMsg("전화번호가 유효하지 않습니다.");
        }

        return vo;
    }

    @RequestMapping(value="/auth.do"
            , method = RequestMethod.POST
            , produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResultVO authAPI(@Valid @RequestBody JSONObject json, HttpServletRequest request) {
        logger.info("=============== LoginController.authAPI phoneNo : {}", json);
        ResultVO vo = new ResultVO();
        JSONObject result = new JSONObject();

        logger.info("CheckVO.getCheck() : {}",checkVO.getCheck().toString());

        String verifyNo = json.get("verifyNumber").toString();
        try {
            logger.info("result : {}", checkVO.getCheck().containsKey(verifyNo));
            if (checkVO.getCheck().containsKey(verifyNo)) {
                logger.info("verifyNo : {}", verifyNo);
                UserVO userVO = new UserVO();
                userVO.setUserpw((String) json.get("UserPw"));
                userVO.setEmail((String) json.get("Email"));
                userVO.setUsername((String) json.get("UserName"));
                userVO.setNickname((String) json.get("NickName"));
                userVO.setPhoneno(checkVO.getCheck().get(verifyNo).toString());

                vo = userCheckService.service(userVO);
                if(vo.getResultCode() == 0) {
                    insertUserService.service(userVO);
                    vo.setResult(true);
                    vo.setResultMsg("회원가입 완료되었습니다.");
                    checkVO.getCheck().remove(verifyNo);
                }
            } else {
                vo.setResult(false);
                vo.setResultMsg("인증번호가 일치하지 않습니다.");
                return vo;
            }
            logger.info("CheckVO.getCheck() : {}", checkVO.getCheck().toString());
        } catch (Exception e) {
            logger.error("ERROR :::::  {}" ,e);
        }
        return vo;
    }

    @RequestMapping(value="/login.do"
            , method = RequestMethod.POST
            , produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResultVO loginAPI(@Valid @RequestBody JSONObject json, HttpServletRequest request) {
        logger.info("=============== LoginController.authAPI phoneNo : {}", json);
        ResultVO vo = new ResultVO();
        JSONObject result = new JSONObject();

        logger.info("CheckVO.getCheck() : {}",checkVO.getCheck().toString());

        try {
            UserVO userVO = new UserVO();
            if (json.containsKey("NickName")) {
                userVO.setNickname((String) json.get("NickName"));
            } else if (json.containsKey("Email")) {
                userVO.setEmail((String) json.get("Email"));
            } else if (json.containsKey("PhoneNo")) {
                userVO.setPhoneno((String) json.get("PhoneNo"));
            }

            userVO.setUserpw((String) json.get("UserPw"));

            result = loginService.service(userVO);
            if(result != null){
                vo.setResult(true);
                vo.setResultMsg("로그인되었습니다.\r\n등록된 회원 정보는 다음과 같습니다.");
                vo.setResultJson(result);
                return vo;
            }
        } catch (Exception e) {
            logger.error("ERROR :::::  {}" ,e);
        }
        return vo;
    }

    @RequestMapping(value="/getInfo.do"
            , method = RequestMethod.POST
            , produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResultVO getInfoAPI(@Valid @RequestBody JSONObject json, HttpServletRequest request) {
        logger.info("=============== LoginController.authAPI phoneNo : {}", json);
        ResultVO vo = new ResultVO();

        JSONObject result = new JSONObject();

        logger.info("CheckVO.getCheck() : {}",checkVO.getCheck().toString());

        String verifyNo = json.get("verifyNumber").toString();

        try {
            logger.info("result : {}", checkVO.getCheck().containsKey(verifyNo));
            if (checkVO.getCheck().containsKey(verifyNo)) {
                logger.info("verifyNo : {}", verifyNo);
                UserVO userVO = new UserVO();
                userVO.setUserpw((String) json.get("UserPw"));
                userVO.setEmail((String) json.get("Email"));
                userVO.setUsername((String) json.get("UserName"));
                userVO.setNickname((String) json.get("NickName"));
                userVO.setPhoneno(checkVO.getCheck().get(verifyNo).toString());

                insertUserService.service(userVO);

                checkVO.getCheck().remove(verifyNo);
                vo.setResult(true);

            }

            logger.info("CheckVO.getCheck() : {}", checkVO.getCheck().toString());

        } catch (Exception e) {
            logger.error("ERROR :::::  {}" ,e);

        }
        return vo;
    }

    @RequestMapping(value="/resetPw.do"
            , method = RequestMethod.POST
            , produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResultVO resetPwAPI(@Valid @RequestBody JSONObject json, HttpServletRequest request) {
        logger.info("=============== LoginController.authAPI phoneNo : {}", json);
        ResultVO vo = new ResultVO();

        JSONObject result = new JSONObject();

        logger.info("CheckVO.getCheck() : {}",checkVO.getCheck().toString());

        String verifyNo = json.get("verifyNumber").toString();

        try {
            logger.info("result : {}", checkVO.getCheck().containsKey(verifyNo));
            if (checkVO.getCheck().containsKey(verifyNo)) {
                logger.info("verifyNo : {}", verifyNo);
                UserVO userVO = new UserVO();
                userVO.setUserpw((String) json.get("UserPw"));

                if(resetPwService.service(userVO)){
                    checkVO.getCheck().remove(verifyNo);
                    vo.setResult(true);
                    vo.setResultMsg("비밀번호가 변경되었습니다.");
                }
            } else {
                vo.setResult(false);
                vo.setResultMsg("인증번호가 일치하지 않습니다.");
                return vo;
            }

            logger.info("CheckVO.getCheck() : {}", checkVO.getCheck().toString());

        } catch (Exception e) {
            logger.error("ERROR :::::  {}" ,e);

        }
        return vo;
    }


    @RequestMapping("/error.do")
    public ResultVO handleError(HttpServletRequest request) throws Exception {
        int status = (Integer)request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String exceptionType = (String)request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE);

        ResultVO vo = new ResultVO();

        vo.setResult(false);
        vo.setResultCode(status);
        vo.setResultMsg(exceptionType);

        return vo;
    }
}
