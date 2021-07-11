package com.moon.dubbo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductEntiry implements Serializable {

    private static final long serialVersionUID = -2680573105896879495L;
    private String id;
    private long price;
    private String name;
    private int status; // 上下架

}
