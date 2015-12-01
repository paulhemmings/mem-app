package com.razor.memories.controllers;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import com.razor.memories.wrappers.TemplateHelper;

@SuppressWarnings("serial")
public class HomeController extends Controller {

    public static final String ctrlName = "/home";

    public void index() throws IOException, ServletException
    {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("signedrequest",this.facebookSR.originalSignedRequest);
        TemplateHelper.callTemplate(configuration,response, ctrlName + "/index.ftl",root);
    }

}
