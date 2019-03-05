package com.fanke.administration.controller;

import com.fanke.administration.pojo.Journal;
import com.fanke.administration.service.JournalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class JournalController {
    @Autowired
    @Qualifier("journalServiceImpl")
    private JournalService journalService;
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 添加教员
     * @param content
     * @return
     */
    @RequestMapping("/addJour")
    @ResponseBody
    public void addUser(String content, HttpSession session){
        logger.info("日志添加");
        Journal journal=new Journal();
        journal.setUserId((Integer) session.getAttribute("userId"));
        logger.info("操作员"+journal.getUserId());
        journal.setJouTime(new Date());
        journal.setContent(content);
        int result=journalService.insertJou(journal);

    }

    /**
     * 查询日志
     * @param model
     * @return
     */
    @RequestMapping(value="joulist.html",method = RequestMethod.GET)
    public String getUserList( Model model){
        logger.info("查询日志======");
        int result=journalService.selectCount();
        List<Journal> jouList=new ArrayList<Journal>();
        jouList=journalService.selectAll();
        model.addAttribute("jouList", jouList);
        model.addAttribute("jouNum",result);
        return "journal-list";
    }


}
