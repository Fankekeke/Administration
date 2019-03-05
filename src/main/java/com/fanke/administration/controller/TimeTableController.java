package com.fanke.administration.controller;

import com.alibaba.fastjson.JSON;
import com.fanke.administration.pojo.*;
import com.fanke.administration.service.*;
import com.fanke.administration.util.DateTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/")
@Scope("singleton")
public class TimeTableController {


    @Autowired
    @Qualifier("timeTableServiceImpl")
    private TimeTableService timeTableService;

    @Autowired
    @Qualifier("teacherServiceImpl")
    private TeacherService teacherService;

    @Autowired
    @Qualifier("curriculumServiceImpl")
    private CurriculumService curriculumService;

    @Autowired
    @Qualifier("classesServiceImpl")
    private ClassesService classesService;

    @Autowired
    @Qualifier("classroomServiceImpl")
    private ClassroomService classroomService;
    Logger logger = LoggerFactory.getLogger(getClass());

    Classroom classroom=new Classroom();
    Classes classes=new Classes();
    Teacher teacher=new Teacher();
    Curriculum curriculum=new Curriculum();

    /**
     * 进入添加课表页面
     * @param timeTable
     * @param model
     * @return
     */
    @RequestMapping(value="addTable.html",method= RequestMethod.GET)
    public String add(@ModelAttribute("timeTable")TimeTable timeTable, Model model){
        logger.info("进入教员添加页面");
//        List<Teacher> teacherList=teacherService.selectTeacher(0,50,"");
//        List<Classes> classesList=classesService.selectCla(0,50,"","");
//        model.addAttribute("teacherList",teacherList);
//        model.addAttribute("classesList",classesList);
        return "timetable-add";
    }

    /**
     * 添加课表
     * @param timeTable
     * @param model
     * @return
     */
    @RequestMapping("/addTable")
    public String addUser(TimeTable timeTable,Model model){
        logger.info("教员添加");
        int result=timeTableService.insertTable(timeTable);
        if(result>=1){
            model.addAttribute("mess", "添加成功");
        }else{
            model.addAttribute("mess", "添加失败");
        }
        return "timetable-list";

    }
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 进入课表修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("updateTable.html")
    public String updateUser(@RequestParam int id, Model model){
        TimeTable timeTable=new TimeTable();
        timeTable=timeTableService.selectTableById(id);
        model.addAttribute("timeTable", timeTable);
        return "timetable-update";
    }

    /**
     * 修改课表信息
     * @param timeTable
     * @param session
     * @return
     */
    @RequestMapping(value="updateTable",method=RequestMethod.POST)
    public String modifyUserSave(TimeTable timeTable, HttpSession session, Model model){
        int result=timeTableService.updateTable(timeTable);
        logger.info("修改课表"+result+"----");
        if(result>=1){
            model.addAttribute("mess", "修改成功");
        }else{
            model.addAttribute("mess", "修改失败");
        }
        return "timetable-list";

    }

    /**
     * 查询课表
     * @param model
     * @return
     */
    @RequestMapping(value="tablelist.html",method = RequestMethod.GET)
    public String getUserList(Model model){
        logger.info("查询课表======");
        int result=timeTableService.selectTableCount();
        List<TimeTable> tableList=new ArrayList<TimeTable>();
        tableList=timeTableService.selectAll();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        for (TimeTable timeTable : tableList) {
            timeTable.setXingqi(DateTools.dateToWeek(simpleDateFormat.format(timeTable.getTabTime())));
            logger.info(timeTable.getXingqi());
        }
        model.addAttribute("tableList", tableList);
        model.addAttribute("tableNum",result);
        return "timetable-list";
    }

    /**
     * 查询教室
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "getclaslist.html", method = RequestMethod.GET)
    @ResponseBody
    public String getClasList(String clasName,Model model) {
        logger.info("查询教员======");
        int result = classesService.selectClaCount();
        List<Classes> teaList = new ArrayList<Classes>();
        teaList = classesService.selectCla(0,50,clasName,"");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<div class=\"mini-panel-border mini-grid-border\" style=\"border-left: 0px; border-right: 0px;\">\n" +
                "     <div class=\"mini-panel-header\" style=\"display: none;\">\n" +
                "      <div class=\"mini-panel-header-inner\">\n" +
                "       <span class=\"mini-panel-icon mini-icon mini-iconfont \" style=\"display: none;\"></span>\n" +
                "       <div class=\"mini-panel-title\">\n" +
                "        &nbsp;\n" +
                "       </div>\n" +
                "       <div class=\"mini-tools\">\n" +
                "        <span id=\"0\" class=\"mini-icon mini-iconfont fa mini-tools-collapse \" style=\";display:none;\"></span>\n" +
                "        <span id=\"1\" class=\"mini-icon mini-iconfont fa mini-tools-close \" style=\";display:none;\"></span>\n" +
                "       </div>\n" +
                "      </div>\n" +
                "     </div>\n" +
                "     <div class=\"mini-panel-viewport mini-grid-viewport\" style=\"height: 263px;\">\n" +
                "      <div class=\"mini-panel-toolbar\" style=\"display: none;\"></div>\n" +
                "      <div class=\"mini-grid-columns\" style=\"display: block;\">\n" +
                "       <div class=\"mini-grid-columns-lock\" style=\"left: -10px; width: 0px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 0px; height: auto;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-topRightCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-columns-view\" style=\"margin-left: 0px; width: auto; padding-right: 17px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"height: auto; width: 100%;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td style=\"width:0;\"></td>\n" +
                "           <td id=\"mini-8$headerCell2$1\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              班级id\n" +
                "             </div>\n" +
                "             <div id=\"1\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$2\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              班级名称\n" +
                "             </div>\n" +
                "             <div id=\"2\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$3\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              班级人数\n" +
                "             </div>\n" +
                "             <div id=\"3\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$4\" class=\"mini-grid-headerCell    mini-grid-bottomCell  mini-grid-rightCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              所属阶段\n" +
                "             </div>\n" +
                "             <div id=\"4\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-topRightCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "      </div>\n" +
                "      <div class=\"mini-grid-filterRow\" style=\"display: none;\">\n" +
                "       <div class=\"mini-grid-filterRow-lock\" style=\"height: 100%; left: -10px; width: 0px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"position:absolute;top:0;left:0;height:100%;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td style=\"width:0;\"></td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-filterRow-view\" style=\"margin-left: 0px; width: auto; padding-right: 0px; height: 0px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"position: absolute; top: 0px; left: 0px; height: 100%; width: 631px;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td style=\"width:0;\"></td>\n" +
                "           <td id=\"mini-8$filter$1\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "           <td id=\"mini-8$filter$2\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "           <td id=\"mini-8$filter$3\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "           <td id=\"mini-8$filter$4\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "      </div>\n" +
                "      <div class=\"mini-panel-body mini-grid-rows\" style=\"height: 200px;\">\n" +
                "       <div class=\"mini-grid-rows-lock\" style=\"left: -10px; width: 0px; height: 100%;\">\n" +
                "        <div class=\"mini-grid-rows-content\">\n" +
                "         <table class=\"mini-grid-table mini-grid-rowstable\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 0px;\">\n" +
                "          <tbody>\n" +
                "           <tr style=\"height:1px;\">\n" +
                "            <td style=\"height:0px;width:0;\"></td>\n" +
                "            <td style=\"width:0px;\"></td>\n" +
                "           </tr>\n" +
                "          </tbody>\n" +
                "         </table>\n" +
                "        </div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-rows-view\" style=\"margin-left: 0px; width: 100%;\">\n" +
                "        <div class=\"mini-grid-rows-content\">\n" +
                "         <table class=\"mini-grid-table mini-grid-rowstable\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 100%;\">\n" +
                "          <tbody>\n" +
                "           <tr style=\"height:0px;\">\n" +
                "            <td style=\"height:0px;width:0;\"></td>\n" +
                "            <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td style=\"width:0px;\"></td>\n" +
                "           </tr>\n" +
                "           <tr id=\"mini-8$emptytext2\" style=\"display:none;\">\n" +
                "            <td style=\"width:0\"></td>\n" +
                "            <td class=\"mini-grid-emptyText\" colspan=\"4\">没有返回的数据</td>\n" +
                "           </tr>\n" +
                "           \n");
        for (Classes classes : teaList) {
            stringBuffer.append("           <tr class=\"mini-grid-row\" id=\"row"+classes.getClasId()+"\" style=\" \" >\n" +
                    "            <td style=\"width:0;\"></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              <div>\n" +
                    "               "+classes.getClasId()+"\n" +
                    "              </div>\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"xucc198712@qq.com\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+classes.getClasName()+"\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+classes.getClasNum()+"\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell  mini-grid-rightCell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+classes.getStage()+"\n" +
                    "             </div></td>\n" +
                    "           </tr>\n" +
                    "       \n");
        }
        stringBuffer.append(
                "          </tbody>\n" +
                        "         </table>\n" +
                        "        </div>\n" +
                        "       </div>\n" +
                        "       <div class=\"mini-grid-vscroll\" style=\"display: none;\">\n" +
                        "        <div class=\"mini-grid-vscroll-content\"></div>\n" +
                        "       </div>\n" +
                        "      </div>\n" +
                        "      <div class=\"mini-grid-summaryRow\" style=\"display: none;\">\n" +
                        "       <div class=\"mini-grid-summaryRow-lock\" style=\"left: -10px; width: 0px; height: auto;\">\n" +
                        "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n" +
                        "         <tbody>\n" +
                        "          <tr style=\"height:0px;\">\n" +
                        "           <td style=\"height:0px;width:0;\"></td>\n" +
                        "           <td style=\"width:0px;\"></td>\n" +
                        "          </tr>\n" +
                        "          <tr>\n" +
                        "           <td style=\"width:0;\"></td>\n" +
                        "          </tr>\n" +
                        "         </tbody>\n" +
                        "        </table>\n" +
                        "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                        "       </div>\n" +
                        "       <div class=\"mini-grid-summaryRow-view\" style=\"margin-left: 0px; width: auto; height: auto; padding-right: 17px;\">\n" +
                        "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 100%;\">\n" +
                        "         <tbody>\n" +
                        "          <tr style=\"height:0px;\">\n" +
                        "           <td style=\"height:0px;width:0;\"></td>\n" +
                        "           <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td style=\"width:0px;\"></td>\n" +
                        "          </tr>\n" +
                        "          <tr>\n" +
                        "           <td style=\"width:0;\"></td>\n" +
                        "           <td id=\"mini-8$summary$1_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "           <td id=\"mini-8$summary$2_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "           <td id=\"mini-8$summary$3_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "           <td id=\"mini-8$summary$4_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "          </tr>\n" +
                        "         </tbody>\n" +
                        "        </table>\n" +
                        "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                        "       </div>\n" +
                        "       <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                        "      </div>\n" +
                        "      </div>\n" +
                        "      <div class=\"mini-panel-footer\" style=\"display: none;\"></div>\n" +
                        "      <div class=\"mini-resizer-trigger\" style=\"\"></div>\n" +
                        "     </div>\n" +
                        "     <a href=\"#\" class=\"mini-grid-focus\" style=\"position: absolute; left: 423px; top: 84px; width: 0px; height: 0px; outline: none;\" hidefocus=\"\" onclick=\"return false\"></a>\n" +
                        "    </div>");

        return stringBuffer.toString();
    }



    /**
     * 查询教室
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "gettealist.html", method = RequestMethod.GET)
    @ResponseBody
    public String getTeaList(String teaName,Model model) {
        logger.info("查询教员======");
        int result = teacherService.selectTeaCount();
        List<Teacher> teaList = new ArrayList<Teacher>();
        teaList = teacherService.selectTeacher(0,50,teaName);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<div class=\"mini-panel-border mini-grid-border\" style=\"border-left: 0px; border-right: 0px;\">\n" +
                "     <div class=\"mini-panel-header\" style=\"display: none;\">\n" +
                "      <div class=\"mini-panel-header-inner\">\n" +
                "       <span class=\"mini-panel-icon mini-icon mini-iconfont \" style=\"display: none;\"></span>\n" +
                "       <div class=\"mini-panel-title\">\n" +
                "        &nbsp;\n" +
                "       </div>\n" +
                "       <div class=\"mini-tools\">\n" +
                "        <span id=\"0\" class=\"mini-icon mini-iconfont fa mini-tools-collapse \" style=\";display:none;\"></span>\n" +
                "        <span id=\"1\" class=\"mini-icon mini-iconfont fa mini-tools-close \" style=\";display:none;\"></span>\n" +
                "       </div>\n" +
                "      </div>\n" +
                "     </div>\n" +
                "     <div class=\"mini-panel-viewport mini-grid-viewport\" style=\"height: 263px;\">\n" +
                "      <div class=\"mini-panel-toolbar\" style=\"display: none;\"></div>\n" +
                "      <div class=\"mini-grid-columns\" style=\"display: block;\">\n" +
                "       <div class=\"mini-grid-columns-lock\" style=\"left: -10px; width: 0px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 0px; height: auto;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-topRightCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-columns-view\" style=\"margin-left: 0px; width: auto; padding-right: 17px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"height: auto; width: 100%;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td style=\"width:0;\"></td>\n" +
                "           <td id=\"mini-8$headerCell2$1\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              教员id\n" +
                "             </div>\n" +
                "             <div id=\"1\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$2\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              教员姓名\n" +
                "             </div>\n" +
                "             <div id=\"2\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$3\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              邮箱地址\n" +
                "             </div>\n" +
                "             <div id=\"3\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$4\" class=\"mini-grid-headerCell    mini-grid-bottomCell  mini-grid-rightCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              工作状态\n" +
                "             </div>\n" +
                "             <div id=\"4\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-topRightCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "      </div>\n" +
                "      <div class=\"mini-grid-filterRow\" style=\"display: none;\">\n" +
                "       <div class=\"mini-grid-filterRow-lock\" style=\"height: 100%; left: -10px; width: 0px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"position:absolute;top:0;left:0;height:100%;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td style=\"width:0;\"></td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-filterRow-view\" style=\"margin-left: 0px; width: auto; padding-right: 0px; height: 0px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"position: absolute; top: 0px; left: 0px; height: 100%; width: 631px;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td style=\"width:0;\"></td>\n" +
                "           <td id=\"mini-8$filter$1\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "           <td id=\"mini-8$filter$2\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "           <td id=\"mini-8$filter$3\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "           <td id=\"mini-8$filter$4\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "      </div>\n" +
                "      <div class=\"mini-panel-body mini-grid-rows\" style=\"height: 200px;\">\n" +
                "       <div class=\"mini-grid-rows-lock\" style=\"left: -10px; width: 0px; height: 100%;\">\n" +
                "        <div class=\"mini-grid-rows-content\">\n" +
                "         <table class=\"mini-grid-table mini-grid-rowstable\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 0px;\">\n" +
                "          <tbody>\n" +
                "           <tr style=\"height:1px;\">\n" +
                "            <td style=\"height:0px;width:0;\"></td>\n" +
                "            <td style=\"width:0px;\"></td>\n" +
                "           </tr>\n" +
                "          </tbody>\n" +
                "         </table>\n" +
                "        </div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-rows-view\" style=\"margin-left: 0px; width: 100%;\">\n" +
                "        <div class=\"mini-grid-rows-content\">\n" +
                "         <table class=\"mini-grid-table mini-grid-rowstable\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 100%;\">\n" +
                "          <tbody>\n" +
                "           <tr style=\"height:0px;\">\n" +
                "            <td style=\"height:0px;width:0;\"></td>\n" +
                "            <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td style=\"width:0px;\"></td>\n" +
                "           </tr>\n" +
                "           <tr id=\"mini-8$emptytext2\" style=\"display:none;\">\n" +
                "            <td style=\"width:0\"></td>\n" +
                "            <td class=\"mini-grid-emptyText\" colspan=\"4\">没有返回的数据</td>\n" +
                "           </tr>\n" +
                "           \n");
        for (Teacher teacher : teaList) {
            String touying="";
            if(teacher.getUps()==0){
                touying="已离职";
            }else{
                touying="正在工作";
            }
            stringBuffer.append("           <tr class=\"mini-grid-row\" id=\"row"+teacher.getTeaId()+"\" style=\" \" >\n" +
                    "            <td style=\"width:0;\"></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              <div>\n" +
                    "               "+teacher.getTeaId()+"\n" +
                    "              </div>\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"xucc198712@qq.com\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+teacher.getTeaName()+"\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+teacher.getTeaMail()+"\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell  mini-grid-rightCell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+touying+"\n" +
                    "             </div></td>\n" +
                    "           </tr>\n" +
                    "       \n");
        }
        stringBuffer.append(
                "          </tbody>\n" +
                        "         </table>\n" +
                        "        </div>\n" +
                        "       </div>\n" +
                        "       <div class=\"mini-grid-vscroll\" style=\"display: none;\">\n" +
                        "        <div class=\"mini-grid-vscroll-content\"></div>\n" +
                        "       </div>\n" +
                        "      </div>\n" +
                        "      <div class=\"mini-grid-summaryRow\" style=\"display: none;\">\n" +
                        "       <div class=\"mini-grid-summaryRow-lock\" style=\"left: -10px; width: 0px; height: auto;\">\n" +
                        "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n" +
                        "         <tbody>\n" +
                        "          <tr style=\"height:0px;\">\n" +
                        "           <td style=\"height:0px;width:0;\"></td>\n" +
                        "           <td style=\"width:0px;\"></td>\n" +
                        "          </tr>\n" +
                        "          <tr>\n" +
                        "           <td style=\"width:0;\"></td>\n" +
                        "          </tr>\n" +
                        "         </tbody>\n" +
                        "        </table>\n" +
                        "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                        "       </div>\n" +
                        "       <div class=\"mini-grid-summaryRow-view\" style=\"margin-left: 0px; width: auto; height: auto; padding-right: 17px;\">\n" +
                        "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 100%;\">\n" +
                        "         <tbody>\n" +
                        "          <tr style=\"height:0px;\">\n" +
                        "           <td style=\"height:0px;width:0;\"></td>\n" +
                        "           <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td style=\"width:0px;\"></td>\n" +
                        "          </tr>\n" +
                        "          <tr>\n" +
                        "           <td style=\"width:0;\"></td>\n" +
                        "           <td id=\"mini-8$summary$1_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "           <td id=\"mini-8$summary$2_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "           <td id=\"mini-8$summary$3_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "           <td id=\"mini-8$summary$4_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "          </tr>\n" +
                        "         </tbody>\n" +
                        "        </table>\n" +
                        "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                        "       </div>\n" +
                        "       <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                        "      </div>\n" +
                        "      </div>\n" +
                        "      <div class=\"mini-panel-footer\" style=\"display: none;\"></div>\n" +
                        "      <div class=\"mini-resizer-trigger\" style=\"\"></div>\n" +
                        "     </div>\n" +
                        "     <a href=\"#\" class=\"mini-grid-focus\" style=\"position: absolute; left: 423px; top: 84px; width: 0px; height: 0px; outline: none;\" hidefocus=\"\" onclick=\"return false\"></a>\n" +
                        "    </div>");

        return stringBuffer.toString();
    }


    /**
     * 查询教室
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "getcurlist.html", method = RequestMethod.GET)
    @ResponseBody
    public String getCurList(String curName,String stage, Model model) {
        logger.info("查询教员======");
        int result = curriculumService.selectCurCount();
        List<Curriculum> teaList = new ArrayList<Curriculum>();
        teaList = curriculumService.selectCur(0,100,stage,curName);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<div class=\"mini-panel-border mini-grid-border\" style=\"border-left: 0px; border-right: 0px;\">\n" +
                "     <div class=\"mini-panel-header\" style=\"display: none;\">\n" +
                "      <div class=\"mini-panel-header-inner\">\n" +
                "       <span class=\"mini-panel-icon mini-icon mini-iconfont \" style=\"display: none;\"></span>\n" +
                "       <div class=\"mini-panel-title\">\n" +
                "        &nbsp;\n" +
                "       </div>\n" +
                "       <div class=\"mini-tools\">\n" +
                "        <span id=\"0\" class=\"mini-icon mini-iconfont fa mini-tools-collapse \" style=\";display:none;\"></span>\n" +
                "        <span id=\"1\" class=\"mini-icon mini-iconfont fa mini-tools-close \" style=\";display:none;\"></span>\n" +
                "       </div>\n" +
                "      </div>\n" +
                "     </div>\n" +
                "     <div class=\"mini-panel-viewport mini-grid-viewport\" style=\"height: 263px;\">\n" +
                "      <div class=\"mini-panel-toolbar\" style=\"display: none;\"></div>\n" +
                "      <div class=\"mini-grid-columns\" style=\"display: block;\">\n" +
                "       <div class=\"mini-grid-columns-lock\" style=\"left: -10px; width: 0px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 0px; height: auto;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-topRightCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-columns-view\" style=\"margin-left: 0px; width: auto; padding-right: 17px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"height: auto; width: 100%;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td style=\"width:0;\"></td>\n" +
                "           <td id=\"mini-8$headerCell2$1\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              课程ID\n" +
                "             </div>\n" +
                "             <div id=\"1\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$1\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              课程名称\n" +
                "             </div>\n" +
                "             <div id=\"1\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$2\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "             阶段\n" +
                "             </div>\n" +
                "             <div id=\"2\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$3\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              章节名称\n" +
                "             </div>\n" +
                "             <div id=\"3\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$4\" class=\"mini-grid-headerCell    mini-grid-bottomCell  mini-grid-rightCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              学时\n" +
                "             </div>\n" +
                "             <div id=\"4\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-topRightCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "      </div>\n" +
                "      <div class=\"mini-grid-filterRow\" style=\"display: none;\">\n" +
                "       <div class=\"mini-grid-filterRow-lock\" style=\"height: 100%; left: -10px; width: 0px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"position:absolute;top:0;left:0;height:100%;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td style=\"width:0;\"></td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-filterRow-view\" style=\"margin-left: 0px; width: auto; padding-right: 0px; height: 0px;\">\n" +
                "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"position: absolute; top: 0px; left: 0px; height: 100%; width: 631px;\">\n" +
                "         <tbody>\n" +
                "          <tr style=\"height:0px;\">\n" +
                "           <td style=\"height:0px;width:0;\"></td>\n" +
                "           <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "           <td style=\"width:0px;\"></td>\n" +
                "          </tr>\n" +
                "          <tr>\n" +
                "           <td style=\"width:0;\"></td>\n" +
                "           <td id=\"mini-8$filter$1\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "           <td id=\"mini-8$filter$2\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "           <td id=\"mini-8$filter$3\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "           <td id=\"mini-8$filter$4\" class=\"mini-grid-filterCell\" style=\"\">&nbsp;</td>\n" +
                "          </tr>\n" +
                "         </tbody>\n" +
                "        </table>\n" +
                "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                "      </div>\n" +
                "      <div class=\"mini-panel-body mini-grid-rows\" style=\"height: 200px;\">\n" +
                "       <div class=\"mini-grid-rows-lock\" style=\"left: -10px; width: 0px; height: 100%;\">\n" +
                "        <div class=\"mini-grid-rows-content\">\n" +
                "         <table class=\"mini-grid-table mini-grid-rowstable\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 0px;\">\n" +
                "          <tbody>\n" +
                "           <tr style=\"height:1px;\">\n" +
                "            <td style=\"height:0px;width:0;\"></td>\n" +
                "            <td style=\"width:0px;\"></td>\n" +
                "           </tr>\n" +
                "          </tbody>\n" +
                "         </table>\n" +
                "        </div>\n" +
                "       </div>\n" +
                "       <div class=\"mini-grid-rows-view\" style=\"margin-left: 0px; width: 100%;\">\n" +
                "        <div class=\"mini-grid-rows-content\">\n" +
                "         <table class=\"mini-grid-table mini-grid-rowstable\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 100%;\">\n" +
                "          <tbody>\n" +
                "           <tr style=\"height:0px;\">\n" +
                "            <td style=\"height:0px;width:0;\"></td>\n" +
                "            <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                "            <td style=\"width:0px;\"></td>\n" +
                "           </tr>\n" +
                "           <tr id=\"mini-8$emptytext2\" style=\"display:none;\">\n" +
                "            <td style=\"width:0\"></td>\n" +
                "            <td class=\"mini-grid-emptyText\" colspan=\"4\">没有返回的数据</td>\n" +
                "           </tr>\n" +
                "           \n");
        for (Curriculum curriculum : teaList) {
            stringBuffer.append("           <tr class=\"mini-grid-row\" id=\"row"+curriculum.getCurId()+"\" style=\" \" >\n" +
                    "            <td style=\"width:0;\"></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              <div>\n" +
                    "               "+curriculum.getCurId()+"\n" +
                    "              </div>\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              <div>\n" +
                    "               "+curriculum.getCurName()+"\n" +
                    "              </div>\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"xucc198712@qq.com\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+curriculum.getStage()+"\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+curriculum.getChapter()+"\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell  mini-grid-rightCell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+curriculum.getCurTime()+"\n" +
                    "             </div></td>\n" +
                    "           </tr>\n" +
                    "       \n");
        }
        stringBuffer.append(
                "          </tbody>\n" +
                        "         </table>\n" +
                        "        </div>\n" +
                        "       </div>\n" +
                        "       <div class=\"mini-grid-vscroll\" style=\"display: none;\">\n" +
                        "        <div class=\"mini-grid-vscroll-content\"></div>\n" +
                        "       </div>\n" +
                        "      </div>\n" +
                        "      <div class=\"mini-grid-summaryRow\" style=\"display: none;\">\n" +
                        "       <div class=\"mini-grid-summaryRow-lock\" style=\"left: -10px; width: 0px; height: auto;\">\n" +
                        "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n" +
                        "         <tbody>\n" +
                        "          <tr style=\"height:0px;\">\n" +
                        "           <td style=\"height:0px;width:0;\"></td>\n" +
                        "           <td style=\"width:0px;\"></td>\n" +
                        "          </tr>\n" +
                        "          <tr>\n" +
                        "           <td style=\"width:0;\"></td>\n" +
                        "          </tr>\n" +
                        "         </tbody>\n" +
                        "        </table>\n" +
                        "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                        "       </div>\n" +
                        "       <div class=\"mini-grid-summaryRow-view\" style=\"margin-left: 0px; width: auto; height: auto; padding-right: 17px;\">\n" +
                        "        <table class=\"mini-grid-table\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width: 100%;\">\n" +
                        "         <tbody>\n" +
                        "          <tr style=\"height:0px;\">\n" +
                        "           <td style=\"height:0px;width:0;\"></td>\n" +
                        "           <td id=\"1\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td id=\"2\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td id=\"3\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td id=\"4\" style=\"padding:0;border:0;margin:0;height:0px;width:20%\"></td>\n" +
                        "           <td style=\"width:0px;\"></td>\n" +
                        "          </tr>\n" +
                        "          <tr>\n" +
                        "           <td style=\"width:0;\"></td>\n" +
                        "           <td id=\"mini-8$summary$1_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "           <td id=\"mini-8$summary$2_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "           <td id=\"mini-8$summary$3_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "           <td id=\"mini-8$summary$4_0\" class=\"mini-grid-summaryCell \" style=\";\">&nbsp;</td>\n" +
                        "          </tr>\n" +
                        "         </tbody>\n" +
                        "        </table>\n" +
                        "        <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                        "       </div>\n" +
                        "       <div class=\"mini-grid-scrollHeaderCell\"></div>\n" +
                        "      </div>\n" +
                        "      </div>\n" +
                        "      <div class=\"mini-panel-footer\" style=\"display: none;\"></div>\n" +
                        "      <div class=\"mini-resizer-trigger\" style=\"\"></div>\n" +
                        "     </div>\n" +
                        "     <a href=\"#\" class=\"mini-grid-focus\" style=\"position: absolute; left: 423px; top: 84px; width: 0px; height: 0px; outline: none;\" hidefocus=\"\" onclick=\"return false\"></a>\n" +
                        "    </div>");

        return stringBuffer.toString();
    }



    @RequestMapping(value="setroom",method=RequestMethod.POST)
    @ResponseBody
    public void setroom(Integer id, HttpSession session, Model model){
        classroom=classroomService.selectRoomById(id);
        logger.info(classroom.toString());
    }

    @RequestMapping(value="getroom",method=RequestMethod.POST)
    @ResponseBody
    public String getroom(HttpSession session, Model model){
        logger.info("获取"+classroom.toString());
        return JSON.toJSONString(classroom);
    }


    @RequestMapping(value="setclas",method=RequestMethod.POST)
    @ResponseBody
    public void setclas(Integer id, HttpSession session, Model model){
        classes=classesService.selectClaById(id);
        logger.info(classes.toString());
    }

    @RequestMapping(value="getclas",method=RequestMethod.POST)
    @ResponseBody
    public String getclas(HttpSession session, Model model){
        return JSON.toJSONString(classes);
    }

    @RequestMapping(value="settea",method=RequestMethod.POST)
    @ResponseBody
    public void settea(Integer id, HttpSession session, Model model){
        teacher=teacherService.selectTeacherById(id);
        logger.info(teacher.toString());
    }

    @RequestMapping(value="gettea",method=RequestMethod.POST)
    @ResponseBody
    public String gettea(HttpSession session, Model model){
        return JSON.toJSONString(teacher);
    }

    @RequestMapping(value="setcur",method=RequestMethod.POST)
    @ResponseBody
    public void setcur(Integer id, HttpSession session, Model model){
        logger.info("课程=============");
        curriculum=curriculumService.selectCurById(id);
        logger.info("课程"+curriculum.toString());
    }

    @RequestMapping(value="getcur",method=RequestMethod.POST)
    @ResponseBody
    public String getcur(HttpSession session, Model model){
        return JSON.toJSONString(curriculum);
    }

}
