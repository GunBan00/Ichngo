package com.jeonbuk.mchms.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String id;
    private String Password;
    private String email;
    private String firstName;
    private String ngoId;
    private String cgiId;
    private String lastName;
    private String signDate;
    private int num;
    private String middleName;
    private String phone;
}
