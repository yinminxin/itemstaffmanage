package com.wonders.itemstaffmanage.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.wonders.itemstaffmanage.service.QiniuService;
import com.wonders.itemstaffmanage.vo.Qiniu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class QiniuServiceImpl implements QiniuService {

	@Autowired
	private Qiniu qiniu;
	
	@Override
	public String getUploadToken(String key) {
		Auth auth = Auth.create(qiniu.getAccessKey(), qiniu.getSecretKey());
		StringMap policy = new StringMap();
		policy.put("returnBody","{\"key\":${key},\"bucket\":${bucket},\"mimeType\":$(mimeType),\"duration\":$(avinfo.format.duration),\"name\":$(fname),\"size\":$(fsize),\"w\":$(imageInfo.width),\"h\":$(imageInfo.height),\"hash\":$(etag)}");
		return auth.uploadToken(qiniu.getBucketName(), key, qiniu.getExpires(), policy, true);
	}
	
	@Override
	public String urlConversion(String filePath) {
		Auth dummyAuth = Auth.create(qiniu.getAccessKey(), qiniu.getSecretKey());
        String downloadUrl = dummyAuth.privateDownloadUrl(filePath, qiniu.getExpires());
		return downloadUrl;
	}
	
	/**
     * 上传
     * @param filePath 文件路径  （也可以是字节数组、或者File对象）
     * @param key 上传到七牛上的文件的名称  （同一个空间下，名称【key】是唯一的）
     * @param bucketName 空间名称  （这里是为了获取上传凭证）
     */
	@Override
	public void upload(File filePath, String key, String bucketName,JSONObject result) {
	    try {
	         UploadManager uploadManager = new UploadManager();
	         // 调用put方法上传
	         Response res = uploadManager.put(filePath, key, getUploadToken(bucketName));
	         // 打印返回的信息
	         System.out.println(res.bodyString());
	    } catch (QiniuException e) {
	        Response r = e.response;
	        // 请求失败时打印的异常的信息
	        System.out.println(r.toString());
	        try {
	            // 响应的文本信息
	             System.out.println(r.bodyString());
	        } catch (QiniuException qe) {
	            // ignore
	         }
	    }
	}
	
	@Override
	public void upload(String filePath, String key, String bucketName,JSONObject result) {
		try {
	         UploadManager uploadManager = new UploadManager();
	         // 调用put方法上传
	         Response res = uploadManager.put(filePath, key, getUploadToken(bucketName));
	         // 打印返回的信息
	         System.out.println(res.bodyString());
	    } catch (QiniuException e) {
	        Response r = e.response;
	        // 请求失败时打印的异常的信息
	        System.out.println(r.toString());
	        try {
	            // 响应的文本信息
	             System.out.println(r.bodyString());
	        } catch (QiniuException qe) {
	            // ignore
	         }
	    }
		
	}
	
	@Override
	public JSONObject upload(byte[] data, String key, String bucketName) {
		try {
	         UploadManager uploadManager = new UploadManager();
	         // 调用put方法上传
	         Response res = uploadManager.put(data, key, getUploadToken(bucketName));
	         // 打印返回的信息
	         //System.out.println(res.bodyString());
	         JSONObject object = JSONObject.parseObject(res.bodyString());
	         return object;
	    } catch (QiniuException e) {
	        Response r = e.response;
	        // 请求失败时打印的异常的信息
	        System.out.println(r.toString());
	        try {
	            // 响应的文本信息
	             System.out.println(r.bodyString());
	        } catch (QiniuException qe) {
	            e.printStackTrace();
	         }
	    }
		return null;
	}
	/**
	 * 获取文件路径前缀
	 * @return
	 */
	@Override
	public String getUrlPrefix() {
		
		return qiniu.getBucketUrl();
	}
	
    
}
