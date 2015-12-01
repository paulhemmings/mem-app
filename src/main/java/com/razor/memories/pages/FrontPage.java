package com.razor.memories.pages;

import java.io.PrintWriter;

import javax.servlet.ServletContext;

import com.razor.memories.wrappers.HTML;

public class FrontPage {

	public void appendHeader(ServletContext context, PrintWriter writer){
		String htmlContent = HTML.getContent(context, "frontPage_header");
		writer.print(htmlContent);			
	}
	
	public void appendContent(ServletContext context, PrintWriter writer){
		String htmlContent = HTML.getContent(context, "frontPage_content");
		writer.print(htmlContent);		
	}	
	
	public void appendFooter(ServletContext context, PrintWriter writer){
		String htmlContent = HTML.getContent(context, "frontPage_footer");
		writer.print(htmlContent);		
	}
}
