package com.jeonbuk.mchms.cont.DispacherCont.view;

import com.jeonbuk.mchms.domain.*;
import com.jeonbuk.mchms.service.city.CityService;
import com.jeonbuk.mchms.service.classification.ClassificationService;
import com.jeonbuk.mchms.service.data.DataService;
import com.jeonbuk.mchms.service.fileevent.FileEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

@Controller
public class ViewController {

    private static Logger logger = LoggerFactory.getLogger(ViewController.class);

    @Autowired
    private DataService dataService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ClassificationService classificationService;

    @Autowired
    private FileEventService fileEventService;


    @RequestMapping(value = "/network_detail", method = RequestMethod.GET)
    public ModelAndView mCHMSView(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();

        try {

            String id = request.getParameter("id");

            String userId = String.valueOf(session.getAttribute("id"));
            EventData eventData = dataService.getEventData(id);
            String [] ids = dataService.getDataId();
            int intid = Integer.parseInt(id);
            int index;

            String ngoId = eventData.getEvent_ngo_id();
            String ngo;
            if(ngoId.equals("0"))
                ngo = dataService.getUserNgoById(userId);
            else
                ngo = dataService.getUserCgiById(userId);
            String comment = eventData.getEvent_image_comment();
            String [] imageComment = comment.split("|");
            String image = eventData.getEvent_image();

            String [] images = image.split(",");
            int length = images.length;

            mv.addObject("length", length);
            mv.addObject("ngo", ngo);
            mv.addObject("eventData", eventData);
            mv.addObject("imageComment", imageComment);
            mv.addObject("images", images);
            mv.setViewName("Main/Base.html");
            mv.addObject("MID_Page", "View/event_view.html");
            return mv;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;
    }
    @RequestMapping(value = "/MCHMSDelete", method = RequestMethod.GET)
    public ModelAndView mchmsDelete(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        mv.addObject("session", session);

        try {
            String id = request.getParameter("ID");

            DataDomain dataDomain = dataService.getDataInfo(id);
            String CityId = dataDomain.getCityId();

            System.out.println(dataDomain.getRegistrant() + " " + session.getAttribute("id"));

            if((session == null) || !(dataDomain.getRegistrant().equals(session.getAttribute("id")))){
                response.setContentType("text/html; charset=UTF-8");

                PrintWriter out = response.getWriter();

                out.println("<script>alert('It cannot Delete'); location.href='/MCHMSSearch/?City_id=" + CityId + "';</script>");
                out.flush();

                return mv;
            }

            dataService.deleteData(id);

            FileEventDomain file = fileEventService.getFilesNameFromDataID(Integer.parseInt(id));

            String[] array = file.getFiles().split("\\|");
            String path = "C:\\image\\";//directory 수정해야됨
            String filesName = "";

            for (int i = 0; i < file.getCount(); i++) {
                filesName = path + array[i];
                File f = new File(filesName);

                f.delete();
            }

            fileEventService.deleteFileData(id);

            response.setContentType("text/html; charset=UTF-8");

            PrintWriter out = response.getWriter();

            out.println("<script>alert('Deleted'); location.href='/MCHMSSearch/?City_id=" + CityId + "';</script>");
            out.flush();

            return mv;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;
    }
}