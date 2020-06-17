package com.jeonbuk.mchms.cont.DispacherCont.user;

import com.jeonbuk.mchms.domain.*;
import com.jeonbuk.mchms.service.mail.MailService;
import com.jeonbuk.mchms.service.user.UserService;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import javax.xml.bind.DatatypeConverter;
import javax.servlet.http.HttpSession;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.PasswordAuthentication;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ApplyController {

    private static Logger logger = LoggerFactory.getLogger(com.jeonbuk.mchms.cont.DispacherCont.main.StatisticsController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/network_signup", method = RequestMethod.GET)
    public ModelAndView Network_Signup(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Main/BASE");
        mv.addObject("MID_Page", "User/sign_up.html");
        try {
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

        } catch (Exception e) {
            logger.error(e.toString());
        }

        return mv;
    }

    @RequestMapping(value = "/network_sign_process", method = RequestMethod.POST)
    public ModelAndView sign_process(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();

        try {

            Map<String, Object> obj = new HashMap<>();

            obj.put("ID", request.getParameter("ID"));
            obj.put("PW", makePassword(request.getParameter("PW")));
            obj.put("EMAIL", request.getParameter("EMAIL"));
            obj.put("FIRSTNAME", request.getParameter("firstName"));
            obj.put("LASTNAME", request.getParameter("lastName"));
            obj.put("PHONE", request.getParameter("phone"));
            obj.put("MIDDLENAME", request.getParameter("middleName"));

            String NGOID = request.getParameter("list_ngo");
            if (NGOID == null){
                NGOID = "0";
            }
            else {
                NGOID = userService.getIdFromNGOS(NGOID).getId();
            }
            System.out.println(NGOID);
            obj.put("NGOID", NGOID);

            String CGIID = request.getParameter("list_cgi");

            if (CGIID == null){
                CGIID = "0";
            }
            else {
                CGIID = userService.getIdFromCGIS(CGIID).getCgiId();
            }
            obj.put("CGIID", CGIID);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date time = new Date();
            String SIGN_DATE = sdf.format(time);

            obj.put("SIGN_DATE", SIGN_DATE);

            userService.setUser(obj);


            MailDomain mail = new MailDomain();
            mail.setAddress(request.getParameter("EMAIL"));
            mail.setTitle("Welcome to ICHNGO.net");
            mail.setMessage("<html><body>Hi " + request.getParameter("lastName") +  ",<br>Thank you for registering to ICHNGO.net website.<br>ICHNGO will provide and share events, news, documents about UNESCO Accredited ICH NGO.<br>You now have an access to upload documents or to leave any comments.<br>Please visit the link below to login the website : <br><a href='http://www.ichngo.net'>www.ichngo.net</a><br><br>If you have any questions or concerns, please contact us directly via icheventboard@gmail.com.<br><br>All the best,<br>CICS(Center for Intangible Culture Studies), an ICH NGO accredited by UNESCO<br>icheventboard@gmail.com<br>Tel : +82 63 277 4098<br>Fax : +82 63 277 4097<br>");
            mailService.mailSend(mail);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response.getWriter();
            out_equals.println("<script type = 'text/javascript'> alert('You have successfully signed up. Please log in.');location.href='/network_login/';</script>");

            out_equals.flush();

        } catch (Exception e) {
            logger.error(e.toString());
        }

        return mv;
    }

    public String makePassword(String decPass) {

        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(decPass.getBytes("UTF-8"), 0, decPass.length());

            String sha1 =  byteToHex(msdDigest.digest());

            msdDigest.update(sha1.getBytes("UTF-8"), 0, sha1.length());
            String sha2 = byteToHex(msdDigest.digest());

            return "*" + sha2;

        }catch (Exception e) {

        }

        return "";
    }

    private static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}