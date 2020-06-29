package com.jeonbuk.mchms.cont.DoCont;

import com.jeonbuk.mchms.domain.NewsletterDomain;
import com.jeonbuk.mchms.domain.User;
import com.jeonbuk.mchms.service.newsletter.NewsletterService;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class NewsletterCont {

    @Autowired
    NewsletterService newsletterService;

    private static Logger logger = LoggerFactory.getLogger(UserDoCont.class);

    @RequestMapping(value = "/Newsletter", method = RequestMethod.GET)
    public ModelAndView Newsletter(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView mv = new ModelAndView();
        mv.addObject("session", session);

        mv.setViewName("Main/BASE");
        mv.addObject("MID_Page", "Newsletter/Newsletter.html");
        try {
            List<NewsletterDomain> Newsletters = newsletterService.SelectNewsletterData();

            mv.addObject("Newsletters", Newsletters);
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }
    @RequestMapping(value = "/Academic_Paper", method = RequestMethod.GET)
    public ModelAndView Academic_Paper(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView mv = new ModelAndView();
        mv.addObject("session", session);

        mv.setViewName("Main/BASE");
        mv.addObject("MID_Page", "Newsletter/Academic_Paper.html");

        return mv;
    }

    @RequestMapping(value = "/Magazine", method = RequestMethod.GET)
    public ModelAndView Magazine(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ModelAndView mv = new ModelAndView();
        mv.addObject("session", session);

        mv.setViewName("Main/BASE");
        mv.addObject("MID_Page", "Newsletter/Magazine.html");

        return mv;
    }
}
