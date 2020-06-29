package com.jeonbuk.mchms.cont.DispacherCont.view;

import com.jeonbuk.mchms.domain.*;
import com.jeonbuk.mchms.service.data.DataService;
//import org.apache.tomcat.util.json.JSONParser;
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
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import java.io.InputStreamReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.nio.charset.Charset;
import java.util.*;

@Controller
public class ViewController {

    private static Logger logger = LoggerFactory.getLogger(ViewController.class);

    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/network_detail", method = RequestMethod.GET)
    public ModelAndView mCHMSView(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();

        try {

            String id = request.getParameter("id");
            String category = request.getParameter("category");

            String userId = String.valueOf(session.getAttribute("id"));
            EventData eventData;
            eventData = dataService.getEventData(id);

            String [] ids = dataService.getEventDataId();
            int index;
            String nextGo="", prevGo="";
            for(int i = 0; i < ids.length; i++)
            {
                if(ids[i].equals(id)){
                    if(i+1 >= ids.length)
                        nextGo = "last";
                    else
                        nextGo = ids[i+1];
                    if(i == 0)
                        prevGo = "first";
                    else
                    prevGo = ids[i-1];
                }
            }
            String ngoId = eventData.getEvent_ngo_id();
            String ngo;
            if(ngoId.equals("0") || ngoId.equals(null))
                ngo = dataService.getCgiName(eventData.getEvent_cgi_id());
            else
                ngo = dataService.getNgoName(eventData.getEvent_ngo_id());
            String imagePath = "";
            String imageTag="";


            String fileTag = "<a href='/Static/Images/"+ eventData.getEvent_file() + "' download><div class='file'>Attached File (1)&nbsp;&nbsp;</div></a>";

            /*************************************************************이미지 태그 추가***********************************************************/
            String image = eventData.getEvent_image();
            int imageFlag = 0;
            if(!image.equals(",,,,")) {
                imageFlag = 1;
                String[] images  = image.split(",");
                int length = images.length;
                String comment = eventData.getEvent_image_comment();
                //System.out.println(length);
                if(!comment.equals("\\||||") && length != 0) {
                    String[] imageComment = new String[5];
                    for(String s : imageComment)
                        s = "";
                    imageComment = comment.split("\\|");
                    //System.out.println(imageComment.length);
                    for (int i = 0; i < length; i++) {
                        if(!imageComment.equals(null) && imageComment.length != 0)
                            if (imageComment[i] != "")
                                imagePath += "<li><img src='/Images/" + images[i] + "'" + " title=" + "'" + imageComment[i] + "'" + " style='cursor: pointer;'/></li>";
                            else
                                imagePath += "<li><img src='/Images/" + images[i] + "'" + "style='cursor: pointer';/></li>";
                        else
                            imagePath += "<li><img src='/Images/" + images[i] + "'" + "style='cursor: pointer';/></li>";

                    }
                    mv.addObject("imagelength", length);
                    mv.addObject("images", images);
                    mv.addObject("imageComment", imageComment);
                }
            }
            /*************************************************************veune로 지도 좌표 구하기***********************************************************/
            String venue = eventData.getEvent_venue();
            if(!venue.equals("")) {
                venue = venue.replaceAll(" ","");
                String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + venue + "&key=AIzaSyAnat35EqtxsdXAANY57zIOaKMXLP2C2jk";
                InputStream is = new URL(url).openStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                int location = jsonText.indexOf("location");
                String afterLocation = jsonText.substring(location);
                int afterLocationlat = afterLocation.indexOf("lat");
                int afterLocationIng = afterLocation.indexOf("lng");
                String lat = afterLocation.substring(afterLocationlat + 7, afterLocationlat + 14);

                String lng = afterLocation.substring(afterLocationIng + 7, afterLocationIng + 14);

                mv.addObject("lat", lat);
                mv.addObject("lng", lng);
            }
            List<Comment> comments = dataService.getEventComment(id);

            String userNgo = dataService.getUserNgoById(userId);
            mv.addObject("id", id);
            mv.addObject("userId", userNgo);
            mv.addObject("imageFlag", imageFlag);
            mv.addObject("comments", comments);
            mv.addObject("prev", prevGo);
            mv.addObject("next", nextGo);
            mv.addObject("fileTag", fileTag);
            mv.addObject("imageTag", imagePath);
            mv.addObject("ngo", ngo);
            mv.addObject("eventData", eventData);
            mv.setViewName("Main/Base.html");
            mv.addObject("MID_Page", "View/event_view.html");
            return mv;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;
    }
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

}