package com.huawei.telegram.bot.maps;

public enum LocationEnum {

	RESULTS("results"), 
	GEOMETRY("geometry"),
	LOCATION("location"), 
	LATITUDE("lat"), 
	LONGITUDE("lng");
	
	private final String value;
	
	LocationEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
