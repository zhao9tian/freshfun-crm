package com.quxin.freshfun.controller.specialmall;

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
import com.quxin.freshfun.model.specialmall.SpecialMallGoodsPOJO;
import com.quxin.freshfun.model.specialmall.SpecialMallPOJO;
import com.quxin.freshfun.service.goods.GoodsService;
import com.quxin.freshfun.service.specialmall.SpecialMallGoodsService;
import com.quxin.freshfun.service.specialmall.SpecialMallService;
import com.quxin.freshfun.utils.DateUtils;
import com.quxin.freshfun.utils.UploadUtils;
@Controller
@RequestMapping("/specialMall")
public class SpecialMallContorller {
	private static String file = "/specialMall";
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private SpecialMallService specialMallService;
	@Autowired
	private SpecialMallGoodsService specialMalGoodslService;
	
	private static String IMAGEIP;
	@Value("${rip}")
	public void setRip(String value) {
		IMAGEIP = value;
	}
	
	/**
	 * 查询banner列表，分页
	 * @param request
	 * @return
	 */
	@RequestMapping("/getSMList")
	public String getSMList(HttpServletRequest request){
		String curPage = request.getParameter("curPage");
		if(curPage==null){
			curPage="1";
		}
		int pageSize = 5;
		int count = specialMallService.findCount();
		int countPage = 0;
		int begin = 0;
		if(count>0){
			if(count%pageSize!=0){
				countPage=count/pageSize+1;
			}else{
				countPage=count/pageSize;
			}
		}
		if(Integer.parseInt(curPage)<countPage+1){
			begin = (Integer.parseInt(curPage)-1)*pageSize;
		}
		Map<String,Object> map = new HashMap<>();
		map.put("begin", begin);map.put("pageSize", pageSize);
		List<SpecialMallPOJO> smList = specialMallService.findAll(map);
		request.setAttribute("smList", smList);
		request.setAttribute("curPage", curPage);
		request.setAttribute("countPage", ""+countPage);
		request.setAttribute("IMAGEIP", IMAGEIP);
		return file+"/specialMallList";
	}
	/**
	 * 启用或禁用banner
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/openOrStopSpecialMall")
	public String openOrStopSpecialMall(HttpServletRequest request,HttpServletResponse response){
		String smid = request.getParameter("smid");
		String isdelete = request.getParameter("isdelete");
		SpecialMallPOJO sm = new SpecialMallPOJO();
		sm.setId(Integer.parseInt(smid));
		byte bt =0;
		if("0".equals(isdelete)){
			bt=1;
		}
		sm.setIsDeleted(bt);
		try {
			sm.setGmtModified(DateUtils.stringToLong(DateUtils.getDate(new Date(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss")/1000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		specialMallService.modifySpecialMall(sm);
		response.setContentType("text/plain;charset=UTF-8");
		String result = "Banner启动成功";
		if(bt==1){
			result="Banner禁用成功";
		}
		try { 
		     response.getWriter().write(result); 
		} catch (IOException e) { 
		e.printStackTrace(); 
		} 
		return null;
	}
	/**
	 * 跳转信息编辑页
	 * @return
	 */
	@RequestMapping("/toShowPage")
	public String toShowPage(HttpServletRequest request){
		String smid = request.getParameter("smid");
		SpecialMallPOJO sm = null;
		if(smid!=null&&!"".equals(smid)){
			sm = specialMallService.findById(Integer.parseInt(smid));
		}
		request.setAttribute("sm", sm);
		request.setAttribute("IMAGEIP", IMAGEIP);
		return file+"/specialMallInfo";
	}
	/**
	 * 保存banner信息
	 * @param request
	 * @param sm
	 * @return
	 */
	@RequestMapping("/saveSpecialMall")
	public String saveSpecialMall(HttpServletRequest request,SpecialMallPOJO sm){
		String path = "";
		try {
			path = UploadUtils.upload(request);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(path!=null){
			String[] pathArr = path.split(",");
			if(pathArr.length>1){
				sm.setMallImg(pathArr[0]);
				sm.setMallInfoImg(pathArr[1]);
			}else{
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					// 取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if(file.getName().equals("mallImg1") && !file.getOriginalFilename().equals("")){
						sm.setMallImg(pathArr[0]);
					}else if(file.getName().equals("mallInfoImg1") && !file.getOriginalFilename().equals("")){
						sm.setMallInfoImg(pathArr[0]);
					}
				}
			}
			
		}
		if(sm.getId()!=null){
			try {
				sm.setGmtModified(DateUtils.stringToLong(DateUtils.getDate(new Date(), "yyyy-MM-dd HH-mm-ss"), "yyyy-MM-dd HH-mm-ss")/1000);
				specialMallService.modifySpecialMall(sm);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				sm.setGmtCreate(DateUtils.stringToLong(DateUtils.getDate(new Date(), "yyyy-MM-dd HH-mm-ss"), "yyyy-MM-dd HH-mm-ss")/1000);
				sm.setGmtModified(DateUtils.stringToLong(DateUtils.getDate(new Date(), "yyyy-MM-dd HH-mm-ss"), "yyyy-MM-dd HH-mm-ss")/1000);
				specialMallService.addSpecialMall(sm);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:https://www.freshfun365.com/freshfun-crm/specialMall/getSMList.do";
	}
	/**
	 * 选择banner
	 * @param request
	 * @return
	 */
	@RequestMapping("/chooseSpecialMall")
	public String chooseSpecial(HttpServletRequest request){
		String way = request.getParameter("way");
		List<SpecialMallPOJO> smList = specialMallService.findAllMall();
		request.setAttribute("smList", smList);
		if(way!=null&&"1".equals(way))
			return file+"/chooseSpecialMallOne";
		return file+"/chooseSpecialMall";
	}
	/**
	 * 添加banner商品
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addSpecialMallGoods")
	public String addSpecialMallGoods(HttpServletRequest request,HttpServletResponse response){
		String goodsId = request.getParameter("goodsId");
		String sps = request.getParameter("theReturns");
		String[] specialId = sps.split(",");
		for(String sId : specialId){
			SpecialMallGoodsPOJO smg = new SpecialMallGoodsPOJO();
			smg.setGoodsId(Integer.parseInt(goodsId));
			smg.setMallId(Integer.parseInt(sId));
			//判断banner中是否已存在该商品
			Map<String,Object> map = new HashMap<>();
			map.put("goodsId", goodsId);
			map.put("mallId", sId);
			List<SpecialMallGoodsPOJO> list = specialMalGoodslService.findMallGoods(map);
			if(list==null||list.size()==0)
				specialMalGoodslService.addGoods(smg);
		}
		response.setContentType("text/plain;charset=UTF-8"); 
		try {
			response.getWriter().write("商品添加Banner成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/addSpecialMallGoodsOne")
	public String addSpecialMallGoodsOne(HttpServletRequest request,HttpServletResponse response){
		String goodsId = request.getParameter("goodsId");
		String specialMall = request.getParameter("specialMall");
		String[] goodsIds = goodsId.split(",");
		for(String gId : goodsIds){
			SpecialMallGoodsPOJO smg = new SpecialMallGoodsPOJO();
			smg.setGoodsId(Integer.parseInt(gId));
			smg.setMallId(Integer.parseInt(specialMall));
			//判断banner中是否已存在该商品
			Map<String,Object> map = new HashMap<>();
			map.put("goodsId", gId);
			map.put("mallId", specialMall);
			List<SpecialMallGoodsPOJO> list = specialMalGoodslService.findMallGoods(map);
			if(list==null||list.size()==0)
				specialMalGoodslService.addGoods(smg);
		}
		response.setContentType("text/plain;charset=UTF-8"); 
		try {
			response.getWriter().write("商品添加Banner成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询banner商品
	 * @param request
	 * @return
	 */
	@RequestMapping("/findSMGoods")
	public String findSMGoods(HttpServletRequest request){
		String smId = request.getParameter("smid");
		SpecialMallPOJO sm = specialMallService.findById(Integer.parseInt(smId));
		
		int pageSize = 5;   //页面数据量
		String countPage = request.getParameter("countPage");//商品总页数
		String curPage = request.getParameter("curPage");//商品当前页
		
		if(curPage==null||"".equals(curPage)){
			curPage="1";
		}
		
		//创建map，查询参数
		Map<String,Object> map = new HashMap<>();
		map.put("smId", smId);
		if(countPage==null||"".equals(countPage)){
			Integer count = 0;
			List<GoodsPOJO> goodsList = goodsService.findSpecialMallGoods(map);
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
		List<GoodsPOJO> goodsList = goodsService.findSpecialMallGoods(map);
		request.setAttribute("list", goodsList);
		request.setAttribute("curPage", curPage);
		request.setAttribute("countPage", countPage);
		request.setAttribute("sm", sm);
		request.setAttribute("IMAGEIP", IMAGEIP);
		return file+"/specialMallGoods";
	}
	
	@RequestMapping("/removeMallGoods")
	public String removeMallGoods(HttpServletRequest request,HttpServletResponse response){
		String goodsId = request.getParameter("goodsId");
		String mallId = request.getParameter("mallId");
		Map<String,Object> map = new HashMap<>();
		map.put("goodsId", Integer.parseInt(goodsId));
		map.put("mallId", Integer.parseInt(mallId));
		specialMalGoodslService.removeGoods(map);
		
		response.setContentType("text/plain;charset=UTF-8"); 
		try { 
		     response.getWriter().write("商品成功从该Banner中移除"); 
		} catch (IOException e) { 
		e.printStackTrace(); 
		} 
		return null;
		
	}
	
}
