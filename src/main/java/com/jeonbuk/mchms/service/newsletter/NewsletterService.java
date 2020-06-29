package com.jeonbuk.mchms.service.newsletter;

import com.jeonbuk.mchms.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NewsletterService {
    @Autowired
    NewsletterMapper newsletterMapper;

    public List<NewsletterDomain> SelectNewsletterData(){
        return newsletterMapper.SelectNewsletterData();
    }
}
