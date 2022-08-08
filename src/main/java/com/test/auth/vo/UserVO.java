package com.test.auth.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {
    private String email;
    private String nickname;
    private String phoneno;
    private String username;
    private String userpw;
}
