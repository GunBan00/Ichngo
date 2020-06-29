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

    @Select("SELECT ID, NAME, EMAIL, NICKNAME FROM User WHERE ID = #{id}")
    UserInfo selectUserInfo(String id);

    @Select("SELECT ID, NICKNAME FROM User WHERE ID = #{id}")
    UserInfo selectUserIdNicknameInfo(String id);

    @Select("SELECT COUNT(Large) as count, Large as large FROM Data LEFT OUTER JOIN Classification ON (Data.Classification_id = Classification.classification_id) WHERE Data.Registrant = #{id} GROUP BY Large")
    List<UserWriteClassificationCount> getClassificationCountByUserId(String id);

    @Select("SELECT ID as id, Title as title, City_id as cityId, Classification_id as classificationId , Serial_Number as serialNumber, Registration_Date as registrationDate FROM Data WHERE Registrant = #{Registrant}")
    List<UserDataDomain> selectUserData(String Registrant);

    @Select("SELECT ID FROM User WHERE NAME = #{name}")
    UserInfo FindUserDataForIdAndName(String name);

    @Select("SELECT ID FROM User WHERE EMAIL = #{email}")
    UserInfo FindUserDataForIdAndEmail(String email);

    @Select("SELECT ID FROM User WHERE ID = #{ID} AND NAME = #{Kinds}")
    UserInfo FindUserDataForPWAndName(Map<String, Object> sqlParam);

    @Select("SELECT ID FROM User WHERE ID = #{ID} AND EMAIL = #{Kinds}")
    UserInfo FindUserDataForPWAndEmail(Map<String, Object> sqlParam);

    @Update("UPDATE User SET PW = #{encPw} WHERE ID = #{ID}")
    void changePassword(Map<String, Object> sqlParam);

    @Update("UPDATE User SET GRADE = 2 WHERE ID = #{id}")
    void changeGrade(String id);

    @Select("SELECT ID as id, NAME as name, EMAIL as email, NICKNAME as nickname, GRADE as grade FROM User WHERE GRADE = '3'")
    List<UserInfo> selectNotPermittedUser();

    @Select("SELECT COUNT(*) FROM User WHERE NICKNAME = ${NICKNAME}")
    int CheckingUserNicknameProcess(String NICKNAME);

    @Select("SELECT GRADE FROM User WHERE ID = '${id}'")
    String checkingUserGrade(String id);



    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
