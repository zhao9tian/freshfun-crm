package com.quxin.freshfun.service.impl.goods;

import com.quxin.freshfun.dao.*;
import com.quxin.freshfun.model.goods.*;
import com.quxin.freshfun.model.specialmall.SpecialMallGoodsPOJO;
import com.quxin.freshfun.model.specialtheme.SpecialThemeGoodsPOJO;
import com.quxin.freshfun.model.user.UsersPOJO;
import com.quxin.freshfun.service.goods.GoodsService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品接口实现类
 * @author TuZl
 * @time 2016年8月22日下午10:43:09
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsDao;
	@Autowired
	private UsersMapper userMapper;
	@Autowired
	private GoodsDetailMapper goodsDetailMapper;
	@Autowired
	private TypeGoodsMapper typeGoodsMapper;
	@Autowired
	private SpecialMallGoodsMapper specialMallGoodsMapper;
	@Autowired
	private SpecialThemeGoodsMapper specialThemeGoodsMapper;
	@Autowired
	private GoodsReationMapper goodsReationMapper;

	private static String SHAREURL;
	
	@Value("${shareURL}")
	public void setShareURL(String value){
		SHAREURL = value;
	}
	
	@Override
	public GoodsPOJO getGoodsById(Integer goodsId) {
		return goodsDao.getGoodsById(goodsId);
	}

	@Override
	public GoodsDetail getGoodsByGoodsId(Integer goodsId) {
		return goodsDetailMapper.selectGoodsDetailByGoodsId(goodsId);
	}

	@Override
	public List<GoodsPOJO> findMysqlList(Map<String, Object> map) {
		List<GoodsPOJO> goodsPOJO;
		goodsPOJO = goodsDao.findAll(map);
		return goodsPOJO;
	}

	@Override
	public Integer addGoods(GoodsPOJO goods , GoodsDetail gm, List<Integer> typeIds) {
		Integer goodsId;
		//1.商品信息存入MySQL
		goodsDao.save(goods);
		//2.获取自增的id
		goodsId = goods.getId();
		//获取到shareurl
		String share = SHAREURL;
		goods.setShareUrl(share);
		goodsDao.update(goods);
		
		//updte
		//3.存部分信息到mongoDB数据库,将商品Id传入
		gm.setGoodsId(goodsId);
		goodsDetailMapper.insertGoodsDetail(gm);
		try{
			for(Integer typeId : typeIds){
				GoodAndType gt = new GoodAndType(goodsId, typeId);
				goodsDao.insertGoodAndType(gt);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return goodsId;
	}
	@Override
	public void removeGoods(List<Integer> goodIds) {
		if(goodIds!=null && goodIds.size()>0){
			for(Integer id : goodIds){
				goodsDao.removeGood(id);
			}
		}
	}

	/**
	 * 商品下架
	 * @param goodsId
	 * @param modifyDate
	 */
	@Override
	public void updateGoodsDown(Integer goodsId,Long modifyDate){
		goodsDao.removeGood(goodsId);
		GoodsRelationPOJO goodsRelation = new GoodsRelationPOJO();//新增关系对象
		goodsRelation.setGoodsId(goodsId);//为商品关系商品id赋值
		List<TypeGoodsPOJO> listType = typeGoodsMapper.findTypeGoodsByGoodsId(goodsId);
		if(listType!=null){
			for(TypeGoodsPOJO tgp : listType){
				if(goodsRelation.getTypeIds()==null||"".equals(goodsRelation.getTypeIds()))
					goodsRelation.setTypeIds(tgp.getGoodsTypeId().toString());
				else
					goodsRelation.setTypeIds(goodsRelation.getTypeIds()+","+tgp.getGoodsTypeId().toString());
				Map<String,Object> map = new HashMap<>();
				map.put("goodsId", goodsId);
				map.put("gtId", tgp.getGoodsTypeId());
				typeGoodsMapper.delete(map);
			}
		}
		List<SpecialMallGoodsPOJO> listMall = specialMallGoodsMapper.selectByGoodsId(goodsId);
		if(listMall!=null){
			for(SpecialMallGoodsPOJO smgp : listMall){
				if(goodsRelation.getBannerIds()==null||"".equals(goodsRelation.getBannerIds()))
					goodsRelation.setBannerIds(smgp.getMallId().toString());
				else
					goodsRelation.setBannerIds(goodsRelation.getBannerIds()+","+smgp.getMallId().toString());
				Map<String,Object> map = new HashMap<>();
				map.put("goodsId", goodsId);
				map.put("mallId", smgp.getMallId());
				specialMallGoodsMapper.delete(map);
			}
		}
		List<SpecialThemeGoodsPOJO> listTheme = specialThemeGoodsMapper.selectByGoodsId(goodsId);
		if(listTheme!=null){
			for(SpecialThemeGoodsPOJO stgp : listTheme){
				if(goodsRelation.getThemeIds()==null||"".equals(goodsRelation.getThemeIds()))
					goodsRelation.setThemeIds(stgp.getThemeId().toString());
				else
					goodsRelation.setThemeIds(goodsRelation.getThemeIds()+","+stgp.getThemeId().toString());
				Map<String,Object> map = new HashMap<>();
				map.put("goodsId", goodsId);
				map.put("themeId", stgp.getThemeId());
				specialThemeGoodsMapper.deleteByMap(map);
			}
		}
		goodsReationMapper.insertSelective(goodsRelation);
	}

	@Override
	public List<Integer> getTypeGoodsIds(Integer goodsId) {
		return goodsDao.getGoodsTypeIdsByGoodsId(goodsId);
	}

	@Override
	public void updateGoods(GoodsPOJO goods, GoodsDetail gm,
			List<Integer> typeIds) {
		//1.商品信息存入MySQL
		goodsDao.update(goods);
		//2.存信息到mongoDB数据库,将商品Id传入
		goodsDetailMapper.updateGoodsDetail(gm);
		try{
			goodsDao.deleteTypes(goods.getId());
			for(Integer typeId : typeIds){
				GoodAndType gt = new GoodAndType(goods.getId(), typeId);
				goodsDao.insertGoodAndType(gt);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public Integer count(Map<String, Object> map) {
		return goodsDao.total(map);
	}

	@Override
	public Integer validateGoodsName(String goodsName , String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("goodsName", goodsName);
		map.put("id", id);
		return goodsDao.getTotalGoodsName(map);
	}

	@Override
	public List<GoodsPOJO> findSpecialGoods(Map<String,Object> map) {
		return goodsDao.findSpecialGoods(map);
	}

	@Override
	public List<GoodsPOJO> findSpecialMallGoods(Map<String,Object> map) {
		return goodsDao.findSpecialMallGoods(map);
	}

	@Override
	public List<GoodsPOJO> findTypeGoods(Map<String,Object> map) {
		return goodsDao.findTypeGoods(map);
	}

	@Override
	public List<GoodsPOJO> findActivityGoods(Map<String,Object> map) {
		return goodsDao.findActivityGoods(map);
	}

	@Override
	public List<GoodsPOJO> findProxyGoods(Map<String, Object> map) {
		return goodsDao.findProxyGoods(map);
	}

	@Override
	public Integer getLastSales(Integer goodsId) {
		return goodsDao.getLastSales(goodsId);
	}

	@Override
	public Integer getAllSales(Integer goodsId) {
		return goodsDao.getAllSales(goodsId);
	}

	/**
	 * 商品上架
	 * @param goodsId
	 * @param modifyDate
	 */
	@Override
	public void updateGoodsUp(Integer goodsId,Long modifyDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("goodsId",goodsId);
		map.put("modifyDate",modifyDate);
		goodsDao.grounding(map);
		GoodsRelationPOJO grp = goodsReationMapper.selectGoodsRelationByGoodsId(goodsId);
		if(grp!=null) {
			if (grp.getBannerIds() != null && !"".equals(grp.getBannerIds())) {
				String[] bIds = grp.getBannerIds().split(",");
				for (String bId : bIds) {
					SpecialMallGoodsPOJO smgp = new SpecialMallGoodsPOJO();
					smgp.setGoodsId(goodsId);
					smgp.setMallId(Integer.parseInt(bId));
					specialMallGoodsMapper.insertSelective(smgp);
				}
			}
			if (grp.getThemeIds() != null && !"".equals(grp.getThemeIds())) {
				String[] tIds = grp.getThemeIds().split(",");
				for (String tId : tIds) {
					SpecialThemeGoodsPOJO stgp = new SpecialThemeGoodsPOJO();
					stgp.setGoodsId(goodsId);
					stgp.setThemeId(Integer.parseInt(tId));
					specialThemeGoodsMapper.insert(stgp);
				}
			}
			if (grp.getTypeIds() != null && !"".equals(grp.getTypeIds())) {
				String[] tIds = grp.getTypeIds().split(",");
				for (String tId : tIds) {
					TypeGoodsPOJO tgp = new TypeGoodsPOJO();
					tgp.setGoodsId(goodsId);
					tgp.setGoodsTypeId(Integer.parseInt(tId));
					typeGoodsMapper.insertSelective(tgp);
				}
			}
		}
		goodsReationMapper.delGoodsRelation(goodsId);
	}

	@Override
	public Integer addGoodsSelection(Map<String, Object> map) {
		map.put("gmtCreate", System.currentTimeMillis()/1000);
		map.put("gmtModified", System.currentTimeMillis()/1000);
		return goodsDao.insertGoodsSelection(map);
	}

	@Override
	public List<GoodsPOJO> queryGoodsOfB(Integer curPage,Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(curPage!=null) {
			map.put("begin", (curPage - 1) * pageSize);
			map.put("pageSize", pageSize);
		}
		return goodsDao.selectGoodsByAgent(map);
	}

	@Override
	public Integer modifyGoodsAgentWithC(String id) {
		if(id!=null&&!"".equals(id)){
			Integer goodsId = Integer.parseInt(id);
			return goodsDao.updateGoodsAgentWithC(goodsId);
		}
		return 0;
	}

	@Override
	public Integer modifyGoodsAgentWithB(String id, String agent) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(id!=null) {
			map.put("id", id);
			map.put("agent", agent);
		}
		return goodsDao.updateGoodsAgentWithB(map);
	}

	@Override
	public Integer modifyGoodsWithAgent(String goodsId, String phone) {
		Integer result=0;
		if(phone!=null&&!"".equals(phone)){
			Long userId = userMapper.selectUserIdByPhone(phone);
			if(userId==null){
				result =  -1;
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("goodsId", Integer.parseInt(goodsId));
				map.put("userId", userId);
				result = goodsDao.updateGoodsWithAgent(map);
			}
		}
		return result;
	}

	@Override
	public List<UsersPOJO> queryUserById(Long id) {
		return userMapper.selectUserById(id);
	}
}
