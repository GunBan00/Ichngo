package com.jeonbuk.mchms.service.register;

import com.jeonbuk.mchms.domain.Cgis;
import com.jeonbuk.mchms.domain.Nation;
import com.jeonbuk.mchms.domain.Ngos;
import com.jeonbuk.mchms.service.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegisterService {
    @Autowired
    RegisterMapper registerMapper;

    public List<Nation> SelectNationName() throws Exception {
        return registerMapper.SelectNationName();
    }

    public void setCgiData(Map<String, String> sqlParam) throws Exception{
        registerMapper.setCgiData(sqlParam);
    }
    public void setNgoData(Map<String, String> sqlParam) throws Exception{
        registerMapper.setNgoData(sqlParam);
    }

    public Ngos ngoDataById(String ID){
        return registerMapper.ngoDataById(ID);
    }

    public Cgis cgiDataById(String ID){
        return registerMapper.cgiDataById(ID);
    }

    public void updateNgoData(Map<String, String> sqlParam){
        registerMapper.updateNgoData(sqlParam);
    }

    public void updateCgiData(Map<String, String> sqlParam){
        registerMapper.updateCgiData(sqlParam);
    }
}
