package com.huawei.telegram.bot.maps;

/**
 * Enums for representation of JSON location constants.
 * 
 * @author Lucas Lima Vieira
 *
 */
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
