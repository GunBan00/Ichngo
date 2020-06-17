package com.jeonbuk.mchms.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MainData {
    String id;
    String ngo_id;
    String cgi_id;
    String subject;
    String title;
    String contents;
    String image;
    String upload_date;
    String author;
    String ngo_name;
    String cgi_name;
}