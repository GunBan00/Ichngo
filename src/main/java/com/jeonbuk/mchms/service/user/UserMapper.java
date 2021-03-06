package com.jeonbuk.mchms.service.user;

import com.jeonbuk.mchms.domain.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("SELECT ID, PW, NGO_ID, CGI_ID, LAST_NAME FROM USER WHERE ID = #{id} AND PW =#{pw}")
    Map<String, Object> loginUser(Map<String, Object> sqlParam);

    @Select("SELECT ID,NAME FROM NGOS order by NAME ASC")
    List<Ngos> getNgoListByIdAndName();

    @Select("SELECT CGI_ID as cgiId FROM CGIS WHERE CGI_NAME = ${CGI_ID}")
    List<Cgis> getCgiIdFromCgi(String CGI_ID);

    @Select("SELECT CGI_ID as cgiId, CGI_NAME as cgiName FROM CGIS WHERE NOT CGI_TYPE LIKE 'individual' AND NOT CGI_TYPE LIKE 'expert' order by CGI_NAME ASC")
    List<Cgis> getCgiInfoFromCgi();

    @Select("SELECT event_id, event_ngo_id, event_cgi_id, event_subject, event_title, event_start_date, event_end_date FROM TB_EVENT_MASTER WHERE event_subject = 'EVENTS' ORDER BY event_start_date DESC LIMIT 0,5")
    List<TbEventMaster> getTbEventMasterInfoFromTbEventMaster();

    @Insert("INSERT INTO USER (ID, PW, EMAIL, FIRST_NAME, NGO_ID, CGI_ID, sign_date, LAST_NAME, MIDDLE_NAME, PHONE) VALUES ('${ID}', '${PW}', '${EMAIL}', '${FIRSTNAME}', '${NGOID}', '${CGIID}', '${SIGN_DATE}', '${LASTNAME}', '${MIDDLENAME}', '${PHONE}')")
    void setUser(Map<String, Object> sqlParam);

    @Select("SELECT COUNT(*) FROM USER WHERE ID = '${ID}'")
    int CheckingUserIDProcess(String ID);

    @Select("SELECT ID as id FROM NGOS WHERE NAME = '${ID}'")
    Ngos getIdFromNGOS(String ID);

    @Select("SELECT CGI_ID as cgiId FROM CGIS WHERE CGI_NAME = '${ID}'")
    Cgis getIdFromCGIS(String ID);

    @Select("SELECT ID, EMAIL, FIRST_NAME as firstName, LAST_NAME as lastName, MIDDLE_NAME as middleName, PHONE, CGI_ID as cgiId, NGO_ID as ngoId FROM USER WHERE ID = '${ID}'")
    User getInfoFromUserById(String ID);

    @Select("SELECT Name as name FROM NGOS WHERE ID = '${ID}'")
    Ngos getNameFromNGOS(String ID);

    @Select("SELECT CGI_NAME as cgiName FROM CGIS WHERE CGI_ID = '${ID}'")
    Cgis getNameFromCGIS(String ID);

    @Update("UPDATE USER SET PW = '${PW}', EMAIL = '${EMAIL}', FIRST_NAME = '${FIRSTNAME}', NGO_ID = '${NGOID}', CGI_ID = '${CGIID}', LAST_NAME = '${LASTNAME}', MIDDLE_NAME = '${MIDDLENAME}', PHONE = '${PHONE}' WHERE  ID = '${ID}'")
    void updateUser(Map<String, Object> sqlParam);

}
