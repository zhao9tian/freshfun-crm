package com.quxin.freshfun.controller.activity;

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

import com.quxin.freshfun.model.activity.ActivityPOJO;
import com.quxin.freshfun.model.activity.ActivityVsGidPOJO;
import com.quxin.freshfun.model.goods.GoodsPOJO;
import com.quxin.freshfun.service.activity.ActivityService;
import com.quxin.freshfun.service.activity.ActivityVsGidService;
import com.quxin.freshfun.service.goods.GoodsService;
import com.quxin.freshfun.utils.DateUtils;
import com.quxin.freshfun.utils.UploadUtils;

/**
 * 活动信息Controller
 * @author fushihao
 * 
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {
	private static String file = "/activity";
	@Autowired
	private ActivityService activityservice;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ActivityVsGidService  activityVsGidService;

	private static String IMAGEIP;
	@Value("${rip}")
	public void setRip(String value) {
		IMAGEIP = value;
	}

	/**
	 * 活动展示
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/activityList")
	public String activityList(HttpServletRequest request) {
		List<ActivityPOJO> activityPOJOs = activityservice.findAll();
		request.setAttribute("activityList", activityPOJOs);
		request.setAttribute("IMAGEIP", IMAGEIP);
		return file+"/activityList";
	}

	/**
	 * 跳转到修改页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toUpdate")
	public String toUpdate(HttpServletRequest request) {
		String id = request.getParameter("id");
		ActivityPOJO activity = null;
		if(id!=null&&!"".equals(id))
			activity = activityservice.findById(Integer.parseInt(id));
		request.setAttribute("activity", activity);
		request.setAttribute("IMAGEIP", IMAGEIP);
		return file+"/updateActivity";
	}


	/**
	 * 新增和修改活动
	 * 
	 * @param request
	 * @param activitypojo
	 * @return
	 */
	@RequestMapping("updateActivity")
	public String updateActivity(HttpServletRequest request, ActivityPOJO activitypojo) {
		String path="";
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
				activitypojo.setActivityImg(pathArr[0]);
				activitypojo.setActivityInfoImg(pathArr[1]);
			}else{
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					// 取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if(file.getName().equals("indexFile1") && !file.getOriginalFilename().equals("")){
						activitypojo.setActivityImg(pathArr[0]);
					}else if(file.getName().equals("indexFile1") && !file.getOriginalFilename().equals("")){
						activitypojo.setActivityInfoImg(pathArr[1]);
					}
				}
			}
			
		}
		if(activitypojo.getId()!=null){
			try {
				activitypojo.setGmtModified(DateUtils.stringToLong(DateUtils.getDate(new Date(), "yyyy-MM-dd HH-mm-ss"), "yyyy-MM-dd HH-mm-ss")/1000);
				activityservice.updateActivity(activitypojo);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				activitypojo.setGmtCreate(DateUtils.stringToLong(DateUtils.getDate(new Date(), "yyyy-MM-dd HH-mm-ss"), "yyyy-MM-dd HH-mm-ss")/1000);
				activitypojo.setGmtModified(DateUtils.stringToLong(DateUtils.getDate(new Date(), "yyyy-MM-dd HH-mm-ss"), "yyyy-MM-dd HH-mm-ss")/1000);
				activityservice.addActivity(activitypojo);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:activityList.do";
	}
    /**
     * 活动的启用和禁用
     * @param response
     * @param id
     * @return
     */
	@RequestMapping("/openOrStopActivity")
	public String removeActivity(HttpServletRequest request,HttpServletResponse response) {
		String aId = request.getParameter("aId");
		String isdelete = request.getParameter("isdelete");
		ActivityPOJO gt = new ActivityPOJO();
		gt.setId(Integer.parseInt(aId));
		byte bt =0;
		if("0".equals(isdelete)){
			bt=1;
		}
		gt.setIsDeleted(bt);
		try {
			gt.setGmtModified(DateUtils.stringToLong(DateUtils.getDate(new Date(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss")/1000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		activityservice.deleteActivity(gt);
		response.setContentType("text/plain;charset=UTF-8");
		String result = "分类启动成功";
		if(bt==1){
			result="分类禁用成功";
		}
		try { 
		     response.getWriter().write(result); 
		} catch (IOException e) { 
		e.printStackTrace(); 
		} 
		return null;
	}
	
	/**
	 * 查看活动商品
	 * @param request
	 * @return
	 */
	@RequestMapping("/activityGoods")
	public String showGoods(HttpServletRequest request){
		String atId = request.getParameter("id");
		ActivityPOJO gt = activityservice.findById(Integer.parseInt(atId));
		
		int pageSize = 5;   //页面数据量
		String countPage = request.getParameter("countPage");//商品总页数
		String curPage = request.getParameter("curPage");//商品当前页
		
		
		if(curPage==null||"".equals(curPage)){
			curPage="1";
		}
		
		//创建map，查询参数
		Map<String,Object> map = new HashMap<>();
		map.put("atId", atId);
		if(countPage==null||"".equals(countPage)){
			Integer count = 0;
			List<GoodsPOJO> goodsList = goodsService.findActivityGoods(map);
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
		List<GoodsPOJO> goodsList = goodsService.findActivityGoods(map);
		request.setAttribute("list", goodsList);
		request.setAttribute("curPage", curPage);
		request.setAttribute("countPage", countPage);
		request.setAttribute("gt", gt);
		request.setAttribute("IMAGEIP", IMAGEIP);
		return file+"/activityGoods";
	}
	
	
	
	/**
	 * 移除活动商品
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/removeActivityGoods")
	public String removeActivityGoods(HttpServletRequest request,HttpServletResponse response){
			String goodsId = request.getParameter("goodsId");
			String gtId = request.getParameter("gtId");
			Map<String,Object> map = new HashMap<>();
			map.put("goodsId", Integer.parseInt(goodsId));
			map.put("gtId", Integer.parseInt(gtId));
			activityVsGidService.delete(map);
			response.setContentType("text/plain;charset=UTF-8"); 
			try { 
			     response.getWriter().write("商品成功从该活动中移除"); 
			} catch (IOException e) { 
			e.printStackTrace(); 
			} 
		return null;
		
	}
	
	
	/**
	 * 添加活动商品
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addActivityGoods")
	public String addTypeGoods(HttpServletRequest request,HttpServletResponse response){
		String goodsId = request.getParameter("goodsId");
		String discount = request.getParameter("discount");
		String discountPrice = request.getParameter("discountPrice");
		String sps = request.getParameter("theReturns");
		String[] specialId = sps.split(",");
		for(String sId : specialId){
			ActivityVsGidPOJO avg = new ActivityVsGidPOJO();
			//判断分类中是否已存在该商品
			Map<String,Object> map = new HashMap<>();
			map.put("goodsId", goodsId);
			map.put("sId", sId);
			List<ActivityVsGidPOJO> list = activityVsGidService.findActicityGoods(map);
			if(list==null||list.size()==0)
				avg.setDiscount((byte)Integer.parseInt(discount));
				avg.setDiscountPrice(Integer.parseInt(discountPrice));
				avg.setGoodsId(Integer.parseInt(goodsId));
				avg.setActivityId(Integer.parseInt(sId));
				activityVsGidService.insertSelective(avg);
		}
		response.setContentType("text/plain;charset=UTF-8"); 
		try {
			response.getWriter().write("商品添加分类成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/chooseActivity")
	public String chooseActivity(HttpServletRequest request){
		String goodsId = request.getParameter("goodsId");
		GoodsPOJO goods = null;
		if(goodsId!=null&&!"".equals(goodsId))
			goods = goodsService.getGoodsById(Integer.parseInt(goodsId));
		List<ActivityPOJO> activityList = activityservice.findAll();
		request.setAttribute("activityList", activityList);
		request.setAttribute("goods", goods);
		return file+"/chooseActivity";
	}
	
	
}
