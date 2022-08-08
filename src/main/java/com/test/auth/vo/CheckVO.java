package com.test.auth.vo;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Data
public class CheckVO {
    private Map check = new HashMap<>();

    private Map auth = new HashMap();
}
