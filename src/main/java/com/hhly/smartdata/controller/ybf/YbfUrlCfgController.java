package com.hhly.smartdata.controller.ybf;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.hhly.smartdata.model.ybf.DimYbfUrlCfg;
import com.hhly.smartdata.service.ybf.DimYbfUrlCfgService;
import com.hhly.smartdata.util.page.Page;
import com.hhly.smartdata.util.page.PageUtil;

@Controller
@RequestMapping(value="/ybfurlcfg")
public class YbfUrlCfgController {
	@Autowired 
	DimYbfUrlCfgService dimYbfUrlCfgService; 
	
	@RequestMapping(value="/list")
	@RequiresPermissions(value = "yfburlcfg")
	public ModelAndView list(@ModelAttribute DimYbfUrlCfg condition,@ModelAttribute Page page){
		 PageUtil.startPage(page);
	     Map<String,Object> model = Maps.newHashMap();
	     model.put("condition",condition);
	     model.put("dimYbfUrlCfgList", this.dimYbfUrlCfgService.search(condition, page));
		 return new ModelAndView("operative/ybf/urlconfig/list.main",model);
	}
	
	@RequestMapping(value="/toAdd")
	@RequiresPermissions(value = "yfburlcfg")
	public ModelAndView toAdd(){
		 return new ModelAndView("operative/ybf/urlconfig/add.main");
	}
	
	@RequestMapping(value="/insert")
	@RequiresPermissions(value = "yfburlcfg")
	public String insert(DimYbfUrlCfg value){
		if(null != value){
			value.setCreatedUser(SecurityUtils.getSubject().getPrincipal().toString());
			if(this.dimYbfUrlCfgService.insert(value)){
				return "redirect:list.do";
			}
		}
		return "redirect:list.do";
	}
	
	@RequestMapping(value="/deleteById")
	@RequiresPermissions(value = "yfburlcfg")
	@ResponseBody
	public String deleteById(String tblId){
		if(this.dimYbfUrlCfgService.deleteById(tblId)){
			return "Y";
		}
		return "N";
	}
	
	@RequestMapping(value="/toUpdate")
	@RequiresPermissions(value = "yfburlcfg")
	public ModelAndView toUpdate(String tblId){
	     Map<String,Object> model = Maps.newHashMap();
	     DimYbfUrlCfg value =  this.dimYbfUrlCfgService.getById(tblId);
	     model.put("value",value);
		 return new ModelAndView("operative/ybf/urlconfig/edit.main",model);
	}
	
	/**
	 * 更新
	 * @param value
	 * @return
	 */
	@RequestMapping(value="/update")
	@RequiresPermissions(value = "yfburlcfg")
	public String update(DimYbfUrlCfg value){
		if(null != value){
			value.setUpdatedUser(SecurityUtils.getSubject().getPrincipal().toString());
			if(this.dimYbfUrlCfgService.update(value)){
				return "redirect:list.do";
			}
		}
		return "redirect:list.do";
	}
	
	@RequestMapping(value="/validateUrlId")
	@RequiresPermissions(value = "yfburlcfg")
	@ResponseBody
	public boolean validateUrlId(String urlId,String tblId){
		DimYbfUrlCfg value = this.dimYbfUrlCfgService.getByUrlId(urlId);
		if(null != value){
			if(!StringUtils.isEmpty(tblId)){
				//tblId不为空，表示修改时校验，tbl不相同，表示校验失败
				if(!tblId.equalsIgnoreCase(value.getTblId())){
					return false;
				}
			}else{
				//tblId为空，表示新增时校验，urlId相同则校验错误
				return false;
			}
		}
		return true;
	}
	
	@RequestMapping(value="/isExist")
	@RequiresPermissions(value = "yfburlcfg")
	@ResponseBody
	public boolean isExist(String tblId){
		if(null != this.dimYbfUrlCfgService.getById(tblId)){
			return true;
		}
		return false;
	}
	
}
