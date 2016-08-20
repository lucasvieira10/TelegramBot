package com.huawei.telegram.bot.maps;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

/**
 * This class is the abstraction for the maps construction, it uses 
 * the Google Maps API and Jsoup for make the maps.
 * 
 * @author Lucas Lima Vieira.
 *
 */
public class Maps {

	private double latitude;
	private double longitude;
	private String location;
	
	/**
	 * Constructor of the Maps class.
	 * 
	 * @param location - is the string of localization.
	 */
	public Maps(String location) {
		this.latitude = 0;
		this.longitude = 0;
		this.location = location;
	}
	
	/**
	 * This method return the latitude of the localization 
	 * placed in the constructor.
	 * 
	 * @return latitude.
	 */
	public float getLatitude() {
		estimatedLatitude();
		return (float) this.latitude;
	}
	
	/**
	 * This method return the longitude of the localization
	 * placed int the constructor.
	 * 
	 * @return longitude.
	 */
	public float getLongitude() {
		estimatedLongitude();
		return (float) this.longitude;
	}
	
	/**
	 * This private method return the url with the Google Maps standards.
	 * 
	 * @return url.
	 */
	private String getURL() {
		String url = GoogleEnum.URL.getValue();
		url += location;
		
		String key = GoogleEnum.KEY.getValue();
		url += key;
		
		return url;
	}
	
	/**
	 * This private method return the json object that contains the locations.
	 * 
	 * @return json object with location.
	 */
	private JSONObject getJSONLocation() {
		String url = getURL();
		
		String jsonString = null;
		JSONObject jsonLocation = null;
		
		try {
			jsonString = Jsoup.connect(url).ignoreContentType(true).execute().body();
			JSONObject obj = new JSONObject(jsonString);
			
			JSONArray results = obj.getJSONArray(LocationEnum.RESULTS.getValue());
			JSONObject firstResult = results.getJSONObject(0);
			
			jsonLocation = firstResult.getJSONObject(LocationEnum.GEOMETRY.getValue())
					.getJSONObject(LocationEnum.LOCATION.getValue());
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return jsonLocation;
	}
	
	/**
	 * This private method esteem the latitude number. 
	 */
	private void estimatedLatitude() {
		JSONObject jsonLocation = getJSONLocation();
		this.latitude = jsonLocation.getDouble(LocationEnum.LATITUDE.getValue());
	}
	
	/**
	 * This private method esteem the longitude number. 
	 */
	private void estimatedLongitude() {
		JSONObject jsonLocation = getJSONLocation();
		this.longitude = jsonLocation.getDouble(LocationEnum.LONGITUDE.getValue());
	}
}
