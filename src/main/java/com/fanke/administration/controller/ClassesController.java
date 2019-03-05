package com.fanke.administration.controller;

import com.fanke.administration.pojo.Classes;
import com.fanke.administration.service.ClassesService;
import com.fanke.administration.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
public class ClassesController {

    @Autowired
    @Qualifier("classesServiceImpl")
    private ClassesService classesService;
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 进入添加班级页面
     * @param classes
     * @param model
     * @return
     */
    @RequestMapping(value="addClas.html",method= RequestMethod.GET)
    public String add(@ModelAttribute("classes") Classes classes, Model model){
        logger.info("进入班级添加页面");
        return "classes-add";
    }

    /**
     * 添加班级
     * @param classes
     * @param model
     * @return
     */
    @RequestMapping("/addClas")
    public String addUser(Classes classes,Model model){
        logger.info("班级添加");
        int result=classesService.insertCla(classes);
        if(result>=1){
            model.addAttribute("mess", "添加成功");
        }else{
            model.addAttribute("mess", "添加失败");
        }
        return "classes-list";

    }

    /**
     * 进入班级修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("updateClas.html")
    public String updateUser(@RequestParam int id, Model model){
        Classes classes=new Classes();
        classes=classesService.selectClaById(id);
        model.addAttribute("classes", classes);
        return "classes-update";
    }

    /**
     * 修改班级信息
     * @param classes
     * @param session
     * @return
     */
    @RequestMapping(value="updateClas",method=RequestMethod.POST)
    public String modifyUserSave(Classes classes, HttpSession session, Model model){
        int result=classesService.updateCla(classes);
        logger.info("修改班级"+result);
        if(result>=1){
            model.addAttribute("mess", "修改成功");
        }else{
            model.addAttribute("mess", "修改失败");
        }
        return "classes-list";

    }

    /**
     * 查询班级
     * @param model
     * @return
     */
    @RequestMapping(value="claslist.html",method = RequestMethod.GET)
    public String getUserList(Model model){
        logger.info("查询班级======");
        int result=classesService.selectClaCount();
        model.addAttribute("clasNum", result);
        List<Classes> clasList=new ArrayList<Classes>();
        clasList=classesService.selectAll();
        model.addAttribute("clasList", clasList);
        model.addAttribute("clasNum",result);
        return "classes-list";
    }

    /**
     * 删除班级信息
     * @param session
     * @return
     */
    @RequestMapping(value="removeClas",method=RequestMethod.POST)
    @ResponseBody
    public String modifyClas(HttpSession session, Model model,Integer id){
        logger.info("删除的id"+id);
        Classes classes1=new Classes();
        classes1.setClasId(id);
        classes1.setUps(0);
        logger.info(classes1.toString());
        int result=classesService.updateCla(classes1);
        logger.info("修改班级"+result);
        if(result>=1){
            return "删除成功";
        }else{
            return "删除失败";
        }

    }


}
