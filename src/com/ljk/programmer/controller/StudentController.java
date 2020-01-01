package com.ljk.programmer.controller;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ljk.programmer.entity.Clazz;
import com.ljk.programmer.entity.Grade;
import com.ljk.programmer.page.Page;
import com.ljk.programmer.service.ClazzService;
import com.ljk.programmer.service.GradeService;
import com.ljk.programmer.util.StringUtil;

import net.sf.json.JSONArray;

@RequestMapping("/student")
@Controller
public class StudentController {

	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private ClazzService clazzService;
	
	/***
	 * 用户管理列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
    public ModelAndView list(ModelAndView model)
    {
		List<Grade> gradeList = gradeService.findAll();
		List<Clazz> clazzList = clazzService.findAll();
		model.setViewName("student/student_list");
		model.addObject("gradeList",gradeList);
		model.addObject("gradeListJson",JSONArray.fromObject(gradeList));
		model.addObject("clazzList",clazzList);
		model.addObject("clazzList",JSONArray.fromObject(clazzList));
		return model;
    }
	
	
	
	//获取用户列表
		@RequestMapping(value="/get_list",method=RequestMethod.POST)
		@ResponseBody
		//返回json字符串
		public Map<String,Object> getList(
				@RequestParam(value="name",defaultValue="",required=false)String name,
				@RequestParam(value="gradeId",required=false)Long  gradeId,
				Page page)
		{
			Map<String,Object> map=new HashMap<String,Object>();
			Map<String,Object> querymap=new HashMap<String,Object>();
			querymap.put("name", "%"+name+"%");
			if(gradeId!=null){
				querymap.put("gradeId", gradeId);
			}
			querymap.put("offset", page.getOffset());
			querymap.put("pageSize",page.getRows());
			map.put("rows",clazzService.findList(querymap));
			map.put("total",clazzService.getTotal(querymap));
			return map;
		}
		
	
		//返回json数据
		@RequestMapping(value="/add",method=RequestMethod.POST)
		@ResponseBody
	    public Map<String,String> add(Clazz Grade)
	    {
			Map<String,String> map=new HashMap<String, String>();
			if(Grade.getGradeId()==null){
				map.put("type", "error");
				map.put("msg", "请选择年级");
				return map;
			}
			if(StringUtils.isEmpty(Grade.getName())){
				map.put("type", "error");
				map.put("msg", "年级名称不能为空");
				return map;
			}
			
			if(clazzService.add(Grade)<=0){
				map.put("type", "error");
				map.put("msg", "年级添加失败");
				return map;
			}
			map.put("type", "success");
			map.put("msg", "添加成功");
			return map;
	    }
		
		//修改
		@RequestMapping(value="/edit",method=RequestMethod.POST)
		@ResponseBody
	    public Map<String,String> eidt(Clazz user)
	    {

			Map<String,String> map=new HashMap<String, String>();
			if(user==null){
				map.put("type", "error");
				map.put("msg", "数据绑定出错,请绑定管理员");
				return map;
			}
			if(StringUtils.isEmpty(user.getName())){
				map.put("type", "error");
				map.put("msg", "年级名称不能为空");
				return map;
			}
			if(clazzService.eidt(user)<=0){
				map.put("type", "error");
				map.put("msg", "修改失败");
				return map;
			}
			map.put("type", "success");
			map.put("msg", "添加成功");
			return map;
	    }
		
		
		
		//删除
				@RequestMapping(value="/delete",method=RequestMethod.POST)
				@ResponseBody
			    public Map<String,String> delete(
			    		@RequestParam(value="ids[]")Long[] ids)
			    {
					Map<String,String> map=new HashMap<String, String>();
					if(map==null ||ids.length<=0){
						map.put("type", "clazzServicerror");
						map.put("msg", "请选择要删除的数据");
						return map;
					}
					String joinString = StringUtil.joinString(Arrays.asList(ids), ",");
					//数组转换集合Arrays.asList
					try {
						if(clazzService.delete("("+joinString+")")<=0){
							map.put("type", "error");
							map.put("msg", "删除失败");
							return map;
						}
					} catch (Exception e) {
						map.put("type", "error");
						map.put("msg", "该班级下存在学生信息,不能删除");
						return map;
						
					}
					
					
					map.put("type", "success");
					map.put("msg", "删除成功");
					return map;
			    }
			
		
		
		
		
		
}
