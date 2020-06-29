package com.jeonbuk.mchms.cont.DoCont;

import com.jeonbuk.mchms.cont.DispacherCont.main.MainController;
import com.jeonbuk.mchms.service.data.DataService;
import com.jeonbuk.mchms.service.user.UserService;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;

@Controller
@Slf4j
public class EventWriteDoCont {

    @Autowired
    DataService dataservice;
    @Autowired
    UserService userService;

    private static Logger logger = LoggerFactory.getLogger(MainController.class);


    @RequestMapping(value = "/network_upload_process", method = RequestMethod.POST)
    public ModelAndView mypage(MultipartHttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try {
            String path =  "C:\\install\\image\\";//directory 수정해야됨

            HttpSession session = request.getSession();

            Date time = new Date();

            SimpleDateFormat format1 = new SimpleDateFormat( "yyyyMMddHHMMSS");

            String time1 = format1.format(time);

            SimpleDateFormat format2 = new SimpleDateFormat( "yyyy-MM-dd");

            String time2 = format2.format(time);
            String eventTitle = request.getParameter("event_title");

            String eventSubject = request.getParameter("event_subject");
            String ngoName = request.getParameter("ngo_name");
            String startMonth = request.getParameter("start_month");
            String startDay = request.getParameter("start_day");
            String startYear = request.getParameter("start_year");
            String endMonth = request.getParameter("end_month");
            String endDay = request.getParameter("end_day");
            String endYear = request.getParameter("end_year");

            String startDate = startYear + "-" + startMonth + "-" + startDay;
            String endDate = endYear + "-" + endMonth + "-" + endDay;
            String venueSelect = request.getParameter("venue_select");
            System.out.println(venueSelect);
            String eventDescription = request.getParameter("event_description");

            String comment1 = request.getParameter("comment1");
            String comment2 = request.getParameter("comment2");
            String comment3 = request.getParameter("comment3");
            String comment4 = request.getParameter("comment4");
            String comment5 = request.getParameter("comment5");

            String comment = comment1+"|"+comment2+"|"+comment3+"|"+comment4+"|"+comment5;

            String id = String.valueOf(session.getAttribute("id"));

            String ngoId = dataservice.getUserNgo(id);
            String cgiId = dataservice.getUserCgi(id);

            MultipartFile image1 = request.getFile("event_image1");
            MultipartFile image2 = request.getFile("event_image2");
            MultipartFile image3 = request.getFile("event_image3");
            MultipartFile image4 = request.getFile("event_image4");
            MultipartFile image5 = request.getFile("event_image5");

            MultipartFile invFile = request.getFile("event_file1");

            String image1name = id + "_" + time1 + "_" + image1.getOriginalFilename().replace(",", "");
            long fileSize1 = image1.getSize();
            if(fileSize1 != 0)
            {
                image1.transferTo(new File(path +  image1name));
            }
            String image2name = id + "_" + time1 + "_" +image2.getOriginalFilename().replace(",", "");
            long fileSize2 = image2.getSize();
            if(fileSize2 != 0)
            {
                image2.transferTo(new File(path + image2name));
            }
            String image3name = id + "_" + time1 + "_" +image3.getOriginalFilename().replace(",", "");
            long fileSize3 = image3.getSize();
            if(fileSize3 != 0)
            {
                image3.transferTo(new File(path + image3name));
            }
            String image4name = id + "_" + time1 + "_" +image4.getOriginalFilename().replace(",", "");
            long fileSize4 = image4.getSize();
            if(fileSize4 != 0)
            {
                image4.transferTo(new File(path + image4name));
            }
            String image5name = id + "_" + time1 + "_" +image5.getOriginalFilename().replace(",", "");
            long fileSize5 = image5.getSize();
            if(fileSize5 != 0)
            {
                image5.transferTo(new File(path + image5name));
            }
            String fileName = id + "_" + time1 + "_" + invFile.getOriginalFilename().replace(",", "");
            long file1 = invFile.getSize();
            if(file1 != 0)
            {
                invFile.transferTo(new File(path +  fileName));
            }

            String imagename = image1name + "," + image2name + "," + image3name+"," + image4name + "," + image5name;

            String continent = dataservice.getContinent(venueSelect);

            Map<String, String> sqlParam = new HashMap<>();

            sqlParam.put("ngoId", ngoId);
            sqlParam.put("cgiId", cgiId);
            sqlParam.put("eventSubject", eventSubject);
            sqlParam.put("eventTitle", eventTitle);
            sqlParam.put("eventDescription", eventDescription);
            sqlParam.put("invImage", imagename);
            sqlParam.put("invFile", fileName);
            sqlParam.put("startDate", startDate);
            sqlParam.put("endDate", endDate);
            sqlParam.put("uploadDate", time2);
            sqlParam.put("comment", comment);
            sqlParam.put("eventAuthor", id);
            sqlParam.put("venueSelect", venueSelect);
            sqlParam.put("continent", continent);

            dataservice.setEventData(sqlParam);

            return new ModelAndView("redirect:/");

        }
        catch (Exception e) {
            logger.error(e.toString());
        }

        return new ModelAndView("redirect:/");
    }


}

