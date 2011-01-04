/**
 * @author Florian Schulz
 */
package de.fhpotsdam.conscape;

import java.util.List;

public class Response {
	Object venues;
	
	class Venue {
		String website;
		String name;
		String url;
		String phonenumber;
		Object location;
		
		public Venue () {

		}
	}
}

