package com.moon.dubbo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserEntiry implements Serializable {

    private static final long serialVersionUID = 4687671958685364776L;
    private String id;
    private String name;
    private String address;
    private long balance;

}
