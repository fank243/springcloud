package com.fank243.cloud.component.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 认证主体
 * 
 * @author FanWeiJie
 * @date 2020-09-21 12:02:29
 */
@Data
public class CurrUser implements Serializable {

    private Long id;

    private String username;

    private String password;

    private List<String> permList = new ArrayList<>(1);
}
