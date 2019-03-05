package com.fanke.administration.controller;

import com.alibaba.fastjson.JSON;
import com.fanke.administration.pojo.Teacher;
import com.fanke.administration.service.TeacherService;
import com.fanke.administration.util.email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class TeacherController {

    @Autowired
    @Qualifier("teacherServiceImpl")
    private TeacherService teacherService;
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 进入添加教员页面
     * @param teacher
     * @param model
     * @return
     */
    @RequestMapping(value="addTea.html",method= RequestMethod.GET)
    public String add(@ModelAttribute("teacher") Teacher teacher, Model model){
        logger.info("进入教员添加页面");
        return "teacher-add";
    }

    /**
     * 添加教员
     * @param teacher
     * @param model
     * @return
     */
    @RequestMapping("/addTea")
    public String addUser(Teacher teacher,Model model){
        logger.info("教员添加");
        int result=teacherService.insertTeacher(teacher);
        if(result>=1){
            model.addAttribute("mess", "添加成功");
        }else{
            model.addAttribute("mess", "添加失败");
        }
        return "teacher-list";

    }

    /**
     * 进入教员修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("updateTea.html")
    public String updateUser(@RequestParam int id, Model model){
        Teacher teacher=new Teacher();
        teacher=teacherService.selectTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "teacher-update";
    }

    /**
     * 修改教员信息
     * @param teacher
     * @param session
     * @return
     */
    @RequestMapping(value="updateTea",method=RequestMethod.POST)
    public String modifyUserSave(Teacher teacher, HttpSession session, Model model){
        int result=teacherService.updateTeacher(teacher);
        logger.info("修改教员"+result+"----");
        if(result>=1){
            model.addAttribute("mess", "修改成功");
        }else{
            model.addAttribute("mess", "修改失败");
        }
        return "teacher-list";

    }

    /**
     * 查询教员
     * @param model
     * @return
     */
    @RequestMapping(value="tealist.html",method = RequestMethod.GET)
    public String getUserList(Model model){
        logger.info("查询教员======");
        int result=teacherService.selectTeaCount();
        List<Teacher> teaList=new ArrayList<Teacher>();
        teaList=teacherService.selectAll();
        model.addAttribute("teaList", teaList);
        model.addAttribute("teaNum",result);
        return "teacher-list";
    }

    /**
     * 删除教员信息
     * @param session
     * @return
     */
    @RequestMapping(value="removeTea",method=RequestMethod.POST)
    @ResponseBody
    public String modifyClas(HttpSession session, Model model,Integer id){
        logger.info("删除的id"+id);
        Teacher teacher=new Teacher();
        teacher.setTeaId(id);
        teacher.setUps(0);
        int result=teacherService.updateTeacher(teacher);
        logger.info("修改教员"+result);
        if(result>=1){
            return "删除成功";
        }else{
            return "删除失败";
        }

    }


    /**
     * 给教员发送邮件
     * @param session
     * @return
     */
    @RequestMapping(value="sendMail",method=RequestMethod.POST)
    @ResponseBody
    public String sendMailToTea(HttpSession session, Model model){
       List<Teacher> teacherList=teacherService.selectTeacher(0,100,"");
       String content="<table class=\"table table-border table-bordered table-bg\">\n" +
               "\t\t<thead>\n" +
               "\t\t\t<tr>\n" +
               "\t\t\t\t<th colspan=\"7\" scope=\"col\">本星期课表  </th>\n" +
               "\t\t\t</tr>\n" +
               "\t\t\t<tr class=\"text-c\">\n" +
               "\t\t\t\t<th>班级</th>\n" +
               "\t\t\t\t<th>地点</th>\n" +
               "\t\t\t\t<th>教员</th>\n" +
               "\t\t\t\t<th>课程</th>\n" +
               "\t\t\t\t<th>上课时间</th>\n" +
               "\t\t\t</tr>\n" +
               "\t\t</thead>\n" +
               "\t\t<tbody>\n" +
               "\t\t\t<tr class=\"text-c\">\n" +
               "\t\t\t\t<td>9班</td>\n" +
               "\t\t\t\t<td>5-3</td>\n" +
               "\t\t\t\t<td>张威龙q</td>\n" +
               "\t\t\t\t<td>SSH</td>\n" +
               "\t\t\t\t<td>2019-02-25</td>\n" +
               "\t\t\t</tr>\n" +
               "\t\t\t<tr class=\"text-c\">\n" +
               "\t\t\t\t<td>10班</td>\n" +
               "\t\t\t\t<td>5-2</td>\n" +
               "\t\t\t\t<td>出久</td>\n" +
               "\t\t\t\t<td>Java基础</td>\n" +
               "\t\t\t\t<td>2019-02-26</td>\n" +
               "\t\t\t</tr>\n" +
               "\t\t\t<tr class=\"text-c\">\n" +
               "\t\t\t\t<td>10班</td>\n" +
               "\t\t\t\t<td>5-4</td>\n" +
               "\t\t\t\t<td>出久</td>\n" +
               "\t\t\t\t<td>SSH</td>\n" +
               "\t\t\t\t<td>2019-02-27</td>\n" +
               "\t\t\t</tr>\n" +
               "\t\t\t<tr class=\"text-c\">\n" +
               "\t\t\t\t<td>10班</td>\n" +
               "\t\t\t\t<td>5-3</td>\n" +
               "\t\t\t\t<td>刘波</td>\n" +
               "\t\t\t\t<td>SSH</td>\n" +
               "\t\t\t\t<td>2019-02-28</td>\n" +
               "\t\t\t</tr>\n" +
               "\t\t\t<tr class=\"text-c\">\n" +
               "\t\t\t\t<td>9班</td>\n" +
               "\t\t\t\t<td>5-3</td>\n" +
               "\t\t\t\t<td>刘波1</td>\n" +
               "\t\t\t\t<td>Java基础</td>\n" +
               "\t\t\t\t<td>2019-03-01</td>\n" +
               "\t\t\t</tr>\n" +
               "\n" +
               "\t\t</tbody>\n" +
               "\t</table>";
        for (Teacher teacher : teacherList) {
            try {
                logger.info("发送邮件给"+teacher.getTeaMail());
                email.sendEmail(teacher.getTeaName(),teacher.getTeaMail(),content);
            } catch (Exception e) {
                e.printStackTrace();
                return "发送失败";
            }
        }
            return "发送成功";
    }




}
