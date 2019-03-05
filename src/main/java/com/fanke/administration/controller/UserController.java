package com.fanke.administration.controller;

import com.fanke.administration.pojo.TimeTable;
import com.fanke.administration.pojo.User;
import com.fanke.administration.service.TimeTableService;
import com.fanke.administration.service.UserService;
import com.fanke.administration.util.DateTools;
import com.fanke.administration.util.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("timeTableServiceImpl")
    private TimeTableService timeTableService;
    Logger logger = LoggerFactory.getLogger(getClass());

    // 实现跳转到登录页
    @RequestMapping(value = "login.html")
    public String login() {
        logger.info("用户跳转到登录页====================>");
        return "login";
    }

    /**
     * 页面跳转
     * @param page
     * @return
     */
    @RequestMapping("{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

    // 主页面
    @RequestMapping(value = "index.html")
    public String hetmain() {
        logger.info("用户跳转到主页面====================>");
        return "index";
    }

    // 实现登录
    @RequestMapping(value = "dologin.html", method = RequestMethod.POST)
    public String doLogin(String userName, String userPwd, HttpSession session,
                          HttpServletRequest request, RedirectAttributes attr, Model model) throws Exception {
        logger.info("登录中======================>");
        User user=new User();
        user=userService.login(userName,userPwd);
        if (null != user) {//登陆成功
            session.setAttribute("userId",user.getUserId());
            attr.addFlashAttribute("u1", user);
            model.addAttribute("user", user);
            List<TimeTable> timeTableList=timeTableService.selectTable(0,50,null,null,null, DateTools.getWeekDate().get("mondayDate"), DateTools.getWeekDate().get("sundayDate"));
            session.setAttribute("timeTableList",timeTableList);
            return "index";
        } else {
            request.setAttribute("error", "密码错误了哦");
            return "login";
        }

    }

    /**
     * 进入添加用户页面
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value="addUser.html",method=RequestMethod.GET)
    public String add(@ModelAttribute("user") User user, Model model){
        logger.info("进入用户添加页面");
        return "admin-add";
    }

    /**
     * 添加用户
     *
     *
     */
    @RequestMapping("/addUser")
    public String addUser(User user,Model model){
        logger.info("用户添加");
        String pwd=user.getUserPwd();
        user.setUserPwd(MD5Utils.MD5Encode(pwd,"utf-8"));
        user.setUserTime(new Date());
        int result=userService.insertUser(user);
        if(result>=1){
            model.addAttribute("mess", "添加成功");
            return "admin-add";
        }else{
            model.addAttribute("mess", "添加失败");
            return "admin-add";
        }

    }

    /**
     * 进入修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("updateUser.html")
    public String updateUser(@RequestParam int id,Model model){
        User user=new User();
        user=userService.selectUserById(id);
        model.addAttribute("user", user);
        return "admin-update";
    }

    /**
     * 修改用户
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value="updateUser",method=RequestMethod.POST)
    public String modifyUserSave(User user, HttpSession session,Model model){

        int result=userService.updateUserPwd(user);
        logger.info("修改用户"+result);
        if(result>=1){
            model.addAttribute("mess", "修改成功");
            return "admin-update";
        }else{
            model.addAttribute("mess", "修改失败");
            return "admin-update";
        }

    }

    /**
     * 查询用户
     * @param model
     * @return
     */
    @RequestMapping(value="userlist.html",method = RequestMethod.GET)
    public String getUserList(String userName,Model model){
        logger.info("查询用户======");
        int result=userService.selectUsersCount();
        model.addAttribute("userNum", result);
        List<User> userList=new ArrayList<User>();
        userList=userService.selectUserByFenye(0,5,userName);
        model.addAttribute("userList", userList);
        model.addAttribute("userNum",result);
        return "admin-list";
    }

    /**
     * 查询用户名称是否重复
     * @return
     */
    @ResponseBody
    @RequestMapping(value="getuserName.html",method = RequestMethod.GET)
    public String getUserName(String userName){
        int result=userService.selectUserCount(userName);
        if(result>=1){
            return "nomiss";
        }else{
            return "miss";
        }
    }

    /**
     * 查看用户
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("showUser.html")
    public String showUser(@RequestParam int id,Model model){
        User user=new User();
        user=userService.selectUserById(id);
        model.addAttribute("user", user);
        return "member-show";
    }



    //创建表头
    private void createTitle(HSSFWorkbook workbook,HSSFSheet sheet){
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(1,12*256);
        sheet.setColumnWidth(3,17*256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("班级");
        cell.setCellStyle(style);


        cell = row.createCell(1);
        cell.setCellValue("课程名称");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("教员");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("地点");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("时间");
        cell.setCellStyle(style);
    }

    //生成user表excel
    @RequestMapping("/getUser.html")
    public String getUser(HttpServletResponse response) throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("统计表");
        createTitle(workbook,sheet);
        List<TimeTable> rows=timeTableService.selectTable(0,50,null,null,null, DateTools.getWeekDate().get("mondayDate"), DateTools.getWeekDate().get("sundayDate"));

        //设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        //新增数据行，并且设置单元格数据
        int rowNum=1;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        for(TimeTable timeTable:rows){
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(timeTable.getClasses().getClasName());
            row.createCell(1).setCellValue(timeTable.getCurriculum().getCurName()+"("+timeTable.getMorning()+")");
            row.createCell(2).setCellValue(timeTable.getTeacher().getTeaName());
            row.createCell(3).setCellValue(timeTable.getClassroom().getRoomName());
            row.createCell(4).setCellValue(simpleDateFormat.format(timeTable.getTabTime())+"  "+DateTools.dateToWeek(simpleDateFormat.format(timeTable.getTabTime())));
            //HSSFCell cell = row.createCell(3);
            //cell.setCellValue(DateTools.dateToWeek(timeTable.getTabTime().toString()));
            //cell.setCellStyle(style);
            rowNum++;
        }

        String fileName = "导出excel例子.xls";

        //生成excel文件
        buildExcelFile(fileName, workbook);

        //浏览器下载excel
        buildExcelDocument(fileName,workbook,response);

        return "download excel";
    }

    //生成excel文件
    protected void buildExcelFile(String filename,HSSFWorkbook workbook) throws Exception{
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    //浏览器下载excel
    protected void buildExcelDocument(String filename,HSSFWorkbook workbook,HttpServletResponse response) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }



}
