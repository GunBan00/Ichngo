package com.jeonbuk.mchms.domain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TbEventMaster {
    String eventId;
    String eventNgoId;
    String eventCgiId;
    String eventSubject;
    String eventTitle;
    String eventDescription;
    String eventImage;
    String eventFile;
    String eventFileType;
    String eventStartDate;
    String eventEndDate;
    String eventUploadDate;
    String eventImageComment;
    String eventAuthor;
    String eventVenue;
    String eventContinent;
}
