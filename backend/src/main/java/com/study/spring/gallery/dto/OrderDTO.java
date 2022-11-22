package com.study.spring.gallery.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private int pid;
    private int memberId;
    private String name;
    private String address;
    private String payment;
    private String cardNumber;
    private String items;
}
