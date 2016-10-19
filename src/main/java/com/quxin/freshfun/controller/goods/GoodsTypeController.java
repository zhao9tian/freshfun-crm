package com.quxin.freshfun.controller.goods;

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
import com.quxin.freshfun.model.goods.GoodsTypePOJO;
import com.quxin.freshfun.model.goods.TypeGoodsPOJO;
import com.quxin.freshfun.service.goods.GoodsService;
import com.quxin.freshfun.service.goods.GoodsTypeService;
import com.quxin.freshfun.service.goods.TypeGoodsService;
import com.quxin.freshfun.utils.DateUtils;
import com.quxin.freshfun.utils.UploadUtils;
@Controller
@RequestMapping("/goodstype")
public class GoodsTypeController {
	private static String file = "/goods";
	
	private static String IMAGEIP;
	
	@Value("${rip}")
	public void setRip(String value) {
		IMAGEIP = value;
	}
	
	@Autowired
	private GoodsTypeService goodsTypeService;
	@Autowired
	private TypeGoodsService typeGoodsService;
	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping("/getGoodsTypeList")
	public String getGoodsTypeList(HttpServletRequest request){
		String curPage = request.getParameter("curPage");
		if(curPage==null||"".equals(curPage)){
			curPage="1";
		}
		int pageSize = 5;
		int count = 0;
		if(goodsTypeService.findAll()!=null)
			count = goodsTypeService.findAll().size();
		int countPage = 0;
		if(count%pageSize==0)
			countPage=count/pageSize;
		else
			countPage=count/pageSize+1;
		int begin = (Integer.parseInt(curPage)-1)*pageSize;
		Map<String,Object> map = new HashMap<>();
		map.put("begin", begin);
		map.put("pageSize", pageSize);
		List<GoodsTypePOJO> typeList = goodsTypeService.findByPage(map);
		request.setAttribute("curPage", curPage);
		request.setAttribute("countPage", ""+countPage);
		request.setAttribute("typeList", typeList);
		request.setAttribute("IMAGEIP", IMAGEIP);
		return file+"/goodsTypeList";
	}
	/**
	 * 启用和禁用分类
	 * @return
	 */
	@RequestMapping("/openOrStopGoodsType")
	public String openOrStopGoodsType(HttpServletRequest request,HttpServletResponse response){
		String gtid = request.getParameter("gtId");
		String isdelete = request.getParameter("isdelete");
		GoodsTypePOJO gt = new GoodsTypePOJO();
		gt.setId(Integer.parseInt(gtid));
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
		goodsTypeService.removeGoodsType(gt);
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
	 * 跳转编辑页
	 * @param request
	 * @return
	 */
	@RequestMapping("/toShowTypeInfo")
	public String toShowTypeInfo(HttpServletRequest request){
		String gtid = request.getParameter("gtid");
		GoodsTypePOJO gt = null;
		if(gtid!=null&&!"".equals(gtid)){
			gt = goodsTypeService.findById(Integer.parseInt(gtid));
		}
		request.setAttribute("gt", gt);
		request.setAttribute("IMAGEIP", IMAGEIP);
		return file+"/goodsTypeInfo";
	}
	/**
	 * 保存分类信息
	 * @return
	 */
	@RequestMapping("/saveGoodsType")
	public String saveGoodsType(HttpServletRequest request,GoodsTypePOJO gt){
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
				gt.setGoodsTypeImg(pathArr[0]);
				gt.setGoodsInfoImg(pathArr[1]);
			}else{
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					// 取得上传文件
					MultipartFile file = multiRequest.getFile(iter.next());
					if(file.getName().equals("mallImg1") && !file.getOriginalFilename().equals("")){
						gt.setGoodsTypeImg(pathArr[0]);
					}else if(file.getName().equals("mallImg2") && !file.getOriginalFilename().equals("")){
						gt.setGoodsInfoImg(pathArr[0]);
					}
				}
			}
			
		}
		if(gt.getId()!=null){
			try {
				gt.setGmtModified(DateUtils.stringToLong(DateUtils.getDate(new Date(), "yyyy-MM-dd HH-mm-ss"), "yyyy-MM-dd HH-mm-ss")/1000);
				goodsTypeService.updateGoodsType(gt);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				gt.setGmtCreate(DateUtils.stringToLong(DateUtils.getDate(new Date(), "yyyy-MM-dd HH-mm-ss"), "yyyy-MM-dd HH-mm-ss")/1000);
				gt.setGmtModified(DateUtils.stringToLong(DateUtils.getDate(new Date(), "yyyy-MM-dd HH-mm-ss"), "yyyy-MM-dd HH-mm-ss")/1000);
				goodsTypeService.addGoodsType(gt);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:https://www.freshfun365.com/freshfun-crm/getGoodsTypeList.do";
	}
	
	/**
	 * 查询分类商品
	 * @param request
	 * @return
	 */
	@RequestMapping("/findTypeGoods")
	public String findTypeGoods(HttpServletRequest request){
		String gtId = request.getParameter("gtid");
		GoodsTypePOJO gt = goodsTypeService.findById(Integer.parseInt(gtId));
		
		int pageSize = 5;   //页面数据量
		String countPage = request.getParameter("countPage");//商品总页数
		String curPage = request.getParameter("curPage");//商品当前页
		
		if(curPage==null||"".equals(curPage)){
			curPage="1";
		}
		
		//创建map，查询参数
		Map<String,Object> map = new HashMap<>();
		map.put("gtId", gtId);
		if(countPage==null||"".equals(countPage)){
			Integer count = 0;
			List<GoodsPOJO> goodsList = goodsService.findTypeGoods(map);
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
		List<GoodsPOJO> goodsList = goodsService.findTypeGoods(map);
		request.setAttribute("list", goodsList);
		request.setAttribute("curPage", curPage);
		request.setAttribute("countPage", countPage);
		request.setAttribute("gt", gt);
		request.setAttribute("IMAGEIP", IMAGEIP);
		return file+"/typeGoods";
	}
	/**
	 * 移除分类商品
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/removeTypeGoods")
	public String removeTypeGoods(HttpServletRequest request,HttpServletResponse response){
		String goodsId = request.getParameter("goodsId");
		String gtId = request.getParameter("gtId");
		Map<String,Object> map = new HashMap<>();
		map.put("goodsId", Integer.parseInt(goodsId));
		map.put("gtId", Integer.parseInt(gtId));
		typeGoodsService.delete(map);
		
		response.setContentType("text/plain;charset=UTF-8"); 
		try { 
		     response.getWriter().write("商品成功从该分类中移除"); 
		} catch (IOException e) { 
		e.printStackTrace(); 
		} 
		return null;
	}
	/**
	 * 查询全部分类
	 * @param request
	 * @return
	 */
	@RequestMapping("/chooseType")
	public String chooseType(HttpServletRequest request){
		String way = request.getParameter("way");
		List<GoodsTypePOJO> gtList = goodsTypeService.findAllType();
		request.setAttribute("gtList", gtList);
		if(way!=null&&"1".equals(way))
			return file+"/chooseTypeOne";
		return file+"/chooseType";
	}
	/**
	 * 添加分类商品
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addTypeGoods")
	public String addTypeGoods(HttpServletRequest request,HttpServletResponse response){
		String goodsId = request.getParameter("goodsId");
		String sps = request.getParameter("theReturns");
		String[] specialId = sps.split(",");
		for(String sId : specialId){
			TypeGoodsPOJO tg = new TypeGoodsPOJO();
			tg.setGoodsId(Integer.parseInt(goodsId));
			tg.setGoodsTypeId(Integer.parseInt(sId));
			//判断分类中是否已存在该商品
			Map<String,Object> map = new HashMap<>();
			map.put("goodsId", goodsId);
			map.put("sId", sId);
			List<TypeGoodsPOJO> list = typeGoodsService.findTpyeGoods(map);
			if(list==null||list.size()==0)
				typeGoodsService.insertSelective(tg);
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
	
	@RequestMapping("/addTypeGoodsOne")
	public String addTypeGoodsOne(HttpServletRequest request,HttpServletResponse response){
		String goodsId = request.getParameter("goodsId");
		String goodsType = request.getParameter("goodsType");
		String[] goodsIds = goodsId.split(",");
		for(String gId : goodsIds){
			TypeGoodsPOJO tg = new TypeGoodsPOJO();
			tg.setGoodsId(Integer.parseInt(gId));
			tg.setGoodsTypeId(Integer.parseInt(goodsType));
			//判断分类中是否已存在该商品
			Map<String,Object> map = new HashMap<>();
			map.put("goodsId", gId);
			map.put("sId", goodsType);
			List<TypeGoodsPOJO> list = typeGoodsService.findTpyeGoods(map);
			if(list==null||list.size()==0)
				typeGoodsService.insertSelective(tg);
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
}
