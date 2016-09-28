package com.quxin.freshfun.controller.goods;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.quxin.freshfun.model.user.UsersPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quxin.freshfun.model.ImgUtilsPOJO;
import com.quxin.freshfun.model.goods.GoodsDetail;
import com.quxin.freshfun.model.goods.GoodsPOJO;
import com.quxin.freshfun.model.goods.GoodsTypePOJO;
import com.quxin.freshfun.service.goods.GoodsService;
import com.quxin.freshfun.service.goods.GoodsTypeService;
import com.quxin.freshfun.utils.DateUtils;
import com.quxin.freshfun.utils.UploadUtils;

@Controller
@RequestMapping("/")
public class GoodsInfoController {
	
	private static String TIMEFORMAT = "yyyy-MM-dd HH:mm:ss";
	private static String IMAGEIP;
	
	@Value("${rip}")
	public void setRip(String value) {
		IMAGEIP = value;
	}
	@Autowired
	private GoodsService goodsService;

	@Autowired
	private GoodsTypeService goodsTypeService;
	
	/**
	 * 商品列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/goodsList")
	public String goodsList(HttpServletRequest request){
		//获取查询条件
		Map<String, Object> queryCondition = getQueryCondition(request);
		//1.设置分页条件
		Integer total = goodsService.count(queryCondition);
		String pageStr=request.getParameter("currentPage");
		String pageSizeStr=request.getParameter("pageSize");
		Integer page = null;
		Integer pageSize = null ;
		Integer totalPage = null;
		Integer start = null;
		//1.1设置页面大小
		if(pageSizeStr!=null && !"".equals(pageSizeStr)){
			pageSize = Integer.parseInt(pageSizeStr);
		}else{
			pageSize = 10;
		}
		totalPage =total%pageSize == 0 ? total/pageSize : total/pageSize +1;
		//1.2设置开始页
		if(pageStr !=null && !"".equals(pageStr)){
			page = Integer.parseInt(pageStr);
			if(page >totalPage){
				page = totalPage;
			}
			if(page == 0 ){
				page = 1;
			}
		}else{
			page =1;
		}
		//1.3设置记录开始数
		start = (page-1)*pageSize;
		
		queryCondition.put("pageSize", pageSize);
		queryCondition.put("start", start);
		
		List<GoodsPOJO> goodsPOJOs = new ArrayList<>();
		goodsPOJOs = goodsService.findMysqlList(queryCondition);
		//设置页面回显
		request.setAttribute("goodsList", goodsPOJOs);
		//分页信息
		request.setAttribute("page", page);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("totalRecords", total);
		
		//查询条件
		request.setAttribute("goodsName", queryCondition.get("goodsName"));
		request.setAttribute("storeId", queryCondition.get("storeId"));
		Long gmtCreateTimeStamp = (Long) queryCondition.get("gmtCreate"); 
		if(gmtCreateTimeStamp!=null){
			try {
				request.setAttribute("gmtCreate", DateUtils.longToString(gmtCreateTimeStamp,TIMEFORMAT));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return "/goods/goodsList";
	}

	/**
	 * 跳转新增页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAddGoods")
	public String toAddGoods(HttpServletRequest request ){
		//1.商品类别
		List<GoodsTypePOJO> goodsTypes = new ArrayList<>();
		goodsTypes = goodsTypeService.findAll();
		request.setAttribute("goodsTypes", goodsTypes);
		return "/goods/addGoods";
	}
	
	/**
	 * 商品下架
	 * @return
	 */
	@RequestMapping("/removeGoods")
	public String removeGoods(@RequestParam(value="id",required =false ,defaultValue="-1") Integer id){
		if(id!=-1){
			goodsService.updateGoodsDown(id);
		}
		return "redirect:goodsList.do";
	}
	
	/**
	 * 商品上下架
	 * @return
	 */
	@RequestMapping("/updateIsOnSale")
	@ResponseBody
	public Map<String,Object> updateIsOnSale(@RequestParam Integer id , Integer isOnSale){
		String result = "";
		if(isOnSale == 1){
			goodsService.updateGoodsDown(id);
			result="商品下架成功";
		}else{
			goodsService.updateGoodsUp(id);
			result="商品上架成功";
		}
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> mapStatus = new HashMap<String,Object>();
		mapStatus.put("code",1);
		mapStatus.put("msg",result);
		map.put("status",mapStatus);
		return map;
	}

	/**
	 * 添加商品到B端  --B端
	 * @param ids 商品id
	 * @return
	 */
	@RequestMapping("/pushGoodsToB")
	@ResponseBody
	public Map<String,Object> pushGoodsToB(@RequestParam String ids){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> mapStatus = new HashMap<String,Object>();
		Integer num = 0;
		if(ids!=null&&!"".equals(ids)){
			String[] idArray = ids.split(",");
			for(String id : idArray){
				if(id!=null&&!"".equals(id)){
					Integer result = goodsService.modifyGoodsAgentWithC(id);
					if(result==1){
						num++;
					}
				}
			}
		}
		mapStatus.put("code",1);
		mapStatus.put("msg","执行成功，影响"+num+"条数据");
		map.put("status",mapStatus);
		return map;
	}

	/**
	 * B端商品上下架   --B端
	 * @param goodsId 商品id
	 * @return
	 */
	@RequestMapping("/goodsAgentStatus")
	@ResponseBody
	public Map<String,Object> goodsAgentStatus(@RequestParam String goodsId,@RequestParam String agent){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> mapStatus = new HashMap<String,Object>();
		Integer result = 0;
		if(goodsId!=null&&!"".equals(goodsId)){
			result = goodsService.modifyGoodsAgentWithB(goodsId,agent);
		}
		String resultStr = "";
		if(agent=="1"){
			resultStr = "商品下架成功";
		}else{
			resultStr = "商品上架成功";
		}
		if(result!=0){
			mapStatus.put("code",1);
			mapStatus.put("msg",resultStr);
		}else{
			mapStatus.put("code",0);
			mapStatus.put("msg","操作失败");
		}

		map.put("status",mapStatus);
		return map;
	}

	/**
	 * B端商品绑定代理人  --B端
	 * @param goodsId 商品id
	 * @return
	 */
	@RequestMapping("/goodsBindProxy")
	@ResponseBody
	public Map<String,Object> goodsBindProxy(@RequestParam String goodsId,@RequestParam String phone){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> mapStatus = new HashMap<String,Object>();
		Integer result = 0;
		if(phone!=null&&!"".equals(phone)){
			result = goodsService.modifyGoodsWithAgent(goodsId,phone);
		}

		if(result==1) {
			mapStatus.put("code", 1);
			mapStatus.put("msg", "绑定成功");
		}else if(result==-1) {
			mapStatus.put("code", 0);
			mapStatus.put("msg", "绑定失败，没有用户绑定该手机");
		}else{
			mapStatus.put("code", 0);
			mapStatus.put("msg", "绑定失败，该商品可能已代理");
		}
		map.put("status",mapStatus);
		return map;
	}

	/**
	 * 访问B端后台  --B端
	 * @return
	 */
	@RequestMapping("/LoginBackstageOfB")
	public String LoginBackstageOfB(){
		return "/goods/goodsListOfB";
	}

	/**
	 * 录入绑定手机  --B端
	 * @return
	 */
	@RequestMapping("/goodsTOBindOfB")
	public String goodsTOBindOfB(){
		return "/goods/goodsListOfB";
	}

	/**
	 * 查询B端商品  --B端
	 * @param curPage 当前页
	 * @return
	 */
	@RequestMapping("/pullGoodsFromB")
	@ResponseBody
	public Map<String,Object> pullGoodsFromB(@RequestParam String curPage){
		List<GoodsPOJO> goodsList = goodsService.queryGoodsOfB(null,null);
		Integer pageSize = 20;
		Integer currentPage = 1;
		Integer countPage = 1;
		if(curPage!=null&&!"".equals(curPage)){
			currentPage = Integer.parseInt(curPage);
		}
		if(goodsList!=null){
			if(goodsList.size()%pageSize==0){
				countPage = goodsList.size()/pageSize;
			}else{
				countPage = goodsList.size()/pageSize+1;
			}
		}

		List<GoodsPOJO> goodsLimit = goodsService.queryGoodsOfB(currentPage,pageSize);
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> mapStatus = new HashMap<String,Object>();
		Map<String,Object> mapResult = new HashMap<String,Object>();
		List<Map<String,Object>> listDate = new ArrayList<Map<String,Object>>();
		for(GoodsPOJO goods : goodsLimit){
			Map<String,Object> mapGoods = new HashMap<String,Object>();
			mapGoods.put("goodsId",goods.getId());
			mapGoods.put("goodsName",goods.getGoodsName());
			mapGoods.put("shopPrice",goods.getShopPrice());
			mapGoods.put("marketPrice",goods.getMarketPrice());
			mapGoods.put("isOnAgent",goods.getIsOnAgent());
			mapGoods.put("proxyId",goods.getMerchantProxyId());
			if(goods.getMerchantProxyId()!=80808081){
				List<UsersPOJO> user = goodsService.queryUserById(goods.getMerchantProxyId());
				if(user!=null&&user.size()>0){
					mapGoods.put("proxyPhone",user.get(0).getMobilePhone());
				}
			}
			listDate.add(mapGoods);
		}
		mapStatus.put("code",1);
		mapStatus.put("msg","查询成功，共"+goodsLimit.size()+"条数据");
		mapResult.put("data",listDate);
		mapResult.put("countPage",countPage);
		mapResult.put("currentPage",currentPage);
		map.put("result",mapResult);
		map.put("status",mapStatus);
		return map;
	}

	/**
	 * 跳转商品详情页  --B端
	 * @return
	 */
	@RequestMapping("/goodsInfoOfB")
	public String goodsInfoOfB(HttpServletRequest request,String goodsId){
		request.setAttribute("goodsId",goodsId);
		return "/goods/goodsInfoOfB";
	}

	/**
	 * 根据id获取商品信息  --B端
	 * @param goodsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findGoodsByGoodsId")
	public Map<String,Object> findGoodsByGoodsId(String goodsId){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> mapStatus = new HashMap<String,Object>();
		Map<String,Object> mapDate = new HashMap<String,Object>();
		List<GoodsTypePOJO> goodsTypes = new ArrayList<>();
		goodsTypes = goodsTypeService.findAll();
		if(goodsId!=null&&!"".equals(goodsId)){
			GoodsPOJO goods = goodsService.getGoodsById(Integer.parseInt(goodsId));
			goods.setGoodsTypeIds((goodsService.getTypeGoodsIds(Integer.parseInt(goodsId))));
			GoodsDetail goodsDetail = goodsService.getGoodsByGoodsId(Integer.parseInt(goodsId));
			if(goods!=null){
				mapDate.put("goodsId",goods.getId());
				mapDate.put("shopPrice",goods.getShopPriceString());
				mapDate.put("marketPrice",goods.getMarketPriceString());
				mapDate.put("proxyId",goods.getMerchantProxyId());
				mapDate.put("goodsTypes", goodsTypes);
				mapDate.put("goodsTypeIds", goods.getGoodsTypeIds());
				try {
					String gmtCreateStr = DateUtils.longToString(goods.getGmtCreate()*1000, TIMEFORMAT);
					String gmtModifiedStr =DateUtils.longToString(goods.getGmtModified()*1000, TIMEFORMAT);
					mapDate.put("createDate",gmtCreateStr);
					mapDate.put("modifyDate",gmtModifiedStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			String rootPath =IMAGEIP;
			if(goodsDetail!=null){
				String detailImgPaths = goodsDetail.getDetailImgPath();
				String carouselImgPaths = goodsDetail.getCarouselImgPath();
				if(detailImgPaths!=null && !"".equals(detailImgPaths)){
					List<ImgUtilsPOJO> imgPaths = new ArrayList<>();
					for(String img : detailImgPaths.split(",")){
						String imgView = IMAGEIP +img;
						String imgPath = img;
						ImgUtilsPOJO imgUtil = new ImgUtilsPOJO(imgView,imgPath);
						imgPaths.add(imgUtil);
					}
					mapDate.put("detailImgPaths", imgPaths);
				}
				if(carouselImgPaths!=null && !"".equals(carouselImgPaths)){
					List<ImgUtilsPOJO> imgPaths = new ArrayList<>();
					for(String img : carouselImgPaths.split(",")){
						String imgView = IMAGEIP +img;
						String imgPath = img;
						ImgUtilsPOJO imgUtil = new ImgUtilsPOJO(imgView,imgPath);
						imgPaths.add(imgUtil);
					}
					mapDate.put("carouselImgPaths", imgPaths);
				}
				mapDate.put("indexPicture", rootPath+goods.getGoodsImg());
				mapDate.put("standardImgPath", rootPath+goodsDetail.getStandardImgPath());
				//5描述处理
				String[] des =  goodsDetail.getDes().split("@`");
				if(des!=null && des.length>1){
					String title = des[0];
					String titleDes = des[1];
					String editer = des[2];
					mapDate.put("title", title);
					mapDate.put("titleDes", titleDes);
					mapDate.put("editer", editer);
				}

			}
		}
		mapStatus.put("code",1);
		mapStatus.put("msg","查询成功");
		map.put("status",mapStatus);
		map.put("result",mapDate);
		return map;
	}

	/**
	 * 查询商品是否被代理
	 * @param goodsId
	 * @return
	 */
	@RequestMapping("/findProxyByGoodsId")
	@ResponseBody
	public Map<String,Object> findProxyByGoodsId(String goodsId){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> mapStatus = new HashMap<String,Object>();
		if(goodsId!=null&&!"".equals(goodsId)){
			GoodsPOJO goods = goodsService.getGoodsById(Integer.parseInt(goodsId));
			if(goods.getMerchantProxyId()==80808081){
				mapStatus.put("code",1);
				mapStatus.put("msg","确认要下架吗？");

			}else{
				mapStatus.put("code",0);
				mapStatus.put("msg","该商品已被代理，确认要继续下架吗？");
			}
		}
		map.put("status",mapStatus);
		return map;
	}
	
	/**
	 * 跳转编辑页面
	 * @param request
	 * @param id 商品id
	 * @return
	 */
	@RequestMapping("/toUpdateGoods")
	public String toUpdateGoods(HttpServletRequest request ,Integer id , HttpSession session){
		//1.商品类别
		List<GoodsTypePOJO> goodsTypes = new ArrayList<>();
		goodsTypes = goodsTypeService.findAll();
		request.setAttribute("goodsTypes", goodsTypes);
		
		//2.根据商品Id查询出主要的商品信息
		GoodsPOJO goods = goodsService.getGoodsById(id);
		goods.setGoodsTypeIds((goodsService.getTypeGoodsIds(id)));
		GoodsDetail gm = goodsService.getGoodsByGoodsId(id);
		
		//3时间显示处理	--
		try {
			String gmtCreateStr = DateUtils.longToString(goods.getGmtCreate()*1000, TIMEFORMAT);
			String gmtModifiedStr =DateUtils.longToString(goods.getGmtModified()*1000, TIMEFORMAT);

			request.setAttribute("gmtCreateStr", gmtCreateStr);
			request.setAttribute("gmtModifiedStr", gmtModifiedStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//4.获取图片地址
		String rootPath =IMAGEIP;
		if(gm!=null){
			String detailImgPaths = gm.getDetailImgPath();
			String carouselImgPaths = gm.getCarouselImgPath();
			if(detailImgPaths!=null && !"".equals(detailImgPaths)){
				List<ImgUtilsPOJO> imgPaths = new ArrayList<>();
				for(String img : detailImgPaths.split(",")){
					String imgView = IMAGEIP +img;
					String imgPath = img;
					ImgUtilsPOJO imgUtil = new ImgUtilsPOJO(imgView,imgPath);
					imgPaths.add(imgUtil);
				}
				request.setAttribute("detailImgPaths", imgPaths);
			}
			if(carouselImgPaths!=null && !"".equals(carouselImgPaths)){
				List<ImgUtilsPOJO> imgPaths = new ArrayList<>();
				for(String img : carouselImgPaths.split(",")){
					String imgView = IMAGEIP +img;
					String imgPath = img;
					ImgUtilsPOJO imgUtil = new ImgUtilsPOJO(imgView,imgPath);
					imgPaths.add(imgUtil);
				}
				request.setAttribute("carouselImgPaths", imgPaths);
			}
			request.setAttribute("indexPicture", rootPath+goods.getGoodsImg());
			request.setAttribute("standardImgPath", rootPath+gm.getStandardImgPath());
			//5描述处理
			String[] des =  gm.getDes().split("@`");
			if(des!=null && des.length>1){
				String title = des[0];
				String titleDes = des[1];
				String editer = des[2];
				request.setAttribute("title", title);
				request.setAttribute("titleDes", titleDes);
				request.setAttribute("editer", editer);
			}
			request.setAttribute("gm", gm);
		}

		request.setAttribute("goods", goods);

		return "/goods/updateGoods";
	}
	
	/**
	 * 保存商品信息
	 * @param request
	 * @param goods 商品主要信息
	 * @param gm 商品详情信息
	 * @return
	 */
	@RequestMapping("/saveGoods")
	public String saveGoods(HttpServletRequest request ,GoodsPOJO goods ,GoodsDetail gm){
		//验证用户名
		Map<String, Object> map = validateGoodsName(goods.getGoodsName(),null);
		if("yes".equals(map.get("msg"))){
			//1.处理日期
			String pickBirthdate = request.getParameter("pickBirthdate");
			String pickOutdate = request.getParameter("pickOutdate");
			try {
				if(pickBirthdate !=null && pickOutdate !=null && !"".equals(pickBirthdate) && !"".equals(pickOutdate)){
					Long goodsBirthdate = DateUtils.stringToLong(pickBirthdate, TIMEFORMAT)/1000;
					Long goodsOutdate = DateUtils.stringToLong(pickOutdate, TIMEFORMAT)/1000;
				}
				goods.setGmtCreate(System.currentTimeMillis()/1000);//创建时间
				goods.setGmtModified(System.currentTimeMillis()/1000);//修改时间--字段为非空,新增时修改时间为创建时间
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			//2.处理商品分类信息
			List<Integer> goodsTypeIds = new ArrayList<>();
			String[] goodsTypes = request.getParameterValues("goodsTypes");
			if(goodsTypes !=null && !"".equals(goodsTypes)){
				for(String id : goodsTypes){
					goodsTypeIds.add(Integer.parseInt(id));
				}
			}
			//3.文件上传
			try {
				goods.setGoodsImg(UploadUtils.uploadOne(request, "indexFile"));
				gm.setStandardImgPath((UploadUtils.uploadOne(request, "standardImg")));
				gm.setCarouselImgPath(UploadUtils.upload(request, "carousel"));
				gm.setDetailImgPath(UploadUtils.upload(request, "detail"));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			//4.处理描述
			String title = request.getParameter("title");
			String titleDes = request.getParameter("titleDes");
			String editer = request.getParameter("editer");
			String des = title+"@`"+titleDes+"@`"+editer;
			gm.setDes(des);
			
			//5.处理金额
			Double shopPriceD = Double.parseDouble(goods.getShopPriceString())*100;
			goods.setShopPrice(shopPriceD.intValue());
			Double marketPriceD = Double.parseDouble(goods.getMarketPriceString())*100;
			goods.setMarketPrice(marketPriceD.intValue());

			//商户id,代理id
			goods.setStoreId(80808080);
			goods.setMerchantProxyId(80808081L);
			//默认上架
			goods.setIsOnSale((byte)1);
			Integer goodsId = goodsService.addGoods(goods, gm,goodsTypeIds);
			//6.处理精选图片
//			try {
//				Map<String, Object> goodsSelcetMap = new HashMap<>();
//				String gsPicPath = UploadUtils.uploadOne(request, "goodsSelect");
//				if(gsPicPath!=null && !"".equals(gsPicPath)){
//					goodsSelcetMap.put("goodsSelectionPic",gsPicPath );
//					goodsSelcetMap.put("goodsId",goodsId );
//					goodsService.addGoodsSelection(goodsSelcetMap);
//				}
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		return "redirect:goodsList.do";
	}
	
	
	/**
	 * 保存商品信息
	 * @param request
	 * @param goods 商品主要信息
	 * @param gm 商品详情信息
	 * @return
	 */
	@RequestMapping("/updateGoods")
	public String updateGoods(HttpServletRequest request ,GoodsPOJO goods ,GoodsDetail gm){
		//1.处理页面时间
		String pickBirthdate = request.getParameter("pickBirthdate");
		String pickOutdate = request.getParameter("pickOutdate");
		SimpleDateFormat sdf = new SimpleDateFormat(TIMEFORMAT);
		Long goodsBirthdate = null;
		Long goodsOutdate = null;
		try {
			if(pickBirthdate != null && pickOutdate != null){
				goodsBirthdate = sdf.parse(pickBirthdate).getTime()/1000;
				goodsOutdate = sdf.parse(pickOutdate).getTime()/1000;
			}
			goods.setGmtModified(System.currentTimeMillis()/1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		gm.setGoodsBirthdate(goodsBirthdate);
		gm.setGoodsOutdate(goodsOutdate);
		//2.商品类型
		List<Integer> goodsTypeIds = new ArrayList<>();
		String[] goodsTypes = request.getParameterValues("goodsTypes");
		if(goodsTypes !=null && !"".equals(goodsTypes)){
			for(String id : goodsTypes){
				goodsTypeIds.add(Integer.parseInt(id));
			}
		}
		//3.图片上传
		try {
			//首页图片
			String indexFile = UploadUtils.uploadOne(request, "indexFile");
			if(!"".equals(indexFile))
				goods.setGoodsImg(indexFile);
			//规格图片
			String standardImg = UploadUtils.uploadOne(request, "standardImg");
			if(!"".equals(standardImg))
				gm.setStandardImgPath(standardImg);
			//轮播原来的图片
			String[] carouselImgs = request.getParameterValues("carousel_file");
			//轮播新的图片
			String newCarouse = UploadUtils.upload(request, "carousel");
			StringBuffer oldCarouse = new StringBuffer("");
			if(carouselImgs!=null){
				for(String img : carouselImgs){
					oldCarouse.append(img+",");
				}
			}
			//判断是否有新的图片添加
			if(!"".equals(newCarouse)){
				gm.setCarouselImgPath(oldCarouse+newCarouse);
			}else{
				//判断旧的图片有没有被删除完
				if(oldCarouse !=null && !"".equals(oldCarouse.toString())){
					gm.setCarouselImgPath(oldCarouse.deleteCharAt(oldCarouse.length()-1).toString());
				}else{
					//删除完了之后就用""代替
					gm.setCarouselImgPath("");
				}
			}
			//详情原来的图片
			String[] detailImgs = request.getParameterValues("detail_file");
			//详情新的图片
			String newDetail = UploadUtils.upload(request, "detail");
			StringBuffer oldDetail = new StringBuffer("");
			if(detailImgs!=null){
				for(String img : detailImgs){
					oldDetail.append(img+",");
				}
			}
			//存在新的图片就用旧的图片追加新的图片路径,否则就取出","
			if(!"".equals(newDetail)){
				gm.setDetailImgPath(oldDetail+newDetail);
			}else{
                if ("".equals(oldDetail.toString())) {
                    gm.setDetailImgPath("");
                } else {
                    gm.setDetailImgPath(oldDetail.deleteCharAt(oldDetail.length()-1).toString());
                }
            }
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

        //4.金额处理
		Double shopPriceD = Double.parseDouble(goods.getShopPriceString())*100;
		goods.setShopPrice(shopPriceD.intValue());
		Double marketPriceD = Double.parseDouble(goods.getMarketPriceString())*100;
		goods.setMarketPrice(marketPriceD.intValue());
		//5.处理描述
		String title = request.getParameter("title");
		String titleDes = request.getParameter("titleDes");
		String editer = request.getParameter("editer");
		
		String des = title+"@`"+titleDes+"@`"+editer;
		gm.setDes(des);
		
		goodsService.updateGoods(goods, gm,goodsTypeIds);
		return "redirect:goodsList.do";
	}


    /**************************************工具方法***************************************************/
	/**
	 * 查询条件拼接
	 * @param req
	 * @return
	 */
	private Map<String, Object> getQueryCondition(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<>();
		String goodsName = req.getParameter("goodsName");
		String storeId = req.getParameter("storeId");
		String gmtCreate = req.getParameter("gmtCreate");
		
		if(goodsName != null && !"".equals(goodsName)){
			map.put("goodsName", goodsName);
		}
		if(storeId != null && !"".equals(storeId)){
			map.put("storeId", storeId);
		}
		if(gmtCreate != null && !"".equals(gmtCreate)){
			try {
				map.put("gmtCreate", DateUtils.stringToLong(gmtCreate,TIMEFORMAT)/1000);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/validateGoodsName")
	public Map<String, Object> validateGoodsName(String goodsName, String id){
		String message;
		Integer count;
		Map<String, Object> map =  new HashMap<>();
		if(id !=null && !"".equals(id)){
			count = goodsService.validateGoodsName(goodsName , id);
		}else{
			count = goodsService.validateGoodsName(goodsName , null);
		}
		if(count < 1){
			message = "yes";//可用
		}else{
			message = "no";//不可用
		}
		map.put("msg", message);
		return map;
	}
	
}
