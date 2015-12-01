package com.razor.memories.controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.razor.memories.factories.FacebookProviderFactory;
import com.razor.memories.models.FacebookSignedRequest;
import com.razor.memories.providers.IFacebookProvider;
import com.razor.memories.wrappers.FBSR;
import com.razor.memories.wrappers.TemplateHelper;
import com.restfb.FacebookClient;

import freemarker.template.Configuration;

/**
 * http://shulgadim.blogspot.com/2012/01/simple-java-mvc-for-google-app-engine.html
 */

@SuppressWarnings("serial")
public class Controller extends HttpServlet {

    protected Configuration configuration;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected enum httpVerb { GET, POST };
    protected httpVerb requestVerb;
    protected IFacebookProvider facebookProvider;
    protected FacebookClient facebookClient;
    protected FacebookSignedRequest facebookSR = null;
    protected boolean runningLocally = false;

    /**
     * Configure the Freemarker template location and configuration object
     */

    public void init() {
        configuration = new Configuration();
        configuration.setServletContextForTemplateLoading(getServletContext(),"WEB-INF/views");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setNumberFormat("0.######");
    }

    /**
     * Process POST request
     */

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.request = req;
        this.response = resp;
        this.requestVerb = httpVerb.POST;
        this.runningLocally = request.getServerName().equalsIgnoreCase("localhost");
        this.facebookProvider = FacebookProviderFactory.buildProvider(this.runningLocally);

        this.routeRequest();
    }

    /**
     * Process GET request
     */

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.request = req;
        this.response = resp;
        this.requestVerb = httpVerb.GET;
        this.runningLocally = request.getServerName().equalsIgnoreCase("localhost");
        this.facebookProvider = FacebookProviderFactory.buildProvider(this.runningLocally);

        this.routeRequest();
    }

    /**
     * Route Controller/Action
     */

    private void routeRequest() throws IOException{

		// we need to authenticate every request
    	// ----------------------------------------------------------------

		try{
			this.facebookSR = this.facebookProvider.retrieveSignedRequest(request, response);
		}catch(Exception ex){
			FBSR.buildRedirectScript(response);
			return;
		}

		// extract authentication token from signed request
		this.facebookClient = this.facebookProvider.buildFacebookClient(this.facebookSR);
		if(this.facebookClient == null) {
			FBSR.buildRedirectScript(response);
			return;
		}

    	// route the request
		// ----------------------------------------------------------------

        System.out.println( "Controller: " + this.request.getServletPath());

        String action = null;
        String pathInfo = this.request.getPathInfo();

        if(pathInfo!=null){
            String[] pathInfoElements = pathInfo.split("/");
            if(pathInfoElements.length>1){
            	action = pathInfoElements[1];
            }
        }else {
        	action = "index";
        }

        if(action!=null){

            System.out.println( "Action: " + action);
            try {
                java.lang.reflect.Method method = this.getClass().getMethod(action);

                try {
                    method.invoke(this, new Object[] {});
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    response.getWriter().println("IllegalArgumentException:" + e.getMessage());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    response.getWriter().println("IllegalAccessException:" + e.getMessage());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    response.getWriter().println("InvocationTargetException:" + e.getCause().getMessage());
                }

            } catch (SecurityException e1) {
                e1.printStackTrace();
                response.getWriter().println("SecurityException:" + e1.getMessage());
            } catch (NoSuchMethodException e1) {
               response.getWriter().println("NoSuchMethodException: " + action);
            }
        }
    }

    /**
     * Display error page
     * @param message
     * @throws IOException
     * @throws ServletException
     */

    public void errorpage(String message) throws IOException, ServletException{
        Map<String,String> root = new HashMap<String,String>();
        root.put("message", message);
        TemplateHelper.callTemplate(configuration,response,"/errorpage.ftl",root);
    }

}
