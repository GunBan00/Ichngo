package com.jeonbuk.mchms.cont.DispacherCont.user;

import com.jeonbuk.mchms.domain.Cgis;
import com.jeonbuk.mchms.domain.Nation;
import com.jeonbuk.mchms.domain.Ngos;
import com.jeonbuk.mchms.service.register.RegisterService;
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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RegistrationController {
    private static Logger logger = LoggerFactory.getLogger(com.jeonbuk.mchms.cont.DispacherCont.main.StatisticsController.class);

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView Registration(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Main/BASE");
        mv.addObject("MID_Page", "Register/Registration.html");

        return mv;
    }

    @RequestMapping(value = "/register_ngo", method = RequestMethod.GET)
    public ModelAndView Register_ngo(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Main/BASE");
        mv.addObject("MID_Page", "Register/Register_NGO.html");
        try{
            List<Nation> nation_lists = registerService.SelectNationName();

            mv.addObject("nation_lists", nation_lists);

        } catch (Exception e) {
            logger.error(e.toString());
        }
        return mv;
    }

    @RequestMapping(value = "/register_cgi", method = RequestMethod.GET)
    public ModelAndView Register_cgi(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Main/BASE");
        mv.addObject("MID_Page", "Register/Register_CGI.html");
        try{
            List<Nation> nation_lists = registerService.SelectNationName();

            mv.addObject("nation_lists", nation_lists);

        } catch (Exception e) {
            logger.error(e.toString());
        }
        return mv;
    }
    @RequestMapping(value = "/cgi_upload_process", method = RequestMethod.POST)
    public ModelAndView CGI_upload_process(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try{
            String cgi_type = request.getParameter("cgi_type");
            String cgi_name = request.getParameter("cgi_name");
            String cgi_email = request.getParameter("cgi_email");
            String cgi_aff = request.getParameter("cgi_aff");
            String cgi_acc_year = request.getParameter("cgi_acc_year");
            String ngo_country = request.getParameter("ngo_country");
            String cgi_domain1 = request.getParameter("cgi_domain1");
            if (cgi_domain1 == null){
                cgi_domain1 = "";
            }
            String cgi_domain2 = request.getParameter("cgi_domain2");
            if (cgi_domain2 == null){
                cgi_domain2 = "";
            }
            String cgi_domain3 = request.getParameter("cgi_domain3");
            if (cgi_domain3 == null){
                cgi_domain3 = "";
            }
            String cgi_domain4 = request.getParameter("cgi_domain4");
            if (cgi_domain4 == null){
                cgi_domain4 = "";
            }
            String cgi_domain5 = request.getParameter("cgi_domain5");
            if (cgi_domain5 == null){
                cgi_domain5 = "";
            }
            String cgi_domain6 = request.getParameter("cgi_domain6");
            if (cgi_domain6 == null){
                cgi_domain6 = "";
            }
            String cgi_address = request.getParameter("cgi_address");
            String cgi_telephone = request.getParameter("cgi_telephone");
            String cgi_fax = request.getParameter("cgi_fax");
            String cgi_homepage = request.getParameter("cgi_homepage");
            String cgi_social = request.getParameter("cgi_social");
            String cgi_represent = request.getParameter("cgi_represent");

            String str_cgi_domain = cgi_domain1 + "/" + cgi_domain2 + "/" + cgi_domain3 + "/" + cgi_domain4 + "/" + cgi_domain5 + "/" + cgi_domain6;

            Map<String, String> sqlParam = new HashMap<>();
            sqlParam.put("cgi_type", cgi_type);
            sqlParam.put("cgi_name", cgi_name);
            sqlParam.put("cgi_email", cgi_email);
            sqlParam.put("cgi_aff", cgi_aff);
            sqlParam.put("cgi_acc_year", cgi_acc_year);
            sqlParam.put("ngo_country", ngo_country);
            sqlParam.put("str_cgi_domain", str_cgi_domain);
            sqlParam.put("cgi_address", cgi_address);
            sqlParam.put("cgi_telephone", cgi_telephone);
            sqlParam.put("cgi_fax", cgi_fax);
            sqlParam.put("cgi_homepage", cgi_homepage);
            sqlParam.put("cgi_social", cgi_social);
            sqlParam.put("cgi_represent", cgi_represent);

            registerService.setCgiData(sqlParam);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response.getWriter();
            if (cgi_type == "individual" || cgi_type == "expert"){
                out_equals.println("<script type = 'text/javascript'> alert('Successfully registered. Congratulations. Please go to sign up.');location.href='/network_signup/?cgi="+ cgi_name + "';</script>");
            }
            else {
                out_equals.println("<script type = 'text/javascript'> alert('Successfully registered. Congratulations. Please go to sign up.');location.href='/network_signup';</script>");
            }
            out_equals.flush();
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return mv;
    }

    @RequestMapping(value = "/ngo_upload_process", method = RequestMethod.POST)
    public ModelAndView NGO_upload_process(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        try{
            String ngo_name = request.getParameter("ngo_name");
            String ngo_region = request.getParameter("ngo_region");
            String ngo_country = request.getParameter("ngo_country");
            String ngo_acc_year = request.getParameter("ngo_acc_year");
            String ngo_safe1 = request.getParameter("ngo_safe1");
            if (ngo_safe1 == null){
                ngo_safe1 = "";
            }
            String ngo_safe2 = request.getParameter("ngo_safe2");
            if (ngo_safe2 == null){
                ngo_safe2 = "";
            }
            String ngo_safe3 = request.getParameter("ngo_safe3");
            if (ngo_safe3 == null){
                ngo_safe3 = "";
            }
            String ngo_safe4 = request.getParameter("ngo_safe4");
            if (ngo_safe4 == null){
                ngo_safe4 = "";
            }
            String ngo_safe5 = request.getParameter("ngo_safe5");
            if (ngo_safe5 == null){
                ngo_safe5 = "";
            }
            String ngo_safe6 = request.getParameter("ngo_safe6");
            if (ngo_safe6 == null){
                ngo_safe6 = "";
            }
            String ngo_domain1 = request.getParameter("ngo_domain1");
            if (ngo_domain1 == null){
                ngo_domain1 = "";
            }
            String ngo_domain2 = request.getParameter("ngo_domain2");
            if (ngo_domain2 == null){
                ngo_domain2 = "";
            }
            String ngo_domain3 = request.getParameter("ngo_domain3");
            if (ngo_domain3 == null){
                ngo_domain3 = "";
            }
            String ngo_domain4 = request.getParameter("ngo_domain4");
            if (ngo_domain4 == null){
                ngo_domain4 = "";
            }
            String ngo_domain5 = request.getParameter("ngo_domain5");
            if (ngo_domain5 == null){
                ngo_domain5 = "";
            }
            String ngo_domain6 = request.getParameter("ngo_domain6");
            if (ngo_domain6 == null){
                ngo_domain6 = "";
            }

            String ngo_level = request.getParameter("ngo_level");
            String ngo_address = request.getParameter("ngo_address");
            String ngo_email = request.getParameter("ngo_email");
            String ngo_telephone = request.getParameter("ngo_telephone");
            String ngo_fax = request.getParameter("ngo_fax");
            String ngo_homepage = request.getParameter("ngo_homepage");
            String ngo_social = request.getParameter("ngo_social");
            String ngo_represent = request.getParameter("ngo_represent");

            String str_ngo_safe = ngo_safe1 + "/" + ngo_safe2 + "/" + ngo_safe3 + "/" + ngo_safe4 + "/" + ngo_safe5 + "/" + ngo_safe6;
            String str_ngo_domain = ngo_domain1 + "/" + ngo_domain2 + "/" + ngo_domain3 + "/" + ngo_domain4 + "/" + ngo_domain5 + "/" + ngo_domain6;

            Map<String, String> sqlParam = new HashMap<>();
            sqlParam.put("ngo_name", ngo_name);
            sqlParam.put("ngo_region", ngo_region);
            sqlParam.put("ngo_country", ngo_country);
            sqlParam.put("ngo_acc", ngo_acc_year);
            sqlParam.put("str_ngo_safe", str_ngo_safe);
            sqlParam.put("str_ngo_domain", str_ngo_domain);
            sqlParam.put("ngo_level", ngo_level);
            sqlParam.put("ngo_address", ngo_address);
            sqlParam.put("ngo_email", ngo_email);
            sqlParam.put("ngo_telephone", ngo_telephone);
            sqlParam.put("ngo_fax", ngo_fax);
            sqlParam.put("ngo_homepage", ngo_homepage);
            sqlParam.put("ngo_social", ngo_social);
            sqlParam.put("ngo_represent", ngo_represent);

            registerService.setNgoData(sqlParam);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response.getWriter();
            out_equals.println("<script type = 'text/javascript'> alert('Successfully registered. Congratulations. Please go to sign up.');location.href='/';</script>");

            out_equals.flush();

        } catch (Exception e) {
            logger.error(e.toString());
        }
        return mv;
    }

    @RequestMapping(value = "/revise_ngo", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView Revise_ngo(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        mv.setViewName("Main/BASE");
        mv.addObject("MID_Page", "Register/revise_ngo.html");
        try{
            mv.addObject("id", id);

            List<Nation> nation_lists = registerService.SelectNationName();
            mv.addObject("nation_lists", nation_lists);

            Ngos ngoData = registerService.ngoDataById(id);
            mv.addObject("ngoData", ngoData);

            String[] ngo_safe = ngoData.getMainMeasures().split("\\/", -1);
            String[] ngo_domain = ngoData.getDomains().split("\\/", -1);

            mv.addObject("ngo_safe", ngo_safe);
            mv.addObject("ngo_domain", ngo_domain);

        } catch (Exception e) {
            logger.error(e.toString());
        }
        return mv;
    }

    @RequestMapping(value = "/revise_cgi", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView Revise_cgi(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        mv.setViewName("Main/BASE");
        mv.addObject("MID_Page", "Register/revise_cgi.html");
        try{
            mv.addObject("id", id);

            List<Nation> nation_lists = registerService.SelectNationName();
            mv.addObject("nation_lists", nation_lists);

            Cgis cgiData = registerService.cgiDataById(id);
            mv.addObject("cgiData", cgiData);

            String[] cgi_domain = cgiData.getCgiDomain().split("\\/", -1);
            mv.addObject("cgi_domain", cgi_domain);

        } catch (Exception e) {
            logger.error(e.toString());
        }
        return mv;
    }

    @RequestMapping(value = "/ngo_update_process", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView ngo_update(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        try{
            Map<String, String> sqlParam = new HashMap<>();
            sqlParam.put("ngo_id", id);
            sqlParam.put("ngo_name", request.getParameter("ngo_name"));
            sqlParam.put("ngo_region", request.getParameter("ngo_region"));
            sqlParam.put("ngo_country", request.getParameter("ngo_country"));
            sqlParam.put("ngo_acc", request.getParameter("ngo_acc"));
            sqlParam.put("ngo_level", request.getParameter("ngo_level"));
            sqlParam.put("ngo_address", request.getParameter("ngo_address"));
            sqlParam.put("ngo_email", request.getParameter("ngo_email"));
            sqlParam.put("ngo_telephone", request.getParameter("ngo_telephone"));
            sqlParam.put("ngo_fax", request.getParameter("ngo_fax"));
            sqlParam.put("ngo_homepage", request.getParameter("ngo_homepage"));
            sqlParam.put("ngo_social", request.getParameter("ngo_social"));
            sqlParam.put("ngo_represent", request.getParameter("ngo_represent"));

            String ngo_safe1 = request.getParameter("ngo_safe1");
            if (ngo_safe1 == null){
                ngo_safe1 = "";
            }
            String ngo_safe2 = request.getParameter("ngo_safe2");
            if (ngo_safe2 == null){
                ngo_safe2 = "";
            }
            String ngo_safe3 = request.getParameter("ngo_safe3");
            if (ngo_safe3 == null){
                ngo_safe3 = "";
            }
            String ngo_safe4 = request.getParameter("ngo_safe4");
            if (ngo_safe4 == null){
                ngo_safe4 = "";
            }
            String ngo_safe5 = request.getParameter("ngo_safe5");
            if (ngo_safe5 == null){
                ngo_safe5 = "";
            }
            String ngo_safe6 = request.getParameter("ngo_safe6");
            if (ngo_safe6 == null){
                ngo_safe6 = "";
            }
            String ngo_domain1 = request.getParameter("ngo_domain1");
            if (ngo_domain1 == null){
                ngo_domain1 = "";
            }
            String ngo_domain2 = request.getParameter("ngo_domain2");
            if (ngo_domain2 == null){
                ngo_domain2 = "";
            }
            String ngo_domain3 = request.getParameter("ngo_domain3");
            if (ngo_domain3 == null){
                ngo_domain3 = "";
            }
            String ngo_domain4 = request.getParameter("ngo_domain4");
            if (ngo_domain4 == null){
                ngo_domain4 = "";
            }
            String ngo_domain5 = request.getParameter("ngo_domain5");
            if (ngo_domain5 == null){
                ngo_domain5 = "";
            }
            String ngo_domain6 = request.getParameter("ngo_domain6");
            if (ngo_domain6 == null){
                ngo_domain6 = "";
            }

            String str_ngo_safe = ngo_safe1 + "/" + ngo_safe2 + "/" + ngo_safe3 + "/" + ngo_safe4 + "/" + ngo_safe5 + "/" + ngo_safe6;
            String str_ngo_domain = ngo_domain1 + "/" + ngo_domain2 + "/" + ngo_domain3 + "/" + ngo_domain4 + "/" + ngo_domain5 + "/" + ngo_domain6;

            sqlParam.put("str_ngo_safe", str_ngo_safe);
            sqlParam.put("str_ngo_domain", str_ngo_domain);

            registerService.updateNgoData(sqlParam);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response.getWriter();
            out_equals.println("<script type = 'text/javascript'>location.href='/';</script>");

            out_equals.flush();
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return mv;
    }

    @RequestMapping(value = "/cgi_update_process", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView cgi_update(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        String id = request.getParameter("id");
        try{
            Map<String, String> sqlParam = new HashMap<>();
            sqlParam.put("cgi_id", id);
            sqlParam.put("cgi_type", request.getParameter("cgi_type"));
            sqlParam.put("cgi_name", request.getParameter("cgi_name"));
            sqlParam.put("cgi_email", request.getParameter("cgi_email"));
            sqlParam.put("cgi_aff", request.getParameter("cgi_aff"));
            sqlParam.put("cgi_acc_year", request.getParameter("cgi_acc_year"));
            sqlParam.put("cgi_country", request.getParameter("cgi_country"));
            sqlParam.put("cgi_address", request.getParameter("cgi_address"));
            sqlParam.put("cgi_telephone", request.getParameter("cgi_telephone"));
            sqlParam.put("cgi_fax", request.getParameter("cgi_fax"));
            sqlParam.put("cgi_homepage", request.getParameter("cgi_homepage"));
            sqlParam.put("cgi_social", request.getParameter("cgi_social"));
            sqlParam.put("cgi_represent", request.getParameter("cgi_represent"));
            String cgi_domain1 = request.getParameter("cgi_domain1");
            if (cgi_domain1 == null){
                cgi_domain1 = "";
            }
            String cgi_domain2 = request.getParameter("cgi_domain2");
            if (cgi_domain2 == null){
                cgi_domain2 = "";
            }
            String cgi_domain3 = request.getParameter("cgi_domain3");
            if (cgi_domain3 == null){
                cgi_domain3 = "";
            }
            String cgi_domain4 = request.getParameter("cgi_domain4");
            if (cgi_domain4 == null){
                cgi_domain4 = "";
            }
            String cgi_domain5 = request.getParameter("cgi_domain5");
            if (cgi_domain5 == null){
                cgi_domain5 = "";
            }
            String cgi_domain6 = request.getParameter("cgi_domain6");
            if (cgi_domain6 == null){
                cgi_domain6 = "";
            }

            String str_cgi_domain = cgi_domain1 + "/" + cgi_domain2 + "/" + cgi_domain3 + "/" + cgi_domain4 + "/" + cgi_domain5 + "/" + cgi_domain6;
            sqlParam.put("str_cgi_domain", str_cgi_domain);

            registerService.updateCgiData(sqlParam);

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out_equals = response.getWriter();
            out_equals.println("<script type = 'text/javascript'>location.href='/';</script>");

            out_equals.flush();
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return mv;
    }
}
