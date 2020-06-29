package com.jeonbuk.mchms.service.newsletter;

import com.jeonbuk.mchms.domain.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface NewsletterMapper {
    @Select("SELECT Id as id, Title as title, Newsletter as newsletter, Homepage as homepage, Country as country FROM NEWSLETTER;")
    List<NewsletterDomain> SelectNewsletterData();
}
