package com.quxin.freshfun.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author TuZl
 * @time 2016年8月27日下午2:06:32
 */
public class UploadUtils {
	
	
	/**
	 * 多文件上传--返回路径字符串,按照文件顺序拼接
	 * @param request
	 * @return	返回上传文件的绝对路径   每天生成一个文件夹
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String upload(HttpServletRequest request) throws IllegalStateException,
			IOException {
		StringBuffer sb = new StringBuffer("");
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				//判断图片是否修改
				if(file.getName().equals("indexFile") && file.getOriginalFilename().equals("")){
					String indexImg = request.getParameter("goodsImg");
					sb.append(indexImg+",");
				}

				if (filterFileType(file.getOriginalFilename())) {
					// 取得当前上传文件的文件名称
					String fileName = file.getOriginalFilename();
					// 如果名称不为"",说明该文件存在，否则说明该文件不存在
					if (!"".equals(fileName.trim())) {
						//更改上传文件的名称--时间戳,以免因为文件名重复而覆盖相同文件名的文件,不保留原名
						String editFileName = System.currentTimeMillis()+"_"+Math.random()*100+"_"+fileName.substring(fileName.lastIndexOf("."), fileName.length());
						String dir = createDirs();
						String path = dir +"/"+ editFileName;
//						File localFile = new File(basePath+path);
//						file.transferTo(localFile);
						String remotePath = OSSUtils.uploadPic(file.getInputStream(),path);
						sb.append(remotePath+",");
					}
				}
			}
		}
		if(!"".equals(sb.toString())){
			sb.delete(sb.lastIndexOf(","),sb.length());
		}
		return "".equals(sb.toString()) ? null: sb.toString();
	}
	/**
	 * 私人订制多文件上传,使用请慎重
	 * 多文件上传--返回路径字符串,按照文件顺序拼接
	 * @param request
	 * @param pre  input file name的前缀
	 * @return	返回上传文件的绝对路径   每天生成一个文件夹
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String upload(HttpServletRequest request ,String pre) throws IllegalStateException,
	IOException {
		StringBuffer sb = new StringBuffer("");
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = multiRequest.getFile(iter.next());
				
				if (file != null && filterFileType(file.getOriginalFilename())) {
					// 取得当前上传文件的文件名称
					String fileName = file.getOriginalFilename();
					//获取属性名与前缀名对比
					String preFileName = file.getName();
					if(preFileName.startsWith(pre)){
						// 如果名称不为"",说明该文件存在，否则说明该文件不存在
						if (!"".equals(fileName.trim())) {
							//更改上传文件的名称--时间戳,以免因为文件名重复而覆盖相同文件名的文件,不保留原名
							String editFileName = System.currentTimeMillis()+"_"+Math.random()*100+"_"+fileName.substring(fileName.lastIndexOf("."), fileName.length());
							String dir = createDirs();
							String path = dir +"/"+ editFileName;
//							File localFile = new File(basePath+path);
//							file.transferTo(localFile);
							String remotePath = OSSUtils.uploadPic(file.getInputStream(),path);
							sb.append(remotePath+",");
						}
					}
				}
			}
		}
		if(!"".equals(sb.toString())){
			sb.delete(sb.lastIndexOf(","),sb.length());
		}
		return "".equals(sb.toString()) ? "": sb.toString();
	}
	
	/**
	 * 上传单张图片返回图片路径
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String uploadOne(HttpServletRequest request ,String fileName) throws IllegalStateException,
	IOException {
		String imgPath ="";
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		// 取得request中的所有文件名
		Iterator<String> iter = multiRequest.getFileNames();
		while(iter.hasNext()){
			MultipartFile file = multiRequest.getFile(iter.next());

			//匹配上传的是哪张图片
			if(file.getName().equals(fileName) && !file.getOriginalFilename().equals("")){
				//更改上传文件的名称--时间戳,以免因为文件名重复而覆盖相同文件名的文件,不保留原名
				String picName = file.getOriginalFilename();
				String editFileName = System.currentTimeMillis()+"_"+Math.random()*100+"_"+picName.substring(picName.lastIndexOf("."), picName.length());
				String dir = createDirs();
				String path = dir +"/"+ editFileName;
//				File localFile = new File(basePath+path);
//				file.transferTo(localFile);
				imgPath = OSSUtils.uploadPic(file.getInputStream(),path);
				break;
			}
		}
		return imgPath;
	}

	//	public static String uploadOne(HttpServletRequest request ,String fileName) throws IllegalStateException,
//			IOException {
//		String basePath = "/home/admin/file";//对应path.xml(注释上面有说明)映射文件的物理路径
//		String imgPath ="";
//		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//		// 取得request中的所有文件名
//		Iterator<String> iter = multiRequest.getFileNames();
//		while(iter.hasNext()){
//			MultipartFile file = multiRequest.getFile(iter.next());
//
//			//匹配上传的是哪张图片
//			if(file.getName().equals(fileName) && !file.getOriginalFilename().equals("")){
//				//更改上传文件的名称--时间戳,以免因为文件名重复而覆盖相同文件名的文件,不保留原名
//				String picName = file.getOriginalFilename();
//				String editFileName = System.currentTimeMillis()+"_"+Math.random()*100+"_"+picName.substring(picName.lastIndexOf("."), picName.length());
//				String dir = createDirs("");
//				String path = dir +"/"+ editFileName;
////				File localFile = new File(basePath+path);
////				file.transferTo(localFile);
////				OSSUtils.uploadPic(path,path);
//				imgPath = path;
//				break;
//			}
//		}
//		return imgPath;
//	}
	
	/**
	 * 生成文件保存目录 	年-月-日 yyyy/MM/dd
	 * @return 图片相对项目的路径
	 */
	private static String createDirs(){
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
//		File file = new File(realPath);
//		if(!file.exists()){
//			file.mkdirs();//创建多级目录
//		}
		return "image/"+year+"/"+month+"/"+day;
	}
	
	/**
	 * 过滤上传的文件的类型
	 * @param originalFilename 带后缀的文件名
	 * @return
	 */
	private static Boolean filterFileType(String originalFilename){
		String contentType = null;
		if(originalFilename != null && !"".equals(originalFilename)){
			contentType = originalFilename.substring(originalFilename.lastIndexOf(".")+1, originalFilename.length());
		}
		Boolean bool;
		List<String> fileTypes = new ArrayList<>();
		fileTypes.add("jpg");
		fileTypes.add("jpeg");
		fileTypes.add("png");
		fileTypes.add("gif");
		bool = fileTypes.contains(contentType);
		return bool;
	}
	
}
