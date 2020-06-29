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
            if(a.getSubject().equals("INVENTORIES") && a.getNgo_id().equals("135"))
                a.setNgo_name(dataMapper.getInvVenue(a.getId()));
            else {
                if (a.getNgo_id().equals("0") || a.getNgo_id() == null)
                    a.setNgo_name(dataMapper.getCgiName(a.getCgi_id()));
                else
                    a.setNgo_name(dataMapper.getNgoName(a.getNgo_id()));
            }
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
    public String [] getInvDataId()
    {
        return dataMapper.getInvDataId();
    }
    public String getCgiName(String id){
        return dataMapper.getCgiName(id);
    }
    public String getNgoName(String id){
        return dataMapper.getNgoName(id);
    }

}
