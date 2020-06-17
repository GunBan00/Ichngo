package com.jeonbuk.mchms.service.user;

import com.jeonbuk.mchms.domain.City;
import com.jeonbuk.mchms.domain.UserDataDomain;
import com.jeonbuk.mchms.domain.UserInfo;
import com.jeonbuk.mchms.domain.Cgis;
import com.jeonbuk.mchms.domain.Ngos;
import com.jeonbuk.mchms.domain.TbEventMaster;
import com.jeonbuk.mchms.domain.UserWriteClassificationCount;
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

    public String changeGrade(String id){
        System.out.println(id);
        userMapper.changeGrade(id);
        return "Y";

    }
    public UserInfo selectUserInfo(String id) {
        return userMapper.selectUserInfo(id);
    }
    public List<UserInfo> selectNotPermittedUser(){
        List<UserInfo> userInfo = userMapper.selectNotPermittedUser();
        List<UserInfo> notPermittedUser=new ArrayList<UserInfo>();
        int index = 1;
        for (UserInfo temp : userInfo) {
            temp.setIndex(index);
            index++;
            notPermittedUser.add(temp);
        }

        return notPermittedUser;
    }
    public UserInfo selectUserIdNicknameInfo(String id){ return userMapper.selectUserIdNicknameInfo(id);}
    public List<UserWriteClassificationCount> getClassificationCountByUserId(String id) {
        return userMapper.getClassificationCountByUserId(id);
    }
    public String checkingUserGrade(String id){
        String a = userMapper.checkingUserGrade(id);
        if(id != null){
            if(a != null)
                return a;
            else
                return "3";
        }
        else
            return "3";
    }

    public List<UserDataDomain> selectUserData(String Registrant){
        return userMapper.selectUserData(Registrant);
    }

    public String FindUserDataForID (String Type, String Kinds){
        if (Type.equals("Name")){
            UserInfo userInfo = userMapper.FindUserDataForIdAndName(Kinds);

            if (userInfo != null){
                return userInfo.getId();
            }
            else {
                return "none";
            }
        }
        else {
            UserInfo userInfo = userMapper.FindUserDataForIdAndEmail(Kinds);

            if (userInfo != null){
                return userInfo.getId();
            }
            else {
                return "none";
            }
        }
    }
    public String FindUserDataForPW (String Type, String ID, String Kinds){
        Map<String, Object> sqlParam = new HashMap<>();

        sqlParam.put("ID", ID);
        sqlParam.put("Kinds", Kinds);

        if (Type.equals("Name")){
            UserInfo userInfo = userMapper.FindUserDataForPWAndName(sqlParam);

            if (userInfo != null){
                return userInfo.getId();
            }
            else {
                return "none";
            }
        }
        else {
            UserInfo userInfo = userMapper.FindUserDataForPWAndEmail(sqlParam);

            if (userInfo != null){
                return userInfo.getId();
            }
            else {
                return "none";
            }
        }
    }
    public void changePassword(String ID, String encPw) throws Exception {

        Map<String, Object> sqlParam = new HashMap<>();

        sqlParam.put("ID", ID);
        sqlParam.put("encPw", encPw);

        userMapper.changePassword(sqlParam);
    }

    public int CheckingUserNicknameProcess(String NICKNAME){
        return userMapper.CheckingUserNicknameProcess(NICKNAME);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

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
}
