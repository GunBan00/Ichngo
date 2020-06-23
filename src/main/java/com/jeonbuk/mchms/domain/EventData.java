package com.jeonbuk.mchms.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventData {
    String event_id;
    String event_ngo_id;
    String event_cgi_id;
    String event_subject;
    String event_title;
    String event_description;
    String event_image;
    String event_file;
    String event_file_type;
    String event_start_date;
    String event_end_date;
    String event_upload_date;
    String event_image_comment;
    String event_author;
    String event_venue;
    String event_continent;
}
