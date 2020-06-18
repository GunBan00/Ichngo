package com.jeonbuk.mchms.cont.DispacherCont.write;

import com.jeonbuk.mchms.cont.DispacherCont.main.MainController;
import com.jeonbuk.mchms.domain.City;
import com.jeonbuk.mchms.domain.Classification;
import com.jeonbuk.mchms.service.city.CityService;
import com.jeonbuk.mchms.service.classification.ClassificationService;
import com.jeonbuk.mchms.service.calnum.CalNumService;
import com.jeonbuk.mchms.service.data.DataService;
import com.jeonbuk.mchms.service.user.UserService;
import groovy.util.logging.Slf4j;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class WriteController {

    @Autowired
    DataService dataService;

    @Autowired
    CityService cityService;

    @Autowired
    UserService userService;

    @Autowired
    CalNumService calNumService;



    private static Logger logger = LoggerFactory.getLogger(MainController.class);
    @RequestMapping(value = "/upload_select", method = RequestMethod.GET)
    public ModelAndView uploadSelect(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {

            HttpSession session = request.getSession();

            mv.setViewName("Main/BASE.html");
            mv.addObject("MID_Page", "Write/uploadSelect.html");

        } catch (Exception e) {
            e.printStackTrace();

        }

        return mv;
    }
    @RequestMapping(value = "/ICH_data_upload", method = RequestMethod.GET)
    public ModelAndView ichDataUpload(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {

            HttpSession session = request.getSession();
            String[] nation_list = dataService.getNationList();
            for(String a : nation_list)
                //System.out.println(a);
            mv.addObject("nation_list", nation_list);


            mv.setViewName("Main/BASE.html");
            mv.addObject("MID_Page", "Write/ichDataUpload.html");

        } catch (Exception e) {
            e.printStackTrace();

        }

        return mv;
    }
    @RequestMapping(value = "/network_upload", method = RequestMethod.GET)
    public ModelAndView networkUpload(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {

            HttpSession session = request.getSession();

            mv.setViewName("Main/BASE.html");
            mv.addObject("MID_Page", "Write/networkUpload.html");

        } catch (Exception e) {
            e.printStackTrace();

        }

        return mv;
    }

}
