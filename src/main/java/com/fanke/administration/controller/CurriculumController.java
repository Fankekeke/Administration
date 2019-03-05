package com.fanke.administration.controller;

import com.fanke.administration.pojo.Curriculum;
import com.fanke.administration.service.CurriculumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class CurriculumController {

    @Autowired
    @Qualifier("curriculumServiceImpl")
    private CurriculumService curriculumService;
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 进入添加课程页面
     * @param curriculum
     * @param model
     * @return
     */
    @RequestMapping(value="addCur.html",method= RequestMethod.GET)
    public String add(@ModelAttribute("curriculum")Curriculum curriculum, Model model){
        logger.info("进入课程添加页面");
        return "curr-add";
    }

    /**
     * 添加课程
     * @param curriculum
     * @param model
     * @return
     */
    @RequestMapping("/addCur")
    public String addUser(Curriculum curriculum,Model model){
        logger.info("课程添加");
        int result=curriculumService.insertCur(curriculum);
        if(result>=1){
            model.addAttribute("mess", "添加成功");
        }else{
            model.addAttribute("mess", "添加失败");
        }
        return "curr-list";

    }

    /**
     * 进入课程修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("updateCur.html")
    public String updateUser(@RequestParam int id, Model model){
        Curriculum curriculum=new Curriculum();
        curriculum=curriculumService.selectCurById(id);
        model.addAttribute("curriculum", curriculum);
        return "curr-update";
    }

    /**
     * 修改课程信息
     * @param curriculum
     * @param session
     * @return
     */
    @RequestMapping(value="updateCur",method=RequestMethod.POST)
    public String modifyUserSave(Curriculum curriculum, HttpSession session, Model model){
        int result=curriculumService.updateCur(curriculum);
        logger.info("修改课程"+result+"----");
        if(result>=1){
            model.addAttribute("mess", "修改成功");
        }else{
            model.addAttribute("mess", "修改失败");
        }
        return "curr-list";

    }

    /**
     * 查询课程
     * @param model
     * @return
     */
    @RequestMapping(value="curlist.html",method = RequestMethod.GET)
    public String getUserList(Model model){
        logger.info("查询课程======");
        int result=curriculumService.selectCurCount();
        List<Curriculum> currList=new ArrayList<Curriculum>();
        currList=curriculumService.selectAll();
        model.addAttribute("currList", currList);
        model.addAttribute("currNum",result);
        return "curr-list";
    }

    /**
     * 删除课程信息
     * @param session
     * @return
     */
    @RequestMapping(value="removeCur",method=RequestMethod.POST)
    @ResponseBody
    public String modifyClas(HttpSession session, Model model,Integer id){
        logger.info("删除的id"+id);
        Curriculum curriculum=new Curriculum();
        curriculum.setCurId(id);
        curriculum.setUps(0);
        int result=curriculumService.updateCur(curriculum);
        logger.info("修改课程"+result);
        if(result>=1){
            return "删除成功";
        }else{
            return "删除失败";
        }

    }
}
