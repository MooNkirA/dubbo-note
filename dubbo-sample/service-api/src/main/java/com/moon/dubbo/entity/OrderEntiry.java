package com.moon.dubbo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderEntiry implements Serializable {

    private static final long serialVersionUID = 6457545044935594297L;
    private String id;
    private long money;
    private String userId;
    private int status = 0;
    private List<ProductEntiry> productlist = new ArrayList<>();

}
