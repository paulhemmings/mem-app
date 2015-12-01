package com.razor.memories.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class RazorMemoriesServlet extends HttpServlet {

	/**
	 * Handle GET request
	 * @throws ServletException
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
	}

	/**
	 * Handle POST request
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	}

}
