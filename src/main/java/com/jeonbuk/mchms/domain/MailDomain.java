package com.jeonbuk.mchms.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailDomain {
    private String address;
    private String title;
    private String message;
}
