package com.razor.memories.wrappers;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class TemplateHelper {
	
    @SuppressWarnings("rawtypes")
    public static void callTemplate(Configuration cfg, HttpServletResponse resp, String template, Map root) throws IOException, ServletException
    {        
    	// Get the template object              
        Template t = cfg.getTemplate(template); 
        resp.setContentType("text/html; charset=utf-8");
        
        Writer writer = resp.getWriter();   
        callTemplate(cfg, writer, template, root);
        
        /*                       
        try { 
        	// Merge the data-model and the template
        	t.process(root, out); 
        } catch (TemplateException e) { 
        throw new ServletException( 
            "Error while processing FreeMarker template", e); 
        }
        */
    }
    
    @SuppressWarnings("rawtypes")
    public static String callTemplate(Configuration cfg, String template, Map root) throws IOException, ServletException
    {
        StringWriter stringWriter = new StringWriter();  
        callTemplate(cfg, stringWriter, template, root);
        return stringWriter.toString();
        
    }    
    
    @SuppressWarnings("rawtypes")
    public static void callTemplate(Configuration cfg, Writer writer, String template, Map root) throws IOException, ServletException
    {    	
    	// Get the template object              
        Template t = cfg.getTemplate(template);   
        
        try { 
        	// Merge the data-model and the template        	
            t.process(root, writer);  
        } catch (TemplateException e) { 
        throw new ServletException( 
            "Error while processing FreeMarker template", e); 
        }        
    }       
    
}