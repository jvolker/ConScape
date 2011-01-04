/**
 * @author Florian Schulz
 */
package de.fhpotsdam.conscape;

import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;

import codeanticode.glgraphics.GLConstants;
import processing.core.PApplet;
import de.fhpotsdam.unfolding.Map;
import de.fhpotsdam.unfolding.events.EventDispatcher;
import de.fhpotsdam.unfolding.interactions.MouseHandler;
import de.fhpotsdam.unfolding.interactions.TuioCursorHandler;
import de.fhpotsdam.unfolding.geo.Location;

public class Main extends PApplet 
{
	Map map;
	EventDispatcher eventDispatcher;
	
	TuioCursorHandler tuioCursorHandler;
	DataLoader dataLoader;
	Venue[] venues;

	public void setup() 
	{
		size(1000, 750, GLConstants.GLGRAPHICS);

		map = new Map(this, "map1", 50, 100, 900, 550);
		map.setTweening(true);
		
		// Tuio-Events
		eventDispatcher = new EventDispatcher();
		tuioCursorHandler = new TuioCursorHandler(this, map);
		eventDispatcher.addBroadcaster(tuioCursorHandler);

		// Maus-Events
		MouseHandler mouseHandler = new MouseHandler(this, map);
		eventDispatcher.addBroadcaster(mouseHandler);
		eventDispatcher.register(map, "pan");
		
		// DataLoader holt die Daten und liefert entsprechende Listen zurück
		dataLoader = new DataLoader();
		venues = dataLoader.getVenuesFromLastFM();
		map.zoomAndPanTo(new Location((float) 52.522, (float) 13.405), 20);
		
	}

	public void draw() 
	{
		background(0);
		
		map.draw();
		
//		for (Venue v : venues) {
//			System.out.println(venues.length);
//			float lat = Float.valueOf(v.geo_lat);
//			float lon = Float.valueOf(v.geo_long);
//			Location location = new Location(lat, lon);
//			float xy[] = map.mapDisplay.getScreenPositionFromLocation(location);
//			
//			noStroke();
//			fill(255, 0, 0);
//			ellipse(xy[0], xy[1], 10, 10);
//		}
		
		// tuioCursorHandler.drawCursors();
	}
}
