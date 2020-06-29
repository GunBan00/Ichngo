package com.jeonbuk.mchms.service.user;

import com.jeonbuk.mchms.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public Map<String, Object> loginUser(String id, String encPw) throws Exception {

        Map<String, Object> sqlParam = new HashMap<>();

        sqlParam.put("id", id);
        sqlParam.put("pw", encPw);

        return userMapper.loginUser(sqlParam);
    }
    public List<Ngos> getNgoListByIdAndName() throws Exception {
        return userMapper.getNgoListByIdAndName();
    }

    public List<Cgis> getCgiIdFromCgi(String CGI_ID) throws Exception {
        return userMapper.getCgiIdFromCgi(CGI_ID);
    }

    public List<Cgis> getCgiInfoFromCgi() throws Exception {
        List<Cgis> a = userMapper.getCgiInfoFromCgi();

        System.out.println(a.size());
        return userMapper.getCgiInfoFromCgi();
    }

    public List<TbEventMaster> getTbEventMasterInfoFromTbEventMaster() throws Exception {
        return userMapper.getTbEventMasterInfoFromTbEventMaster();
    }

    public void setUser(Map<String, Object> sqlParam) throws Exception {
        userMapper.setUser(sqlParam);
    }

    public int CheckingUserIDProcess(String ID){
        return userMapper.CheckingUserIDProcess(ID);
    }

    public Ngos getIdFromNGOS(String ID){
        return userMapper.getIdFromNGOS(ID);
    }

    public Cgis getIdFromCGIS(String ID){
        return userMapper.getIdFromCGIS(ID);
    }

    public User getInfoFromUserById(String ID){
        return userMapper.getInfoFromUserById(ID);
    }

    public String getNameFromNGOS(String ID) {
        return userMapper.getNameFromNGOS(ID).getName();
    }

    public String getNameFromCGIS(String ID) { return userMapper.getNameFromCGIS(ID).getCgiName(); }

    public void updateUser(Map<String, Object> sqlParam){
        userMapper.updateUser(sqlParam);
    }
}
