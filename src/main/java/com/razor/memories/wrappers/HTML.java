package com.razor.memories.wrappers;

import java.io.InputStream;

import javax.servlet.ServletContext;

public class HTML {

	public static String getContent(ServletContext context, String name){		
		String htmlContent = null;
		try{
			InputStream is = context.getResourceAsStream("/WEB-INF/views/" + name + ".html");		
			try {
		        htmlContent = new java.util.Scanner(is).useDelimiter("\\A").next();
		    } catch (java.util.NoSuchElementException e) {
		    }			    		
			is.close();
		}catch(Exception ex){			
		}
		return htmlContent;
	}
}
