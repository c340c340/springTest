package com.trans;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/** 
 * 读取配置文件
 * @ClassName   类名：ReadConfig 
 * @Description 功能说明：
 * <p>
 *</p>
 ************************************************************************
 * @date        创建日期：2011-3-24
 * @author      创建人：李中俊
 * @version     版本号：V1.0
 *<p>
 ***************************修订记录*************************************
 * 
 *   2011-3-24   李中俊   创建该类功能。
 *
 ***********************************************************************
 *</p>
 */
public class ReadConfig {
	
	public static Map<String,String> getConfig(){
		Map<String,String> hm = new HashMap<String,String>();
		PathMatchingResourcePatternResolver resolover = new PathMatchingResourcePatternResolver();
		Resource r = resolover.getResource("base.properties");
		Properties pro = new Properties();
		try {
			pro.load(r.getInputStream());
			for(Object key :pro.keySet()){
				String keys = key.toString();
				hm.put(keys, pro.getProperty(keys));
			}
		}catch (Exception e) {
			
		}
		return hm;
	}
	/**
	 * <p>函数名称： getConfig</p>
	 * <p>功能说明：得到配置文件对象
	 *
	 * </p>
	 *<p>参数说明：</p>
	 * @param fileName 文件名忽略大小写 config/base.properties config/nPay.properties
	 * @return
	 *
	 * @date   创建时间：2016-9-18
	 * @author 作者：陈熙
	 */
	public static Map<String,String> getConfig(String fileName){
		Map<String,String> hm = new HashMap<String,String>();
		PathMatchingResourcePatternResolver resolover = new PathMatchingResourcePatternResolver();
		Resource r = resolover.getResource(fileName);
		Properties pro = new Properties();
		try {
			pro.load(r.getInputStream());
			for(Object key :pro.keySet()){
				String keys = key.toString();
				hm.put(keys, pro.getProperty(keys));
			}
		}catch (Exception e) {
			
		}
		return hm;
	}
	
	public static String getValue(String key){
		return getConfig().get(key);
	}
	
	//测试
	public static void main(String[] args) {
		Map map=ReadConfig.getConfig();
		System.out.println(map.get("appName"));
		//String a[][][]={{{"a","b"},{"a","b"}},{{"a","b"},{"a","b"}}};
		Map map2=ReadConfig.getConfig("CONFIG/NPAY.PROPERTIES");
		System.out.println(map2.get("ssmPosUser"));
	}
}
