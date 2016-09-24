package com.quxin.freshfun.utils;


import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.ObjectMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;

/**
 *
 * @author TuZl
 *
 */
public class OSSUtils {
	private static Logger logger = LoggerFactory.getLogger("result");
    private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private static String accessKeyId = "wdhNdpIWAjlQaNkG";
    private static String accessKeySecret = "4fhRflFow19uPPQjPs4Spjt98GdRWj";

    private static OSSClient client = null;

    static {
        client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }



    /**
     * 获取Bucket Info
     *
     * @param bucketName
     * @return
     */
    public static BucketInfo getBucketInfo(String bucketName) {
        try {
        	BucketInfo getBucketInfo = client.getBucketInfo(bucketName);
            return getBucketInfo;
        } catch (OSSException e) {
            logger.error("创建文件夹操作异常, 错误: ", e);
        } catch (ClientException e) {
            logger.error("创建文件夹连接异常, 错误: ", e);
        }
        return null;
    }


    /**
     * 创建文件夹
     *
     * @param remotePath 文件夹路径，需要以 / 结尾
     */
    public static void createDir(String bucketName, String remotePath) {
        try {
            logger.info("Create remotePath " + remotePath + " on bucketName " + bucketName);
            client.putObject(bucketName, remotePath, new ByteArrayInputStream(new byte[0]));
        } catch (OSSException e) {
            logger.error("创建文件夹操作异常, 错误: ", e);
        } catch (ClientException e) {
            logger.error("创建文件夹连接异常, 错误: ", e);
        }
    }


    /**
     * 上传本地文件
     *
     * @param bucketName ...
     * @param key  ...
     * @param localFile ...
     */
    public static void uploadFile(String bucketName, String key , String localFile) {
        try {
            logger.info("Upload remoteFile " + key + " on bucketName " + bucketName);
            client.putObject(bucketName, key, new File(localFile));
        } catch (OSSException e) {
            logger.error("上传本地文件操作异常, 错误: ", e);
        } catch (ClientException e) {
            logger.error("上传本地文件连接异常, 错误: ", e);
        }
    }


    /**
     * 删除文件
     *
     * @param bucketName
     * @param fileName
     */
    public static void deleteFile(String bucketName, String fileName) {
        try {
            client.deleteObject(bucketName, fileName);
        } catch (OSSException e) {
            logger.error("删除文件-操作异常, 错误: ", e);
        } catch (ClientException e) {
            logger.error("删除文件-连接异常, 错误: ", e);
        }
    }
    /**
     * Shutdown the instance to release any allocated resources
     */
    public static void shutdown() {
        client.shutdown();
    }

    /**
     * 获得url链接
     *
     * @param remotePath
     * @return
     */
    private static String getUrl(String remotePath) {
      // 设置URL过期时间为10年  3600l* 1000*24*365*10
      Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
      // 生成URL
      URL url = client.generatePresignedUrl("freshfunpic", remotePath, expiration);
      if (url != null) {
          StringBuffer urlSb = new StringBuffer(url.toString());
          String urlStr = urlSb.substring(urlSb.indexOf("/image"),urlSb.indexOf("?"));
          //http://freshfunpic.oss-cn-hangzhou.aliyuncs.com/image/gg.png?Expires=1790050351&OSSAccessKeyId=wdhNdpIWAjlQaNkG&Signature=vC7JREujInjHd8JpxyF%2Bz/wz4Vk%3D
        return urlStr;
      }
      return null;
    }

    /**
     * 上传图片
     * 会自动生成文件夹
     * @param localFilePath
     * @param remoteFilePath
     * @return
     */
    public static String uploadPic(InputStream localFilePath , String remoteFilePath){
        client.putObject("freshfunpic", remoteFilePath , localFilePath);
        return getUrl(remoteFilePath);
    }
//    public static String uploadPic(String localFilePath , String remoteFilePath){
//        ObjectMetadata meta = new ObjectMetadata();
//        File file = new File(localFilePath);
//        InputStream content = null;
//        try {
//            content = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            logger.error("找不到本地文件, 错误: ", e);
//        }
//        meta.setContentLength(file.length());
//        client.putObject("freshfunpic", remoteFilePath, content , meta);
//        return getUrl(remoteFilePath);
//    }

    public static void main(String[] args) throws IOException {
        System.out.println(OSSUtils.getBucketInfo("freshfunpic").getBucket().getLocation());
//        OSSUtils.createDir("freshfunpic", "m1/m2/m3/");
//        OSSUtils.uploadFile("freshfunpic", "/", "a.png");
//        System.out.println(uploadPic("e:/gg.png" , "image/gg.png"));

        client.putObject("freshfunpic" , "gg.png" ,new File("e:/gg.png"));
//        OSSUtils.deleteFile("freshfunpic", "image/gg.png");


    }

}