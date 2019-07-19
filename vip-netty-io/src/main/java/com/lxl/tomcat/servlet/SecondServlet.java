package com.lxl.tomcat.servlet;


import com.lxl.tomcat.http.GPRequest;
import com.lxl.tomcat.http.GPResponse;
import com.lxl.tomcat.http.GPServlet;

public class SecondServlet extends GPServlet {

	public void doGet(GPRequest request, GPResponse response) throws Exception {
		this.doPost(request, response);
	}

	public void doPost(GPRequest request, GPResponse response) throws Exception {
		response.write("This is Second Serlvet");
	}

}
