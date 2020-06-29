package com.jeonbuk.mchms.cont.DispacherCont.user;

import com.jeonbuk.mchms.domain.*;
import com.jeonbuk.mchms.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MypageController {

    @Autowired
    UserService userService;

    private static Logger logger = LoggerFactory.getLogger(com.jeonbuk.mchms.cont.DispacherCont.user.MypageController.class);

    @RequestMapping(value = "/mypage", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView mypage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();

        HttpSession session = request.getSession();
        mv.addObject("session", session);
        String id = (String) session.getAttribute("id");
        if (id == null){
            id = "testtest";
        }
        System.out.println(id);

        mv.setViewName("Main/BASE");
        mv.addObject("MID_Page", "User/Mypage.html");

        try {
            User userinfo = userService.getInfoFromUserById(id);

            mv.addObject("userinfo", userinfo);

            if(!userinfo.getNgoId().equals("0")) {
                userinfo.setNgoId(userService.getNameFromNGOS(userinfo.getNgoId()));
            }
            System.out.println(userinfo.getCgiId());
            if(!userinfo.getCgiId().equals("0")) {
                userinfo.setCgiId(userService.getNameFromCGIS(userinfo.getCgiId()));
            }
            return mv;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/user_modify", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView userModify(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();

        HttpSession session = request.getSession();
        mv.addObject("session", session);
        String id = (String) session.getAttribute("id");
        if (id == null){
            id = "testtest";
        }
        mv.setViewName("Main/BASE");
        mv.addObject("MID_Page", "User/user_modify.html");
        try {
            User userin = userService.getInfoFromUserById(id);
            System.out.println(userin.getCgiId() + " " + userin.getNgoId());
            mv.addObject("userinfo", userin);

            String cgi_name = request.getParameter("cgi");
            if(cgi_name == null){
                cgi_name = "";
            }
            mv.addObject("cgi_name", cgi_name);

            List<Ngos> ngo_lists = userService.getNgoListByIdAndName();
            mv.addObject("ngo_lists", ngo_lists);

            if (cgi_name != ""){
                List<Cgis> cgi_ids = userService.getCgiIdFromCgi(cgi_name);
                mv.addObject("cgi_ids", cgi_ids);
            }

            List<Cgis> cgi_lists = userService.getCgiInfoFromCgi();
            mv.addObject("cgi_lists", cgi_lists);

            List<TbEventMaster> event_lists = userService.getTbEventMasterInfoFromTbEventMaster();
            mv.addObject("event_lists", event_lists);

            return mv;

        } catch (Exception e) {
            logger.error(e.toString());
        }

        return mv;
    }
}
