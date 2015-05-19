package com.example.cognitive.personality;

import com.ibm.watson.developer_cloud.personality_insights.v2.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v2.model.Profile;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PIServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PersonalityInsights service = new PersonalityInsights();
		service.setUsernameAndPassword("2649831b-3b65-467c-9bd5-1300f0775cdc", "ESpPTxWTDi4t");
		String text = request.getParameter("statement");
		Profile profile = service.getProfile(text);
		PrintWriter out = response.getWriter();
		out.println(profile);
	}
}
