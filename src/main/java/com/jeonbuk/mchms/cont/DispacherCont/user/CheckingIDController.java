package com.jeonbuk.mchms.cont.DispacherCont.user;

import com.jeonbuk.mchms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CheckingIDController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/network_check_id")
    public HashMap<String, Object> Network_check_id(@RequestBody String id){
        HashMap<String, Object> map = new HashMap<>();
        String TestID = id.substring(3);
        int checkID = userService.CheckingUserIDProcess(TestID);
        String canMakeId;

        if(checkID == 1){
            canMakeId = "N";
        }
        else {
            canMakeId = "Y";
        }

        map.put("canMakeId", canMakeId);
        return map;
    }
}
