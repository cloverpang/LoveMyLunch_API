/***************************************************************************
 * This document contains confidential and proprietary information
 * subject to non-disclosure agreements with AsiaInspection. This
 * information shall not be distributed or copied without written
 * permission from the AsiaInspection.
 ***************************************************************************/
package com.lovemylunch.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class ServiceConfig {

	//获取application.properties的属性
	@Value("${base.url}")
    private String baseURL;

	@Value("${redis.host}")
    private String redisHost;

	@Value("${redis.port}")
    private String redisPort;

	@Value("${redis.password}")
    private String redisPassword;

	@Value("${resource.share.folder}")
    private String excleLoggoCommonSource;

	@Value("${max.multiplart.request.size}")
	int maxRequestSize;

	@Value("${local.ia.temp.folder}")
	private String localIATempDir;

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getRedisHost() {
		return redisHost;
	}

	public void setRedisHost(String redisHost) {
		this.redisHost = redisHost;
	}

	public String getRedisPort() {
		return redisPort;
	}

	public void setRedisPort(String redisPort) {
		this.redisPort = redisPort;
	}

	public String getRedisPassword() {
		return redisPassword;
	}

	public void setRedisPassword(String redisPassword) {
		this.redisPassword = redisPassword;
	}

	public String getExcleLoggoCommonSource() {
		return excleLoggoCommonSource;
	}

	public void setExcleLoggoCommonSource(String excleLoggoCommonSource) {
		this.excleLoggoCommonSource = excleLoggoCommonSource;
	}

	public int getMaxRequestSize() {
		return maxRequestSize;
	}

	public void setMaxRequestSize(int maxRequestSize) {
		this.maxRequestSize = maxRequestSize;
	}

	public String getLocalIATempDir() {
		return localIATempDir;
	}

	public void setLocalIATempDir(String localIATempDir) {
		this.localIATempDir = localIATempDir;
	}

	@Override
	public String toString() {
		return "ServiceConfig{" +
				"baseURL='" + baseURL + '\'' +
				", redisHost='" + redisHost + '\'' +
				", redisPort='" + redisPort + '\'' +
				", redisPassword='" + redisPassword + '\'' +
				", excleLoggoCommonSource='" + excleLoggoCommonSource + '\'' +
				", maxRequestSize=" + maxRequestSize +
				", localIATempDir='" + localIATempDir + '\'' +
				'}';
	}
}
