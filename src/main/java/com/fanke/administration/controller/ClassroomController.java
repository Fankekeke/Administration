package com.fanke.administration.controller;

import com.alibaba.fastjson.JSON;
import com.fanke.administration.pojo.Classroom;
import com.fanke.administration.pojo.Teacher;
import com.fanke.administration.service.ClassroomService;
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
public class ClassroomController {
    @Autowired
    @Qualifier("classroomServiceImpl")
    private ClassroomService classroomService;
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 进入添加教室页面
     *
     * @param classroom
     * @param model
     * @return
     */
    @RequestMapping(value = "addRoom.html", method = RequestMethod.GET)
    public String add(@ModelAttribute("classroom") Classroom classroom, Model model) {
        logger.info("进入教室添加页面");
        return "classroom-add";
    }

    /**
     * 添加教室
     *
     * @param classroom
     * @param model
     * @return
     */
    @RequestMapping("/addRoom")
    public String addUser(Classroom classroom, Model model) {
        logger.info("教室添加");
        int result = classroomService.insertRoom(classroom);
        if (result >= 1) {
            model.addAttribute("mess", "添加成功");
        } else {
            model.addAttribute("mess", "添加失败");
        }
        return "classroom-list";

    }

    /**
     * 进入教室修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("updateRoom.html")
    public String updateUser(@RequestParam int id, Model model) {
        Classroom classroom = new Classroom();
        classroom = classroomService.selectRoomById(id);
        model.addAttribute("classroom", classroom);
        return "classroom-update";
    }

    /**
     * 修改教室信息
     *
     * @param classroom
     * @param session
     * @return
     */
    @RequestMapping(value = "updateRoom", method = RequestMethod.POST)
    public String modifyUserSave(Classroom classroom, HttpSession session, Model model) {
        int result = classroomService.updateRoom(classroom);
        logger.info("修改教室" + result + "----" + classroom.getProjection());
        if (result >= 1) {
            model.addAttribute("mess", "修改成功");
        } else {
            model.addAttribute("mess", "修改失败");
        }
        return "classroom-list";

    }

    /**
     * 查询教室
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "roomlist.html", method = RequestMethod.GET)
    public String getUserList(Model model) {
        logger.info("查询教室======");
        int result = classroomService.selectRoomCount();
        List<Classroom> roomList = new ArrayList<Classroom>();
        roomList = classroomService.selectAll();
        model.addAttribute("roomList", roomList);
        model.addAttribute("roomNum", result);
        return "classroom-list";
    }

    /**
     * 查询教室
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "getroomlist.html", method = RequestMethod.GET)
    @ResponseBody
    public String getroomList(String roomName,Model model) {
        logger.info("查询教员======");
        int result = classroomService.selectRoomCount();
        List<Classroom> teaList = new ArrayList<Classroom>();
        teaList = classroomService.selectRoom(0,50,null,roomName);
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
                "              房间id\n" +
                "             </div>\n" +
                "             <div id=\"1\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$2\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              房间名称\n" +
                "             </div>\n" +
                "             <div id=\"2\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$3\" class=\"mini-grid-headerCell    mini-grid-bottomCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              座位数\n" +
                "             </div>\n" +
                "             <div id=\"3\" class=\"mini-grid-column-splitter\"></div>\n" +
                "            </div></td>\n" +
                "           <td id=\"mini-8$headerCell2$4\" class=\"mini-grid-headerCell    mini-grid-bottomCell  mini-grid-rightCell\" style=\"text-align:center;\">\n" +
                "            <div class=\"mini-grid-headerCell-outer\">\n" +
                "             <div class=\"mini-grid-headerCell-inner  mini-grid-headerCell-nowrap \" title=\"\">\n" +
                "              投影\n" +
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
        for (Classroom classroom : teaList) {
            String touying="";
            if(classroom.getProjection()==0){
                touying="无";
            }else{
                touying="有";
            }
            stringBuffer.append("           <tr class=\"mini-grid-row\" id=\"row"+classroom.getRoomId()+"\" style=\" \" >\n" +
                    "            <td style=\"width:0;\"></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              <div>\n" +
                    "               "+classroom.getRoomId()+"\n" +
                    "              </div>\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"xucc198712@qq.com\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+classroom.getRoomName()+"\n" +
                    "             </div></td>\n" +
                    "            <td class=\"mini-grid-cell \" style=\"text-align:center;\" title=\"\">\n" +
                    "             <div class=\"mini-grid-cell-inner  mini-grid-cell-nowrap \" style=\"\">\n" +
                    "              "+classroom.getSeatNum()+"\n" +
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
     * 删除教室信息
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "removeRoom", method = RequestMethod.POST)
    @ResponseBody
    public String modifyClas(HttpSession session, Model model, Integer id) {
        logger.info("删除的id" + id);
        Classroom classroom = new Classroom();
        classroom.setRoomId(id);
        classroom.setUps(0);
        int result = classroomService.updateRoom(classroom);
        logger.info("修改教室" + result);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }

    }

}
