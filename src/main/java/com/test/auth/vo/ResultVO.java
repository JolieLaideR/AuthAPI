package com.test.auth.vo;

import lombok.Data;
import org.json.simple.JSONObject;

@Data
public class ResultVO {
    private boolean result;
    private int resultCode;
    private String resultMsg;
    private JSONObject resultJson;
}
