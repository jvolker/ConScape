/**
 * @author Florian Schulz
 */
package de.fhpotsdam.conscape;

public class Response {
	Venue[] venues;
	
	class Venue {
		String website;
		String name;
		String url;
		String phonenumber;
		Object location;
	}
	
	public Response() {

	}
}

