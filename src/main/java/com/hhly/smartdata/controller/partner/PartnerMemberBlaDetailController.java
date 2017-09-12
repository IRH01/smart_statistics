package com.hhly.smartdata.controller.partner;

import com.hhly.smartdata.service.game.operative.ViewOrderDetailService;
import com.hhly.smartdata.service.partner.LmdzBettingLogService;
import com.hhly.smartdata.service.partner.LmdzPresentLogService;
import com.hhly.smartdata.service.partner.LydjBettingLogService;
import com.hhly.smartdata.service.partner.PartnerMemberBlaDetailService;
import com.hhly.smartdata.util.FileUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 合格会员佣金报表
 *
 * @author wanghuang
 */
@Controller
@RequestMapping(value = "/partner/ptnmbbladetail")
public class PartnerMemberBlaDetailController{

    @Autowired
    private PartnerMemberBlaDetailService partnerMemberBlaDetailService;

    @Autowired
    private ViewOrderDetailService viewOrderDetailService;

    @Autowired
    private LmdzPresentLogService lmdzPresentLogService;

    @Autowired
    private LydjBettingLogService lydjBettingLogService;

    @Autowired
    private LmdzBettingLogService lmdzBettingLogService;


    @RequiresPermissions(value = {"partner:checkreport"})
    @RequestMapping(value = "/show")
    public ModelAndView show(ModelMap modelMap, String partnerUserId, String monthId){
        modelMap.put("partnerUserId", partnerUserId);
        modelMap.put("monthId", monthId);
        ModelAndView view = new ModelAndView();
        view.setViewName("operative/partner/ptnmbbladetail/show");
        return view;
    }

    @RequiresPermissions(value = {"partner:checkreport"})
    @RequestMapping(value = "/getPartnerMemberBlaDetails", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getPartnerMemberBlaDetails(HttpServletRequest request){
        Map<String, Object> conMap = new HashMap<String, Object>();
        conMap.put("monthId", request.getParameter("monthId"));
        conMap.put("partnerUserId", request.getParameter("partnerUserId"));
        conMap.put("gameTypeId", request.getParameter("gameTypeId"));
        return JSONArray.fromObject(partnerMemberBlaDetailService.find(conMap)).toString();
    }

    @RequiresPermissions(value = {"partner:checkreport"})
    @RequestMapping(value = "/getGameTypes", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getGameTypes(HttpServletRequest request){
        Map<String, Object> conMap = new HashMap<String, Object>();
        conMap.put("monthId", request.getParameter("monthId"));
        conMap.put("partnerUserId", request.getParameter("partnerUserId"));
        return JSONArray.fromObject(partnerMemberBlaDetailService.findGameType(conMap)).toString();
    }

    @RequiresPermissions(value = {"partner:checkreport"})
    @RequestMapping(value = "/getCommissionInfo", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getCommissionInfo(String monthId, String partnerUserId, int gameTypeId, int rackBackModel){
        return JSONObject.fromObject(partnerMemberBlaDetailService.findOne(monthId, partnerUserId, gameTypeId, rackBackModel)).toString();
    }

    @RequiresPermissions(value = {"partner:checkreport"})
    @RequestMapping(value = "/list", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String list(HttpServletRequest request, int pageNumber, int pageSize){
        String result = "";
        Map<String, Object> conMap = getConditionMap(request);
        return result;
    }

    private Map<String, Object> getConditionMap(HttpServletRequest request){
        Map<String, Object> conMap = new HashMap<String, Object>();
        conMap.put("monthId", request.getParameter("monthId"));
        conMap.put("partnerUserId", request.getParameter("partnerUserId"));
        conMap.put("gameTypeId", request.getParameter("gameTypeId"));
        conMap.put("rackBackModel", request.getParameter("rackBackModel"));
        conMap.put("dateStart", request.getParameter("dateStart"));
        conMap.put("dateEnd", request.getParameter("dateEnd"));
        return conMap;
    }

    @RequiresPermissions(value = {"partner:checkreport"})
    @RequestMapping(value = "/export")
    @ResponseBody
    public Object export(HttpServletRequest request, HttpSession session){
        Map<String, Object> conditionMap = this.getConditionMap(request);
        Map<String, Object> result = new HashMap<String, Object>();
        String filePath = null;
        String uuid = UUID.randomUUID().toString();
        session.setAttribute(uuid, filePath);
        result.put("canExport", true);
        result.put("uuid", uuid);
        return result;
    }

    @RequiresPermissions(value = {"partner:checkreport"})
    @RequestMapping(value = "/download")
    public void download(String uuid, HttpServletResponse response, HttpSession session){
        String filePath = (String) session.getAttribute(uuid);
        FileUtil.downLoadFile(response, new File(filePath), "合作伙伴对账详情报表");
        FileUtil.delete(filePath);
    }
}
