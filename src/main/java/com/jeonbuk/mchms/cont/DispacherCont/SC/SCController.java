package com.jeonbuk.mchms.cont.DispacherCont.SC;

import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class SCController {
    @RequestMapping(value = "/SC/Aboutsc", method = RequestMethod.GET)
    public ModelAndView AboutSC(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        try {
            mv.setViewName("Main/Base.html");

            mv.addObject("MID_Page", "SC/AboutSC.html");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/SC/Bylaws", method = RequestMethod.GET)
    public ModelAndView Bylaws(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        try {
            mv.setViewName("Main/Base.html");

            mv.addObject("MID_Page", "SC/Bylaws.html");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/SC/Code", method = RequestMethod.GET)
    public ModelAndView Codeof(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        try {
            mv.setViewName("Main/Base.html");

            mv.addObject("MID_Page", "SC/Codeof.html");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/SC/Members", method = RequestMethod.GET)
    public ModelAndView Members(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        try {
            mv.setViewName("Main/Base.html");

            mv.addObject("MID_Page", "SC/Members.html");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;
    }

    @RequestMapping(value = "/SC/Minutes", method = RequestMethod.GET)
    public ModelAndView Minutes(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        try {
            mv.setViewName("Main/Base.html");

            mv.addObject("MID_Page", "SC/Minutes.html");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mv;
    }

}
