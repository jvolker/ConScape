package de.fhpotsdam.conscape.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class StringUtil {
	public static String urlencode (String s)
	{
		try {
			s = URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}
	public static String readFileAsString(String filePath) throws java.io.IOException{
	    byte[] buffer = new byte[(int) new File(filePath).length()];
	    FileInputStream f = new FileInputStream(filePath);
	    f.read(buffer);
	    return new String(buffer);
	}
}
