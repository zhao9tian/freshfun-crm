package com.quxin.freshfun.service.impl.specialtheme;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.quxin.freshfun.dao.SpecialThemeMapper;
import com.quxin.freshfun.model.specialtheme.SpecialThemePOJO;
import com.quxin.freshfun.service.specialtheme.SpecialThemeService;

@Service
public class SpecialThemeServiceImpl implements SpecialThemeService {
	
	@Autowired
	private SpecialThemeMapper stMapper;
	
private static String SHAREURL;
	
	@Value("${shareUrl}")
	public void setShareURL(String value){
		SHAREURL = value;
	}
	@Override
	public List<SpecialThemePOJO> findAll(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return stMapper.findAll(map);
	}

	@Override
	public Integer save(SpecialThemePOJO specialTheme) {
		specialTheme.setShareUrl(SHAREURL);
		// TODO Auto-generated method stub
		return stMapper.insert(specialTheme);
	}

	@Override
	public void update(SpecialThemePOJO specialTheme) {
		specialTheme.setShareUrl(SHAREURL);
		// TODO Auto-generated method stub
		stMapper.update(specialTheme);
	}

	@Override
	public void removeSpecialTheme(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public SpecialThemePOJO find(Integer id) {
		// TODO Auto-generated method stub
		return stMapper.findById(id);
	}

	@Override
	public List<SpecialThemePOJO> find(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
