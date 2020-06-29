package com.jeonbuk.mchms.service.data;

import com.jeonbuk.mchms.domain.*;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataMapper {

    @Insert("INSERT INTO" +
            " TB_INVENTORY_MASTER" +
            "(inv_ngo_id, inv_cgi_id, inv_title, inv_contents, inv_definition, inv_contribution," +
            "inv_safeguarding, inv_community, inv_inventory, inv_image, inv_image_comment, inv_file, inf_file_type," +
            "inv_ICHDomains, inv_ICHCategories, inv_Number, inv_ICHYear, inv_venue, inv_upload_date, inv_author, inv_subject, inv_continent) " +
            "VALUES " +
            "('${ngoId}', '${cgiId}', '${eventTitle}', '${invContents}', '${invDefinition}'," +
            " '${invContribution}','${invSafeguarding}','${invCommunity}','${invInventory}','${invImage}','${comment}'" +
            ",'${invFile}','${invFileType}','${inv_ICHDomains}','${inv_ICHCategories}','${inv_Number}','${inv_ICHYear}','${venueSelect}','${uploadDate}','${invAuthor}','${invSubject}'" +
            ",'${continent}')")
    void setData(Map<String, String> sqlParam);

    @Insert("INSERT INTO" +
            " TB_EVENT_MASTER" +
            "(event_ngo_id, event_cgi_id, event_subject, event_title,  event_description, event_image, event_file, event_start_date, event_end_date, " +
            "event_upload_date, event_image_comment, event_author, event_venue, event_continent) " +
            "VALUES " +
            "('${ngoId}', '${cgiId}', '${eventSubject}', '${eventTitle}', '${eventDescription}'," +
            " '${invImage}','${invFile}','${startDate}','${endDate}','${uploadDate}','${comment}'" +
            ",'${eventAuthor}','${venueSelect}','${continent}')")
    void setEventData(Map<String, String> sqlParam);

    @Select("SELECT * FROM main_V3 WHERE subject = '${Category}' ORDER BY upload_date DESC limit 9")
    List<MainData> getDataByCategory(String Category);

    @Select("SELECT * FROM main_V3 WHERE subject = '${Category}' ORDER BY upload_date DESC limit ${json_num}, 9")
    List<MainData> getDataByCategoryMore(String Category, String json_num);

    @Select("SELECT * FROM main_V3 WHERE subject='INVENTORIES' ORDER BY upload_date")
    List<MainData> getInvData();
    @Select("SELECT * FROM main_V3 ORDER BY upload_date DESC limit 9")
    List<MainData> getAllData();
    @Select("SELECT * FROM main_V3 ORDER BY upload_date DESC limit ${json_num}, 6")
    List<MainData> getAllDataMore(String json_num);

    @Select("SELECT CGI_NAME FROM CGIS WHERE CGI_ID = '${id}'")
    String getCgiName(String id);
    @Select("SELECT NAME FROM NGOS WHERE ID = '${id}'")
    String getNgoName(String id);

    @Select("SELECT NGO_ID FROM USER WHERE ID = '${id}'")
    String getUserNgoIdById(String id);

    @Select("SELECT CGI_ID FROM USER WHERE ID = '${id}'")
    String getUserCgiIdById(String id);

    @Select("SELECT nation FROM NATION ORDER BY nation")
    String[] getNationList();

    @Select("SELECT continent FROM NATION WHERE nation ='${venue}'")
    String getContinent(String venue);

    @Select("SELECT NAME FROM NGOS order by NAME ASC;")
    String[] getNgoNames();

    @Select("SELECT * FROM TB_EVENT_MASTER WHERE event_id = '${id}'")
    EventData getEventData(String id);

    @Select("SELECT event_id FROM TB_EVENT_MASTER")
    String [] getDataId();

    @Select("SELECT * FROM TB_INVENTORY_MASTER WHERE event_id = '${id}'")
    InvData getInventoryData(String id);

    @Select("SELECT event_id FROM TB_EVENT_MASTER ORDER BY event_id")
    String [] getEventDataId();

    @Select("SELECT * FROM COMMENT WHERE EVENT_ID='${id}' ORDER BY COMMENT_ID")
    List<Comment> getEventComment(String id);

    @Select("SELECT * FROM INVENTORY_COMMENT WHERE INVENTORY_ID='${id}' ORDER BY COMMENT_ID")
    List<Comment> getInvComment(String id);
}
