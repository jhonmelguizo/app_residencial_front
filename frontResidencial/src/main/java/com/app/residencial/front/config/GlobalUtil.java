package com.app.residencial.front.config;

import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class GlobalUtil {
	
	public static HttpHeaders getheaders()
	{
		// create headers
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
				
				return headers;
	}

}
