package com.wonders.itemstaffmanage.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public interface QiniuService {

	Logger log = LogManager.getLogger();
	
	/**
	 * 获取上传token
	 */
	String getUploadToken(String key);
	
	/**
	 * 转换为可播放的资源地址
	 * @param filePath 资源地址
	 * @return
	 */
	String urlConversion(String filePath);
	
	/**
	 * 上传文件
	 * @param file 文件对象
	 * @param key
	 * @param bucketName
	 */
	void upload(File file, String key, String bucketName, JSONObject result);

	/**
	 * 上传文件
	 * @param filePath 文件地址
	 * @param key
	 * @param bucketName
	 */
	void upload(String filePath, String key, String bucketName, JSONObject result);
	
	/**
	 * 上传文件
	 * @param data 字节数组
	 * @param key
	 * @param bucketName
	 */
	JSONObject upload(byte[] data, String key, String bucketName);
	/**
	 * 获取文件路径前缀
	 */
	String getUrlPrefix();
}
