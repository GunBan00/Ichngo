package com.jeonbuk.mchms.cont.DispacherCont.main;


import com.jeonbuk.mchms.domain.City;
import com.jeonbuk.mchms.domain.Classification;
import com.jeonbuk.mchms.domain.DataDomain;
import com.jeonbuk.mchms.domain.MainData;
import com.jeonbuk.mchms.service.city.CityService;
import com.jeonbuk.mchms.service.classification.ClassificationService;
import com.jeonbuk.mchms.service.data.DataService;

import com.jeonbuk.mchms.service.user.UserService;
import groovy.util.logging.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.mobile.device.*;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;



@Controller
@Slf4j
public class MainController {
    @Autowired

    private DataService dataService;

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView base(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String userAgent;

        HttpSession session = request.getSession();
        try {
            String category = request.getParameter("category");
            List<MainData> dataList;
            if(category != null) {
                mv.addObject("category", category);
                if(category.equals("events"))
                    category = "EVENTS";
                else if(category.equals("ichgram"))
                    category = "ICHGRAM";
                else if(category.equals("inventory"))
                    category = "INVENTORIES";
                dataList = dataService.getDataByCategory(category);
            }
            else {
                mv.addObject("category", "");

                dataList = dataService.getAllData();
            }
            int count = 0;
            for(MainData MList : dataList){
                //System.out.println(MList.getId());
                //System.out.println(MList.getImage());
                count++;
            }

            mv.addObject("dataList", dataList);
            mv.addObject("length_list", count);
            mv.setViewName("Main/Base.html");
            mv.addObject("MID_Page", "Main/Main.html");
            return mv;

        } catch (Exception e) {
            logger.error(e.toString());
        }

        return mv;
    }
    @RequestMapping(value = "/main_json", method = RequestMethod.GET)
    public Map<String, Object> mainJson(HttpServletRequest request) {

        Map<String, Object> readMore = new HashMap<String, Object>();
        try {
            String category = request.getParameter("category");
            String Keyword = request.getParameter("Keyword");
            String json_num = request.getParameter("json_num");
            List<MainData> dataList;
            if(category != null) {
                if (category.equals("events"))
                    category = "EVENTS";
                else if (category.equals("ichgram"))
                    category = "ICHGRAM";
                else if (category.equals("inventory"))
                    category = "INVENTORIES";
                dataList = dataService.getDataByCategoryMore(category, json_num);
            }
            else
                dataList = dataService.getAllDataMore(json_num);
            readMore.put("EventList", dataList);
            System.out.println(readMore);
            return readMore;
        }
        catch (Exception e) {
            logger.error(e.toString());
        }
        return readMore;
    }


}