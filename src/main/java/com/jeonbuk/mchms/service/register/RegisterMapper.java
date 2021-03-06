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

    @Select("SELECT NAME as name, LOCATE as locate, NATION as nation, ACC_YEAR as accYear, MAIN_MEASURES as mainMeasures, DOMAINS as domains, LEVEL as level, ADDRESS as address, EMAIL as email, TEL as tel, FAX as fax, URL as url, SOCIAL as social, REPRE_NGO as repreNgo FROM NGOS WHERE ID = '${ID}';")
    Ngos ngoDataById(String ID);

    @Select("SELECT CGI_TYPE as cgiType, CGI_NAME as cgiName, CGI_EMAIL as cgiEmail, CGI_AFF as cgiAff, CGI_FOUND_YEAR as cgiFoundYear, CGI_DOMAIN as cgiDomain, CGI_ADDRESS as cgiAddress, CGI_TELEPHONE as cgiTelephone, CGI_FAX as cgiFax, HOMEPAGE as homepage, SOCIAL as social, REPRESENT as represent, COUNTRY as country FROM CGIS WHERE CGI_ID = '${ID}'")
    Cgis cgiDataById(String ID);

    @Update("UPDATE NGOS SET NAME='${ngo_name}', LOCATE='${ngo_region}', NATION='${ngo_country}', ACC_YEAR='${ngo_acc}', MAIN_MEASURES='${str_ngo_safe}', DOMAINS='${str_ngo_domain}', LEVEL='${ngo_level}', ADDRESS='${ngo_address}', EMAIL='${ngo_email}', TEL='${ngo_telephone}', FAX='${ngo_fax}', URL='${ngo_homepage}', SOCIAL='${ngo_social}', REPRE_NGO='${ngo_represent}' WHERE ID='${ngo_id}'")
    void updateNgoData(Map<String, String> sqlParam);

    @Update("UPDATE CGIS SET CGI_TYPE = '${cgi_type}', CGI_NAME = '${cgi_name}', CGI_EMAIL = '${cgi_email}', CGI_AFF = '${cgi_aff}', CGI_FOUND_YEAR = '${cgi_acc_year}', CGI_DOMAIN = '${str_cgi_domain}', CGI_ADDRESS = '${cgi_address}', CGI_TELEPHONE = '${cgi_telephone}', CGI_FAX = '${cgi_fax}', HOMEPAGE = '${cgi_homepage}', SOCIAL = '${cgi_social}', REPRESENT = '${cgi_represent}', COUNTRY = '${cgi_country}' WHERE CGI_ID = '${cgi_id}'")
    void updateCgiData(Map<String, String> sqlParam);
}
