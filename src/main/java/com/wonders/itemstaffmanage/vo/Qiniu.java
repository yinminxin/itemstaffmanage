package com.wonders.itemstaffmanage.vo;

public class Qiniu {

	private String accessKey;
	
	private String secretKey;
	
	private String bucketUrl;
	
	private String bucketName;
	
	private String privateBucketUrl;
	
	private String privateBucketName;
	
	private int expires;

	public Qiniu() {
		
	}
	
	public Qiniu(String accessKey, String secretKey, String bucketUrl,
                 String bucketName, String privateBucketUrl, String privateBucketName, int expires) {
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.bucketUrl = bucketUrl;
		this.bucketName = bucketName;
		this.privateBucketUrl = privateBucketUrl;
		this.privateBucketName = privateBucketName;
		this.expires = expires;
	}
	
	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getBucketUrl() {
		return bucketUrl;
	}

	public void setBucketUrl(String bucketUrl) {
		this.bucketUrl = bucketUrl;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	
	public String getPrivateBucketUrl() {
		return privateBucketUrl;
	}

	public void setPrivateBucketUrl(String privateBucketUrl) {
		this.privateBucketUrl = privateBucketUrl;
	}

	public String getPrivateBucketName() {
		return privateBucketName;
	}

	public void setPrivateBucketName(String privateBucketName) {
		this.privateBucketName = privateBucketName;
	}

	public int getExpires() {
		return expires;
	}

	public void setExpires(int expires) {
		this.expires = expires;
	}
	
}
