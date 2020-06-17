package com.jeonbuk.mchms.service.register;

import com.jeonbuk.mchms.domain.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface RegisterMapper {

    @Select("SELECT nation FROM NATION ORDER BY nation ASC")
    List<Nation> SelectNationName();

    @Insert("INSERT INTO CGIS (`CGI_TYPE`, `CGI_NAME`, `CGI_EMAIL`, `CGI_AFF`, `CGI_FOUND_YEAR`, `CGI_DOMAIN`, `CGI_ADDRESS`, `CGI_TELEPHONE`, `CGI_FAX`, `HOMEPAGE`, `SOCIAL`, `REPRESENT`, `COUNTRY`) VALUES ('${cgi_type}', '${cgi_name}', '${cgi_email}', '${cgi_aff}', '${cgi_acc}', '${str_cgi_domain}', '${cgi_address}', '${cgi_telephone}', '${cgi_fax}', '${cgi_homepage}', '${cgi_social}', '${cgi_represent}', '${ngo_country}')")
    void setCgiData(Map<String, String> sqlParam);

    @Insert("INSERT INTO NGOS (`NAME`, `LOCATE`, `NATION`, `ACC_YEAR`, `MAIN_MEASURES`, `DOMAINS`, `LEVEL`, `ADDRESS`, `EMAIL`, `TEL`, `FAX`, `URL`, `SOCIAL`, `REPRE_NGO`) VALUES ('${ngo_name}', '${ngo_region}', '${ngo_country}', '${ngo_acc}', '${str_ngo_safe}', '${str_ngo_domain}', '${ngo_level}', '${ngo_address}', '${ngo_email}', '${ngo_telephone}', '${ngo_fax}', '${ngo_homepage}', '${ngo_social}', '${ngo_represent}')")
    void setNgoData(Map<String, String> sqlParam);
}
