/**
 * @author Florian Schulz
 */
package de.fhpotsdam.conscape;

import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;

import codeanticode.glgraphics.GLConstants;
import processing.core.PApplet;
import processing.core.PFont;
import de.fhpotsdam.unfolding.Map;
import de.fhpotsdam.unfolding.events.EventDispatcher;
import de.fhpotsdam.unfolding.interactions.MouseHandler;
import de.fhpotsdam.unfolding.interactions.TuioCursorHandler;
import de.fhpotsdam.unfolding.mapdisplay.MapDisplayFactory;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.geo.Location;

public class Conscape extends PApplet 
{
	Map map;
	EventDispatcher eventDispatcher;
	
	TuioCursorHandler tuioCursorHandler;
	DataLoader dataLoader;
	Venue[] venues;
	
	PFont font;

	public void setup() 
	{
		size(800, 600, GLConstants.GLGRAPHICS);
		font = createFont("Courier", 32);
		textFont(font, 14); 
		map = new Map(this);
		// map = new Map(this, "Berlin-Mitte", 50, 100, 900, 550, true, false, new OpenStreetMap.CloudmadeProvider(MapDisplayFactory.OSM_API_KEY, 23058));
		map.setTweening(false);
		
		// Tuio-Events
		eventDispatcher = new EventDispatcher();
		tuioCursorHandler = new TuioCursorHandler(this, map);
		eventDispatcher.addBroadcaster(tuioCursorHandler);
		
		eventDispatcher.register(map, "pan");
		
		// Maus-Events
		eventDispatcher = MapUtils.createDefaultEventDispatcher(this, map);
		
		// DataLoader holt die Daten und liefert entsprechende Listen zurück
		dataLoader = new DataLoader();
		venues = dataLoader.getVenuesFromLastFM();
		map.zoomAndPanTo(new Location((float) 52.522, (float) 13.405), 20);
		
	}

	public void draw() 
	{
		background(0);
		
		map.draw();
		
		for (Venue v : venues) {
			System.out.println(venues.length);
			float lat = Float.valueOf(v.geo_lat);
			float lon = Float.valueOf(v.geo_long);
			Location location = new Location(lat, lon);
			float xy[] = map.mapDisplay.getScreenPositionFromLocation(location);
			
			noStroke();
			fill(0);
			ellipse(xy[0], xy[1], 5, 5);
			fill(50);
			stroke(255);
			strokeWeight(4);
			text(v.name, xy[0] + 10, xy[1] + 10);
		}
		
		// tuioCursorHandler.drawCursors();
	}
}
