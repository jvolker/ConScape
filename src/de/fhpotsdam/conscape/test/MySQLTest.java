/**
 * @author Florian Schulz
 */
package de.fhpotsdam.conscape.test;

import processing.core.PApplet;
import de.bezier.data.sql.*;

public class MySQLTest extends PApplet  
{
	private MySQL msql;

	public void setup () {
		size(100, 100);
		String host = "localhost";
		String user = "conscape";
		String pass = "qmjKynvKXrTF3qqZ";
	    String database = "conscape";
	    
	    msql = new MySQL(this, host, database, user, pass);
	    
	    if (msql.connect()) {
	        msql.query("SELECT * FROM events");
	        while (msql.next()) {
	            String s = msql.getString("lastfm_venue_id");
	            println(s);
	        }
	    }
	    else
	    {
	        println("Konnte keine Verbindung zur Datenbank herstellen!");
	    }
	}
}

