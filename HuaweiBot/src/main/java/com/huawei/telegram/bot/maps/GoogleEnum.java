package com.huawei.telegram.bot.maps;

/**
 * Enums for representation of Google Maps constants.
 * 
 * @author Lucas Lima Vieira.
 *
 */
public enum GoogleEnum {

	URL("https://maps.googleapis.com/maps/api/geocode/json?address="),
	KEY("&key=AIzaSyAM9oKREbslfnIDFDKG_okBEfUwcvS9NjQ");
	
	private final String value;
	
	GoogleEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
