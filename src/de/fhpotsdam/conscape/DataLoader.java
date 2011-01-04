/**
 * @author Florian Schulz
 */
package de.fhpotsdam.conscape;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import de.fhpotsdam.conscape.util.StringUtil;

public class DataLoader 
{
	private String venuesFromLastFM = "../data/venues_lastfm.json";
	
	public DataLoader() 
	{
	
	}
	private Response load(String path) throws Exception
	{
		String source = StringUtil.readFileAsString(path);
		System.out.println(source);
        Response response = new Gson().fromJson(source, Response.class);
        
		return response;
	}
	public Venue[] getVenuesFromLastFM () 
	{
		Response response;
		Venue[] venues = null;
	
		try {
			response = load(venuesFromLastFM);
			venues = response.venues;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// auf response zugreifen
		return venues; 
	}
}

