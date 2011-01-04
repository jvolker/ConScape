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

public class Main extends PApplet 
{

	Map map;
	EventDispatcher eventDispatcher;
	
	TuioCursorHandler tuioCursorHandler;
	DataLoader dataLoader;

	public void setup() 
	{
		size(1000, 750, GLConstants.GLGRAPHICS);

		map = new Map(this, "map1", 50, 100, 900, 550);
		map.setTweening(false);
		
		eventDispatcher = new EventDispatcher();
		
		tuioCursorHandler = new TuioCursorHandler(this, map);
		eventDispatcher.addBroadcaster(tuioCursorHandler);

		MouseHandler mouseHandler = new MouseHandler(this, map);
		eventDispatcher.addBroadcaster(mouseHandler);

		eventDispatcher.register(map, "pan");
		
		dataLoader = new DataLoader();
		dataLoader.getVenuesFromLastFM();
		
	}

	public void draw() 
	{
		background(0);
		
		map.draw();
		
		tuioCursorHandler.drawCursors();
	}
}
