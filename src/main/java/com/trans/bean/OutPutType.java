package com.trans.bean;
/**
 * @Description: TODO
 * @author :lushaorong
 */
public enum OutPutType {
	WordAndUK("wk"),
	WorkAndUS("ws"),
	UK("uk"),
	US("us");
	
	public String value = "";
	OutPutType(String value){
		this.value = value;
	}
}


