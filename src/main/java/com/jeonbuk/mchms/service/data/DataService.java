package com.jeonbuk.mchms.service.data;

import com.jeonbuk.mchms.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataService {

    @Autowired
    private DataMapper dataMapper;

    public void setData(Map<String, String> sqlParam) throws Exception {
        dataMapper.setData(sqlParam);
    }
    public String changeSmallTag(String stringContents)
    {
        String brTag = "<br><div class=";
        String brr = "\"";
        String tag = brTag+brr+"remark_title"+brr+">";

        String ptag = "<p class=";
        String tag2 = ptag + brr + "text_indent"+brr+">";
        String returnContents = stringContents.replace("<<", tag);
        returnContents = returnContents.replace(">>","</div>");
        returnContents = returnContents.replace("<div>\r\n","</div>");


        return returnContents;
    }
    public String changeBigTag(String stringContents)
    {
        String brr = "\"";
        String ptag = "<p class=";
        String tag2 = ptag + brr + "text_indent"+brr+">";
        String returnContents = stringContents.replace("[[", tag2);
        returnContents = returnContents.replace("]]","</p>");

        return returnContents;
    }
    public String changeTagToSmallbr(String stringContents)
    {
        String brTag = "<br><div class=";
        String brr = "\"";
        String tag = brTag+brr+"remark_title"+brr+">";

        String ptag = "<p class=";
        String tag2 = ptag + brr + "text_indent"+brr+">";
        String returnContents = stringContents.replace(tag, "<<");
        returnContents = returnContents.replace("</div>",">>\r\n");

        return returnContents;
    }
    public String changeTagToBigbr(String stringContents)
    {
        String brr = "\"";
        String ptag = "<p class=";
        String tag2 = ptag + brr + "text_indent"+brr+">";
        String returnContents = stringContents.replace(tag2, "[[");
        returnContents = returnContents.replace("</p>", "]]");

        return returnContents;
    }
    public void setFiles(int id, String filesName, int fileCount) throws Exception
    {
        System.out.println("service");
        dataMapper.setFiles(id, filesName, fileCount);
    }

    public void changeData(Map<String, String> sqlParam) throws Exception {
        System.out.println("asdf");
        dataMapper.changeData(sqlParam);
    }
    public void changeFiles(int id, String filesName, int fileCount, int fileFlag) throws Exception
    {
        if (fileFlag == 1)
            dataMapper.changeFiles(id, filesName, fileCount);
    }

    public void deleteData(String id){
        dataMapper.deleteData(id);
    }
    public int getMaxId(String id)
    {
        return dataMapper.getMaxId(id);
    }

    public DataDomain getDataInfo(String id) throws Exception {
        DataDomain dataDomain = dataMapper.selectData(id);
        return dataDomain;
    }

    public List<DataDomain> getDataByKeyword(String keyword) throws Exception{
        keyword = keyword + "*";
        return dataMapper.getDataByKeyword(keyword);
    }

    public Classification getClassificationById(int classId) throws Exception{

        return dataMapper.getClassificationById(classId);
    }

    public List<DataDomain> getDataByCityId(int cityId) throws Exception {
        return dataMapper.getDataByCityId(cityId);
    }

    public List<DataDomain> getDataByCityIdAndJoinCity(String cityId, String Order) throws Exception {
        Map<String, Object> sqlParam = new HashMap<>();

        sqlParam.put("cityId", cityId);
        sqlParam.put("Order", Order);

        return dataMapper.getDataByCityIdAndJoinCity(sqlParam);
    }

    public List<DataDomain> getDataByCityIdAndJoinClassifi(String cityId, String Order) throws Exception {
        Map<String, Object> sqlParam = new HashMap<>();

        sqlParam.put("cityId", cityId);
        sqlParam.put("Order", Order);

        return dataMapper.getDataByCityIdAndJoinClassifi(sqlParam);
    }

    public List<DataDomain> getDataByCityIdAndNotJoin(String cityId, String TypeToSort, String Order) throws Exception {

        Map<String, Object> sqlParam = new HashMap<>();

        sqlParam.put("cityId", cityId);
        sqlParam.put("TypeToSort", TypeToSort);
        sqlParam.put("Order", Order);

        return dataMapper.getDataByCityIdAndNotJoin(sqlParam);
    }

    public List<DataDomain> getDataByKeyword(String Keyword, String TypeToSort, String Order) throws Exception {
        Map<String, Object> sqlParam = new HashMap<>();

        String keywordQuery = "Title LIKE '%" + Keyword +"%' OR Title_my LIKE '%" + Keyword +"%' OR Serial_Number LIKE '%" + Keyword + "%' OR Remarks_en LIKE '%" + Keyword + "%' OR Remarks_my LIKE '%" + Keyword + "%' OR Reference_en LIKE '%" + Keyword + "%' OR Reference_my LIKE '%" + Keyword + "%' ";

        sqlParam.put("keywordQuery", keywordQuery);
        sqlParam.put("Order", Order);

        if (TypeToSort.equals("Classification")){
            return dataMapper.getDataByKeywordIdAndJoinClassifi(sqlParam);
        }
        else if(TypeToSort.equals("City")){
            return dataMapper.getDataByKeywordIdAndJoinCity(sqlParam);
        }
        else{
            sqlParam.put("TypeToSort", TypeToSort);
            return dataMapper.getDataByKeywordIdAndNotJoin(sqlParam);
        }
    }

    public List<DataDomain> getDataById(String Registrant, String TypeToSort, String Order) throws Exception {
        Map<String, Object> sqlParam = new HashMap<>();

        sqlParam.put("Registrant", Registrant);
        sqlParam.put("Order", Order);

        if (TypeToSort.equals("Classification")){
            return dataMapper.getDataByIdAndJoinClassifi(sqlParam);
        }
        else if(TypeToSort.equals("City")){
            return dataMapper.getDataByIdAndJoinCity(sqlParam);
        }
        else{
            sqlParam.put("TypeToSort", TypeToSort);
            return dataMapper.getDataByIdAndNotJoin(sqlParam);
        }
    }

    public List<DataDomain> getDataByCityName(String Cities){
        return dataMapper.getDataByCityName(Cities);

    }

    public List<DataDomain> getDataAdvancedSearch(Map<String, String> Param){
        String sqlSentence = "";
        int searchNum = 0;
        int clid = Integer.parseInt(Param.get("clid"));
        int ctid = Integer.parseInt(Param.get("ctid"));

        if(clid != 0){
            if(Param.get("SEL_SEQ").equals("NOT")){
                sqlSentence += "NOT Classification_id = " + clid;
            }
            else{
                sqlSentence += "Classification_id = " + clid;
            }
            searchNum = 1;
        }
        System.out.println("test : " + sqlSentence + 1);
        if(ctid != 0){
            if (searchNum == 0){
                if(Param.get("SEL_CITY").equals("NOT")){
                    sqlSentence += "NOT City_id = " + ctid;
                }
                else{
                    sqlSentence += "City_id = " + ctid;
                }
                searchNum = 1;
            }
            else{
                if(Param.get("SEL_CITY").equals("NOT")){
                    sqlSentence += " AND NOT City_id = " + ctid;
                }
                else if(Param.get("SEL_CITY").equals("OR")){
                    sqlSentence += " OR City_id = " + ctid;
                }
                else{
                    sqlSentence += " AND City_id = " + ctid;
                }
            }
        }
        System.out.println("test : " + sqlSentence + 2);
        if(!(Param.get("SRCH_TITLE").equals(""))){
            if (searchNum == 0){
                if(Param.get("SEL_TITLE").equals("NOT")){
                    sqlSentence += "Title NOT like '%" + Param.get("SRCH_TITLE") + "%'";
                }
                else{
                    sqlSentence += "Title like '%" + Param.get("SRCH_TITLE") + "%'";
                }
                searchNum = 1;
            }
            else{
                if(Param.get("SEL_TITLE").equals("NOT")){
                    sqlSentence += " AND Title NOT like '%" + Param.get("SRCH_TITLE") + "%'";
                }
                else if(Param.get("SEL_TITLE").equals("OR")){
                    sqlSentence += " OR Title like '%" + Param.get("SRCH_TITLE") + "%'";
                }
                else{
                    sqlSentence += " AND Title like '%" + Param.get("SRCH_TITLE") + "%'";
                }
            }
        }
        System.out.println("test : " + sqlSentence + 3);
        if(!(Param.get("SRCH_REMARK").equals(""))){
            if (searchNum == 0){
                if(Param.get("SEL_REMARK").equals("NOT")){
                    sqlSentence += "Remarks_en NOT like '%" + Param.get("SRCH_REMARK") + "%'";
                }
                else{
                    sqlSentence += "Remarks_en like '%" + Param.get("SRCH_REMARK") + "%'";
                }
                searchNum = 1;
            }
            else{
                if(Param.get("SEL_REMARK").equals("NOT")){
                    sqlSentence += " AND Remarks_en NOT like '%" + Param.get("SRCH_REMARK") + "%'";
                }
                else if(Param.get("SEL_REMARK").equals("OR")){
                    sqlSentence += " OR Remarks_en like '%" + Param.get("SRCH_REMARK") + "%'";
                }
                else{
                    sqlSentence += " AND Remarks_en like '%" + Param.get("SRCH_REMARK") + "%'";
                }
            }
        }
        System.out.println("test : " + sqlSentence + 4);
        System.out.println("test : " + Param.get("SRCH_REG"));
        if(!(Param.get("SRCH_REG").equals(""))){
            if (searchNum == 0){
                if(Param.get("SEL_REG").equals("NOT")){
                    sqlSentence += "Registrant NOT like '%" + Param.get("SRCH_REG") + "%'";
                }
                else{
                    sqlSentence += "Registrant like '%" + Param.get("SRCH_REG") + "%'";
                }
                searchNum = 1;
            }
            else{
                if(Param.get("SEL_REG").equals("NOT")){
                    sqlSentence += " AND Registrant NOT like '%" + Param.get("SRCH_REG") + "%'";
                }
                else if(Param.get("SEL_REG").equals("OR")){
                    sqlSentence += " OR Registrant like '%" + Param.get("SRCH_REG") + "%'";
                }
                else{
                    sqlSentence += " AND Registrant like '%" + Param.get("SRCH_REG") + "%'";
                }
            }
        }
        System.out.println("test : " + sqlSentence + 5);
        if(!((Param.get("SRCH_REG_SRT_DATE").equals("")) && (Param.get("SRCH_REG_END_DATE").equals("")))){
            if (searchNum == 0){
                if(Param.get("SEL_DATE").equals("NOT")){
                    sqlSentence += "NOT Registration_Date between date('" + Param.get("SRCH_REG_SRT_DATE")+ "') and date('" + Param.get("SRCH_REG_END_DATE") + "')";
                }
                else{
                    sqlSentence += "Registration_Date between date('" + Param.get("SRCH_REG_SRT_DATE")+ "') and date('" + Param.get("SRCH_REG_END_DATE") + "')";
                }
                searchNum = 1;
            }
            else{
                if(Param.get("SEL_DATE").equals("NOT")){
                    sqlSentence += " AND Registration_Date NOT between date('" + Param.get("SRCH_REG_SRT_DATE")+ "') and date('" + Param.get("SRCH_REG_END_DATE") + "')";
                }
                else if(Param.get("SEL_DATE").equals("OR")){
                    sqlSentence += " OR Registration_Date between date('" + Param.get("SRCH_REG_SRT_DATE")+ "') and date('" + Param.get("SRCH_REG_END_DATE") + "')";
                }
                else{
                    sqlSentence += " AND Registration_Date between date('" + Param.get("SRCH_REG_SRT_DATE")+ "') and date('" + Param.get("SRCH_REG_END_DATE") + "')";
                }
            }
        }
        System.out.println("test : " + sqlSentence + 6);
        if(!(Param.get("SRCH_RELIC_NUM").equals(""))){
            if (searchNum == 0){
                if(Param.get("SEL_RELIC_NUM").equals("NOT")){
                    sqlSentence += " Serial_Number NOT like '%" + Param.get("SRCH_RELIC_NUM") + "%'";
                }
                else{
                    sqlSentence += " Serial_Number like '%" + Param.get("SRCH_RELIC_NUM") + "%'";
                }
                searchNum = 1;
            }
            else{
                if(Param.get("SEL_RELIC_NUM").equals("NOT")){
                    sqlSentence += " AND Serial_Number NOT like '%" + Param.get("SRCH_RELIC_NUM") + "%'";
                }
                else if(Param.get("SEL_RELIC_NUM").equals("OR")){
                    sqlSentence += " OR Serial_Number like '%" + Param.get("SRCH_RELIC_NUM") + "%'";
                }
                else{
                    sqlSentence += " AND Serial_Number like '%" + Param.get("SRCH_RELIC_NUM") + "%'";
                }
            }
        }
        System.out.println("test : " + sqlSentence + 7);
        return dataMapper.getDataAdvancedSearch(sqlSentence);
    }
    public List<MainData> getAllData() throws Exception{
        List<MainData> list = dataMapper.getAllData();
        for(MainData a : list)
        {
            String c =a.getImage();
            String [] b;
            String tag ="<img class=\'content_img\' src='/thumbnail/";
            if(!c.equals(",,,,")) {
                if (a.getImage().charAt(0) != ',') {
                    b = c.split(",");
                    tag = tag + b[0] + "'/>";
                    a.setImage(tag);
                }
            }
            else
                a.setImage("");

            if(a.getNgo_id().equals("0") || a.getNgo_id() == null)
                a.setNgo_name(dataMapper.getCgiName(a.getCgi_id()));
            else
                a.setNgo_name(dataMapper.getNgoName(a.getNgo_id()));
        }
        return list;
    }
    public List<MainData> getAllDataMore(String json_num) throws Exception{
        List<MainData> list = dataMapper.getAllDataMore(json_num);
        for(MainData a : list)
        {
            String c =a.getImage();
            String [] b;
            String tag ="<img class=\'content_img\' src='/thumbnail/";
            if(!c.equals(",,,,")) {
                if (a.getImage().charAt(0) != ',') {
                    b = c.split(",");
                    tag = tag + b[0] + "'/>";
                    a.setImage(tag);
                }
            }
            else
                a.setImage("");

            if(a.getNgo_id().equals("0") || a.getNgo_id() == null)
                a.setNgo_name(dataMapper.getCgiName(a.getCgi_id()));
            else
                a.setNgo_name(dataMapper.getNgoName(a.getNgo_id()));
        }
        return list;
    }
    public List<MainData> getDataByCategory(String Category) throws Exception{
        List<MainData> list = dataMapper.getDataByCategory(Category);
        for(MainData a : list)
        {
            String c =a.getImage();
            //System.out.println(c);
            String [] b;
            String tag ="<img class=\'content_img\' src='/thumbnail/";
            if(!c.equals(",,,,")) {
                if (a.getImage().charAt(0) != ',') {
                    b = c.split(",");
                    tag = tag + b[0] + "'/>";
                    a.setImage(tag);
                }
            }
            else
                a.setImage("");

            if(a.getNgo_id().equals("0") || a.getNgo_id() == null)
                a.setNgo_name(dataMapper.getCgiName(a.getCgi_id()));
            else
                a.setNgo_name(dataMapper.getNgoName(a.getNgo_id()));
        }
        return list;
    }
    public List<MainData> getDataByCategoryMore(String Category, String json_num) throws Exception{
        List<MainData> list = dataMapper.getDataByCategoryMore(Category, json_num);
        for(MainData a : list)
        {
            String c =a.getImage();
            //System.out.println(c);
            String [] b;
            String tag ="<img class=\'content_img\' src='/thumbnail/";
            if(!c.equals(",,,,")) {
                if (a.getImage().charAt(0) != ',') {
                    b = c.split(",");
                    tag = tag + b[0] + "'/>";
                    a.setImage(tag);
                }
            }
            else
                a.setImage("");

            if(a.getNgo_id().equals("0") || a.getNgo_id() == null)
                a.setNgo_name(dataMapper.getCgiName(a.getCgi_id()));
            else
                a.setNgo_name(dataMapper.getNgoName(a.getNgo_id()));
        }
        return list;
    }
    public List<MainData> getInvData() throws Exception{
        return dataMapper.getInvData();
    }
    public String[] getNationList(){

        return dataMapper.getNationList();
    }
    public String getUserNgo(String id) throws Exception
    {
        String ngoId = dataMapper.getUserNgoIdById(id);
        return ngoId;
    }
    public void setEventData(Map<String, String> sqlParam) throws Exception {
        dataMapper.setEventData(sqlParam);
    }
    public String getUserCgi(String id) throws Exception{
        String cgiId = dataMapper.getUserCgiIdById(id);
        return cgiId;
    }
    public String getContinent(String venues) throws Exception{
        String continent="";

        continent = dataMapper.getContinent(venues);

        return continent;
    }

    public String[] getNgoNames() throws Exception{

        return dataMapper.getNgoNames();
    }
    public String getUserNgoById(String id)
    {
        System.out.print(dataMapper.getNgoName(dataMapper.getUserNgoIdById(id)));
        return dataMapper.getNgoName(dataMapper.getUserNgoIdById(id));
    }
    public String getUserCgiById(String id)
    {
        return dataMapper.getCgiName(dataMapper.getUserCgiIdById(id));
    }
    public EventData getEventData(String id)
    {
        return dataMapper.getEventData(id);
    }
    public List<Comment> getEventComment(String id)
    {
        return dataMapper.getEventComment(id);
    }
    public List<Comment> getInvComment(String id)
    {
        return dataMapper.getInvComment(id);
    }

    public InvData getInventoryData(String id)
    {
        return dataMapper.getInventoryData(id);
    }
    public String [] getEventDataId()
    {
        return dataMapper.getEventDataId();
    }
    public String getCgiName(String id){
        return dataMapper.getCgiName(id);
    }
    public String getNgoName(String id){
        return dataMapper.getNgoName(id);
    }

}
