package com.quxin.freshfun.controller.specialtheme;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.quxin.freshfun.model.goods.GoodsPOJO;
import com.quxin.freshfun.model.specialtheme.SpecialThemeGoodsPOJO;
import com.quxin.freshfun.model.specialtheme.SpecialThemePOJO;
import com.quxin.freshfun.service.goods.GoodsService;
import com.quxin.freshfun.service.specialtheme.SpecialThemeGoodsService;
import com.quxin.freshfun.service.specialtheme.SpecialThemeService;
import com.quxin.freshfun.utils.DateUtils;
import com.quxin.freshfun.utils.UploadUtils;

/**
 * 专题Controller
 * @author TuZl
 * @time 2016年8月26日上午10:44:49
 */
@Controller
public class SpecialThemeController {
	
	public static String FOLDER = "/specialTheme";
	private static String IMAGEIP;
	
	@Value("${rip}")
	public void setRip(String value) {
		IMAGEIP = value;
	}
	
	@Autowired
	private SpecialThemeService stService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private SpecialThemeGoodsService stgService;
	
	@RequestMapping("/specialThemeIndex")
	public String index(HttpServletRequest request){
		String curPage = request.getParameter("curPage");
		if(curPage==null){
			curPage="1";
		}
		int pageSize = 5;
		int countPage = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		List<SpecialThemePOJO> stList = stService.findAll(map);
		if(stList!=null&&stList.size()>pageSize){
			if(stList.size()%pageSize==0){
				countPage = stList.size()/pageSize;
			}else{
				countPage = stList.size()/pageSize+1;
			}
		}else{
			countPage=1;
		}
		if(Integer.parseInt(curPage)>countPage){
			curPage=""+countPage;
		}
		int begin = (Integer.parseInt(curPage)-1)*pageSize;
		map.put("begin", begin);
		map.put("pageSize", pageSize);
		stList = stService.findAll(map);
		request.setAttribute("stList", stList);
		request.setAttribute("curPage", curPage);
		request.setAttribute("countPage", ""+countPage);
		request.setAttribute("IMAGEIP", IMAGEIP);
		return FOLDER+"/specialThemeIndex";
	}
	
	@RequestMapping("toEditSpecialTheme")
	public String toEditSpecialTheme(HttpServletRequest request,String stId){
		SpecialThemePOJO st = stService.find(Integer.parseInt(stId));
		request.setAttribute("st", st);
		request.setAttribute("IMAGEIP", IMAGEIP);
		return FOLDER+"/editSpecialTheme";
	}
	
	@RequestMapping("toAddSpecialTheme")
	public String toAddSpecialTheme(HttpServletRequest request){
		request.setAttribute("IMAGEIP", IMAGEIP);
		return FOLDER+"/addSpecialTheme";
	}
	
	@RequestMapping("/saveSpecialTheme")
	public String saveSpecialTheme(HttpServletRequest request,SpecialThemePOJO st){
		String path = "";
		try {
			path = UploadUtils.upload(request);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(path!=null){
			String[] pathArr = path.split(",");
			if(pathArr.length>1){
				st.setThemeImg(pathArr[0]);
				st.setThemeInfoImg(pathArr[1]);
			}else{
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					// 取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if(file.getName().equals("themeImg1") && !file.getOriginalFilename().equals("")){
						st.setThemeImg(pathArr[0]);
					}else if(file.getName().equals("themeInfoImg1") && !file.getOriginalFilename().equals("")){
						st.setThemeInfoImg(pathArr[0]);
					}
				}
			}

		}
		if(st.getId()!=null){
			try {
				st.setGmtModified(DateUtils.stringToLong(DateUtils.getDate(new Date(), "yyyy-MM-dd HH-mm-ss"), "yyyy-MM-dd HH-mm-ss"));
				stService.update(st);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				st.setGmtCreate(DateUtils.stringToLong(DateUtils.getDate(new Date(), "yyyy-MM-dd HH-mm-ss"), "yyyy-MM-dd HH-mm-ss"));
				st.setGmtModified(DateUtils.stringToLong(DateUtils.getDate(new Date(), "yyyy-MM-dd HH-mm-ss"), "yyyy-MM-dd HH-mm-ss"));
				stService.save(st);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "redirect:specialThemeIndex.do";
	}
	
	@RequestMapping("/openOrStopSpecialTheme")
	public String openOrStopSpecialTheme(HttpServletRequest request,HttpServletResponse response){
		String stid = request.getParameter("stid");
		String isdelete = request.getParameter("isdelete");
		SpecialThemePOJO st = new SpecialThemePOJO();
		st.setId(Integer.parseInt(stid));
		byte b = 0;
		if("0".equals(isdelete))
			b=1;
		st.setIsDeleted(b);
		try {
			st.setGmtModified(DateUtils.stringToLong(DateUtils.getDate(new Date(), "yyyy-MM-dd HH-mm-ss"), "yyyy-MM-dd HH-mm-ss"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		stService.update(st);
		String result = "";
		if("0".equals(isdelete))
			result = "专题禁用成功";
		else
			result = "专题启用成功";
		response.setContentType("text/plain;charset=UTF-8"); 
		try { 
		     response.getWriter().write(result); 
		} catch (IOException e) { 
		e.printStackTrace(); 
		} 
		return null;
	}
	
	@RequestMapping("/findSpecialGoods")
	public String findSpecialGoods(HttpServletRequest request){
		String stId = request.getParameter("stid");
		int pageSize = 5;   //页面数据量
		String countPage = request.getParameter("countPage");//商品总页数
		String curPage = request.getParameter("curPage");//商品当前页
		SpecialThemePOJO st = stService.find(Integer.parseInt(stId));
		
		
		if(curPage==null||"".equals(curPage)){
			curPage="1";
		}
		
		//创建map，查询参数
		Map<String,Object> map = new HashMap<>();
		map.put("stId", stId);
		if(countPage==null||"".equals(countPage)){
			Integer count = 0;
			List<GoodsPOJO> goodsList = goodsService.findSpecialGoods(map);
			count = goodsList.size();
			if(count>0){
				if(count%pageSize!=0)
					countPage=((Integer)(count/pageSize+1)).toString();
				else
					countPage=((Integer)(count/pageSize)).toString();
			}
		}
		
		map.put("begin", (Integer.parseInt(curPage)-1)*pageSize);
		map.put("pageSize", pageSize);
		//查询符合条件的商品总数
		List<GoodsPOJO> goodsList = goodsService.findSpecialGoods(map);
		request.setAttribute("list", goodsList);
		request.setAttribute("curPage", curPage);
		request.setAttribute("countPage", countPage);
		request.setAttribute("st", st);
		request.setAttribute("IMAGEIP", IMAGEIP);
		return FOLDER+"/specialGoods";
	}
	@RequestMapping("/removeStopSpecialThemeGoods")
	public String removeStopSpecialThemeGoods(HttpServletRequest request,HttpServletResponse response){
		String goodsId = request.getParameter("goodsId");
		String themeId = request.getParameter("themeId");
		Map<String,Object> map = new HashMap<>();
		map.put("goodsId", Integer.parseInt(goodsId));
		map.put("themeId", Integer.parseInt(themeId));
		stgService.removeSpecialGoods(map);
		
		response.setContentType("text/plain;charset=UTF-8"); 
		try { 
		     response.getWriter().write("商品成功从该专题中移除"); 
		} catch (IOException e) { 
		e.printStackTrace(); 
		} 
		return null;
		
	}
	@RequestMapping("chooseSpecial")
	public String chooseSpecial(HttpServletRequest request){
		String way = request.getParameter("way");
		Map<String,Object> map = new HashMap<String,Object>();
		List<SpecialThemePOJO> stList = stService.findAll(map);
		request.setAttribute("stList", stList);
		if(way!=null&&"1".equals(way))
			return FOLDER+"/chooseSpecialOne";
		return FOLDER+"/chooseSpecial";
	}
	
	@RequestMapping("/addSpecialGoods")
	public String addSpecialGoods(HttpServletRequest request,HttpServletResponse response){
		String goodsId = request.getParameter("goodsId");
		String sps = request.getParameter("theReturns");
		String[] specialId = sps.split(",");
		for(String sId : specialId){
			SpecialThemeGoodsPOJO stg = new SpecialThemeGoodsPOJO();
			stg.setGoodsId(Integer.parseInt(goodsId));
			stg.setThemeId(Integer.parseInt(sId));
			//判断banner中是否已存在该商品
			Map<String,Object> map = new HashMap<>();
			map.put("goodsId", goodsId);
			map.put("themeId", sId);
			List<SpecialThemeGoodsPOJO> list = stgService.findSpecialGoods(map);
			if(list==null||list.size()==0)
			stgService.addSpecialGoods(stg);
		}
		response.setContentType("text/plain;charset=UTF-8"); 
		try {
			response.getWriter().write("商品添加专题成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/addSpecialGoodsOne")
	public String addSpecialGoodsOne(HttpServletRequest request,HttpServletResponse response){
		String goodsId = request.getParameter("goodsId");
		String sts = request.getParameter("specialId");
		String[] goodsIds = goodsId.split(",");
		for(String gId : goodsIds){
			SpecialThemeGoodsPOJO stg = new SpecialThemeGoodsPOJO();
			stg.setGoodsId(Integer.parseInt(gId));
			stg.setThemeId(Integer.parseInt(sts));
			//判断banner中是否已存在该商品
			Map<String,Object> map = new HashMap<>();
			map.put("goodsId", Integer.parseInt(gId));
			map.put("themeId", Integer.parseInt(sts));
			List<SpecialThemeGoodsPOJO> list = stgService.findSpecialGoods(map);
			if(list==null||list.size()==0)
				stgService.addSpecialGoods(stg);
		}
		response.setContentType("text/plain;charset=UTF-8"); 
		try {
			response.getWriter().write("商品添加专题成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
